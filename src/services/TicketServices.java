/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connex.Connexion;
import entities.Event;
import entities.Panier;
import entities.Personne;
import entities.Ticket;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.runtime.JSType;



/**
 *
 * @author chaym
 */
public class TicketServices {
     Connection cn= Connexion.getInstance().getConnection() ; 
    Statement st ; 
    PreparedStatement pst ; 
      
    private static TicketServices instance;
   
    private ResultSet rs;
    
       public static TicketServices getInstance(){
        if(instance==null) 
            instance=new TicketServices();
        return instance;
    }
    public void AjouterTicket(Ticket ticket)
    {  
        String requ="INSERT INTO ticket(prix,quantite,event,type,titre) VALUES('"+ticket.getPrix()+"','"+ticket.getQte()+"','"+ticket.getEvent().getId()+"',"
                + "'"+ticket.getType()+"','"+ticket.getTitre()+"')" ; 
        
         try { 
            st= cn.createStatement() ;
            st.executeUpdate(requ) ; 
                   System.out.println("Personne Ajoutée "); 

        } catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
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
   
     public ObservableList<Ticket> displayAll(User u) {
        String req="select * from ticket ";
        ObservableList<Ticket> list=FXCollections.observableArrayList();     
        try {
                st= cn.createStatement() ;
            rs=st.executeQuery(req);
            while(rs.next()){
 Ticket T =new Ticket();
                T.setId(rs.getInt(1));
                T.setPrix(rs.getFloat(2));  
                T.setTitre(rs.getString("titre"));
               int id= rs.getInt("event") ; 
               T.setEvent(rechEvent(id));
                T.setQte(rs.getInt(3));
                list.add(T);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
     
         public ArrayList<Ticket> display(User u) {
        String req="select * from ticket where user='"+u.getId()+"'";
        ArrayList<Ticket> list= new ArrayList();     
        
        
        
        try {
                st= cn.createStatement() ;
            rs=st.executeQuery(req);
            while(rs.next()){
                Ticket T =new Ticket();
                T.setId(rs.getInt(1));
                T.setPrix(rs.getFloat(2));  
                T.setTitre(rs.getString("titre"));
                 T.setType(rs.getString("type"));
                int id= rs.getInt("event") ; 
               T.setEvent(rechEvent(id));
               
                System.out.println(T.getTitre());
                
                T.setQte(rs.getInt(3));
                list.add(T);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
         
         
          public ArrayList<Ticket> recherche(Event e) {
        String req="select * from ticket where event='"+e.getId()+"'";
        ArrayList<Ticket> list= new ArrayList();     
        try {
                st= cn.createStatement() ;
            rs=st.executeQuery(req);
            while(rs.next()){
        Ticket T =new Ticket();
        T.setId(rs.getInt(1));
        T.setPrix(rs.getFloat(2));  
        T.setTitre(rs.getString("titre"));
          T.setType(rs.getString("type"));
           T.setEvent(e) ; 
               //System.out.println(T.getTitre());
                T.setQte(rs.getInt(3));
          //int idu = rs.getInt(4) ; 
          User u = new User(1) ; 
          T.setUser(u);
          list.add(T);
            }} catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
     
      public boolean update(Ticket T) {
        String qry = "UPDATE ticket SET prix = '"+T.getPrix()+"', quantite = '"+T.getQte()+"' , titre = '"+T.getTitre()+"' WHERE id = "+T.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                System.out.println("ticket ************  modifié ");
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

     
     
     
     public void delete(Ticket T) {
        String req="delete from ticket where id="+T.getId();
     
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
      public ArrayList<Ticket> mytickets(User u) {
        String req="select * from panier where etat like 'payé'  ";
        ArrayList<Ticket> list= new ArrayList();     
        PanierServices ps = PanierServices.getInstance() ; 
        try {
                st= cn.createStatement() ;
            rs=st.executeQuery(req);
            while(rs.next()){
               Panier  T =new Panier();
               Ticket tc = new Ticket(); 
                T.setId(rs.getInt(1));
                T.setQte(rs.getInt(4));  
                tc = ps.rech(rs.getInt(2));
                System.out.println(tc.getId());
                list.add(tc);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
    
}
