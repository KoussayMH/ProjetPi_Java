/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entites.Demande;
import entites.Evenement;
import java.io.IOException;
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
import javafx.stage.Stage;
import services.DemandeServices;
import services.RHServices;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EvenementsDispoController implements Initializable {

    @FXML
    private JFXListView<Evenement> listView;
      @FXML
    private JFXButton Quitter;

    @FXML
    void QuiterAction(ActionEvent event) {
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
    
     private Number id;
    RHServices rhService = new RHServices(); 
    DemandeServices event = new DemandeServices();
    ObservableList<Evenement> listEvent = FXCollections.observableArrayList(event.displayWithoutRSList());

    /**
     * Initializes the controller class.
     */
    
    public void setText (int id )
{
    this.id = id; 
     
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        listView.setItems(listEvent);
        
        listView.setOnMouseClicked((e)-> {
            
           Evenement event = new Evenement(); 
           DemandeServices demandeService = new DemandeServices(); 
           event = listView.getSelectionModel().getSelectedItem(); 
           if (demandeService.verifierExistance(new Demande(event.getId(), id.intValue(), event.getDated())))
           {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Déja Envoyée!");
                    alert.show();
           }
           else {
               if (rhService.dispoDateRs(id.intValue(), event.getDated()))
           {
               
               demandeService.ajouterDemande(event.getId(), id.intValue(), event.getDated());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre Demande est envoyée! ");
                    alert.show();
           }
           
           else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Ressource n'est pas disponible");
                    alert.show();
           }
           }
           
           
          
                   
            
            
            
        });
        
        
    }


    
}
