/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Presentation.EventController;
import connex.Connexion;
import entities.Comment;
import entities.Event;
import entities.Personne;
import java.io.InputStream;
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
    
    public int u =54;
    
           
    public void AjouterEvent(Event e) throws SQLException
    {
        
           
         String  req="INSERT INTO event (titre,image1,date,description,etat,note,lieu,nbparticipants) VALUES('"+e.getTitre()+"','"+e.getImage1()+"','"+e.getDate_event()+"','"+e.getDescription()+"','"+e.getEtat()+"',"
                   + "'"+e.getNote()+"','"+e.getLieu()+"','"+e.getNb_participants()+"')" ; 
                pst= cn.prepareStatement(req) ;
                pst.executeUpdate();

                  System.out.println("Event Ajouté "); 

    }
    public String donneesGalerie(int id) throws SQLException
    {
        String req="select * from event where id ="+id;
        ResultSet rs =st.executeQuery(req) ;
        Event e =new Event();
            rs=st.executeQuery(req);
            rs.next();
                e.setTitre(rs.getString("titre"));                
                e.setLieu(rs.getString("lieu"));
                e.setDescription(rs.getString("description"));
                e.setNb_participants(rs.getInt("nbparticipants"));
                e.setDate_event(rs.getDate("date"));

                
        return "Titre :" + e.getTitre()+ "`\n Description :"+ e.getDescription()+ "\n lieu :" + e.getLieu()+ "\n nbparticipants :"+ e.getNb_participants()+"\n Date :"+ e.getDate_event(); 
    }
    public void AjouterComment(Comment c ) throws SQLException 
    {  String  req="INSERT INTO comment (description,maxcaracs,Event_id,user_id)"
            + " VALUES('"+c.getDescription()+"','"+c.getMax_caracs()
            +"','"+c.getEvent_id()+"','"+c.getUser_id()+"' )" ; 
                pst= cn.prepareStatement(req) ;
                pst.executeUpdate();

                  System.out.println("comment Ajouté "); 

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
    public ObservableList<Comment> afficherCommentByid(int id_ev) throws SQLException 
    {
            System.out.println("eh bah oui dkhalt lel fonction");
            ObservableList<Comment> myList= FXCollections.observableArrayList() ;
            String req=" SELECT * from comment  where Event_id = '"+id_ev+"'";
            st= cn.createStatement() ;
            ResultSet rs =st.executeQuery(req) ;
            while(rs.next())
            {
                Comment c = new Comment() ;
                c.setId(rs.getInt(1)) ;
                c.setDescription(rs.getString("description"));
                c.setMax_caracs(rs.getInt("maxcaracs"));
                c.setDate(rs.getDate("date"));
                c.setEvent_id(rs.getInt("Event_id"));
                c.setUser_id(rs.getInt("user_id"));
               
                myList.add(c) ;
            }
            return myList ; 

    }
    public ObservableList<Comment> afficherAllComments() throws SQLException 
    {
            ObservableList<Comment> myList= FXCollections.observableArrayList() ;
            String req=" SELECT * from comment " ;
            st= cn.createStatement() ;
            ResultSet rs =st.executeQuery(req) ;
            while(rs.next())
            {
                Comment c = new Comment() ;
                c.setId(rs.getInt(1)) ;
                c.setDescription(rs.getString("description"));
                c.setMax_caracs(rs.getInt("maxcaracs"));
                c.setDate(rs.getDate("date"));
                c.setEvent_id(rs.getInt("Event_id"));
                c.setUser_id(rs.getInt("user_id"));
               
                myList.add(c) ;
            }
            return myList ; 

    }
   
    
    public ObservableList<Event> displayAll()
     {
 ObservableList<Event> myList= FXCollections.observableArrayList() ;

        try {
            String req="SELECT * from event";
            st= cn.createStatement() ;
            ResultSet rs =st.executeQuery(req) ;
            while(rs.next())
            {
                Event e = new Event() ;
                e.setId(rs.getInt(1)) ;
                e.setTitre(rs.getString("titre"));
                e.setDate_event(rs.getDate("date"));
                e.setDescription(rs.getString("description"));
                e.setEtat(rs.getString("etat"));
                e.setLieu(rs.getString("lieu"));
                e.setNb_participants(rs.getInt("nbparticipants"));
                
                myList.add(e) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }            return myList ; 


    }  
    public List<Event> displayAllList() throws SQLException {
        String req="select * from event ";
        List<Event> list=new ArrayList<>();
        ResultSet rs =st.executeQuery(req) ;
     
            rs=st.executeQuery(req);
            while(rs.next()){
                Event e = new Event() ;
                e.setId(rs.getInt(1)) ;
                e.setTitre(rs.getString("titre"));
                e.setDate_event(rs.getDate("date"));
                e.setDescription(rs.getString("description"));
                e.setEtat(rs.getString("etat"));
                e.setLieu(rs.getString("lieu"));
                e.setNb_participants(rs.getInt("nbparticipants"));
                
                
                list.add(e);
            }
            return list;
    }

    public ObservableList<String> listViewEvents() 
        { 
            ObservableList<String> list=FXCollections.observableArrayList();

        try {
            String donnees="";
            for (int i=0; i< this.displayAllList().size(); i++)
            {
                donnees = this.donneesGalerie(this.displayAllList().get(i).getId());
                list.add(donnees); 
                donnees="";
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }             return list;

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
    
    
    
    public void supprimerComment(Comment c){
     String req = "DELETE FROM comment WHERE id = ?";
        try {
            PreparedStatement st  = cn.prepareStatement(req);
            st.setInt(1, c.getId());        
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
     }
    
    public void modifier_Titre(Event e) throws SQLException{
            
                    System.out.println("titre event modifié "); 

            String req = "UPDATE event SET titre=? WHERE id= ?";
       
            PreparedStatement st  = cn.prepareStatement(req);
            st.setString(1, e.getTitre());
            st.setInt(2, e.getId());
            st.executeUpdate();

    }
     
    public InputStream displayImageByIndex( int index ) {
       
            int count = 0;
            InputStream is = null;
            String req="select * from event "; 
            int test = 0;
             try {
            ResultSet rs =st.executeQuery(req) ;
            while(rs.next()){
                
                if (count == index )
                {
                    System.out.println("INDEX TROUVE"+ count);
                    is= rs.getBinaryStream("image");
                    return is ; 
                }
                else {
                    count ++; //nehsbou les cases
                }}
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        } return is;

    }

    public void confirmerEvent(Event e){
            
            String req = "UPDATE event SET etat='confirme' WHERE id= ?";
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
      
    public void modifier_nb(Event e){
            
            String req = "UPDATE event SET nbparticipants=? WHERE id= ?";
        try {
            PreparedStatement st  = cn.prepareStatement(req);
            st.setInt(1, e.getNb_participants());
            st.setInt(2, e.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          System.out.println("nb participp event modifié "); 

    }
         
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
  
    public void AjouterComment(Event e) throws SQLException
    {
        
           
         String  req="INSERT INTO comment (date,description,etat,Event_id,user_id) "
                 + "VALUES('"+e.getTitre()+"','"+e.getImage1()+"','"+e.getDate_event()+"',"
                 + "'"+e.getDescription()+"','"+e.getEtat()+"',"
                   + "'"+e.getNote()+"','"+e.getLieu()+"','"+e.getNb_participants()+"')" ; 
            pst= cn.prepareStatement(req) ;
             pst.executeUpdate();

      
           System.out.println("comment Ajouté "); 

    }
   
      
}
