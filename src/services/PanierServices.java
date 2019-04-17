/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import connex.Connexion;
import entities.Commande;
import entities.Event;
import entities.Panier;
import entities.Ticket;
import entities.paiement;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author chaym
 */
public class PanierServices {
    
    
    
         Connection cn= Connexion.getInstance().getConnection() ; 
    Statement st ; 
    PreparedStatement pst ; 
      private static PanierServices instance;
   
    private ResultSet rs;
    
       public static PanierServices getInstance(){
        if(instance==null) 
            instance=new PanierServices();
        return instance;
    }
    
     public void Ajouter(Panier p)
    {
        String requ="INSERT INTO panier(ticket,user,qte,club,etat) VALUES('"+p.getTicket().getId()+"','"+p.getUser().getId()+"','"+p.getQte()+"',"
                + "'"+p.getClub().getId()+"','"+p.getEtat()+"')" ; 
        
         try { 
            st= cn.createStatement() ;
            st.executeUpdate(requ) ; 
                   System.out.println("ligne panier Ajouté "); 

        } catch (SQLException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
   
    
     public Ticket rech(int id) throws SQLException
     {Ticket T = new Ticket() ; 
         String requ="select * from ticket where id='"+id+"'" ; 
                   try{ st= cn.createStatement() ;
            ResultSet rss=st.executeQuery(requ);
            
            while(rss.next())
            {
                T.setId(rss.getInt(1) ); 
                //System.out.println(T.getId());
                    T.setType(rss.getString("type"));
                     T.setTitre(rss.getString("titre"));
                     //System.out.println(T.getTitre());
                    T.setPrix(rss.getInt("prix"));
                    int i= (rss.getInt("event")) ; 
                    T.setEvent(rechEvent(i));
            }
            }
                   
                   catch (SQLException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return T ; 
         
     }
     public Event rechEvent(int id)
     {
         Event T = new Event() ; 
         String requ="select * from event where id='"+id+"'" ; 
                   try{ st= cn.createStatement() ;
            ResultSet rss=st.executeQuery(requ);
            
            while(rss.next())
            {
                T.setId(rss.getInt(1) ); 
            
                    T.setTitre(rss.getString("titre"));
            }
            }
                   
                   catch (SQLException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return T ; 
         
     }
      public ObservableList<Panier> Afficher(User u) {
        String req="select * from panier where user='"+u.getId()+"' and etat like 'panier' ";
        ObservableList<Panier> list=FXCollections.observableArrayList();     
        try {
                st= cn.createStatement() ;
            rs=st.executeQuery(req);
            while(rs.next()){
         Panier P =new Panier();
                P.setId(rs.getInt(1));
                 P.setQte(rs.getInt("qte"));
                int id = rs.getInt("ticket") ;  
                P.setTicket(rech(id));
                P.setType(rech(id).getType());
                P.setEvent(rech(id).getEvent().getTitre());
                 P.setPrix(rech(id).getPrix());
                 System.out.println(P.getTicket().getId()) ;
               
                list.add(P);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
     
        public boolean update(Panier P) {
        String qry = "UPDATE panier SET qte= '"+P.getQte()+"' WHERE id = "+P.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                System.out.println("panier ************  modifié ");
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

     public void delete(Panier P) {
        String req="delete from panier where id="+P.getId();
     
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   public int quantite(Ticket P) throws SQLException
     { Ticket T = new Ticket() ; 
         String requ="select * from ticket where id='"+P.getId()+"'" ; 
                   try{ st= cn.createStatement() ;
            ResultSet rss=st.executeQuery(requ);
            
            while(rss.next())
            {
                T.setId(rss.getInt(1) ); 
                //System.out.println(T.getId());
                    T.setType(rss.getString("type"));
                    T.setPrix(rss.getInt("prix"));
                    T.setQte(rss.getInt("quantite"));
                    int i= (rss.getInt("event")) ; 
                    T.setEvent(rechEvent(i));
            }
            }
                   
                   catch (SQLException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return T.getQte() ; 
         
     }
   
   
    public boolean payer(String numeroCarte,int moisExp,int anneeExp,String cvc,int montant,String description) throws AuthenticationException, InvalidRequestException, ApiConnectionException, CardException, ApiException, StripeException
    {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> tokenParams = new HashMap<>();
        Map<String, Object> cardParams = new HashMap<>();
        paiement p = new paiement();
        Stripe.apiKey = p.getApiKey();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 11);
        cardParams.put("exp_year", 19);
        cardParams.put("cvc", "123");
        int nMontant= montant*100;
        tokenParams.put("card",cardParams);
        Token token =Token.create(tokenParams);
        if (token.getId()!=null){
        params.put("amount", nMontant);
        params.put("currency", "usd");
        params.put("description", description);
        params.put("source", token.getId());
        Charge charge = Charge.create(params);
        }
        else 
            return false;
        return true; 
    }
     
    
    public void Confirmer(User u) throws SQLException
    { String req="update  panier set etat='payé' where user='"+u.getId()+"' and etat like 'panier' ";
         st= cn.createStatement() ;
         st.executeUpdate(req);
    }
    
    
}
