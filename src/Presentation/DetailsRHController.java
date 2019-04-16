/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.DemandeServices;
import utils.UserSession;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DetailsRHController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label nom;
    @FXML
    private Label company;
    @FXML
    private Label domaine;
    @FXML
    private Label prenom;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    @FXML
    private Label link;
    @FXML
    private JFXListView<String> listView;
    @FXML
    private JFXButton reserver;
    @FXML
    private JFXButton quitter;
    @FXML
    private Label description;
    @FXML
    private Label username;
    private Number id; 
    Image image; 
    
    @FXML
    private JFXButton actualiser;
     @FXML
    void ActualiserAction(ActionEvent event) {
        listView.setVisible(true);
        ObservableList<String> list = FXCollections.observableArrayList(eventServices.listViewEvent(id.intValue()));  
        System.out.println(list);
        listView.setItems(list);

    }
    
      
    DemandeServices eventServices= new DemandeServices(); 
    public void setText(int id , String nom,String company,String domaine,String prenom,String adresse,String email,String link,String description , InputStream is)
    {
        this.id = id; 
            this.nom.setText(nom);
            this.company.setText(company);
            this.domaine.setText(domaine);
            this.prenom.setText(prenom);
            this.adresse.setText(adresse);
            this.email.setText(email);
            this.link.setText(link);
            this.description.setText(description);
            
            image = new Image("file:photo.jpg", 100, 150, true, true); 
            System.out.println("KAAAED NHAWEL");
            imageView.setImage(image);
            imageView.setLayoutX(81);            
            imageView.setLayoutY(84);
            imageView.setFitHeight(476);
            imageView.setFitWidth(349);
             list = FXCollections.observableArrayList(eventServices.listViewEvent(id)); 
            
 

    }
    
       
    
     class Cell extends ListCell<String> 
            {
                HBox hbox = new HBox(); 
                
                Label label = new Label (""); 
                Pane pane = new Pane(); 
                
          public Cell( )
          {
              
            
             hbox.getChildren().addAll(  label, pane); 
        
           
   
           }
                public void updateItem(String name, boolean empty)
                {
                    super.updateItem(name, empty);
                    setText(null);
                    setGraphic(null);
                    if (name !=null && !empty)
                    {
                        label.setText(name);
                        setGraphic(hbox);
                    }
                }
                
                
            }

    /**
    
    
    /**
     * Initializes the controller class.
     */
      ObservableList<String> list; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(UserSession.getInstance().getUsername() + " |CLUB");
       /* ObservableList<String> list = FXCollections.observableArrayList(eventServices.listViewEvent(7));  
        System.out.println(list);
        listView.setItems(list);*/
       listView.setVisible(false);
         // listView.setCellFactory(param-> new Cell());
    }    

    @FXML
    private void ajouterReservation(ActionEvent event) {
        if (eventServices.displayWithoutRSList().size()!=0)
        {
                FXMLLoader Loader = new FXMLLoader ();
                Loader.setLocation(DetailsRHController.this.getClass().getResource("/views/EvenementsDispo.fxml"));
            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(DetailsRHController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                EvenementsDispoController controller = Loader.getController();
                controller.setText(id.intValue());
                
                try {
                Parent page1 =  Loader.getRoot();
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                Utils util = new Utils (); 
                util.setStageIcon(stage);
                stage.show();
                } catch (Exception ex) {
                    Logger.getLogger(ListeRHController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Evenement Disponible");
            alert.show();
        }
        
        
    }

    @FXML
    private void QuitterAction(ActionEvent event) {
        
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/ListViewRH.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                Utils util = new Utils (); 
                util.setStageIcon(stage);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeProduitsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }
    
 
}
