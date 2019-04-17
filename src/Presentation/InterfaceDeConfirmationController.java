/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import utils.UserSession;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class InterfaceDeConfirmationController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField CleConfirmation;
    @FXML
    private JFXButton Confirmer;

    static String token;
    static int id;
    static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        InterfaceDeConfirmationController.username = username;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        InterfaceDeConfirmationController.id = id;
    }

    public static String getToken() {
        return token;
    }
     public static void setToken(String token) {
        InterfaceDeConfirmationController.token = token;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmationAction(ActionEvent event) {

        if (CleConfirmation.getText().equals(token)) {
            if (UserSession.getInstance().getRole() == 0) {
                try {

                    UtilisateurServices u = new UtilisateurServices();
                    u.confirmAccount(id);
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));
                    Parent root = loader.load();
                    ClientInterfaceController tex = loader.getController();
                    tex.setUsername(username.substring(0, 1).toUpperCase() + username.substring(1) + " | Client");
                    Scene scene = new Scene(root);
                    stage.setTitle("TeckEvent");
                    stage.setScene(scene);
                    Utils util = new Utils (); 
                    util.setStageIcon(stage);
                    stage.show();
                    parent.getScene().getWindow().hide();
                    LoginController.mainLoginScene.getWindow().hide();
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceDeConfirmationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (UserSession.getInstance().getRole() == 3) 
            {
                try {

                UtilisateurServices u = new UtilisateurServices();
                u.confirmAccount(id);
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClubInterface.fxml"));
                Parent root = loader.load();
              //  ClubInterfaceController tex = loader.getController();
              //  tex.setUsername(username.substring(0, 1).toUpperCase() + username.substring(1) + " | Club");
                Scene scene = new Scene(root);
                stage.setTitle("Bienvenue");
                stage.setScene(scene);
                stage.show();
                parent.getScene().getWindow().hide();
                LoginController.mainLoginScene.getWindow().hide();
                //System.out.println(logint.mainLoginScene);
            } catch (IOException ex) {
                Logger.getLogger(InterfaceDeConfirmationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            

        }
        else
            {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            //alert.setHeaderText("Information Alert");
            String s = "Code de Confirmation Incorrecte !";
            alert.setContentText(s);
            alert.show();
            }
    }
    
}
