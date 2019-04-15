/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zliaa
 */
public class Pagination {
    private double xOffset = 0;
    private double yOffset = 0;

    public Pagination(String src)throws IOException {
        Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(src));
        
        stage.initStyle(StageStyle.UNDECORATED);
       
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
         //set mouse drag
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        

        Scene scene = new Scene(root);     
         stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
        
        
}
