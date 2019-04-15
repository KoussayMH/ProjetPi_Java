/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import entities.Personne;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.PersonneServices;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class PersonneController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
   
        
        

    
  
    
    @FXML
    private TextField Nom ;
    @FXML
    private TextField Prenom ;
     @FXML
    private Button btnajout ;
         ObservableList data;

        PersonneServices persService ;
        Personne personne ; 

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        

               persService = new PersonneServices() ;
                personne=new Personne() ; 


        
             btnajout.setOnAction((event) -> {
             personne.setNom(Nom.getText());
             personne.setPrenom(Prenom.getText());
            
     System.out.println(personne);
            persService.AjouterPersonne(personne);         
            Nom.setText("");
            Prenom.setText("");
            
        });
           
  
    }    


       
    
}
