/*
 * JoinMessage - Handles players logging into the lobby of a server
 * 
 *
 * ARRAY VALUES
 * index[0] = playerId
 * index[1] = password
 */
package orb.p.network.messages;

/**
 *
 * @author Blainezor
 */
public class JoinMessage extends Message {

    private String playerId;
    private String password;
    private static int VARS = 2;
    private JoinMessage() {
    }

    public JoinMessage(String playerId, String password) {
        this.playerId = playerId;
        this.password = password;
    }
    
    /**
     * @param message
     * @return 
     */
    public static JoinMessage fromString(String message) {
        JoinMessage newMessage = new JoinMessage();
        String[] values = message.split(DELIMITER);

        if (values.length >= VARS) {
            newMessage.setPlayerId(values[0]);
            newMessage.setPassword(values[1]);
        }
        return newMessage;
    }

    @Override
    /**
     *  Generates a String to represent the data stored in this class
     */
    public String toString() {
        String returnString = "";

        returnString += playerId + DELIMITER + password;
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
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
