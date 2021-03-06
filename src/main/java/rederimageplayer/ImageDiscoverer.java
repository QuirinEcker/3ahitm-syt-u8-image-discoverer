package rederimageplayer;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageDiscoverer implements Runnable {
    private int width;
    private int height;
    private Image sourceImage;
    private WritableImage destinationImage;
    private PixelWriter pixelWriter;
    private PixelReader pixelReader;
    private ProgressBar progressBar;

    public ImageDiscoverer(Image sourceImage, ProgressBar progressBar) {
        this.sourceImage = sourceImage;
        this.width = (int) sourceImage.getWidth();
        this.height = (int) sourceImage.getHeight();
        this.destinationImage = new WritableImage(this.width, this.height);
        this.pixelWriter = destinationImage.getPixelWriter();
        this.pixelReader = destinationImage.getPixelReader();
        this.progressBar = progressBar;
        initImage();
    }

    private void initImage() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixelWriter.setColor(col, row, Color.CORNFLOWERBLUE);
            }
        }
    }

    public WritableImage getDestinationImage() {
        return destinationImage;
    }

    public void discover() {
        for (int row = 0; row < height; row++) {
            final int localRow = row;

            Platform.runLater(() -> {
                for (int col = 0; col < width; col++) {
                    Color pixel = pixelReader.getColor(col, localRow);
                    pixelWriter.setColor(col, localRow, Color.CORNFLOWERBLUE);
                }

                progressBar.setProgress(localRow / height);
            });

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void run() {
        this.discover();
    }
}
