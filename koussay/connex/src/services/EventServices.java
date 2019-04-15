/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connex.Connexion;
import entities.Event;
import entities.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

/**
 *
 * @author chaym
 */
public class EventServices {
    Connection cn= Connexion.getInstance().getConnection() ; 
    Statement st ; 
    PreparedStatement pst ; 
    
    
     public void AjouterEvent(Event e) throws SQLException
    {
        /*String req="INSERT INTO event (titre,image1,date_event,description,etat,note,lieu,nb_participants) VALUES(?,?,?,?,?,?,?,?)" ; 
        try { 
            
            pst.setString(1,e.getTitre());   
            pst.setString(2,e.getImage1()); 
            pst.setDate(3, (Date) e.getDate_event()); 
            pst.setString(4,e.getDescription());  
            pst.setString(5,e.getEtat()); 
            pst.setInt(6,e.getNote()); 
            pst.setString(7,e.getLieu()); 
            pst.setInt(8,e.getNb_participants()); 

            pst.executeUpdate(); */
           System.out.println("Event Ajouté "); 
           
         String  req="INSERT INTO event (titre,image1,date,description,etat,note,lieu,nbparticipants) VALUES('"+e.getTitre()+"','"+e.getImage1()+"','"+e.getDate_event()+"','"+e.getDescription()+"','"+e.getEtat()+"',"
                   + "'"+e.getNote()+"','"+e.getLieu()+"','"+e.getNb_participants()+"')" ; 
try{
pst= cn.prepareStatement(req) ;
 pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     
     
      public ObservableList<Event> afficherEvent() throws SQLException
    {
            ObservableList<Event> myList= FXCollections.observableArrayList() ;
            String req="SELECT * from event";
            st= cn.createStatement() ;
            ResultSet rs =st.executeQuery(req) ;
            while(rs.next())
            {
                Event e = new Event() ;
                e.setId(rs.getInt(1)) ;
                e.setTitre(rs.getString("titre"));
                e.setImage1(rs.getString("image1"));
                 e.setDate_event(rs.getDate("date"));
                e.setDescription(rs.getString("description"));
                e.setEtat(rs.getString("etat"));
                e.setLieu(rs.getString("lieu"));
                e.setNb_participants(rs.getInt("nbparticipants"));
                
                myList.add(e) ;
            }
         
          
return myList ; 

    }
     
     
      public void supprimerEvent(Event e){
     String req = "DELETE FROM event WHERE id = ?";
        try {
            PreparedStatement st  = cn.prepareStatement(req);
            st.setInt(1, e.getId());        
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
     }
    
     
      public void modifier_Titre(Event e){
            
            String req = "UPDATE event SET titre=? WHERE id= ?";
        try {
            PreparedStatement st  = cn.prepareStatement(req);
            st.setString(1, e.getTitre());
            st.setInt(2, e.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          System.out.println("titre event modifié "); 

    }
     
      public void confirmerEvent(Event e){
            
            String req = "UPDATE event SET etat=confirmé WHERE id= ?";
        try {
            PreparedStatement st  = cn.prepareStatement(req);
            st.setInt(1, e.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          System.out.println(" event confirme "); 
   }
   
      public void modifier_Descr(Event e){
            
            String req = "UPDATE event SET description=? WHERE id= ?";
        try {
            PreparedStatement st  = cn.prepareStatement(req);
            st.setString(1, e.getDescription());
            st.setInt(2, e.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          System.out.println("description event modifié "); 

    }
     
      public void modifier_Lieu(Event e){
            
            String req = "UPDATE event SET lieu=? WHERE id= ?";
        try {
            PreparedStatement st  = cn.prepareStatement(req);
            st.setString(1, e.getLieu());
            st.setInt(2, e.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          System.out.println("lieu event modifié "); 

    }
        /* public void modifierEvent(Event e, String colName) throws SQLException {
            
            String req = "UPDATE event SET "+ colName +" = ? WHERE id = ?";
            PreparedStatement st  = cn.prepareStatement(req);
            switch(colName){
                case "titre":
                    st.setString(1, e.getTitre());
                    break;
                case "description":
                    st.setString(1, e.getDescription());
                    break;
                case "lieu":
                    st.setString(1, e.getLieu());
                    break;
                case "date":
                    st.setDate(1, (Date) e.getDate_event());
                    break;
                st.setInt(2, e.getId());
                     st.executeUpdate();
       
    }
*/
 
      
     public ObservableSet<String> afficherListeDateEventSansRedondance(){
        Statement st;
        ResultSet rs;
        ObservableSet<String> myList = FXCollections.observableSet();
        
             
        try {
            String req = "SELECT date FROM event ";
            st = cn.createStatement();
            rs = st.executeQuery(req);
            while(rs.next()){                
                Date date_rs = rs.getDate("date");                             
                myList.add(date_rs.toString());
                
            } 
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        return myList;
        
    }

      
       //Afficher la liste des experts dispo par date (pour la localisation)
    public ObservableList<Event> afficherListeEvent_ParDate(Date d) throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Event> myList = FXCollections.observableArrayList();
        
             
            String req = "SELECT * from event where date=?";
            st = cn.prepareStatement(req); 
            st.setDate(1, d);
            rs = st.executeQuery();
            while(rs.next()){                
             
                 Event e = new Event() ;
                e.setId(rs.getInt(1)) ;

                e.setTitre(rs.getString("titre"));
                e.setImage1(rs.getString("image1"));
                 e.setDate_event(rs.getDate("date"));
                e.setDescription(rs.getString("description"));
                e.setEtat(rs.getString("etat"));
                e.setLieu(rs.getString("lieu"));
                e.setNb_participants(rs.getInt("nbparticipants"));
                
                myList.add(e) ;
                
            } 
     
        return myList;
       
    }
      
      
}
