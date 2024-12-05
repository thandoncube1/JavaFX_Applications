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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.beginner.steamlabs.mediaLibrary.*;

import java.util.ArrayList;
import java.util.Objects;

public class StreamLibrary extends Application {
    // Set up pane constants for the application
    private static final GridPane gridpane = new GridPane();
    private static final GridPane gridpaneDetails = new GridPane();
    private static final GridPane gridpaneTitle = new GridPane();
    private static final StackPane imagePane = new StackPane();
    private static final BorderPane borderPane = new BorderPane();
    private static final FlowPane flowPane = new FlowPane();
    private static final FlowPane flowPaneDetails = new FlowPane();
    private static final HBox hBox = new HBox();
    // Setup views for the program
    private static final ListView<Media> listView = new ListView<>();
    private static final ArrayList<Media> mediaList = Library.getLibraryCollection();
    private static final ImageView mediaImage = new ImageView();
    private static Text mediaTitle = null;
    private static Text mediaDescription = null;
    // Setup Constant Sizes
    private static final double VIEW_WIDTH = 720;
    private static final double VIEW_HEIGHT = 430;
    private static final double LISTVIEW_HEIGHT = 430;
    private static final double SCENE_WIDTH = 1080;
    private static final double SCENE_HEIGHT = 800;
    private static final int H1_SIZE = 35;
    private static final int TITLE_SIZE = 20;
    private static final int SUB_TITLE_SIZE = 28;
    private static final int TEXT_SIZE = 18;

    static {
        mediaImage.setFitWidth(VIEW_WIDTH);
        mediaImage.setFitHeight(VIEW_HEIGHT);
    }

    @Override
    public void start(Stage stage) {
        Media firstMedia = mediaList.get(0);
        Text title = NewText("Stream Library", H1_SIZE, FontWeight.BOLD, FontPosture.REGULAR, Color.BLACK);
        Text subTitle = NewText("Movies & Games Library", SUB_TITLE_SIZE, FontWeight.LIGHT, FontPosture.ITALIC, Color.DARKGRAY);
        mediaTitle = NewText(firstMedia.getMediaTitle(), TITLE_SIZE, FontWeight.BOLD, FontPosture.REGULAR, Color.BLACK);
        mediaDescription = NewText(firstMedia.getDescription(), TEXT_SIZE, FontWeight.NORMAL, FontPosture.REGULAR, Color.BLACK);
        listView.setItems(FXCollections.observableList(mediaList));
        getSelectionList(listView); // Initialize the list View
        listViewCellFactory(listView); // Create a cell for each list
        listView.setStyle("-fx-cell-size: 24px;");
        listView.getSelectionModel().select(0);
        listView.setPrefWidth(320);
        listView.setPrefHeight(LISTVIEW_HEIGHT);
        listView.setMinHeight(LISTVIEW_HEIGHT);
        listView.setMaxHeight(LISTVIEW_HEIGHT);
        // Adjust title and subtitle
        gridpaneTitle.add(title, 0, 0);
        gridpaneTitle.add(subTitle, 0, 1);

        // Adding image to the StackPane
        imagePane.getChildren().add(mediaImage);
        imagePane.setMaxWidth(VIEW_WIDTH);
        imagePane.setMaxHeight(VIEW_HEIGHT);
        imagePane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        // Set up the flow pane
        flowPane.setPadding(new Insets(0, 12.5, 8, 0));
        flowPane.setVgap(2.5);
        flowPane.getChildren().add(gridpaneTitle);
        // Flow pane details
        flowPaneDetails.getChildren().add(gridpaneDetails);
        hBox.setSpacing(10);
        hBox.getChildren().add(flowPaneDetails);
        // Media Details
        gridpaneDetails.add(NewLabel("Title:", TITLE_SIZE, FontWeight.NORMAL, FontPosture.REGULAR, Color.GRAY), 0, 0);
        gridpaneDetails.add(NewLabel("Description:", TITLE_SIZE, FontWeight.NORMAL, FontPosture.REGULAR, Color.GRAY), 0, 1);
        gridpaneDetails.add(mediaTitle, 1, 0);

        // Set up the border
        borderPane.setPadding(new Insets(11.5, 12.5, 8, 10));
        borderPane.setTop(flowPane);
        borderPane.setLeft(imagePane);
        borderPane.setRight(listView);
        borderPane.setBottom(hBox);

        // Set up the stage
        Scene scene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT);
        // Get access to stylesheets
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheets/styles.css")).toExternalForm());
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

    public static Label NewLabel(String name, int size, FontWeight weight, FontPosture posture, Paint paint) {
        Label newText = new Label();
        newText.setText(name);
        newText.setFont(Font.font("Serif", weight, posture, size));
        newText.setTextFill(paint);
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
                ? "/library-resources/movies/" + mediaMetadata.getImages()[1] + ".jpeg"
                : "/library-resources/" + mediaMetadata.getImages()[0] + ".jpeg");
        Image image = new Image(imagePath);
        scaleImageFactor(image);
        mediaImage.setImage(image);
        mediaTitle.setText(mediaMetadata.getMediaTitle());
        mediaDescription.setText(mediaMetadata.getDescription());
    }

    public static void scaleImageFactor(Image image) {
        double scaleFactor = calculateScaleFactor(image);

        // Apply the scale factor
        mediaImage.setFitWidth(image.getWidth() * scaleFactor);
        mediaImage.setFitHeight(image.getHeight() * scaleFactor);

        // Ensure the image stays within bounds
        if (mediaImage.getFitWidth() > VIEW_WIDTH) {
            mediaImage.setFitWidth(VIEW_WIDTH);
        }
        if (mediaImage.getFitHeight() > VIEW_HEIGHT) {
            mediaImage.setFitHeight(VIEW_HEIGHT);
        }

        mediaImage.setPreserveRatio(true);
        mediaImage.setSmooth(true);
    }

    private static double calculateScaleFactor(Image image) {
        double widthRatio = VIEW_WIDTH / image.getWidth();
        double heightRatio = VIEW_HEIGHT / image.getHeight();

        // Use the smaller ratio to ensure the image fits within bounds
        return Math.min(widthRatio, heightRatio);
    }

    public static void listViewCellFactory(ListView<Media> listView) {
        listView.setCellFactory(lv -> new ListCell<>() {
            private final HBox hBox = new HBox();
            private final VBox vBox = new VBox();
            private final ImageView posterImage = new ImageView();
            private final Text itemText = new Text();
            private final Text itemType = new Text();


            {
                vBox.getChildren().addAll(itemText, itemType);
                vBox.setSpacing(5);
                hBox.setSpacing(10); // Space between number and text
                hBox.getChildren().addAll(posterImage, vBox);
                hBox.setPadding(new Insets(5, 0, 5, 0));

                // Hover effect
                hBox.setOnMouseEntered(e -> {
                    if (!isSelected()) {
                        hBox.setStyle("-fx-background-color: #dddedf;");
                    }
                });

                hBox.setOnMouseExited(e -> {
                    if (!isSelected()) {
                        hBox.setStyle("-fx-background-color: transparent;");
                    }
                });
            }

            protected void updateItem(Media item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String imagePath = (item.getMediaType().equals("movie")
                            ? "/library-resources/movies/" + item.getImages()[0] + ".jpeg"
                            : "/library-resources/" + item.getImages()[1] + ".jpeg");

                    Image image = new Image(imagePath);
                    posterImage.setImage(image);
                    posterImage.setFitWidth(15);
                    posterImage.setFitHeight(30);
                    itemText.setText(item.getMediaTitle());
                    itemType.setText(item.getMediaType());

                    // Selection style
                    if (isSelected()) {
                        hBox.setStyle("-fx-background-color: transparent;");
                        itemText.setFont(Font.font("serif", FontWeight.BOLD, FontPosture.REGULAR, 14));
                    } else {
                        hBox.setStyle("-fx-background-color: transparent;");
                        itemText.setFont(Font.font("serif", FontWeight.NORMAL, FontPosture.REGULAR, 14));
                    }

                    setGraphic(hBox);
                }
            }
        });
    }
}
