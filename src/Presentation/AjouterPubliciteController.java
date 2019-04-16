/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.PubliciteServices;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Publicite;
import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JLabel;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Malek Ben Jemaa
 */
public class AjouterPubliciteController implements Initializable {

    @FXML
    private AnchorPane parent;

    @FXML
    private Button ajouter;

    @FXML
    private JFXTextField ParcourirText;

    @FXML
    private Button Parcourir;

    @FXML
    private Button Quitter;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXTextField priorite;

    @FXML
    private JFXTextField date;

    @FXML
    private JFXTextField duree;
    @FXML
    private JFXComboBox<String> comboBox;
    
     private JLabel labelImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboBox.setItems(PubliciteServices.getInstance().displayAllEvent());
    }    

    @FXML
    private void AjouterPublicite(ActionEvent event) throws FileNotFoundException {
        
       // System.out.println(description.getText()+  date.getText()+  duree.getText()+  ParcourirText.getText() +Integer.parseInt(priorite.getText()));
        Publicite p = new Publicite( description.getText(),  date.getText(),  duree.getText(), 
                ParcourirText.getText() ,Integer.parseInt(priorite.getText()), comboBox.getValue()); 
        System.out.println("LE NOM DE LEVENEMENT"+ comboBox.getValue());
        PubliciteServices pdao = PubliciteServices.getInstance();
            pdao.insert(p);
            pdao.envoyerMailUtilisateur(comboBox.getValue());
       
        description.setText("");
        date.setText("");
        duree.setText("");
        priorite.setText("");
        
        Image img = new Image("/SmaalTick.png");
       Notifications notificationBuilder = Notifications.create().title("Ajout avec success").text(" Publicité Ajoutée! ")
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
    
    public void setPathFile(String username) {
        this.ParcourirText.setText(username);
    }
    
    @FXML 
    private void parcourirAction (ActionEvent event) {
    FileChooser fc = new FileChooser() ; 
        File selectedFile = fc.showOpenDialog(null) ; 
        
        if ( selectedFile != null )
        {setPathFile( selectedFile.getAbsolutePath());
        
        }
        else System.out.println("file is not valid ");
    }  
    
    
    
    
    @FXML
    void QuitterPublicité(ActionEvent event) {
try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/ListePublicite.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterPubliciteController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
