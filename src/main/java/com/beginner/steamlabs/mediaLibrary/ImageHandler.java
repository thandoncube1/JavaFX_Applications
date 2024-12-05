package com.beginner.steamlabs.mediaLibrary;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class ImageHandler {
    private static final double VIEW_WIDTH = 680;
    private static final double VIEW_HEIGHT = 640;

    public static ImageView createImageView(String imagePath) {
        // Load the image
        Image image = new Image(imagePath);

        // Create ImageView with fixed dimensions
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(VIEW_WIDTH);
        imageView.setFitHeight(VIEW_HEIGHT);

        // Preserve ratio and disable stretching
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        // Center the image within the ImageView bounds
        StackPane container = new StackPane(imageView);
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(VIEW_WIDTH, VIEW_HEIGHT);

        return imageView;
    }

    public static void updateImage(ImageView imageView, String newImagePath) {
        Image newImage = new Image(newImagePath);
        imageView.setImage(newImage);
    }

    // Optional method to calculate scaling factor while maintaining aspect ratio
    private static double calculateScaleFactor(Image image) {
        double widthRatio = VIEW_WIDTH / image.getWidth();
        double heightRatio = VIEW_HEIGHT / image.getHeight();

        // Use the smaller ratio to ensure the image fits within bounds
        return Math.min(widthRatio, heightRatio);
    }
}