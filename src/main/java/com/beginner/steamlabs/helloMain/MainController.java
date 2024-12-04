package com.beginner.steamlabs.helloMain;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {
    @FXML
    public Label mainViewText;

    private Stage myStage;

    @FXML
    public void onCloseButtonClick() {
        setStageAndSetupListeners(myStage);
    }

    public void setStageAndSetupListeners(Stage stage) {
        myStage = stage;
        myStage.close();
    };
}
