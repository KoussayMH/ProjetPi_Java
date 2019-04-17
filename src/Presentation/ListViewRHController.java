/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entites.RH;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.RHServices;
import utils.UserSession;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListViewRHController implements Initializable {

    @FXML
    private JFXListView<String> listView;
    @FXML
    private JFXButton retouner;
    @FXML
    private Label username;
  
    
    @FXML
    void retournerAction(ActionEvent event) {
            try {
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ClubInterface.fxml"));
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
    
    RHServices rhService= new RHServices(); 
    int count = -1; 
    
    ObservableList<RH> listRH = FXCollections.observableArrayList(rhService.displayAll());
    ObservableList<String> list = FXCollections.observableArrayList(rhService.listViewRH());     
    
     class Cell extends ListCell<String> 
            {
                HBox hbox = new HBox(); 
                Label label = new Label (""); 
                Pane pane = new Pane(); 
                
                public Cell( )
                {
              
                try { 

                       OutputStream os = new FileOutputStream(new File("photo1.jpg"));
                       RHServices rhService = new RHServices(); 

                       InputStream is = rhService.displayImageByIndex(count);  
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
        username.setText(UserSession.getInstance().getUsername() + " |  CLUB"); 
        listView.setItems(list);
      
        listView.setCellFactory(param-> new Cell());
        listView.setOnMouseClicked(event->{
            try {
               
                
                RH rh = new RH(); 
                rh = listRH.get(listView.getSelectionModel().getSelectedIndex()); 
                
                
                FXMLLoader Loader = new FXMLLoader ();
                Loader.setLocation(ListViewRHController.this.getClass().getResource("/views/DetailsRH.fxml"));
                Loader.load();
                
                  /**************************************************/

                    OutputStream oss = new FileOutputStream(new File("photo.jpg"));
                    InputStream iss = rhService.displayImageById(rh.getId()); 
                    byte[] content1 = new byte[1024]; 
                    int size1 =0; 
                    
                    while ((size1 = iss.read(content1))!= -1)
                    {
                        oss.write(content1, 0, size1);

                    }

                 /*****************************************************/
                    DetailsRHController details = Loader.getController();
                    InputStream is = rhService.displayImageByIndex(listView.getSelectionModel().getSelectedIndex()); 
                    details.setText(rh.getId(), rh.getNom(), rh.getCompany(), rh.getDomaine(), rh.getPrenom(), rh.getAdresse(), rh.getEmail(), rh.getLink(), rh.getDescription(), is);
                    oss.close();
                    iss.close();
                
                
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
                    } catch (IOException ex) {
                         System.out.println("BUTTON WARNING ");
                        Logger.getLogger(ListViewRHController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
        
        
        
    }    
    
    
}
