/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.DemandeServices;
import services.RHServices;
import utils.UserSession;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeRHController implements Initializable {

     @FXML
    private TableView<RH> listeRH;

    @FXML
    private TableColumn<RH, String> PrenomColonne;

    @FXML
    private TableColumn<RH, String> NomColonne;

    @FXML
    private TableColumn<RH, String> DomaineColonne;

    @FXML
    private TableColumn<RH, String> DescriptionColonne;

    @FXML
    private TableColumn<RH, String> CompanyColonne;

    @FXML
    private TableColumn<RH, String> AdresseColonne;

    @FXML
    private TableColumn<RH, String> EmailColonne;

    @FXML
    private TableColumn<RH, String> LinkColonne;

    @FXML
    private Button supprimer;

    @FXML
    private JFXButton Reourner;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private ImageView imageView;

    @FXML
    private Button actualiser;

    @FXML
    private Button ajouter;
    @FXML
    private Button Demandes;
    @FXML
    private Label notificationLabel;
    Image image;
    
    private boolean test = false ; 
    
    @FXML
    private Label username;
    
     private ObservableList<RH> listdata = FXCollections.observableArrayList(); 
     RHServices rhService =RHServices.getInstance();
    
    @FXML
    void ConsulterDemandes(ActionEvent event) {
            Utils.loadWindow(getClass().getResource("Demandes.fxml"), "Demandes", null);

        
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
                Logger.getLogger(ListeRHController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    void actualiserAction(ActionEvent event) {
        if (test = true )
        {
             listdata = rhService.displayAll(); 
        listeRH.setItems(listdata);
        
        PrenomColonne.setCellValueFactory(cell -> cell.
                getValue().getPrenomProperty());
        NomColonne.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        DomaineColonne.setCellValueFactory(cell -> cell.
                getValue().getDomaineProperty());
        
        DescriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        CompanyColonne.setCellValueFactory(cell -> cell.
                getValue().getCompanyProperty());
        AdresseColonne.setCellValueFactory(cell -> cell.
                getValue().getAdresseProperty());
        EmailColonne.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
        LinkColonne.setCellValueFactory(cell -> cell.
                getValue().getLinkProperty());
        test = false ; 
        }
       
       
    }

    @FXML
    void ajouterAction(ActionEvent event) {
        
         Utils.loadWindow(getClass().getResource("ajouterRH.fxml"), "Ajouter une nouvelle ressource", null);
         test = true ; 

    }
     @FXML
    void supprimerRessource(ActionEvent event) {
        
        test= true ; 
           //juste dans le tab
        ObservableList<RH> selectedRows, allProduct;
        allProduct = listeRH.getItems();
        
        //this gives us the rows that were selected
        selectedRows = listeRH.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (RH rh: selectedRows)
        {
             allProduct.remove(rh);
             RHServices rhService = RHServices.getInstance();
             rhService.delete(rh);
        }


    }

    


 
    

       @FXML
    void changeAdresseCellEvent(TableColumn.CellEditEvent edittedCell) {
        RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setAdresse(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Adresse modifiée"); 
    }

    @FXML
    void changeCompanyCellEvent(TableColumn.CellEditEvent edittedCell) {
        RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setCompany(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Comapny modifiée"); 
    }

    @FXML
    void changeDescriptionCellEvent(TableColumn.CellEditEvent edittedCell) {
        RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setDescription(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Description modifiée");
    }

    @FXML
    void changeDomaineCellEvent(TableColumn.CellEditEvent edittedCell) {
         RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setDomaine(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Domaine modifiée");
    }

    @FXML
    void changeEmailCellEvent(TableColumn.CellEditEvent edittedCell) {
        RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setEmail(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Email modifiée");
    }

    @FXML
    void changeNomCellEvent(TableColumn.CellEditEvent edittedCell) {
        RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setNom(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Nom modifiée");
    }

    @FXML
    void changePrenomCellEvent(TableColumn.CellEditEvent edittedCell) {
        RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setPrenom(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Prenom modifiée");
    }

    @FXML
    void changelinkCellEvent(TableColumn.CellEditEvent edittedCell) {
        RH RHSelected =  listeRH.getSelectionModel().getSelectedItem();
        RHSelected.setLink(edittedCell.getNewValue().toString());        
        RHServices rhServices = RHServices.getInstance();
        rhServices.update(RHSelected);
        System.out.println("Link modifiée");
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
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        username.setText(UserSession.getInstance().getUsername());
          /* Affichage dans tableView  */
          listdata = rhService.displayAll(); 
        listeRH.setItems(listdata);
        
        PrenomColonne.setCellValueFactory(cell -> cell.
                getValue().getPrenomProperty());
        NomColonne.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        DomaineColonne.setCellValueFactory(cell -> cell.
                getValue().getDomaineProperty());
        
        DescriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        CompanyColonne.setCellValueFactory(cell -> cell.
                getValue().getCompanyProperty());
        AdresseColonne.setCellValueFactory(cell -> cell.
                getValue().getAdresseProperty());
        EmailColonne.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
        LinkColonne.setCellValueFactory(cell -> cell.
                getValue().getLinkProperty());
          
          
          
          
          
          /***************************************/ 
        listeRH.setEditable(true);
        PrenomColonne.setCellFactory(TextFieldTableCell.forTableColumn());        
        NomColonne.setCellFactory(TextFieldTableCell.forTableColumn());        
        DomaineColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        CompanyColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        AdresseColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        LinkColonne.setCellFactory(TextFieldTableCell.forTableColumn());
        
        /***************************************/ 
         try { 
                        listeRH.setOnMouseClicked( (e)-> 
                     {

                        try { 
                         OutputStream os = new FileOutputStream(new File("photo1.jpg"));
                         InputStream is = rhService.displayImageById(listeRH.getSelectionModel().getSelectedItem().getId()); 
                         if (is != null) 
                         {
                             byte[] content = new byte[1024]; 
                         int size =0; 

                         while ((size = is.read(content))!= -1)
                         {
                             os.write(content, 0, size);

                         }
                         os.close();
                         is.close();
                         image = new Image("file:photo1.jpg", 100, 150, true, true); 

                         imageView.setImage(image);
                         imageView.setLayoutX(36);            
                         imageView.setLayoutY(93);
                         imageView.setFitHeight(300);
                         imageView.setFitWidth(234);
                         }
                         else 
                         {
                              System.out.println("Image Introuvable");
                         }
                         
                     } catch (FileNotFoundException ex) {
                            System.out.println("Image Introuvable");
                     } catch (IOException ex) {
                        System.out.println("Image Introuvable");
                     }
                   }  );
      } catch (Exception ex) {
                            System.out.println("Image Introuvable");
                     }
    

        
           /***************************************/ 
              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
        } catch (IOException ex) {
            Logger.getLogger(ListeRHController.class.getName()).log(Level.SEVERE, null, ex);
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
        
           /***************************************/ 
         DemandeServices demandeService = new DemandeServices(); 
           if (demandeService.nombreEnCours()> 0)
           {
               notificationLabel.setText("Vous avez des demandes en cours");
               Notifications notificationBuilder = Notifications.create()
                                                            .title("Demande Pour Evenements")
                                                            .text("Vous avez des demandes en cours")
                                                            .graphic(new ImageView("/views/icon.png"))
                                                            .graphic(null)
                                                            .position(Pos.TOP_RIGHT)
                                                            .onAction(new EventHandler<ActionEvent> (){
                                                                @Override 
                                                                        public void handle (ActionEvent event)
                                                                        {
             Utils.loadWindow(getClass().getResource("Demandes.fxml"), "Demandes", null);

                                                                        }
            }); 
        notificationBuilder.darkStyle(); 
        notificationBuilder.show();
               
               
               
           }
        // TODO
    }
    
}
