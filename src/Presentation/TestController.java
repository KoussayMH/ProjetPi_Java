/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class TestController implements Initializable {

    @FXML
    private VBox clubVBs;
    @FXML
    private Label type ;
@FXML
    private Label titre ;
@FXML
    private Label id ;

@FXML
    private ImageView img ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //String t= MesTicketsController.te; 
        //txt.setText(t);
       id.setText(MesTicketsController.id.toString());
       String x = MesTicketsController.titre ; 
       String y=MesTicketsController.type ;
      //System.out.println(MesTicketsController.titre);
       //type.setText(MesTicketsController.type);
       type.setText(y);
      titre.setText(x);
        
        // TODO
    }    
    
}
