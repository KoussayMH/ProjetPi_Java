/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import services.UtilisateurServices;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ReinitialiserMdpNouveauController implements Initializable {

     @FXML
    private JFXPasswordField mdp;

    @FXML
    private JFXPasswordField retapezMdp;
    @FXML
    private JFXButton suivant;
    @FXML
    private AnchorPane parent;

    
    static int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void suivantAction(ActionEvent event) {
           String firstp = mdp.getText();
        String lastp = retapezMdp.getText();

        if (firstp.equals(lastp)) {
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, firstp.toCharArray());
            UtilisateurServices u = new UtilisateurServices();
            u.ResetPasswordLast(id, bcryptHashString);
            ButtonType okButton = new ButtonType("OK");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", okButton);
            alert.setTitle("Error");
            alert.setHeaderText("Information Alert");
            String s = "Votre Mot de passe a été changé !";
            alert.setContentText(s);
            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(res -> {
                if (res.equals(okButton)) {
                    parent.getScene().getWindow().hide();
                }
            });
        }
    }
    
}
