/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rederimageplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

/**
 *
 * @author robreder
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label stateLabel;
    @FXML
    private FlowPane buttonBar;

    private ImageDiscoverer imageDiscoverer;
    private Thread discoverThread;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onStartButtonClicked(ActionEvent event) {
        stateLabel.setText("Start discovering ...");
        //imageDiscoverer.discover();
        discoverThread = new Thread(imageDiscoverer);
        discoverThread.start();

    }

    @FXML
    private void onLoadButtonClicked(ActionEvent event) {
        //stateLabel.setText("Load Button clicked");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.setInitialDirectory(new File("."));
        FileChooser.ExtensionFilter extensionFilterJPG
                = new FileChooser.ExtensionFilter("JPG files *.jpg", "*.jpg", "JPG");
        FileChooser.ExtensionFilter extensionFilterPNG
                = new FileChooser.ExtensionFilter("PNG files *.png", "*.PNG", "*.png");
        FileChooser.ExtensionFilter extensionFilterAll
                = new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.PNG", "*.JPG");

        fileChooser.getExtensionFilters().addAll(
                extensionFilterAll,
                extensionFilterJPG,
                extensionFilterPNG);

        File imageFile = fileChooser.showOpenDialog(null);
        
        if (imageFile != null) {
            stateLabel.setText(imageFile.getName());
            Image img = new Image(imageFile.toURI().toString());
            setImage(img);
        }
    }

    @FXML
    private void onPauseButtonClicked() {
        discoverThread.
    }

    private void setImage(Image image) {
        imageDiscoverer = new ImageDiscoverer(image, progressBar);
        imageView.setImage(imageDiscoverer.getDestinationImage());
        

        imageView.setImage(image);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        Scene scene = imageView.getScene();

        BorderPane borderPane = (BorderPane) imageView.getParent();
        borderPane.setPrefHeight(Math.min(height + buttonBar.getPrefHeight(), 800.0));
        borderPane.setPrefWidth(Math.max(width, 300.0));
        scene.getWindow().sizeToScene();
    }

}
