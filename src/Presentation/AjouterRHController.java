/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import entites.RH;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import services.RHServices;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AjouterRHController implements Initializable {

    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField domaine;
    @FXML
    private JFXTextField company;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField link;
    @FXML
    private JFXButton quitter;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField ParcourirText;
    
    @FXML
    private JFXButton parcourir;
    
    private JLabel labelImage;
  
    @FXML
    private AnchorPane  rootPane;
    @FXML
    private ImageView imageView;
    /**
     * Initializes the controller class.
     */
    boolean test1, test2, test3, test4, test5, test6, test7, test8; 
    Utils util = new Utils(); 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      labelImage = new JLabel();
      labelImage.setBounds(356, 85,200 ,234 );
            test1=false;       
            test2=false; 
            test3=false; 
            test4=false; 
            test5=false; 
            test6=false; 
            test7=false;       
            test8=false; 


      
      RequiredFieldValidator validator = new RequiredFieldValidator();      
      RequiredFieldValidator validator2 = new RequiredFieldValidator();

      NumberValidator numValidator = new NumberValidator();
       /***********REQUIRED*******************/ 
       
       
        description.getValidators().add(validator);
        nom.getValidators().add(validator);
        prenom.getValidators().add(validator);
        domaine.getValidators().add(validator);
        link.getValidators().add(validator);
        email.getValidators().add(validator);
        adresse.getValidators().add(validator);
        company.getValidators().add(validator);
        validator.setMessage("Champs Vide");
         /***********LISTENER *******************/
        nom.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                     if (nom.validate() && (util.CaracSpec(nom.getText())))
                            {
                                test1 = true ; 
                            }
                       else if (!util.CaracSpec(nom.getText())) 
                            {
                                 test1= false ; 
                                 Utils util = new Utils (); 
                                 utils.AlertMaker.showErrorMessage("Attention", "Pas de caratères spéciaux ou chiffre dans un nom");
                                 nom.setText("");
                                
                            }
                       else
                            {
                                test1= false ; 
                            }
                    
                    
                    
                }
            }

         }
        );
        prenom.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
               if (prenom.validate() && (util.CaracSpec(prenom.getText())))
                            {
                                test2 = true ; 
                            }
                       else if (!util.CaracSpec(prenom.getText())) 
                            {
                                 test2= false ; 
                                 Utils util = new Utils (); 
                                 utils.AlertMaker.showErrorMessage("Attention", "Pas de caratères spéciaux ou chiffre dans un prenom");
                                 prenom.setText("");
                                
                            }
                       else
                            {
                                test2= false ; 
                            }
                    
                    
                    
                }
            }

        }
        );
        description.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                    if (!description.validate())
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
        domaine.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (domaine.validate() && (util.CaracSpec(domaine.getText())))
                            {
                                test4 = true ; 
                            }
                       else if (!util.CaracSpec(domaine.getText())) 
                            {
                                 test4= false ; 
                                 Utils util = new Utils (); 
                                 utils.AlertMaker.showErrorMessage("Attention", "Pas de caratères spéciaux ou chiffre dans un Domaine");
                                 domaine.setText("");
                                
                            }
                       else
                            {
                                test4= false ; 
                            }
                    
                    
                    
                }
            

        }
        );
        email.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                       if (email.validate() && (util.controleEmail(email.getText())))
                            {
                               
                                test5 = true ; 
                            }
                        if (!util.controleEmail(email.getText())) 
                            {
                                 System.out.println(email.getText());
                                 test5= false ; 
                                 Utils util = new Utils (); 
                                 utils.AlertMaker.showErrorMessage("Attention", "Email Invalide");
                                 email.setText("");
                                
                            }
                       else
                            {
                                System.out.println(email.getText());
                                test5= false ; 
                            }
                    
                    
                    
                }
            }

        }
        );
        
        link.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                 if (!newValue) {
                if (link.validate() && (util.lienVerif(link.getText())))
                      
                            {
                                test6 = true ; 
                            }
                       else if (!util.lienVerif(link.getText())) 
                            {
                                 
                                 Utils util = new Utils (); 
                                 utils.AlertMaker.showErrorMessage("Attention", "Link Invalide");
                                 link.setText("");
                                 test6= false ; 
                                
                            }
                       else
                            {
                                test6= false ; 
                            }
                    
                    
                    
                
            }}

        }
        );
        adresse.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
                    if (!adresse.validate())
                {
                    test7 = false ; 
                }
                    else 
                {
                    test7= true ; 
                }
                    
                    
                    
                }
            }

        }
        );
        company.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    
               if (company.validate() && (util.CaracSpec(company.getText())))
                            {
                                test8 = true ; 
                            }
                       else if (!util.CaracSpec(company.getText())) 
                            {
                                 test8= false ; 
                                 Utils util = new Utils (); 
                                 utils.AlertMaker.showErrorMessage("Attention", "Pas de caratères spéciaux ou chiffre dans un Company");
                                 company.setText("");
                                
                            }
                       else
                            {
                                test8= false ; 
                            }
                    
                    
                    
                    
                }
            }

        }
        );
         
    }    

    @FXML
    private void ajouterAction(ActionEvent event) throws FileNotFoundException {
        
        RH p = new RH( nom.getText(),prenom.getText(),description.getText() , adresse.getText() , domaine.getText(),company.getText(),email.getText(), link.getText() , ParcourirText.getText()  );
        RHServices pdao = RHServices.getInstance();
        pdao.insert(p);
        
       /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Ressource inséré avec succés!");
        alert.show();*/
        nom.setText(""); 
        prenom.setText(""); 
        description.setText(""); 
        adresse.setText(""); 
        domaine.setText(""); 
        company.setText(""); 
        email.setText(""); 
        link.setText(""); 
        ParcourirText.setText("");       
      
        description.setText(""); 
        
    }
    @FXML 
    private void parcourirAction (ActionEvent event) {
    FileChooser fc = new FileChooser() ; 
        File selectedFile = fc.showOpenDialog(null) ; 
        
        if ( selectedFile != null )
        {setPathFile( selectedFile.getAbsolutePath());
            }
        else System.out.println("file is not valid ");
           try {
           
            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.getAbsolutePath().toString());
           imageView.setImage(image);
        } catch (Exception ex) {
            
        }
        
        
 
    }
    
    public void setPathFile(String username) {
        this.ParcourirText.setText(username);
        
        
        
           
        
        
        
    }
      
      
       @FXML
    private void quitterAction (ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
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
