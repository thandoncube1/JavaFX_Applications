package com.beginner.steamlabs.helloMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Media Library [Games and Movies]");
        stage.setScene(scene);
        stage.show();

        Stage newStage = new Stage();
        FXMLLoader newFxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        newStage.setScene(new Scene(newFxmlLoader.load(), 320, 240));
        newStage.setTitle("Application HomePage");

        HelloController helloController = fxmlLoader.getController();
        MainController mainController = newFxmlLoader.getController();
        helloController.setStageAndSetupListener(newStage);
        mainController.setStageAndSetupListeners(newStage);
    }

    public static void main(String[] args) {
        launch();
    }
}