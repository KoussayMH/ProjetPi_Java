/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Event;
import entities.Promotion;
import entities.Publicite;
import entities.Ticket;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import utils.Connexion;

/**
 *
 * @author Malek Ben Jemaa
 */
public class PromotionServices {
    
    private static PromotionServices instance;
    private Statement st;
    private ResultSet rs;
    
     public PromotionServices() {
        Connexion cs=Connexion.getInstance();
        try {
            st=cs.getConnexion().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static PromotionServices getInstance(){
        if(instance==null) 
            instance=new PromotionServices();
        return instance;
    }
    
    
   public void insert(Promotion o)
    {
                   Connexion cs=Connexion.getInstance();

            String req = "insert into promotion(Description, duree ,date_deb , date_fin ,pourcentage)"
            + "values(" + o.getDescrpiton() + "," + o.getDuree()+ ",'" + o.getDate_debut() 
                + "','" + o.getDate_fin() + "','" + o.getPourcentage()+ "')";
        try {
            st.executeUpdate(req);
            System.out.println("ajoutee");
            } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        
        
        
    }  
   
   
       public List<Promotion> displayAllList() {
        String req="select * from promotion";
        List<Promotion> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Promotion p=new Promotion();
                p.setId(rs.getInt(1));
                p.setDescrpiton(rs.getString("description"));
               // p.setDate(rs.getString("date"));
               // p.setDuree(rs.getString("duree"));
               
                p.setPourcentage(rs.getInt(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Promotion displayById(int id) {
           String req="select * from promotion where id ="+id;
           Promotion p=new Promotion();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setDescrpiton(rs.getString("description"));
                p.setDuree(rs.getInt("duree"));
                p.setDate_debut(rs.getDate("date_deb").toLocalDate());
                p.setDate_fin(rs.getDate("date_fin").toLocalDate());
           
                p.setPourcentage(rs.getInt("pourcentage"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
    
    
     public ObservableList<Promotion> displayAll() {
        String req= " select * from promotion ";
        ObservableList<Promotion> list= FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Promotion p=new Promotion();
                p.setId(rs.getInt(1));
                
                p.setDuree(rs.getInt("duree"));
                p.setDate_debut(rs.getDate("date_deb").toLocalDate());
                p.setDate_fin(rs.getDate("date_fin").toLocalDate());
                p.setDescrpiton(rs.getString("description"));
                p.setPourcentage(rs.getInt("pourcentage"));
                list.add(p);
                System.out.println("Services.PromotionServices.displayAll()");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


     
    public void delete(Promotion o) {
        String req="delete from promotion where id="+o.getId();
        Promotion p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    public boolean update(Promotion p) {
        String qry = "UPDATE promotion SET Description = '"+p.getDescrpiton()+"', duree = '"+p.getDuree()+"' , date_deb = '"+p.getDate_debut()+"', date_fin = '"+p.getDate_fin()+"', pourcentage = '"+p.getPourcentage()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
   
    //*****************PROMOTION ***********************//
public ObservableList<Ticket> displayTicketSansPromos() {
        String req= " select * from ticket where idpromo is null ";
        ObservableList<Ticket> list= FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Ticket p=new Ticket();
                p.setId(rs.getInt(1));
                
                p.setPrix(rs.getFloat(3));
               
                p.setType(rs.getString("type"));
                System.out.println("TIIICKETTTT ");
                list.add(p);
               // System.out.println("events");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public ObservableList<Ticket> displayTicketAvecPromos() {
        String req= " SELECT * FROM `ticket` WHERE idPromo is NOT null  ";
        ObservableList<Ticket> list= FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Ticket p=new Ticket();
                p.setId(rs.getInt(1));
                
                p.setPrix(rs.getFloat(3));
               
                p.setType(rs.getString("type"));
                System.out.println("TIIICKETTTT ");
                list.add(p);
               // System.out.println("events");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public void affecterPromotion (int idTicket, int idPromo , int pourcentage )
 {  //recuperer 
         Ticket ticket=new Ticket();
         float prix ; 
         String req1 = "select * from ticket where id = '"+idTicket+"'  "; 
         try {
            rs=st.executeQuery(req1);
            rs.next(); 
                
                ticket.setId(rs.getInt(1));
                ticket.setPrix(rs.getFloat(3));
                ticket.setType(rs.getString("type"));
   
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         //inserer
         String req2 = "insert into recuperation (idTicket, prix) values ('" + ticket.getId() + "','" + ticket.getPrix()+ "') "; 
         try {
            st.executeUpdate(req2);
            System.out.println("ajoutee a recuperation ");
            } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         //modifier 
         String req3 = "update  ticket set  idPromo = '"+idPromo+"'  where id = " + idTicket ; 
         
          try {
              st.executeUpdate(req3);
           
              System.out.println("Ticket modifie");
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          prix =  ticket.getPrix() - (ticket.getPrix() *( (float) pourcentage/100)); 
          System.out.println("VALEUR PROMO "+ pourcentage);
          System.out.println("ANCIEN PRIX " + ticket.getPrix());
          System.out.println("NOUVEAU" + prix);
          String req4 = "update  ticket set  prix = '"+prix+"'  where id = " + idTicket ; 
         System.out.println("le prix" + prix);
          try {
              st.executeUpdate(req4);
           
              System.out.println("prix modifie");
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
                                    System.out.println("");
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                  //  alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Promotion affectée avec sucès");
                                    alert.show();
            
         
         
     }
 
public void annulerPromotion (int idTicket)
{
    float prix =0; 
    String req1 = "select * from recuperation where idticket = '"+idTicket+"'"; 
    try {
            rs=st.executeQuery(req1);
            while(rs.next()){
                prix =rs.getFloat("prix");
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String req2 = "update Ticket set prix = '"+prix+"' where id ='"+idTicket+"' "; 
    try {
              st.executeUpdate(req2);
           
              System.out.println("Ticket modifie");
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String req4 = "update Ticket set idpromo = null where id  ='"+idTicket+"' "; 
    try {
              st.executeUpdate(req4);
           
              System.out.println("Ticket modifie");
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String req3="delete from recuperation where idTicket="+idTicket;
        
        
          
              try {
           
            st.executeUpdate(req3);
             
        } catch (SQLException ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
