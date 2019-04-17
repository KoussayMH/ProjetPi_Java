/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import entites.RatingProduit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.CrudRating;
import services.ProduitServices;
import utils.UserSession;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DetailsProduitController implements Initializable {

    private RatingProduit rating;
    
    @FXML
    private Rating ra;
    
    
    
    
    @FXML
    private ImageView imageView;
    @FXML
    private Label libelle;
    @FXML
    private Label prix;
    @FXML
    private Label categorie;
    @FXML
    private Label description;

    Image image;
    @FXML
    private JFXButton retour;
    
    
    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXButton ajouterNombre;

    @FXML
    private JFXButton facebook;
    
     @FXML
    private Label note;
      @FXML
    private Label username;
    boolean test ;
    private Number id; 
    
      @FXML
    void partagerFacebook(ActionEvent event) {
        String x = "PRODUIT " ; 
        String accessToken = "EAAgvucjlPcoBADkmZAQlWhKZBl9DmpzUURbJALbQlt1C1ZB6SzgeKHZCTBLE49GLvuFlnvnHGZBaKX7QeO6tBQvygZCVTLasHZAdiQ8edrZCbTsnEuB7CgIDbKaBNfFIxzgcHrvPo1yOXYOtcqYQsgS2d8bbZCTAI2DZBrhAn8GoiGZAYuqQfSDem6oTZAzg53SJR6V51htOq8gFZAAZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        FacebookType reponse = fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", x));
        System.out.println("fb.com/" + reponse.getId());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("la publication a été partagé dans votre page facebook  avec succes !! ");
        alert.showAndWait();
    }
  
    @FXML
    void ajouterNombreAction(ActionEvent event) {
          System.out.println("id du produit = " + id.intValue());
        ProduitServices p = new ProduitServices(); 
        if (test== false) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Verifiez les champs !");
            alert.show();
        }
        else if (!p.verifierNombre(Integer.parseInt(nombre.getText()), id.intValue()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Quantite disponible insuffisante !");
            alert.show();
            
        }
         
        else {
            p.updatecategorie(Integer.parseInt(nombre.getText()), categorie.getText()); // hedhi baad bech twali baad validation
           // p.updateQuantite(Integer.parseInt(nombre.getText()), id.intValue());
            System.out.println("procedure dachat");
            
        }
        
    }

    
    
    /**
     * Initializes the controller class.
     */

        @FXML
    void RetourListe(ActionEvent event) {
     try {
               
                        Parent parent2=FXMLLoader
                        .load(getClass().getResource("/views/listViewProduits.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Accueil");
                Utils util = new Utils (); 
                util.setStageIcon(stage);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(DetailsProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
       public void setText(int id, String libelle,String prix,String categorie,String description, InputStream is)
    {      
            this.id = id; 
            this.libelle.setText(libelle);
            this.prix.setText(prix);
            this.categorie.setText(categorie);
            this.description.setText(description);
            
            image = new Image("file:photo.jpg", 100, 150, true, true); 
            System.out.println("KAAAED NHAWEL");
            imageView.setImage(image);
            imageView.setLayoutX(36);            
            imageView.setLayoutY(93);
            imageView.setFitHeight(300);
            imageView.setFitWidth(234);
            

    }
    
    CrudRating crudRating = new CrudRating(); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        test=false; 
                username.setText(UserSession.getInstance().getUsername() + " |CLUB");

        
        RequiredFieldValidator validator = new RequiredFieldValidator();
        nombre.getValidators().add(validator);
        validator.setMessage("Champs Vide");
        
        NumberValidator numValidator = new NumberValidator();
        nombre.getValidators().add(numValidator);
        numValidator.setMessage("Veuillez saisir un numero valide");
        
        
        nombre.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                       if (!nombre.validate())
                {
                    test = false ; 
                }
                         else 
                {
                    test= true ; 
                }
                    
                }
            }

        }
        );
         ra.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Rating changed");
                setNum(newValue);
                RatingProduit r = new RatingProduit();
                String note1 = getNum().toString();
                System.out.println("Note : "+note1.substring(0,1));
                r.setNote(Integer.parseInt(getNum().toString().substring(0,1)));
                r.setId_client(1);
                r.setId_produit(id.intValue());
                
                System.out.println(r);

                CrudRating cre = new CrudRating();
                cre.ajouterRating(r);
                System.out.println("NOOOOOOOOOOOOOOOOOTTTEE" + crudRating.note(id.intValue()));
                try {
                                    note.setText(Float.toString(crudRating.note(id.intValue())));

                }catch(Exception e)
                {
                    System.out.println("mochkla fel calcule fel main");
                }

                //System.out.println("emchyyyy");

            }

        }
        ); 
          
          
    } 
    
    private Number num;
     public void setNum(Number num) {
        this.num = num;
    }
     public Number getNum() {
        return num;
    }
}
