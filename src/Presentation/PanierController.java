/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import static Presentation.LoginController.u;
import entities.Panier;
import entities.Ticket;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import jdk.nashorn.internal.runtime.JSType;
import services.PanierServices;
import services.TicketServices;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import entities.Commande;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class PanierController implements Initializable {
    
    
    public static float tots ; 
 @FXML
    private TableView<Panier> Tab;
    @FXML
    private TableColumn<Panier, String> EventCol;
    @FXML
    private TableColumn<Panier,String> PrixCol;
    @FXML
    private TableColumn<Panier,String> QtCol;
    @FXML
  private TableColumn<Panier, String> TypeCol;
    
     @FXML
    private Button payer;
     
       @FXML
    private Button delete;
     
        @FXML
    private Label total;
                 private ObservableList<Panier> listdata = FXCollections.observableArrayList(); 
                 
                 
                 @FXML
     public void changeQuantiteCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Panier pan =  Tab.getSelectionModel().getSelectedItem();
        pan.setQte(Integer.parseInt(edittedCell.getNewValue().toString()));        
        PanierServices panService = PanierServices.getInstance();
        panService.update(pan);
        System.out.println("Quantite panier modifiée"); 
        afficher() ; 
    }
     
     
    public void CheckAction(ActionEvent event)  {
                
        try {
            Pagination p = new Pagination("/Presentation/checkout.fxml");
        } catch (IOException ex) {

        }
    }
     private float tot ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PanierServices ps = new PanierServices() ; 
        afficher();
          delete.setOnAction(xx->{
           supprimerPanier(xx);
          afficher();});
     Tab.getSelectionModel().setSelectionMode(
    SelectionMode.MULTIPLE);
     
      payer.setOnAction(event->{
          Commande C = new Commande(tot,u) ; 
            try { 
                ps.Confirmer(u) ;
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
          //ps.commander(C) ; 
            CheckAction(event);
                       
        });
     
     
    }    
    
    
    
    private void afficher()
    {
        tot=0 ; 
         PanierServices panService = PanierServices.getInstance();
           User u = new User() ; 
           u= LoginController.u ; 
        listdata = panService.Afficher(u)  ; 
      
        for (Panier p : listdata)
        {
            tot= tot+ p.getPrix()*p.getQte() ; 
        }
         tots= tot ; 
        total.setText(JSType.toString(tot));
        Tab.setItems(listdata);
              //PrixCol.setCellFactory(TextFieldTableCell.forTableColumn());
 PrixCol.setCellValueFactory(cell -> cell.
                getValue().getPrixProperty().asString());
 
  QtCol.setCellValueFactory(cell -> cell.
                getValue().getQuantitProperty().asString());
  
         Tab.setEditable(true);
         TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));  
         EventCol.setCellValueFactory(new PropertyValueFactory<>("event"));  
  
        QtCol.setCellFactory(TextFieldTableCell.forTableColumn());
       //fin aff 
        
    }
    
    
    
     private void supprimerPanier(ActionEvent event) {
        //juste dans le tab
        ObservableList<Panier> selectedRows, allTicks;
        allTicks = Tab.getItems();
        
        //this gives us the rows that were selected
        selectedRows = Tab.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Panier pan: selectedRows)
        {
             allTicks.remove(pan);
             PanierServices panService = PanierServices.getInstance();
             panService.delete(pan);
        }
    }
      
 
}
            
        
    
    
         
    

