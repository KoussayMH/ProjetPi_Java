/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import entities.Event;
import entities.Personne;
import entities.Ticket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.runtime.JSType;
import services.PersonneServices;
import services.TicketServices;
import Presentation.AffEventController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class AjouterTicketController implements Initializable {
 @FXML
    private TextField titre ;
    
    @FXML
    private TextField qte ;
    @FXML
    private TextField prix;
     @FXML
    private Button ajouter ;
      @FXML
    private Button Back ;
         ObservableList data;

        TicketServices tickService ;
        Ticket ticket ; 
        
             @FXML
    private ComboBox type ;
                      ObservableList<String> list = FXCollections.observableArrayList("Gold","Silver","Bronze") ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 type.setItems(list);

        tickService= new TicketServices() ; 
        ticket= new Ticket() ; 
        //int id ; 
        Event ev = new Event() ; 
        ev= AffEventController.e ; 
        ticket.setEvent(ev);
        ajouter.setOnAction((event) -> {
        ticket.setTitre(titre.getText());
        ticket.setType(type.getValue().toString());
        ticket.setQte(JSType.toInt32(qte.getText()));
        ticket.setPrix(JSType.toInt32(prix.getText()));
                    

     System.out.println(ticket);

            tickService.AjouterTicket(ticket);         
            titre.setText("") ; 
            //type.setText(""); 
            prix.setText(""); 
            qte.setText(""); 
            
            
        });
        
         Back.setOnAction(event->{
            BackFN(event);
                       
        });
             
    }    
    
    private void BackFN(ActionEvent event)  {
                
        try {
            Pagination p = new Pagination("/Presentation/AfficherTickets.fxml");
        } catch (IOException ex) {

        }
    }
    
}
