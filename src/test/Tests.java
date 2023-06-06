import ica.chatviewer.ChatMessage;
import ica.chatviewer.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Tests{

    @Test
    void ConfigureChatMessageTest(){
        ChatMessage msg = new ChatMessage();
        Controller controller = new Controller();

        controller.ConfigureChatMessage(msg, "Time:12:34:56");
        assertEquals(msg.Time, "12:34:56", "Error configuring message's time.");

        controller.ConfigureChatMessage(msg, "Name:Adam");
        assertEquals(msg.Name, "Adam", "Error configuring message's sender's name.");

        controller.ConfigureChatMessage(msg, "Message:Hello Bob");
        assertEquals(msg.Message, "Hello Bob", "Error configuring message's text content.");
    }
}