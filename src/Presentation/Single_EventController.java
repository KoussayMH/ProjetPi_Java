/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import static Presentation.EventController.myVariable;
import entities.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author kouss
 */
public class Single_EventController implements Initializable {
 
     EventServices eventserv ;
        Event e ; 
    @FXML
    private Button menu_button_1;
    @FXML
    private Label titre_input;
    @FXML
    private ImageView image1_input;
    @FXML
    private Label date_input;
    @FXML
    private Label note_input;
    @FXML
    private Label decription_input;
    @FXML
    private Label lieu_input;
    @FXML
    private Label nb_participants_input;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            e = new Event () ;
            e= EventController.myVariable;
            System.out.println("single event "+e.getTitre());   
            
            eventserv = new EventServices() ;
            titre_input.setText(e.getTitre());
            decription_input.setText(e.getDescription());
            lieu_input.setText(e.getLieu());
    }    

    @FXML
    private void ouvrir_admin(ActionEvent event) {
        
        
    }
    
}
