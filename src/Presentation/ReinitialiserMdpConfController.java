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
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ReinitialiserMdpConfController implements Initializable {

    static String code_reset;
    
    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField codeConfirmation;
    
    
    
    
      @FXML
    private JFXButton Suivant;

    @FXML
    void SuivantAction(ActionEvent event) {
   
          if(codeConfirmation.getText().equals(code_reset))
        {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("reinitialiserMdpNouveau.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setTitle("TeckEvent");
                stage.setScene(scene);
                Utils util = new Utils (); 
                util.setStageIcon(stage);
                stage.show();
                parent.getScene().getWindow().hide();
            } catch (IOException ex) {
                Logger.getLogger(ReinitialiserMdpConfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Information Alert");
            String s = "Code Incorrecte !";
            alert.setContentText(s);
            alert.show();
        }
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
     
        
        // TODO
    }    
    
}
