/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entites.Demande;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.DemandeServices;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DemandesController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXListView<Demande> listView;
     
    DemandeServices demandeServices= new DemandeServices(); 
    
    ObservableList<Demande> listDemande = FXCollections.observableArrayList(demandeServices.displayAll());
    
    @FXML
    private Button actualiser;

    @FXML
    private Button supprimer;

    @FXML
    private JFXButton Quitter;

    @FXML
    void QuitterAction(ActionEvent event) {
       Stage stage = (Stage) parent.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actualiserAction(ActionEvent event) {
            ObservableList<Demande> listDemande = FXCollections.observableArrayList(demandeServices.displayAll());
            listView.setItems(listDemande);
    }

    @FXML
    void supprimerAction(ActionEvent event) {
            demandeServices.delete(listView.getSelectionModel().getSelectedItem().getId());
             ObservableList<Demande> listDemande = FXCollections.observableArrayList(demandeServices.displayAll());
            listView.setItems(listDemande);
    }
    Demande demande = new Demande(); 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setItems(listDemande);
        listView.setOnMouseClicked((e)->{
        demande = listView.getSelectionModel().getSelectedItem();

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog with Custom Actions");
                    alert.setHeaderText("Vous Voulez Confirmez ou Supprimer la Demande ?");
                    alert.setContentText("Choose your option.");

                    ButtonType buttonTypeOne = new ButtonType("Accepter");
                    ButtonType buttonTypeTwo = new ButtonType("Supprimer");

                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                       System.out.println("************************* Demande " + demande);
                        System.out.println("************************* id rh " + demande.getRessource() + "    id event " + demande.getEvent());
                        demandeServices.modifierEtat(demande.getId(), demande.getRessource(), demande.getEvent()); 
                         listDemande = FXCollections.observableArrayList(demandeServices.displayAll());
                        listView.setItems(listDemande);
                        System.out.println("mail destination"+demandeServices.chercherMailRs(demande.getRessource()) );
                        demandeServices.envoyerMail(demandeServices.chercherMailRs(demande.getRessource()), demandeServices.chercherTitreEvenement(demande.getEvent()), demande.getDated());
                        

                        
                    } else if (result.get() == buttonTypeTwo) {
                       demandeServices.delete(demande.getId());
                        listDemande = FXCollections.observableArrayList(demandeServices.displayAll());
                        listView.setItems(listDemande);
                    }

             
             
       
            
            
        });
        
        
        
        //listView.setCellFactory(param-> new Cell()); // fazet new cell ken maa liste de chaine 
      
      
    }    
    
    
}
