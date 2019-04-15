/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author kouss
 */
public class Menu_EventController implements Initializable {

    @FXML
    private Button menu_button_2;
    @FXML
    private Button menu_button_1;
    private Parent fxml;
    @FXML
    private ScrollPane myInterface;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ouvrir_admin(ActionEvent event) {
       
 try {
            fxml = FXMLLoader.load(getClass().getResource("/Presentation/Event.fxml"));//YOUR FXML DOC
            myInterface.setContent(fxml);
        } catch (IOException ex) {
            Logger.getLogger(Menu_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     @FXML
    private void ouvrir_client(ActionEvent event) {
       
             try {
            fxml = FXMLLoader.load(getClass().getResource("/Presentation/Table_Events.fxml"));//YOUR FXML DOC
            myInterface.setContent(fxml);
        } catch (IOException ex) {
            Logger.getLogger(Menu_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
