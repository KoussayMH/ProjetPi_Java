/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import entities.Ticket;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.TicketServices;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import jdk.nashorn.internal.runtime.JSType;





/**
 * FXML Controller class
 *
 * @author chaym
 */
public class AfficherTicketsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TableView<Ticket> Ticket;
    @FXML
    private TableColumn<Ticket, String> EventCol;
    @FXML
    private TableColumn<Ticket,String> PrixCol;
    @FXML
    private TableColumn<Ticket,String> QteCol;
    @FXML
  private TableColumn<Ticket, String> TitreCol;
    
     @FXML
    private Button Supprimer;
     
     
      private ObservableList<Ticket> data;
      
    @FXML
    private TextField recherche;

  

    @FXML
    private Button Ajouter;

         private ObservableList<Ticket> listdata = FXCollections.observableArrayList(); 
         
         
    @FXML
     public void changePrixCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Ticket tick =  Ticket.getSelectionModel().getSelectedItem(); 
        tick.setPrix(Float.parseFloat(edittedCell.getNewValue().toString()));   
        //tick.setPrix(100);
        TicketServices tickService = TicketServices.getInstance();
        tickService.update(tick);
        System.out.println("prix modif"); 
    }
   
     
       @FXML
     public void changeQuantiteCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Ticket tick =  Ticket.getSelectionModel().getSelectedItem();
        tick.setQte(Integer.parseInt(edittedCell.getNewValue().toString()));        
        TicketServices tickService = TicketServices.getInstance();
        tickService.update(tick);
        System.out.println("Quantite modifiée"); 
    }
     
         @FXML
     public void changeTitreCellEvent(TableColumn.CellEditEvent edittedCell)
    {
     Ticket tick =  Ticket.getSelectionModel().getSelectedItem();
        tick.setTitre(edittedCell.getNewValue().toString());        
        TicketServices tickService = TicketServices.getInstance();
        tickService.update(tick);
        System.out.println("titre modifiée"); 
    }
     
    
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Affichage
        
        Ticket.getSelectionModel().setSelectionMode(
    SelectionMode.MULTIPLE
);
           TicketServices tickService =TicketServices.getInstance();
           User u = new User() ; 
           u= LoginController.u ; 
        listdata = tickService.displayAll(u)  ; 
        Ticket.setItems(listdata);
              //PrixCol.setCellFactory(TextFieldTableCell.forTableColumn());
 PrixCol.setCellValueFactory(cell -> cell.
                getValue().getPrixProperty().asString());
 
  QteCol.setCellValueFactory(cell -> cell.
                getValue().getQuantiteProperty().asString());
  
         Ticket.setEditable(true);
         TitreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));         
       EventCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ticket,String>,ObservableValue<String>>(){
               public ObservableValue<String> call(TableColumn.CellDataFeatures<Ticket, String> param) {
                    return new SimpleStringProperty(param.getValue().getEvent().getTitre());
              }
          });
        
       // TitreCol.setCellFactory(TextFieldTableCell.forTableColumn());
       //EventCol.setCellFactory(TextFieldTableCell.forTableColumn());
        QteCol.setCellFactory(TextFieldTableCell.forTableColumn());
       PrixCol.setCellFactory(TextFieldTableCell.forTableColumn());
       //fin aff 
       
       Ajouter.setOnAction(event->{
            ajouterTicket(event);
                       
        });
       
        Supprimer.setOnAction(xx->{
           supprimerTicket(xx);
         
        });
        /////////// recherche 
         data = FXCollections.observableArrayList();
         TicketServices ticks = new TicketServices() ;
         ArrayList<Ticket> lista = ticks.display(u);

        for(Ticket s:lista){
            System.out.println(s);
            data.add(s);
        }
          Ticket.setItems(data);
       FilteredList<Ticket> FilteredData= new FilteredList<>(data , e-> true);
       recherche.setOnKeyReleased(e->{
           recherche.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                   FilteredData.setPredicate((Predicate<? super Ticket>) tick -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if ((tick.getTitre().contains(newValue)) || (tick.getTitre().toLowerCase().contains(newValue))){
                        return true;
                    }
                    else if(tick.getTitre().toLowerCase().contains(lowerCaseFilter)){
                        return true ;
                    }
                    
                  
                  /*  else if(JSType.toString(tick.getQte())== JSType.toInt32(newValue)){
                        return true ;
                    }*/
                     else if(JSType.toString(tick.getQte()).contains(newValue)){
                        return true ;
                    }
                      else if(JSType.toString(tick.getPrix()).contains(newValue)){
                        return true ;
                    }
                    return false;

           });
       });
           SortedList<Ticket> SortedData = new SortedList<>(FilteredData)  ; 
                    SortedData.comparatorProperty().bind(Ticket.comparatorProperty());
                    Ticket.setItems(SortedData);
 });
       }  
    ///////////////////////////

       
        
     
    
     private void ajouterTicket(ActionEvent event)  {
                
        try {
            Pagination p = new Pagination("/Presentation/AjouterTicket.fxml");
        } catch (IOException ex) {

        }
    }
    private void supprimerTicket(ActionEvent event) {
        //juste dans le tab
        ObservableList<Ticket> selectedRows, allTicks;
        allTicks = Ticket.getItems();
        
        //this gives us the rows that were selected
        selectedRows = Ticket.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Ticket ticket: selectedRows)
        {
             allTicks.remove(ticket);
             TicketServices tickService = TicketServices.getInstance();
             tickService.delete(ticket);
        }
    }
         
    
}
