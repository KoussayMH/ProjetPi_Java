/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import static Presentation.EventController.myVariable;
import entities.Comment;
import entities.Event;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author kouss
 */
public class Single_EventController implements Initializable {
 
     EventServices eventserv ;
        Event e ; 
       // Comment c;
         private Date d1 ;
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
    @FXML
    private TextField comment_input;
    @FXML
    private Button button_Submit;
    @FXML
    private Button quitter;

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
    private void handleButtonSubmitAction(ActionEvent event) throws SQLException {
            

	Date localDate = Date.from(Instant.now());
        System.out.println(localDate);
        Comment c = new Comment(); 
       c.setDescription(comment_input.getText());
       // c.setDescription("aa");
       System.out.println(c.getDescription());

        c.setMax_caracs(50);
        c.setUser_id(1);
        c.setEvent_id(1);
        c.setDate(localDate);
        
        eventserv.AjouterComment(c); 
        System.out.println("le comment "+c.getDescription()+"a ete ajoute");    

       
            
    }

    @FXML
    private void quitter_action(ActionEvent event) {
         Stage stage = (Stage) quitter.getScene().getWindow();  
        stage.close();
    }
    
}
