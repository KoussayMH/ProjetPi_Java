/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import entites.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import services.ProduitServices;
import java.awt.Image;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private AnchorPane parent;

    @FXML
    private Button Parcourir;

    @FXML
    private JFXTextField ParcourirText;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXTextField libelle;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXSlider slider;

    @FXML
    private Button ajouter;

    @FXML
    private Button Quitter;
     
    @FXML
    private JFXComboBox<String> Categorie;
    //ObservableList<String> listCategorie=FXCollections.observableArrayList(); 
    
    
    
    private JLabel labelImage;
    private boolean test1, test2, test3  ; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    /*************************CONTROLE DE SAISIE *******************************/     
        RequiredFieldValidator validator = new RequiredFieldValidator();
        NumberValidator numValidator = new NumberValidator();
        test1 = false ; 
        test2= false ; 
        test3 = false ; 
        /***********REQUIRED*******************/ 
        prix.getValidators().add(validator);
        validator.setMessage("Champs Vide");
        description.getValidators().add(validator);
        validator.setMessage("Champs Vide");
        libelle.getValidators().add(validator);
        validator.setMessage("Champs Vide");
        
        /***********NUMBER *******************/
        prix.getValidators().add(numValidator);
        numValidator.setMessage("Veuillez saisir un numero valide");
        /***********LISTENER *******************/
        prix.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                    if (!prix.validate())
                {
                    test1 = false ; 
                }
                    else 
                {
                    test1= true ; 
                }
                    
                    
                    
                }
            }

        }
        );
        description.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                       if (! description.validate())
                {
                    test2 = false ; 
                }
                         else 
                {
                    test2= true ; 
                }
                   
                    
                }
            }

        }
        );
        libelle.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                       if (!libelle.validate())
                {
                    test3 = false ; 
                }
                         else 
                {
                    test3= true ; 
                }
                    
                }
            }

        }
        );
       
      
        
       // listCategorie.setAll("Chaise", "Table", "Decoration"); 
        
        ProduitServices p = new ProduitServices(); 
        Categorie.setItems(p.listeCategories()); 
        
        labelImage = new JLabel();
        labelImage.setBounds(446,182,322,331);
        

        
 
    } 
    

       @FXML
    private void quitterAction (ActionEvent event) {
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.close();
    }
    
    
    
    @FXML
    private void ajouterProduit(ActionEvent event) throws FileNotFoundException {
        if (test1 && test2 && test3 )
        {
           Produit p = new Produit(libelle.getText(),Integer.parseInt( prix.getText()), ((int) slider.getValue()), Categorie.getValue(), description.getText(),ParcourirText.getText() );
        ProduitServices pdao = ProduitServices.getInstance();
        pdao.insert(p);
        
      /*  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit inséré avec succés!");
        alert.show();*/
      
        libelle.setText("");
        prix.setText("");
        description.setText("");  
        }
        else {
             Utils util = new Utils (); 
            utils.AlertMaker.showErrorMessage("Attention", "Verifiez vos champs !");
            
       /*     
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Verifiez les champs !");
        alert.show();
        */
        }
        

    }
    
    public void setPathFile(String username) {
        this.ParcourirText.setText(username);
    }
    
    @FXML 
    private void parcourirAction (ActionEvent event) {
    FileChooser fc = new FileChooser() ; 
        File selectedFile = fc.showOpenDialog(null) ; 
        
        if ( selectedFile != null )
        {setPathFile( selectedFile.getAbsolutePath());
        
        }
        else System.out.println("file is not valid ");
    }
    
    
    public ImageIcon ResizeImage(String ImagePath, byte[] pic)
    {
        ImageIcon MyImage = null;
        if(ImagePath != null)
        {
           MyImage = new ImageIcon(ImagePath);
        }else
        {
            MyImage = new ImageIcon(pic);
        }
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(labelImage.getWidth(), labelImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
  
}
