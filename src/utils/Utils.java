/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Lenovo
 */
public class Utils {
    public static final String ICON_IMAGE_LOC = "/views/icon.png";
     public  void setStageIcon(Stage stage) {
         
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
         System.out.println("icon");
    }
   
     public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            Utils util = new Utils (); 
                util.setStageIcon(stage);
            stage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }
     
     public boolean controleEmail(String email){
         boolean test = false ; 
         String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
         Pattern pattern = Pattern.compile(masque);
         Matcher controler = pattern.matcher(email);
            if (controler.matches()){
                test= true ; 
                System.out.println("MAIL VALIDE");

            }else{
            test = false ; 
           //  Utils util = new Utils (); 
            // utils.AlertMaker.showErrorMessage("Attention", "Email Invalide");
             email=""; 
            }
            return test; 
    }
       public boolean CaracSpec(String chaine){
         boolean test = false ; 
         String masque = "^[a-zA-Z ]*$";

         Pattern pattern = Pattern.compile(masque);
         Matcher controler = pattern.matcher(chaine);
            if (controler.matches()){
                test= true ; 

            }else{
            test = false ; 
            }
            return test; 
    }
     public boolean lienVerif(String chaine){
         boolean test = false ; 
         String masque = "^https://www.[a-zA-Z0-9\\/._-]*$";

         Pattern pattern = Pattern.compile(masque);
         Matcher controler = pattern.matcher(chaine);
            if (controler.matches()){
                test= true ; 

            }else{
            test = false ; 
            }
            return test; 
    }
     
     
     
}
