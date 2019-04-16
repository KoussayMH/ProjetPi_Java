/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.PromotionServices;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.Promotion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Malek Ben Jemaa
 */
public class AjouterPromotionController implements Initializable {
   @FXML
    private AnchorPane parent;

    @FXML
    private GridPane gridpane;

    @FXML
    private JFXTextField pourcentage;

    @FXML
    private JFXTextField duree;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXDatePicker date_deb;

    @FXML
    private JFXDatePicker date_fin;

    @FXML
    private Button ajouter;

    @FXML
    private Button quitter;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
           @FXML
    void QuitterAction(ActionEvent event) {
try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/ListePromotions.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterPubliciteController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    private void Ajouterpromotion(ActionEvent event) throws FileNotFoundException {
        
        System.out.println(pourcentage.getText());
         Promotion p = new Promotion(   description.getText() ,Integer.parseInt(duree.getText()),  date_deb.getValue(),  date_fin.getValue(),Integer.parseInt(pourcentage.getText())); 
        
         PromotionServices pdao = PromotionServices.getInstance();
         pdao.insert(p);
            
   
       duree.setText("");
        description.setText("");
        pourcentage.setText("");
        
        Image img = new Image("/SmaalTick.png");
       Notifications notificationBuilder = Notifications.create().title("Ajout avec success").text(" Promotion Ajoutée! ")
               .graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
               .position(Pos.TOP_LEFT)
               .onAction(new EventHandler<ActionEvent>() { 
           
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
               });
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        
        
        
    }
    
    
    
    
}
