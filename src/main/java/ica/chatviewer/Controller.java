package ica.chatviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    @FXML
    private Text txtPath;
    @FXML
    private GridPane chatGrid;
    @FXML
    private ScrollPane scrollPane;

    private List<ChatMessage> MessagesHistory;

    @FXML
    protected void onChooseFileClick() {
        scrollPane.setFitToWidth(true);
        scrollPane.setVisible(true);
        chatGrid.getChildren().clear();
        MessagesHistory = new ArrayList<>();

        File fileRead = HelperFunctions.OpenFile();

        if (fileRead != null) {
            ReadMessageFile(fileRead);
            GenerateText();
        }
    }

    private void ReadMessageFile(File toRead) {
        try {
            Scanner myReader = new Scanner(toRead);
            ChatMessage msg = new ChatMessage();
            txtPath.setText(toRead.toString());

            while (myReader.hasNextLine()) {

                String line = myReader.nextLine();

                if (!line.isEmpty()) {
                    if (line.startsWith("Message:")) {
                        ConfigureChatMessage(msg, line);
                        MessagesHistory.add(msg);
                        msg = new ChatMessage();
                        msg.PreviousMessageAuthor = MessagesHistory.get(MessagesHistory.size() - 1).Name;
                    } else {
                        ConfigureChatMessage(msg, line);
                    }
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in reading the file");
            e.printStackTrace();
        }
    }

    public void ConfigureChatMessage(ChatMessage msg, String line) {
        try {
            if (line.startsWith("Time:")) {
                msg.Time = line.substring(5);
            } else if (line.startsWith("Name:")) {
                msg.Name = line.substring(5);
            } else if (line.startsWith("Message:") && line.length() > 8) {
                msg.Message = line.substring(8);
            } else {
                throw new Exception("Error reading the file");
            }
        } catch (Exception e) {
            System.err.print("Error reading the text message");
            if (!msg.Time.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Error reading the message with timestamp of \""
                        + msg.Time + "\" ", ButtonType.OK).showAndWait();
                System.err.print("Error reading the message with timestamp of \"" + msg.Time + "\" ");
            }
            System.exit(-1);
        }
    }

    private void GenerateText() {

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