module ica.chatviewer {
    requires javafx.controls;
    requires javafx.fxml;


    opens ica.chatviewer to javafx.fxml;
    exports ica.chatviewer;
}