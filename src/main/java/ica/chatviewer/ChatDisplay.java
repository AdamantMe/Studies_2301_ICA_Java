package ica.chatviewer;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

/**
 * The ChatDisplay class for the ChatViewer application.
 * This class is responsible for displaying the chat messages on the UI.
 */
public class ChatDisplay {
    private GridPane chatGrid;

    /**
     * Constructs a new ChatDisplay object with the provided GridPane.
     *
     * @param chatGrid The GridPane where chat messages will be displayed.
     */
    public ChatDisplay(GridPane chatGrid) {
        this.chatGrid = chatGrid;
    }

    /**
     * This method generates a TextFlow for each message and adds it to the grid.
     * It loops through each ChatMessage in the provided list, creating TextFlows and adding them to the grid.
     *
     * @param MessagesHistory The list of ChatMessage objects to display.
     */
    public void GenerateText(List<ChatMessage> MessagesHistory) {

        int rowCounter = 0;

        for (ChatMessage msg : MessagesHistory) {
            TextFlow flow = new TextFlow();

            Text time = new Text("[" + msg.Time + "] ");

            String nameToShow = msg.Name.equals(msg.PreviousMessageAuthor) ? "... : " : msg.Name + ": ";
            Text name = new Text(nameToShow);
            name.setStyle("-fx-fill: blue");

            flow.getChildren().addAll(time, name);

            ConfigureMessageText(flow, msg.Message);

            chatGrid.addRow(rowCounter, flow);
            rowCounter++;
        }
    }

    /**
     * This method formats the chat message text for display in the TextFlow.
     * It replaces emojis with corresponding images and adds the resulting Text or ImageView to the TextFlow.
     *
     * @param flow The TextFlow that the formatted text is added to.
     * @param text The text content of the chat message.
     */
    private void ConfigureMessageText(TextFlow flow, String text) {
        if (text != null && !text.isEmpty()) {
            String[] singleWords = text.split(" ");
            for (String word : singleWords) {
                if (word.equals(":)") || word.equals(":(")) {
                    flow.getChildren().add(HelperFunctions.GetEmoji(word.equals(":)")));
                } else {
                    Text txt = new Text(word + " ");
                    txt.setStyle("-fx-font-weight: bold");
                    flow.getChildren().add(txt);
                }
            }
        }
    }
}