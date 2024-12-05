package com.beginner.steamlabs;

import com.beginner.steamlabs.mediaLibrary.Media;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.beginner.steamlabs.mediaLibrary.Library;

import java.util.ArrayList;

public class StreamLibrary extends Application {
    // Set up constants for the application
    private static final GridPane gridpane = new GridPane();
    private static final GridPane gridpaneDetails = new GridPane();
    private static final GridPane gridpaneTitle = new GridPane();
    private static final BorderPane borderPane = new BorderPane();
    private static final FlowPane flowPane = new FlowPane();
    private static final FlowPane flowPaneDetails = new FlowPane();
    private static final ListView<Media> listView = new ListView<>();
    private static final ArrayList<Media> mediaList = Library.getLibraryCollection();
    private static final ImageView mediaImage = new ImageView();
    private static Text mediaTitle = null;
    private static Text mediaDescription = null;

    static {
        mediaImage.setFitWidth(540);
        mediaImage.setFitHeight(480);
    }

    @Override
    public void start(Stage stage) {
        Media firstMedia = mediaList.get(0);
        Text title = NewText("Steam Labs", 35, FontWeight.BOLD, FontPosture.REGULAR, Color.BLACK);
        Text subTitle = NewText("Movies & Games Library", 28, FontWeight.LIGHT, FontPosture.ITALIC, Color.DARKGRAY);
        mediaTitle = NewText(firstMedia.getMediaTitle(), 20, FontWeight.BOLD, FontPosture.REGULAR, Color.BLACK);
        mediaDescription = NewText(firstMedia.getDescription(), 18, FontWeight.NORMAL, FontPosture.REGULAR, Color.BLACK);
        listView.setItems(FXCollections.observableList(mediaList));
        getSelectionList(listView);
        listView.getSelectionModel().select(0);
        listView.setPrefHeight(350);
        // Adjust title and subtitle
        gridpaneTitle.add(title, 0, 0);
        gridpaneTitle.add(subTitle, 0, 1);

        // Set up the flow pane
        flowPane.setPadding(new Insets(0, 12.5, 8, 0));
        flowPane.setVgap(2.5);
        flowPane.getChildren().add(gridpaneTitle);
        // Flow pane details
        flowPaneDetails.getChildren().add(gridpaneDetails);
        // Media Details
        gridpaneDetails.add(mediaTitle, 0, 0);
        gridpaneDetails.add(mediaDescription, 0, 1);
        // Set up the border
        borderPane.setPadding(new Insets(11.5, 12.5, 8, 10));
        borderPane.setTop(flowPane);
        borderPane.setLeft(mediaImage);
        borderPane.setRight(listView);

        // Set up the stage
        Scene scene = new Scene(borderPane, 1020, 780);
        stage.setTitle("Stream Library");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Text NewText(String name, int size, FontWeight weight, FontPosture posture, Paint paint) {
         Text newText = new Text();
         newText.setText(name);
         newText.setFont(Font.font("Serif", weight, posture, size));
         newText.setFill(paint);
         return newText;
    }

    public static void getSelectionList(ListView<Media> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            updateMediaDisplay(newValue);
        });
    }

    public static void updateMediaDisplay(Media mediaMetadata) {
        // Create image path
        String imagePath = (mediaMetadata.getMediaType().equals("movie")
                ? "/library-resources/movies/" + mediaMetadata.getImages()[0] + ".jpeg"
                : "/library-resources/" + mediaMetadata.getImages()[0] + ".jpeg");
        Image image = new Image(imagePath);
        mediaImage.setImage(image);
        mediaTitle.setText(mediaMetadata.getMediaTitle());
        mediaDescription.setText(mediaMetadata.getDescription());
    }
}
