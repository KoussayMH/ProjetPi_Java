/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entities.paiement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.runtime.JSType;
import services.PanierServices;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class CheckoutController implements Initializable {
    
    
        @FXML
    private TextField card;
        
              @FXML
    private TextField code;
                   
              @FXML
    private ChoiceBox mois;   
              @FXML
    private ChoiceBox year;
              @FXML
    private Label total;
              
              
              
               private int tot ; 
              
                      ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12") ;
                      ObservableList<String> list2 = FXCollections.observableArrayList("17","18","19","20","21","22","23","24","25","26","27","28","29") ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       tot = JSType.toInt32(PanierController.tots) ; 
       total.setText(JSType.toString(tot));
       mois.setItems(list);
       year.setItems(list2);
       
       
       
        
        // TODO
    }  
    
     
 @FXML
    private void onvaliderclick(ActionEvent event) throws IOException, SQLException, InvalidRequestException, ApiConnectionException, CardException, ApiException, StripeException {
       
        String cart= card.getText() ; 
        String cvc= code.getText() ; 
        int m= JSType.toInt32(mois.getValue()); 
        int a= JSType.toInt32(year.getValue()); 
        
        
        if(cart.length()!=16)
        {
              Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Numéro invalide");
                alert.setHeaderText(null);
                alert.setContentText("Numéro carte invalide ");
                alert.showAndWait();
        }else
        {
            if(cvc.length()!=3)
            {
              Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Code invalide");
                alert.setHeaderText(null);
                alert.setContentText("Code érroné");
                alert.showAndWait();   
            }
            else
            {
                   if(a<19)
            {
              Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Année  invalide");
                alert.setHeaderText(null);
                alert.setContentText("Année invalide");
                alert.showAndWait();   
            }
                   else{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous valider votre panier ?");
                alert.showAndWait();
                   PanierServices spr = new PanierServices();
                spr.payer(cart,m, a,cvc,tot, "payment valide");

               }
                   }
                
                
            }
            
        }
      
        
     
    
}
