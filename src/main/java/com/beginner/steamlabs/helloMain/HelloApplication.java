package com.beginner.steamlabs.helloMain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HelloController helloController = new HelloController();
        VBox vb = new VBox();
        vb.setSpacing(12);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(20));
        Label label = new Label();
        label.setText("Welcome to the Java FX World.");
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Button button1 = new Button();
        Button button2 = new Button();
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (((!label.getText().equalsIgnoreCase("Welcome to the Java FX World.")))) {
                    label.setText("Welcome to the Java FX World.");
                } else label.setText("Thank you for the call");
            }
        });
        button1.setText("Hello!");
        button2.setText("Next Stage!");
        vb.getChildren().addAll(label, button1, button2);
        Scene scene = new Scene(vb, 320, 240);
        stage.setTitle("Media Library [Games and Movies]");
        stage.setScene(scene);
        stage.show();

        VBox vb2 = new VBox();
        Button back = new Button();
        back.setText("Back To Main");
        vb2.setAlignment(Pos.CENTER);
        vb2.setPadding(new Insets(20));
        Label label2 = new Label();
        label2.setText("New Scene - New Stage");
        label2.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20));
        vb2.getChildren().addAll(label2, back);
        Stage newStage = new Stage();
        newStage.setTitle("Next Stage - Fun Life");
        Scene scene2 = new Scene(vb2, 320, 240);
        newStage.setScene(scene2);

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                newStage.show();
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newStage.close();
                stage.show();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}