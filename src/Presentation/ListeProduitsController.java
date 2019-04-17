/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import entites.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import services.ProduitServices;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.UserSession;
import utils.Utils;



/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeProduitsController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private TableView<Produit> listeProduits;
    @FXML
    private TableColumn<Produit, String> libelleColonne;
    @FXML
    private TableColumn<Produit, String> CategorieColonne;
    @FXML
    private TableColumn<Produit, String> prixColonne;
    @FXML
    private TableColumn<Produit, String> quantiteColonne;
    @FXML
    private TableColumn<Produit, String> DescriptionColonne;
     @FXML
    private Button supprimer;

    @FXML
    private Button actualiser;

    @FXML
    private Button ajouter;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private JFXButton Reourner;
  
    @FXML
    private Button statProduit;
    
    @FXML
    private Label username;
    
    Image image;
    
     private ObservableList<Produit> listdata = FXCollections.observableArrayList(); 
    /**
     * Initializes the controller class.
     */
   
      @FXML
    void statProduits(ActionEvent event) {
        System.out.println("STAAAAT");
         Utils.loadWindow(getClass().getResource("statistiqueProduits.fxml"), "statistique des Produits", null);

    }
        @FXML
    void DeconnecterAction(ActionEvent event) {
        try {
            UserSession.getInstance().cleanUserSession();
            Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TeckEvents");
            Utils util = new Utils (); 
                util.setStageIcon(stage);
            stage.setScene(scene);
            
            stage.show();
            username.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ClubInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void supprimerProduit(ActionEvent event) {
        //juste dans le tab
        ObservableList<Produit> selectedRows, allProduct;
        allProduct = listeProduits.getItems();
        
        //this gives us the rows that were selected
        selectedRows = listeProduits.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Produit produit: selectedRows)
        {
             allProduct.remove(produit);
             ProduitServices produitService = ProduitServices.getInstance();
             produitService.delete(produit);
        }
        
        
      
    }
     @FXML
    private void ajouterAction(ActionEvent event) {
        System.out.println("views.ToolbarController.ajouterAction()");
         Utils.loadWindow(getClass().getResource("ajouterProduit.fxml"), "Add New Member", null);

    }
    
    @FXML
     public void changeLibelleCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Produit ProduitSelected =  listeProduits.getSelectionModel().getSelectedItem();
        ProduitSelected.setLibelle(edittedCell.getNewValue().toString());        
        ProduitServices produitService = ProduitServices.getInstance();
        produitService.update(ProduitSelected);
        System.out.println("Libelle modifiée"); 
    }
      @FXML
     public void changeDescriptionCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Produit ProduitSelected =  listeProduits.getSelectionModel().getSelectedItem();
        ProduitSelected.setDescription(edittedCell.getNewValue().toString());        
        ProduitServices produitService = ProduitServices.getInstance();
        produitService.update(ProduitSelected);
        System.out.println("Description modifiée"); 
    }
      @FXML
     public void changeCategorieCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Produit ProduitSelected =  listeProduits.getSelectionModel().getSelectedItem();
        ProduitSelected.setCategorie(edittedCell.getNewValue().toString());        
        ProduitServices produitService = ProduitServices.getInstance();
        produitService.update(ProduitSelected);
        System.out.println("Categorie modifiée"); 
    }
     
      @FXML
     public void changePrixCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Produit ProduitSelected =  listeProduits.getSelectionModel().getSelectedItem();
        ProduitSelected.setPrix(Float.parseFloat(edittedCell.getNewValue().toString()));        
        ProduitServices produitService = ProduitServices.getInstance();
        produitService.update(ProduitSelected);
        System.out.println("prix modifiée"); 
    }
     @FXML
     public void changeQuantiteCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Produit ProduitSelected =  listeProduits.getSelectionModel().getSelectedItem();
        ProduitSelected.setQuantite(Integer.parseInt(edittedCell.getNewValue().toString()));        
        ProduitServices produitService = ProduitServices.getInstance();
        produitService.update(ProduitSelected);
        System.out.println("Quantite modifiée"); 
    }
        @FXML
    void RetournerAction(ActionEvent event) {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/AdminInterface.fxml"));
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
     
    @FXML 
    public void actualiserAction(Event event )
    {
         ProduitServices produitService =ProduitServices.getInstance();
        listdata = produitService.displayAll(); 
        listeProduits.setItems(listdata);
        
        libelleColonne.setCellValueFactory(cell -> cell.
                getValue().getLibelleProperty());
        prixColonne.setCellValueFactory(cell -> cell.
                getValue().getPrixProperty().asString());
        quantiteColonne.setCellValueFactory(cell -> cell.
                getValue().getQuantiteProperty().asString());
        CategorieColonne.setCellValueFactory(cell -> cell.
                getValue().getCategorieProperty());
        DescriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        username.setText("Espace " + UserSession.getInstance().getUsername());
        /* Affichage dans tableView  */
        ProduitServices produitService =ProduitServices.getInstance();
        listdata = produitService.displayAll(); 
        listeProduits.setItems(listdata);
        
        libelleColonne.setCellValueFactory(cell -> cell.
                getValue().getLibelleProperty());
        prixColonne.setCellValueFactory(cell -> cell.
                getValue().getPrixProperty().asString());
        quantiteColonne.setCellValueFactory(cell -> cell.
                getValue().getQuantiteProperty().asString());
        CategorieColonne.setCellValueFactory(cell -> cell.
                getValue().getCategorieProperty());
        DescriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        
        /******************************************/ 
  
        listeProduits.setEditable(true);
        CategorieColonne.setCellFactory(TextFieldTableCell.forTableColumn());        
        prixColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        libelleColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        quantiteColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionColonne.setCellFactory(TextFieldTableCell.forTableColumn());
      /******************************************/ 
      
      
      listeProduits.setOnMouseClicked( (e)-> 
      {
          
          /**************************************************/
          System.out.println("KAAAED NHAWEL");
           try { 
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            InputStream is = produitService.displayImageById(listeProduits.getSelectionModel().getSelectedItem().getId()); 
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
            imageView.setLayoutX(30);            
            imageView.setLayoutY(185);
            imageView.setFitHeight(300);
            imageView.setFitWidth(234);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListeProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
           /*****************************************************/
      }
      
      );

           
       /********************************************/
  
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
        } catch (IOException ex) {
            Logger.getLogger(ListeProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
        
         /********************************************/
     
    }

    
}
