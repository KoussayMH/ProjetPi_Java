/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import entities.Event;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author kouss
 */
public class Ajout_EventController implements Initializable {

    
    
        EventServices eventserv ;
        Event e ; 
    @FXML
    private Button button_Submit;
    @FXML
    private Button button_Exit;
    @FXML
    private Label adresseError;
    @FXML
    private Label numTelError;
    @FXML
    private TextField titre_input;
    @FXML
    private TextField desc_input;
    @FXML
    private TextField image_input;
    @FXML
    private DatePicker date_input;
    @FXML
    private TextField lieu_input;
    @FXML
    private TextField nbre_participant_input;
    @FXML
    private Label titre_error;
    @FXML
    private Label desc_error;
    @FXML
    private Label image_error;
    @FXML
    private Label lieu_error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         eventserv = new EventServices() ;
         e=new Event() ; 
         
     boolean verifTitre= true;

         
    }    

    @FXML
    private void handleButtonSubmitAction(ActionEvent event) throws SQLException {
        
      /*  boolean verifCapacite= true;
        boolean verifDate= true;*/
        
         e.setTitre(titre_input.getText());
         e.setImage1(image_input.getText());
         e.setDate_event(Date.valueOf(date_input.getValue()));
         e.setDescription(desc_input.getText());
         e.setEtat("en attente");
         e.setNote(0);
         e.setClub(0);
         e.setLieu(lieu_input.getText());
         e.setNb_participants(Integer.valueOf(nbre_participant_input.getText()));

        eventserv.AjouterEvent(e); 
        System.out.println("l'event "+e.getTitre()+"a ete ajoute");    

       
        
    }

    @FXML
    private void handleButtonExitAction(ActionEvent event) {
         Stage stage = (Stage) button_Exit.getScene().getWindow();  
        stage.close();
    }
         
    }
    

