package login;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Random;
import java.util.ArrayList;

public class Message {
    
    private static int $totalMessagesSent = 0;
    private static ArrayList<Message> $messages = new ArrayList<>();
    
    private String $messageID;
    private String $recipient;
    private String $messageText;
    private String $messageHash;
    private String $status;
    
    // Validation methods
    public boolean checkMessageID(String $id) {
        return $id != null && $id.length() == 10 && $id.matches("\\d{10}");
    }
    
    public boolean checkRecipientCell(String $cell) {
        // +27 followed by 9 digits
        return $cell != null && $cell.matches("^\\+27\\d{9}$");
    }
    
    public String checkMessageLength(String $msg) {
        if ($msg.length() <= 250) {
            return "Message ready to send.";
        } else {
            int $diff = $msg.length() - 250;
            return "Message exceeds 250 characters by " + $diff + ", please reduce size.";
        }
    }
    
    public String createMessageHash() {
        String[] $words = $messageText.split(" ");
        String $firstWord = $words[0];
        String $lastWord = $words[$words.length - 1];
        String $firstTwo = $messageID.substring(0, 2);
        $messageHash = ($firstTwo + ":" + $totalMessagesSent + ":" + $firstWord + $lastWord).toUpperCase();
        return $messageHash;
    }
    
    public String sendMessage() {
        String[] $options = {"Send Message", "Disregard Message", "Store Message"};
        int $choice = JOptionPane.showOptionDialog(null, 
                "Choose an option for this message:",
                "Send Message",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                $options,
                $options[0]);
        
        switch ($choice) {
            case 0:
                $status = "Message successfully sent.";
                $totalMessagesSent++;
                $messages.add(this);
                printMessage();
                break;
            case 1:
                $status = "Press 0 to delete message.";
                break;
            case 2:
                $status = "Message successfully stored.";
                storeMessage();
                break;
            default:
                $status = "No valid selection made.";
                break;
        }
        return $status;
    }
    
    public void printMessage() {
        JOptionPane.showMessageDialog(null, 
                "Message ID: " + $messageID + "\n" +
                "Message Hash: " + $messageHash + "\n" +
                "Recipient: " + $recipient + "\n" +
                "Message: " + $messageText,
                "Message Sent",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static int returnTotalMessages() {
        return $totalMessagesSent;
    }
    
    // Stores in json
    public void storeMessage() {
        JSONObject $messageObj = new JSONObject();
        $messageObj.put("MessageID", $messageID);
        $messageObj.put("Recipient", $recipient);
        $messageObj.put("Message", $messageText);
        $messageObj.put("MessageHash", $messageHash);
        $messageObj.put("Status", $status);
        
        JSONArray $messageList = new JSONArray();
        $messageList.add($messageObj);
        
        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write($messageList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // setup
    public Message(String $recipient, String $msg) {
        Random $rand = new Random();
        this.$messageID = String.format("%010d", $rand.nextInt(1000000000));
        this.$recipient = $recipient;
        this.$messageText = $msg;
        createMessageHash();
    }
}
