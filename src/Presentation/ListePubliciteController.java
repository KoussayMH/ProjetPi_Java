/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.PubliciteServices;
import entities.Publicite;
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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Malek Ben Jemaa
 */
public class ListePubliciteController implements Initializable {

    private ObservableList<Publicite> listdata = FXCollections.observableArrayList();
       @FXML
    private AnchorPane parent;

    @FXML
    private TableView<Publicite> publiciteTable;

    @FXML
    private TableColumn<Publicite, String> descriptionColonne;

    @FXML
    private TableColumn<Publicite, String> dateColonne;

    @FXML
    private TableColumn<Publicite, String> dureeColonne;

    @FXML
    private TableColumn<Publicite, String> prioriteColonne;

    @FXML
    private ImageView imageView;

    @FXML
    private Button quitter;

    @FXML
    private Button supprimer;
     Image image;

    /**
     * Initializes the controller class.
     */
     @FXML
   private void SupprimerAction(ActionEvent event) {
    
    
           ObservableList<Publicite> selectedRows, allProduct;
        allProduct = publiciteTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = publiciteTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Publicite Publicite: selectedRows)
        {
            allProduct.remove(Publicite);
              PubliciteServices PS = PubliciteServices.getInstance();
        PS.delete(Publicite);
        }
  
    
    }

   
   public void modifierDescriptionAction(TableColumn.CellEditEvent edittedCell)
    {
        Publicite PubliciteSelected =  publiciteTable.getSelectionModel().getSelectedItem();
        PubliciteSelected.setDescription(edittedCell.getNewValue().toString());
        PubliciteServices pdao = PubliciteServices.getInstance();
        pdao.update(PubliciteSelected);
        System.out.println("publicite Modifie"); 
    }
   
    public void modifierDateAction(TableColumn.CellEditEvent edittedCell)
    {
    Publicite PubliciteSelected =  publiciteTable.getSelectionModel().getSelectedItem();
    PubliciteSelected.setDate(edittedCell.getNewValue().toString());
     PubliciteServices pdao = PubliciteServices.getInstance();
     pdao.update(PubliciteSelected);
     System.out.println("date Modifie"); 
    
    
    }
   public void modifierDureeAction(TableColumn.CellEditEvent edittedCell)
    {
    Publicite PubliciteSelected =  publiciteTable.getSelectionModel().getSelectedItem();
    PubliciteSelected.setDuree(edittedCell.getNewValue().toString());
     PubliciteServices pdao = PubliciteServices.getInstance();
     pdao.update(PubliciteSelected);
     System.out.println("Duree Modifie"); 
    
    
    }
      
  
        
     public void modifierPrioriteAction(TableColumn.CellEditEvent edittedCell)
    {
    Publicite PubliciteSelected =  publiciteTable.getSelectionModel().getSelectedItem();
     PubliciteSelected.setPriorite(Integer.parseInt( edittedCell.getNewValue().toString()));
     PubliciteServices pdao = PubliciteServices.getInstance();
     pdao.update(PubliciteSelected);
     System.out.println("Priorite Modifie"); 
    
    
    }
     
        @FXML 
    public void actualiserAction(Event event )
    {
         PubliciteServices produitService =PubliciteServices.getInstance();
        listdata = produitService.displayAll(); 
        publiciteTable.setItems(listdata);
        
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProprety());
        dateColonne.setCellValueFactory(cell -> cell.
                getValue().getDateProprety());
        dureeColonne.setCellValueFactory(cell -> cell.
                getValue().getDureeProprety());
        prioriteColonne.setCellValueFactory(cell -> cell.
                getValue().getPrioriteProprety().asString());
      
    }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //l'affichage dans tabelView -->ki nhelou yji laffichage direct 
        PubliciteServices PS = PubliciteServices.getInstance();
        listdata = PS.displayAll();
        
        publiciteTable.setItems(listdata);
        
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProprety());
        dateColonne.setCellValueFactory(cell -> cell.
                getValue().getDateProprety());
        
        dureeColonne.setCellValueFactory(cell -> cell.
                getValue().getDureeProprety());
        prioriteColonne.setCellValueFactory(cell -> cell.
                getValue().getPrioriteProprety().asString());
        // TODO
        
        
        publiciteTable.setEditable(true);
        descriptionColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        dureeColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        prioriteColonne.setCellFactory(TextFieldTableCell.forTableColumn());

publiciteTable.setOnMouseClicked( (e)-> 
      {
              PubliciteServices publiciteService = new PubliciteServices(); 

          System.out.println("KAAAED NHAWEL");
           try { 
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            InputStream is = publiciteService.displayImageById(publiciteTable.getSelectionModel().getSelectedItem().getId()); 
            byte[] content = new byte[1024]; 
            int size =0; 
             
            while ((size = is.read(content))!= -1)
            {
                os.write(content, 0, size);
                
            }
            os.close();
            is.close();
            image = new Image("file:photo.jpg", 100, 150, true, true); 
            System.out.println("KAAAED NHAWEL");
            imageView.setImage(image);
            imageView.setLayoutX(315);            
            imageView.setLayoutY(27);
            imageView.setFitHeight(168);
            imageView.setFitWidth(200);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListePubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListePubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      });}

           
       /********************************************/
  
        
        
        
        
        
        
        
        
        
        
    }  
    
    
    
    
    
    
    
    
    

