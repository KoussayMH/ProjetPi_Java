/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import connex.Connexion;
import entities.Comment;
import entities.Event;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import jdk.nashorn.internal.runtime.JSType;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author kouss
 */

public class CommentController implements Initializable {

    
        EventServices eventserv ;
        Comment c; 
        Connection cn= Connexion.getInstance().getConnection() ; 
        Statement st ; 
        PreparedStatement pst ;
        
        
    @FXML
    private TableColumn <Comment, String> col_Text;
    @FXML
    private TableColumn <Comment, String>  col_Maccaract;
    @FXML
    private TableColumn <Comment, String>  col_date;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<Comment> table;
    @FXML
    private TableColumn<Comment, String> col_id;
    @FXML
    private TableColumn<Comment, Integer>  event_id;
    @FXML
    private TableColumn<Comment, Integer>  user_id;
    /**
     * Initializes the controller class.
     */
                int id_rech= EventController.myVariable_comment.getId();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    eventserv = new EventServices() ;


         /*    try {*/
                 
                 c=new Comment() ;
                 col_id.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity not from the base
                 col_Text.setCellValueFactory(new PropertyValueFactory<>("description"));
                 col_Maccaract.setCellValueFactory(new PropertyValueFactory<>("max_caracs"));
                 col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 event_id.setCellValueFactory(new PropertyValueFactory<>("Event_id"));
                 user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
              
                 
                 table.setEditable(true); //Rend le tableau "editable"
               

                 table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Pour séléctionner plusieurs lignes en même temps
                 try {
                     table.setItems(eventserv.afficherCommentByid(id_rech));
                 } catch (SQLException ex) {
                     Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                  /*
                 
                 /////////// recherche
                 ObservableList<Event> data;
                 data = eventserv.afficherEvent();
                
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
  
*/

    }    

    @FXML
    private void Text_edit_Action(TableColumn.CellEditEvent event) {
    }

    @FXML
    private void Max_edit_Action(TableColumn.CellEditEvent event) {
    }

    @FXML
    private void Date_edit_Action(TableColumn.CellEditEvent event) {
    }

    @FXML
    private void supprimerAction(ActionEvent event) throws SQLException {
         System.out.println("Theb tfassekh comment!");

      c = new Comment () ;
       c=table.getSelectionModel().getSelectedItem();
       eventserv.supprimerComment(c);
        table.setItems(eventserv.afficherCommentByid(id_rech));
    }
    
}
