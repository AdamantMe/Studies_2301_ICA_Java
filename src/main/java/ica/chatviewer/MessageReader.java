package ica.chatviewer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The MessageReader class is responsible for reading chat messages from a file.
 */
public class MessageReader {

    /**
     * Reads a chat history file and returns a list of ChatMessage objects.
     *
     * @param toRead the chat history file to read.
     * @return a list of ChatMessage objects representing the chat messages in the file.
     */
    public List<ChatMessage> ReadMessageFile(File toRead) {
        List<ChatMessage> messagesHistory = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(toRead);
            ChatMessage msg = new ChatMessage();

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                    if (line.startsWith("Message:")) {
                        configureChatMessage(msg, line);
                        messagesHistory.add(msg);
                        msg = new ChatMessage();
                        if (!messagesHistory.isEmpty()) {
                            msg.PreviousMessageAuthor = messagesHistory.get(messagesHistory.size() - 1).Name;
                        }
                    } else {
                        configureChatMessage(msg, line);
                    }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return messagesHistory;
    }

    /**
     * Configures a ChatMessage object based on a line from the chat history file.
     *
     * @param msg  the ChatMessage object to configure.
     * @param line the line from the chat history file.
     */
    private void configureChatMessage(ChatMessage msg, String line) {
        try {
            if (line.startsWith("Time:")) {
                msg.Time = line.substring(5);
            } else if (line.startsWith("Name:")) {
                msg.Name = line.substring(5);
            } else if (line.startsWith("Message:") && line.length() > 8) {
                msg.Message = line.substring(8);
            } else {
                if (msg.Time == null && msg.Message == null && msg.Name == null)
                {}
                else{
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }
            }
        } catch (Exception e) {
            System.err.print("Error reading the text message");
            if (msg.Time != null && !msg.Time.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Error reading the message with timestamp of \""
                        + msg.Time + "\" ", ButtonType.OK).showAndWait();
                System.err.print("Error reading the message with timestamp of \"" + msg.Time + "\" ");
            }
            System.exit(-1);
        }
    }
}