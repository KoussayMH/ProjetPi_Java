/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.PubliciteServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entities.Publicite;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Malek Ben Jemaa
 */
public class PubliciteParPrioriteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXListView<String> listView;
  
    int count = -1; 
    
    ObservableList<Publicite> listPub = FXCollections.observableArrayList(PubliciteServices.getInstance().displayAll());
    ObservableList<String> list = FXCollections.observableArrayList(PubliciteServices.getInstance().listViewPub());     
    
     class Cell extends ListCell<String> 
            {
                HBox hbox = new HBox(); 
                Label label = new Label (""); 
                Pane pane = new Pane(); 
                
                public Cell( )
                {
              
                try { 

                       OutputStream os = new FileOutputStream(new File("photo1.jpg"));
                       InputStream is = PubliciteServices.getInstance().displayImageByIndex(count);  
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      listView.setItems(list);
      
        listView.setCellFactory(param-> new Cell());
    }    
    
}
