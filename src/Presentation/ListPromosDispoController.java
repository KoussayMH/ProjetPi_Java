/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.PromotionServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entities.Promotion;
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
public class ListPromosDispoController implements Initializable {

    @FXML
    private JFXListView<Promotion> listView;
    @FXML
    private JFXButton valider;

    private Number idTicket ; 
    
    
    public void setInfo (int id )
    {
        this.idTicket = id ; 
       
    }
    ObservableList<Promotion> list = PromotionServices.getInstance().displayAll(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      listView.setItems(list);

    }    

    @FXML
    private void validerAction(ActionEvent event) {
     System.out.println("SELECTED ITEM" + listView.getSelectionModel().getSelectedItems());
     Promotion p = new Promotion(); 
      
        if ("[]".equals(listView.getSelectionModel().getSelectedItems().toString()))
        {
            
                                    System.out.println("VEUILLEZ CHOISIR UN Ticket ");
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                  //  alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Veuillez choisir une promotion ! ");
                                    alert.show();
            
        }
        else {
             p= listView.getSelectionModel().getSelectedItem(); 
             PromotionServices.getInstance().affecterPromotion(idTicket.intValue(), p.getId(), p.getPourcentage());
        }
        
                
                
    }
    

}
