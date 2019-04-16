/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.PromotionServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entities.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malek Ben Jemaa
 */
public class EventsAvecPromosController implements Initializable {

    @FXML
    private JFXListView<Ticket> listView;
    @FXML
    private JFXButton Suivant;

    @FXML
    private JFXButton actualiser;
    
    ObservableList<Ticket> list = PromotionServices.getInstance().displayTicketAvecPromos();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listView.setItems(list);
    }    

    @FXML
    private void SuivantAction(ActionEvent event) {
        System.out.println("SELECTED ITEM" + listView.getSelectionModel().getSelectedItems());
        Ticket e = new Ticket (); 
        e=null; 
        if (listView.getSelectionModel().getSelectedItems().toString() == "[]")
        {
            
                                    System.out.println("VEUILLEZ CHOISIR UN Ticket ");
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                  //  alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Veuillez choisir un Ticket ! ");
                                    alert.show();
            
        }
        else 
        {
             e=listView.getSelectionModel().getSelectedItem(); 
            PromotionServices.getInstance().annulerPromotion(e.getId());
        
        }
        
        
        
        
    }
    
        @FXML
    void actualiserAction(ActionEvent event) {
            list = PromotionServices.getInstance().displayTicketAvecPromos();
            listView.setItems(list);
    }
    
}
