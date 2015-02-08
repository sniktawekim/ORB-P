/*
 * JoinMessage - Handles players logging into the lobby of a server
 * 
 *
 * ARRAY VALUES
 * index[0] = playerId
 * index[1] = message
 */
package orb.p.network.messages;

/**
 *
 * @author Blainezor
 */
public class LobbyChatMessage extends Message {

    private String playerId;
    private String message;
    private static int VARS = 2;
    private LobbyChatMessage() {
    }

    public LobbyChatMessage(String playerId, String message) {
        this.playerId = playerId;
        this.message = message;
    }
    
    /**
     * @param message
     * @return 
     */
    public static LobbyChatMessage fromString(String message) {
        LobbyChatMessage newMessage = new LobbyChatMessage();
        String[] values = message.split(DELIMITER);

        if (values.length >= VARS) {
            newMessage.setPlayerId(values[0]);
            newMessage.setMessage(values[1]);
        }
        return newMessage;
    }

    @Override
    /**
     *  Generates a String to represent the data stored in this class
     */
    public String toString() {
        String returnString = "";

        returnString += playerId + DELIMITER + message;
        return returnString;
    }

    //Getters and setters
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the password
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the password to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
