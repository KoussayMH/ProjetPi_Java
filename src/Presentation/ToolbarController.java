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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.UserSession;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ToolbarController implements Initializable {
 
    @FXML
    private Button browse;

    @FXML
    private JFXTextField PathFile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setPathFile(String username) {
        this.PathFile.setText(username);
    }
 
  @FXML
    void gestionProduitsActions(ActionEvent event) {
   
    }

    @FXML
    void gestionRhActions(ActionEvent event) {

    }


    
}
