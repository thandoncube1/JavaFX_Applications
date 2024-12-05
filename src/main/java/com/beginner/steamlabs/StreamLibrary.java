package com.beginner.steamlabs;

import com.beginner.steamlabs.mediaLibrary.Media;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.stage.Stage;

import com.beginner.steamlabs.mediaLibrary.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    private static final HBox hBoxSearch = new HBox();

    // Setup views for the program
    private static final ListView<Media> listView = new ListView<>();
    private static final ArrayList<Media> mediaList = Library.getLibraryCollection();
    private static final ImageView mediaImage = new ImageView();
    private static Text mediaTitle = null;
    private static Text mediaDescription = null;
    private static Text mediaType = null;
    private static Text mediaCondition = null;
    private static final ImageView mediaContent = new ImageView();
    private static Text mediaGenre = null;
    private static Label mediaLabelCondition = null;
    // Setup Constant Sizes
    private static final double VIEW_WIDTH = 720;
    private static final double VIEW_HEIGHT = 460;
    private static final double LISTVIEW_HEIGHT = 460;
    private static final double SCENE_WIDTH = 1080;
    private static final double SCENE_HEIGHT = 820;
    private static final int H1_SIZE = 35;
    private static final int TITLE_SIZE = 24;
    private static final int TEXT_SIZE = 18;
    // Setup font family
    private static final String FONT_FAMILY = "Open Sans, sans-serif";

    static {
        mediaImage.setFitWidth(VIEW_WIDTH);
        mediaImage.setFitHeight(VIEW_HEIGHT);
        mediaContent.setFitWidth(180);
        mediaContent.setFitHeight(250);
    }

    @Override
    public void start(Stage stage) {
        Media firstMedia = mediaList.get(0);
        Text title = NewText("Stream Library", H1_SIZE, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, Color.BLACK);
        Text subTitle = NewText("Movies & Games Library", TITLE_SIZE, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, Color.DARKGRAY);
        mediaTitle = NewText(firstMedia.getMediaTitle(), TITLE_SIZE, FontWeight.BOLD, FontPosture.REGULAR, Color.BLACK);
        mediaDescription = NewText(firstMedia.getDescription(), TEXT_SIZE, FontWeight.NORMAL, FontPosture.REGULAR, Color.BLACK);
        mediaCondition = (firstMedia instanceof Movie)
                ? NewText(((Movie) firstMedia).getDuration(), TEXT_SIZE, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, Color.GRAY)
                : NewText(((Game) firstMedia).getPlatform(), TEXT_SIZE, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, Color.GRAY);
        mediaType = NewText(firstMedia.getMediaType(), TEXT_SIZE, FontWeight.NORMAL, FontPosture.REGULAR, Color.BLACK);
        StringBuilder genre = new StringBuilder();
        Arrays.stream(firstMedia.getGenres()).forEach(item -> genre.append(item.getTitle()).append(" ").append(" "));
        mediaGenre = NewText(genre.toString(), TEXT_SIZE, FontWeight.NORMAL, FontPosture.REGULAR, Color.BLACK);
        mediaLabelCondition = (firstMedia instanceof Movie)
                ? NewLabel("Duration ", TEXT_SIZE, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, Color.GRAY)
                : NewLabel("Platform ", TEXT_SIZE, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, Color.GRAY);
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

        // Set up the search button and textfield
        Button search = new Button("Search");
        TextField textInput = NewTextField(search);
        Text error = new Text("No results found.");

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                error.setVisible(false);
                ArrayList<Media> mediaList = Library.getLibraryCollection();
                for (Media media: mediaList) {
                    int index = mediaList.indexOf(media);
                    if (media.getMediaTitle().toLowerCase().contains(textInput.getText().toLowerCase())) {
                        listView.getSelectionModel().select(index);
                        return;
                    }
                }
                error.setVisible(true);
            }
        });

        error.setVisible(false);
        error.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, FontPosture.REGULAR, 18));
        error.setFill(Color.RED);
        // Set up the search and textfield
        textInput.setMinWidth(350);
        textInput.setMinHeight(30);
        textInput.setStyle("-fx-border-radius: 5;");
        hBoxSearch.setSpacing(10);
        hBoxSearch.setPadding(new Insets(10));
        hBoxSearch.getChildren().addAll(search, textInput, error);
        // Adding image to the StackPane
        imagePane.getChildren().add(mediaImage);
        imagePane.setMaxWidth(VIEW_WIDTH);
        imagePane.setMaxHeight(VIEW_HEIGHT);
        imagePane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        // Set up the flow pane
        flowPane.setPadding(new Insets(0, 12.5, 8, 0));
        flowPane.setVgap(10);
        flowPane.setHgap(100);
        flowPane.getChildren().addAll(gridpaneTitle, hBoxSearch);
        // Flow pane details
        flowPaneDetails.getChildren().add(gridpaneDetails);
        hBox.setSpacing(20);
        hBox.setMinWidth(VIEW_WIDTH+30);
        hBox.setMaxWidth(VIEW_WIDTH+30);
        hBox.setMinHeight(250);
        hBox.setMaxHeight(250);
        hBox.getChildren().addAll(flowPaneDetails, mediaContent);
        hBox.setStyle("-fx-background-color: #fafafb;");
        // Setup text flow
        TextFlow textFlow = new TextFlow(mediaDescription);
        textFlow.setMaxWidth(VIEW_WIDTH-80);
        // Media Details
        gridpaneDetails.add(NewLabel("Title ", TEXT_SIZE, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, Color.GRAY), 0, 0);
        gridpaneDetails.add(mediaLabelCondition, 0, 2);
        gridpaneDetails.add(mediaCondition, 1, 2);
        gridpaneDetails.add(NewLabel("Genre ", TEXT_SIZE, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, Color.GRAY), 0, 3);
        gridpaneDetails.add(mediaGenre, 1, 3);
        gridpaneDetails.add(mediaTitle, 1, 0);
        gridpaneDetails.add(textFlow, 1, 1);
        gridpaneDetails.setHgap(20);
        gridpaneDetails.setVgap(12);

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
         newText.setFont(Font.font(FONT_FAMILY, weight, posture, size));
         newText.setFill(paint);
         return newText;
    }

    public static Label NewLabel(String name, int size, FontWeight weight, FontPosture posture, Paint paint) {
        Label newText = new Label();
        newText.setText(name);
        newText.setFont(Font.font(FONT_FAMILY, weight, posture, size));
        newText.setTextFill(paint);
        return newText;
    }

    public static TextField NewTextField(Button search) {
        TextField textField = new TextField();
        textField.setAlignment(Pos.TOP_LEFT);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    search.fire();
                }
            }
        });
        return textField;
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
        String posterPath = (mediaMetadata.getMediaType().equals("movie")
                ? "/library-resources/movies/" + mediaMetadata.getImages()[0] + ".jpeg"
                : "/library-resources/" + mediaMetadata.getImages()[1] + ".jpeg");
        Image image = new Image(imagePath);
        Image poster = new Image(posterPath);
        scaleImageFactor(image);
        mediaImage.setImage(image);
        mediaContent.setImage(poster);
        mediaTitle.setText(mediaMetadata.getMediaTitle());
        mediaDescription.setText(mediaMetadata.getDescription());
        mediaType.setText(mediaMetadata.getMediaType());
        StringBuilder genre = new StringBuilder();
        Arrays.stream(mediaMetadata.getGenres()).forEach(item -> genre.append(item.getTitle()).append(" ").append(" "));
        mediaGenre.setText(genre.toString());
        mediaGenre.setFill(Color.GRAY);

        if (mediaMetadata instanceof Game) {
            mediaCondition.setText(((Game) mediaMetadata).getPlatform());
            mediaLabelCondition.setText("Platform");
        };
        if (mediaMetadata instanceof Movie) {
            mediaCondition.setText(((Movie) mediaMetadata).getDuration());
            mediaLabelCondition.setText("Duration");
        }
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
            private final HBox details = new HBox();
            private final ImageView posterImage = new ImageView();
            private final Text itemText = new Text();
            private final Text itemType = new Text();
            private final Text itemRating = new Text();


            {
                details.getChildren().addAll(itemType, itemRating);
                details.setSpacing(80);
                vBox.getChildren().addAll(itemText, details);
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
                    posterImage.setFitWidth(40);
                    posterImage.setFitHeight(60);
                    itemText.setText(item.getMediaTitle());
                    itemType.setText(item.getMediaType());
                    itemRating.setText(!item.getAgeRating().isEmpty() ? item.getAgeRating().toLowerCase() : item.getAgeRating());
                    itemType.setFill(Color.GRAY);
                    itemRating.setFill(Color.GRAY);

                    // Selection style
                    if (isSelected()) {
                        hBox.setStyle("-fx-background-color: transparent;");
                        itemText.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, 14));
                    } else {
                        hBox.setStyle("-fx-background-color: transparent;");
                        itemText.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, FontPosture.REGULAR, 14));
                    }

                    setGraphic(hBox);
                }
            }
        });
    }
}
