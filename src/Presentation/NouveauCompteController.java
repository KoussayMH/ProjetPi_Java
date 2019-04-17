/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Utils;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.RequiredFieldValidator;
import entites.Utilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import services.UtilisateurServices;
import utils.SendMail;
import utils.codeGen;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class NouveauCompteController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField nomUtilisateur;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXPasswordField mdp;
    @FXML
    private JFXPasswordField RetapMdp;
    @FXML
    private JFXComboBox<String> role;
    @FXML
    private JFXButton confirmer;
    @FXML
    private JFXButton quitter;
    
    
     boolean test1, test2, test3, test4; 
     Utils util = new Utils(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       role.setItems(FXCollections.observableArrayList("Client", "Club"));
           //   role.setItems(FXCollections.observableArrayList("Client", "Club" , "Admin"));

            test1=false;       
            test2=false; 
            test3=false; 
            test4=false; 
            
     RequiredFieldValidator validator = new RequiredFieldValidator();
     validator.setMessage("Champs Vide");
        nomUtilisateur.getValidators().add(validator);
        Email.getValidators().add(validator);
        mdp.getValidators().add(validator);
        RetapMdp.getValidators().add(validator);
       
       nomUtilisateur.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                     if (nomUtilisateur.validate() && (util.CaracSpec(nomUtilisateur.getText())))
                            {
                                test1 = true ; 
                            }
                       else if (!util.CaracSpec(nomUtilisateur.getText())) 
                            {
                                 test1= false ; 
                                 Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Pas de caratères spéciaux ou chiffre dans un nom");
                                    alert.show();
                                 nomUtilisateur.setText("");
                                
                            }
                       else
                            {
                                test1= false ; 
                            }
                    
                    
                    
                }
            }

         }
        );
       
      Email.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                       if (Email.validate() && (util.controleEmail(Email.getText())))
                            {
                               
                                test2 = true ; 
                            }
                        if (!util.controleEmail(Email.getText())) 
                            {
                                 System.out.println(Email.getText());
                                 test2= false ; 
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Email Invalide");
                                    alert.show();                                 
                                    Email.setText("");
                                
                            }
                       else
                            {
                                System.out.println(Email.getText());
                                test2= false ; 
                            }
                    
                    
                    
                }
            }

        }
        );
        mdp.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                    if (!mdp.validate())
                {
                    test3 = false ; 
                }
                    else 
                {
                    test3= true ; 
                }
                    
                    
                    
                }
            }

        }
        );
          RetapMdp.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                    if (!RetapMdp.validate())
                {
                    test4 = false ; 
                }
                    else 
                {
                    test4= true ; 
                }
                    
                    
                    
                }
            }

        }
        );
      
        
       
       
    }    

    @FXML
    private void confirmerAction(ActionEvent event) {
        
        String usernamef = nomUtilisateur.getText();
        String mailf = Email.getText();
        String passwordf = mdp.getText();
        String passwordcf = RetapMdp.getText();
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, passwordf.toCharArray());

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        
               if (passwordf.equals(passwordcf)) {
            try {
                codeGen code = new codeGen();
                String codeg = code.randomString(8);
                String roles = role.getValue();
                int rolereg = 10;
                int active = 0;
                if (roles.equals("Client")) {
                    rolereg = 0;
                } else if (roles.equals("Club")) {
                    rolereg = 3;
                    active = 0;
                }
                else if(roles.equals("Admin"))
                {
                    rolereg = 2;
                    active = 0;
                }
                SendMail.send(mailf, "Bienvenue à TeckEvent", "Bienvenue , \n Voici votre code de confirmation : " + codeg, "pidevmail25@gmail.com", "20104702esT.");
                Utilisateur user = new Utilisateur(active, rolereg, usernamef, mailf, bcryptHashString, codeg, "", date);
                UtilisateurServices add = new UtilisateurServices();
                add.registerC(user);
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                String s = "Succés !";
                a.setContentText(s);
                a.show();
                AnchorPane anchor1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
                parent.getChildren().setAll(anchor1);
            } catch (IOException ex) {
                Logger.getLogger(NouveauCompteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Information Alert");
            String s = "Mot de Passe non identique !";
            alert.setContentText(s);
            alert.show();
        }
        
        
    }

    @FXML
    private void QuitterAction(ActionEvent event) {
        
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                Utils util = new Utils (); 
                util.setStageIcon(stage);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeProduitsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
