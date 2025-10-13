package login;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class MessageNGTest {

    @Test
    public void testCheckMessageLengthSuccess() {
        Message $m = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        String $result = $m.checkMessageLength("Hi Mike, can you join us for dinner tonight");
        assertEquals($result, "Message ready to send.");
    }

    @Test
    public void testCheckMessageLengthFailure() {
        String $longMsg = "A".repeat(260);
        Message $m = new Message("+27718693002", $longMsg);
        String $result = $m.checkMessageLength($longMsg);
        assertTrue($result.contains("exceeds 250 characters"));
    }

    @Test
    public void testCheckRecipientCellValid() {
        Message $m = new Message("+27718693002", "Hello");
        assertTrue($m.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        Message $m = new Message("08575975889", "Hello");
        assertFalse($m.checkRecipientCell("08575975889"));
    }

    @Test
    public void testCreateMessageHash() {
        Message $m = new Message("+27718693002", "Hi Mike tonight");
        String $hash = $m.createMessageHash();
        assertTrue($hash.matches("\\d{2}:\\d+:HITONIGHT"));
    }
}
