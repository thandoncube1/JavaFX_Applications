package com.beginner.steamlabs.helloMain;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;

    private Stage myStage;

    @FXML
    protected void onHelloButtonClick() {
        if (!welcomeText.getText().equalsIgnoreCase("Welcome to JavaFX Application!")) {
            welcomeText.setText("Welcome to JavaFX Application!");
        } else {
            welcomeText.setText("Thank you, there is a new call.");
        }
    }

    @FXML
    protected void onContinueButtonClick() {
        setStageAndSetupListener(myStage);
    }

    public void setStageAndSetupListener(Stage stage) {
        myStage = stage;
        myStage.show();
    }
}