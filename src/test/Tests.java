import ica.chatviewer.ChatMessage;
import ica.chatviewer.Controller;
import ica.chatviewer.MessageReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Tests{
    @Test
    void ReadMessageFileTest(){
        MessageReader messageReader = new MessageReader();

        String testMessageFilePath = "src/test/test_msg_file.msg";
        List<ChatMessage> messagesHistory = messageReader.ReadMessageFile(new File(testMessageFilePath));

        assertEquals(3, messagesHistory.size(), "Error reading messages from the file.");

        ChatMessage msg1 = messagesHistory.get(0);
        assertEquals("12:00:00", msg1.Time, "Error reading time of message 1.");
        assertEquals("TestUser1", msg1.Name, "Error reading name of message 1.");
        assertEquals("This is a test message :)", msg1.Message, "Error reading content of message 1.");

        ChatMessage msg2 = messagesHistory.get(1);
        assertEquals("12:00:01", msg2.Time, "Error reading time of message 2.");
        assertEquals("TestUser2", msg2.Name, "Error reading name of message 2.");
        assertEquals("This is another test message :(", msg2.Message, "Error reading content of message 2.");

        ChatMessage msg3 = messagesHistory.get(2);
        assertEquals("12:00:02", msg3.Time, "Error reading time of message 3.");
        assertEquals("TestUser1", msg3.Name, "Error reading name of message 3.");
        assertEquals("Reply to the second message", msg3.Message, "Error reading content of message 3.");
    }
}