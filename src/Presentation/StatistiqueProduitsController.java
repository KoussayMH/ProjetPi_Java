/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ProduitServices;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class StatistiqueProduitsController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private BarChart<?, ?> ProduitsChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
       @FXML
    private JFXButton quitter;

    @FXML
    void QuitterAction(ActionEvent event) {
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        XYChart.Series set1= new XYChart.Series<>();
        ProduitServices p = new ProduitServices(); 
        set1.getData().add(new XYChart.Data("Tables",p.nombreParCat("Table") )) ;         
        set1.getData().add(new XYChart.Data("Chaises",p.nombreParCat("Chaise") )) ;          
        set1.getData().add(new XYChart.Data("Décorations",p.nombreParCat("Decoration") )) ;         
        set1.getData().add(new XYChart.Data("Linges",p.nombreParCat("Linge") )) ;         
        set1.getData().add(new XYChart.Data("Tentes",p.nombreParCat("Tente") )) ;         
        set1.getData().add(new XYChart.Data("Vaisselle",p.nombreParCat("Vaisselle") )) ;         
        ProduitsChart.getData().setAll(set1); 
    }    
    
}
