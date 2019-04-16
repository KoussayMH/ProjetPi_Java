/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.UserSession;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AdminInterfaceController implements Initializable {

   @FXML
    private VBox groupCompany;

    @FXML
    private VBox groupCompany1;

    @FXML
    private VBox groupCompany2;

    @FXML
    private VBox groupCompany21;

    @FXML
    private VBox groupCompany11;

    @FXML
    private JFXButton ressource1;

    @FXML
    private JFXButton ressource;

    @FXML
    private JFXButton deconnexion;
@FXML
    private Label username;
    @FXML
    void ConsulterProduits(ActionEvent event) {
 try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClubInterface.fxml"));
               
                System.out.println(username.getText());
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/listeProduits.fxml"));
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

    @FXML
    void ConsulterRessources(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClubInterface.fxml"));
               
                System.out.println(username.getText());
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/ListeRH.fxml"));
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

    @FXML
    void deconnecterClient(ActionEvent event) {
        try {
            UserSession.getInstance().cleanUserSession();
            Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TeckEvents");
            Utils util = new Utils (); 
                util.setStageIcon(stage);
            stage.setScene(scene);
            
            stage.show();
            username.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ClubInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       username.setText("Espace "+UserSession.getInstance().getUsername() ); 
    }    
    
}
