/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rederimageplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 *
 * @author robreder
 */
public class RederImagePlayer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        final URL resource = getClass().getResource("FXMLDocument.fxml");
        System.out.println(resource.toString());
        Parent root = FXMLLoader.load(resource);
        
        Scene scene = new Scene(root);
        stage.setTitle("Image Player SYT");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
