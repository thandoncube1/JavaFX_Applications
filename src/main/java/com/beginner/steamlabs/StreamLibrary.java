package com.beginner.steamlabs;

import com.beginner.steamlabs.mediaLibrary.Media;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.beginner.steamlabs.mediaLibrary.Library;

import java.util.ArrayList;

public class StreamLibrary extends Application {
    // Set up constants for the application
    private static final GridPane gridpane = new GridPane();
    private static final ListView<Media> listView = new ListView<>();
    private static final ArrayList<Media> mediaList = Library.getLibraryCollection();

    @Override
    public void start(Stage stage) {
        // Set up the stage
        Scene scene = new Scene(gridpane, 1020, 780);
        scene.setFill(Color.web("#1e1f1f"));
        stage.setTitle("Stream Library");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
