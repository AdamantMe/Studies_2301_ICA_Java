package ica.chatviewer;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class for the ChatViewer application.
 * This class is responsible for handling user input and updating the UI.
 */
public class Controller {
    @FXML
    private Text txtPath;
    @FXML
    private GridPane chatGrid;
    @FXML
    private ScrollPane scrollPane;

    private List<ChatMessage> MessagesHistory;

    /**
     * Handles the event when the 'Choose File' button is clicked.
     * Reads and parses the selected chat history file and displays the messages on the UI.
     */
    @FXML
    protected void onChooseFileClick() {
        scrollPane.setFitToWidth(true);
        scrollPane.setVisible(true);
        chatGrid.getChildren().clear();
        MessagesHistory = new ArrayList<>();

        File fileRead = HelperFunctions.OpenFile();

        if (fileRead != null) {
            txtPath.setText(fileRead.getAbsolutePath());  // Update the file path
            MessageReader messageReader = new MessageReader();
            MessagesHistory = messageReader.ReadMessageFile(fileRead);
            ChatDisplay chatDisplay = new ChatDisplay(chatGrid);
            chatDisplay.GenerateText(MessagesHistory);
        }
    }
}
