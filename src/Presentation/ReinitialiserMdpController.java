/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UtilisateurServices;
import utils.SendMail;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ReinitialiserMdpController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton suivant;

    /**
     * Initializes the controller class.
     */
    
     boolean test; 
     Utils util = new Utils(); 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                test=false; 
            
     RequiredFieldValidator validator = new RequiredFieldValidator();
     validator.setMessage("Champs Vide");
          email.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                       if (email.validate() && (util.controleEmail(email.getText())))
                            {
                               
                                test = true ; 
                            }
                        if (!util.controleEmail(email.getText())) 
                            {
                                 System.out.println(email.getText());
                                 test= false ; 
                                  Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Email invalide");
                                    alert.show();
                                    email.setText("");
                                
                            }
                       else
                            {
                                System.out.println(email.getText());
                                test= false ; 
                            }
                    
                    
                    
                }
            }

        }
        );
        // TODO
    }    
    
    @FXML
    void suivantAction(ActionEvent event) {
                UtilisateurServices u = new UtilisateurServices();
        int idreset = u.checkUSername(email.getText());
        if (idreset != 0) {
            try {
                Utils util = new Utils ();
                ReinitialiserMdpNouveauController.id = idreset;
                String reset_token = u.resetPasswordFirst(idreset);
                ReinitialiserMdpConfController.code_reset = reset_token;
                SendMail.send(email.getText(), "Reinitialiser votre mot de passe", "Bonjour , \n  pour changer votre mot de passe : " + reset_token, "pidevmail25@gmail.com", "20104702esT.");
                util.sendSms("Voici votre code de confirmation " + reset_token);
                
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("reinitialiserMdpConf.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setTitle("TeckEvents");
                stage.setScene(scene);
                 
                util.setStageIcon(stage);
                stage.show();
                parent.getScene().getWindow().hide();
            } catch (IOException ex) {
                Logger.getLogger(ReinitialiserMdpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Information Alert");
            String s = "Username ou email introuvable !";
            alert.setContentText(s);
            alert.show();
        }
    }
    
    
}
