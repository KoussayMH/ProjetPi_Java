/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import static Presentation.EventController.myVariable;
import com.jfoenix.controls.JFXListView;
import entities.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author kouss
 */
public class Event_FrontController implements Initializable {
 EventServices eventserv ;
        Event e ; 
        
    @FXML
    private TextField input_recherche;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnconsulter;
    @FXML
    private Button find_event;

    
    public static Event myVariable;

    public static Event getMyVariable() {
        return myVariable;
    }

    public static void setMyVariable(Event myVariable) {
        EventController.myVariable = myVariable;
    }
    
    @FXML
    private ListView<String> table;
    
    public int count = -1; 
    
    ObservableList<Event> listEvents = FXCollections.observableArrayList(eventserv.displayAll());
    ObservableList<String> list = FXCollections.observableArrayList(eventserv.listViewEvents());     
    
    class Cell extends ListCell<String> 
            {
                HBox hbox = new HBox(); 
                Label label = new Label (""); 
                Pane pane = new Pane(); 
                
                public Cell( )
                {
              
                try { 

                       OutputStream os = new FileOutputStream(new File("photo1.jpg"));
                       eventserv = new EventServices(); 

                       InputStream is = eventserv.displayImageByIndex(count);  
                       byte[] content = new byte[2048]; 
                       int size =0; 
                try{
                       count ++; 
                                 while ((size = is.read(content))!= -1)
                           {
                               os.write(content, 0, size);

                           }
                   }
                   catch(Exception e)
                   {
                       System.out.println("IMAAAAAAGGGEE");  
                   }

                   os.close();

                    Image image = new Image("file:photo1.jpg", 100, 150, true, true); 
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setLayoutX(36);            
                    imageView.setLayoutY(93);
                    imageView.setFitHeight(300);
                    imageView.setFitWidth(234);
            
                hbox.getChildren().addAll( imageView, label, pane); 
                   } catch (FileNotFoundException ex) {
                          System.out.println("WARNIIIINNN 11");
                   } catch (IOException ex) {
                       System.out.println("WARNIIIINNN 22 ");
                   }
           
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
     * Initializes the controller class.
     */
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        table.setItems(list);
        table.setCellFactory(param-> new Cell());  
    }    

    @FXML
    private void handleInputSearchAction(KeyEvent event) {
    }

    @FXML
    private void handleButtonAddAction(MouseEvent event) {
    }

    @FXML
    private void ajoutAction(ActionEvent event) throws IOException, SQLException {
           
        
            System.out.println("You clicked add button!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Ajout_Event.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();

    }

    @FXML
    private void SingleEventAction(ActionEvent event) throws IOException {
     /*     System.out.println("You clicked Single_event button!");

       e = new Event () ;
       e=table.getSelectionModel().getSelectedItem();
       myVariable=e;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Single_Event.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show(); 
*/
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
