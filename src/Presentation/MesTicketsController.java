/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import static Presentation.LoginController.u;
import entities.Club;

import entities.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.runtime.JSType;
import services.TicketServices;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class MesTicketsController implements Initializable {
    
    public static Ticket listc;
     public static String  type;
     public static String  titre;
     public static String  id;
       public static String  img;
    
  @FXML
    private VBox clubVBs;
   
    @FXML
    private TextField rechercheTF;

    @FXML
    private Button createBTN;
    @FXML
    private ListView<Ticket> mesClubsLV;
    public static String path="/Presentation/test.fxml" ; 
int i= 0 ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       try{      chercherClub("");
           
               
    }   catch (SQLException ex) {
            Logger.getLogger(MesTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MesTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void chercherClub(String filter) throws SQLException, IOException{
       clubVBs.getChildren().clear();
TicketServices cs = new TicketServices();
        HashSet<Ticket> clubs = new HashSet<>();
     // clubs.addAll(cs.mytickets(u));
      clubs.addAll(cs.display(u));
  HBox hbj;
         Iterator<Ticket> iterator = clubs.iterator(); 
       for(int i=0;i<clubs.size()/2+1;i++){
             hbj =new HBox(15);
        for (int j=0 ; j<3 ; j++) {
             if(iterator.hasNext()){
                 Ticket club= iterator.next() ; 
           id=JSType.toString(club.getId());
                 System.out.println(club.getType());
          type=club.getType(); 
            titre=club.getEvent().getTitre(); 
         URL x =getClass().getResource(path);
         x.getContent();
           hbj.getChildren().add(FXMLLoader.load(getClass().getResource(path))) ;
          
            }
             
        }
               
             
               clubVBs.getChildren().add(hbj);  }
    
    
    }    
            
           
            
        
        
        
    

   /* @FXML
    private void createAction(ActionEvent event) throws IOException {
        
        createBTN.getScene().setRoot(FXMLLoader.load(getClass().getResource(Routes.CreateClub)));
    }
    
    public void initMesClubs() throws SQLException{
        
        ClubService cs = new ClubService();
        User u = new User(2, "", "", true, "", "", "");
        List<Club> lc = cs.getAllClubsByAdmin(u.getId());
        mesClubsLV.getItems().addAll(lc);
        
        
    }*/
    
}