/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import entities.Event;
import entities.Panier;
import entities.Ticket;
import entities.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.runtime.JSType;
import services.PanierServices;
import services.TicketServices;

/**
 * FXML Controller class
 *
 * @author chaym
 */



public class TicketsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label titreg ;
     @FXML
    private Label titres ;
     @FXML
    private Label titrec ;
     
    @FXML
    private Label prixg ;
     @FXML
    private Label event ;
    @FXML
    private Label prixs ;
    @FXML
    private Label prixc ;
    
     @FXML
    private TextField qtg ;
        @FXML
    private TextField qts ;
           @FXML
    private TextField qtc ;
           
  
           
           @FXML
           private Button gButton ; 
           
           @FXML
           private Button sButton ;
           
           @FXML
           private Button ButtonC ; 
    
    
    
    
             private ArrayList<Ticket> listdata = new ArrayList<Ticket>(); 
              
        public  Ticket tg = new Ticket() ; 
        public  Ticket ts = new Ticket() ; 
        public Ticket tc = new Ticket() ; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Event e = new Event() ; 
        e= AffEventController.e ; 
        User u= new User() ; 
        u= LoginController.u ; 
        event.setText(e.getTitre());
       
        
         TicketServices tickService = TicketServices.getInstance();
          listdata = tickService.recherche(e)  ; 
          
          for(Ticket s:listdata){
           if(s.getType().equals("Gold")){
                   tg=s ; 
         titreg.setText(s.getTitre());
         prixg.setText(JSType.toString(s.getPrix())) ; 
      System.out.println(tg.getUser().getId());
      
      
         }    
                 
            
             if(s.getType().equals("Silver")){
                  ts=s ; 
         titres.setText(s.getTitre());
            prixs.setText(JSType.toString(s.getPrix())) ;}
               
            
         
              if(s.getType().equals("Bronze")){
                  tc=s ; 
                  
         titrec.setText(s.getTitre());
            prixc.setText(JSType.toString(s.getPrix())) ;
        }

          
          }
          
              if(titreg.getText().equals("Label"))
          {  qtg.setVisible(false);
              titreg.setText("Non Disponible");
           prixg.setVisible(false);
                 gButton.setVisible(false);
          }
                  if(titres.getText().equals("Label"))
          {
                qts.setVisible(false);
              titres.setText("Non Disponible");
           prixs.setVisible(false);
                 sButton.setVisible(false);
          }
          if(titrec.getText().equals("Label"))
          { 
                qtc.setVisible(false);
                //cButton.setVisible(false);
              titrec.setText("Non Disponible");
           prixc.setVisible(false);
           ButtonC.setVisible(false);
                
          }
          
             PanierServices panService= new PanierServices() ;
          Panier pank = new Panier() ; 
          System.out.println(pank.getEtat());
        pank.setTicket(tg); 
        pank.setQte(0);
       
          pank.setUser(u);
          pank.setClub(tg.getUser());
             // System.out.println(pan.getClub().getId());
           gButton.setOnAction((even) -> {
      
               //System.out.println(pang);
               int q;
            try { 
                q=panService.quantite(tg) ;
                System.out.print(q);
                 if(JSType.toInt32(qtg.getText())>=q)
                    {
                         Alert al = new Alert(Alert.AlertType.ERROR); 
                       al.setTitle("Quantité insuffisante");
                       al.setHeaderText("");
                        al.setContentText("Quantité non disponible");
                       al.showAndWait();
                    }
                 else
                 {
                            pank.setQte(JSType.toInt32(qtg.getText()));
                            panService.Ajouter(pank); 
                 }
            } catch (SQLException ex) {
                Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
         
        
            
            
        });
        Panier pans = new Panier() ; 
        pans.setTicket(ts); 
          pans.setUser(u);
          pans.setClub(ts.getUser());
           System.out.println(pans);
             // System.out.println(pan.getClub().getId());
           sButton.setOnAction((eve) -> {

     
     int q;
            try { 
                q=panService.quantite(ts) ;
                System.out.print(q);
                 if(JSType.toInt32(qts.getText())>=q)
                    {
                        Alert al = new Alert(Alert.AlertType.ERROR); 
                       al.setTitle("Quantité insuffisante");
                       al.setHeaderText("");
                        al.setContentText("Quantité non disponible");
                       al.showAndWait();
                          } 
                    
         
     else
                 { pans.setQte(JSType.toInt32(qts.getText()));
           

            panService.Ajouter(pans);  }
            }
            catch (SQLException ex) {
                Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
           
           Panier panc = new Panier() ; 
            
        panc.setTicket(tc); 
          panc.setUser(u);
              
          panc.setClub(tc.getUser());
           ButtonC.setOnAction((ev) -> {
               int q;
            try { 
                q=panService.quantite(tc) ;
                System.out.print(q);
                 if(JSType.toInt32(qtc.getText())>=q)
                    {
                       Alert al = new Alert(Alert.AlertType.ERROR); 
                       al.setTitle("Quantité insuffisante");
                       al.setHeaderText("Quantité insuffisante");
                       al.setContentText("Quantité non disponible");
                       al.showAndWait();
                    }
                 else{
                         panc.setQte(JSType.toInt32(qtc.getText()));
                            panService.Ajouter(panc);
                 }
            } catch (SQLException ex) {
                Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        
            
            
        });
        
        
        
    
}}
