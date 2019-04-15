/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import entities.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.JSType;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author kouss
 */
public class EventController implements Initializable {

    
     EventServices eventserv ;
        Event e ; 
    
    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, Integer> col_id;
    @FXML
    private TableColumn<Event, String> col_titre;
    @FXML
    private TableColumn<Event, String> col_image1;
    @FXML
    private TableColumn<Event, String> col_date;
    @FXML
    private TableColumn<Event, String> col_description;
    @FXML
    private TableColumn<Event, String> col_etat;
    @FXML
    private TableColumn<Event, String> col_lieu;
     @FXML
    private TableColumn<Event, Integer> col_nbre_participants;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField input_recherche;
    @FXML
    private Button btnconsulter;
   
    public static Event myVariable;

    public static Event getMyVariable() {
        return myVariable;
    }

    public static void setMyVariable(Event myVariable) {
        EventController.myVariable = myVariable;
    }
    @FXML
    private Button btnconfirmer;
    @FXML
    private Button find_event;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

             try {
                 
                 
                 eventserv = new EventServices() ;
                 e=new Event() ;
                 col_id.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
                 col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 col_image1.setCellValueFactory(new PropertyValueFactory<>("image1"));
                 col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
                 col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
                 col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                 col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                 col_nbre_participants.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
                 
                 table.setEditable(true); //Rend le tableau "editable"
                 col_titre.setCellFactory(TextFieldTableCell.forTableColumn());
                 col_description.setCellFactory(TextFieldTableCell.forTableColumn());
                 
                 table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Pour séléctionner plusieurs lignes en même temps
                 try {
                     table.setItems(eventserv.afficherEvent());
                 } catch (SQLException ex) {
                     Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 ObservableList<Event> data;
                 data = eventserv.afficherEvent();
                 
                 
                 /////////// recherche
                 data = FXCollections.observableArrayList();
                 // TicketServices ticks = new TicketServices() ;
                 ObservableList<Event> lista = eventserv.afficherEvent();
                 
                 for(Event s:lista){
                     System.out.println(s);
                     data.add(s);
                 }
                 table.setItems(data);
                 FilteredList<Event> FilteredData= new FilteredList<>(data , e-> true);
                 input_recherche.setOnKeyReleased(e->{
                     input_recherche.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                         FilteredData.setPredicate((Predicate<? super Event>) eve -> {
                             if (newValue == null || newValue.isEmpty()) {
                                 return true;
                             }
                             String lowerCaseFilter = newValue.toLowerCase();
                             if ((eve.getTitre().contains(newValue)) || (eve.getTitre().toLowerCase().contains(newValue))){
                                 return true;
                             }
                             else if(eve.getTitre().toLowerCase().contains(lowerCaseFilter)){
                                 return true ;
                             }
                             
                             if (((eve.getDescription()).contains(newValue)) || (eve.getDescription().toLowerCase().contains(newValue))){
                                 return true;
                             }
                             else if(eve.getDescription().toLowerCase().contains(lowerCaseFilter)){
                                 return true ;
                             }
                             
                             else if(JSType.toString(eve.getNb_participants()).contains(newValue)){
                                 return true ;
                             }
                             
                             return false;
                             
                         });
                     });
                     SortedList<Event> SortedData = new SortedList<>(FilteredData)  ;
                     SortedData.comparatorProperty().bind(table.comparatorProperty());
                     table.setItems(SortedData);
                 });
                 
                 
                 
                 
                 
             } catch (SQLException ex) {
                 Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
             }

         
         
         
         
    }    


    @FXML
    private void handleButtonAddAction(MouseEvent event) {
        
        
       
    }

    @FXML
    private void ajoutAction(ActionEvent event) {
        
        
        try {
            System.out.println("You clicked add button!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Ajout_Event.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    @FXML
    private void handleButtonDeleteAction(MouseEvent event) {
    }

    @FXML
    private void supprimerAction(ActionEvent event) throws SQLException {
         System.out.println("You clicked delete button!");

      e = new Event () ;
       e=table.getSelectionModel().getSelectedItem();
       eventserv.supprimerEvent(e);
        table.setItems(eventserv.afficherEvent());
    }
    
    
     @FXML
    private void SingleEventAction(ActionEvent event) throws IOException {
        
        System.out.println("You clicked Single_event button!");

       e = new Event () ;
       e=table.getSelectionModel().getSelectedItem();
       myVariable=e;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Single_Event.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
    }

     @FXML
    private void confirmerAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked confirmer button!");

      e = new Event () ;
      e=table.getSelectionModel().getSelectedItem();
       eventserv.confirmerEvent(e);
      System.out.println("l'event"+e.getTitre()+"a ete confirmer");

        table.setItems(eventserv.afficherEvent());
    }
   
    @FXML
    private void handleInputSearchAction(KeyEvent event) throws IOException {
        
    }

    @FXML
    private void ExportToExcel(ActionEvent event) {
    }

    @FXML
    private void ExportToPDF(ActionEvent event) {
    }

    
    private void edit_titre(TableColumn.CellEditEvent<Event, String> event) throws SQLException {
        Event e = table.getSelectionModel().getSelectedItem(); 
        e.setTitre(event.getNewValue());
        eventserv.modifier_Titre(e);
        table.setItems(eventserv.afficherEvent());

    }
      
    private void edit_description(TableColumn.CellEditEvent<Event, String> event) throws SQLException {
        Event e = table.getSelectionModel().getSelectedItem(); 
        e.setDescription(event.getNewValue());
        eventserv.modifier_Descr(e);
        table.setItems(eventserv.afficherEvent());

    }
    
    private void edit_Lieu(TableColumn.CellEditEvent<Event, String> event) throws SQLException {
        Event e = table.getSelectionModel().getSelectedItem(); 
        e.setLieu(event.getNewValue());
        eventserv.modifier_Lieu(e);
        table.setItems(eventserv.afficherEvent());

    }

    @FXML
    private void onEditStringCommitAction(TableColumn.CellEditEvent<String, String> event) {
    }

    @FXML
    private void onEditIntegerCommitAction(TableColumn.CellEditEvent<String, String> event) {
    }

    @FXML
    private void onEditDateCommitAction(TableColumn.CellEditEvent<String, String> event) {
    }

    @FXML
    private void search_event(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Event_map.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
    }


   
   
  

    
}
