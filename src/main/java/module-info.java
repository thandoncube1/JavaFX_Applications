module com.beginner.steamlabs {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.beginner.steamlabs to javafx.fxml;
    exports com.beginner.steamlabs;
    exports com.beginner.steamlabs.helloMain;
    opens com.beginner.steamlabs.helloMain to javafx.fxml;
}