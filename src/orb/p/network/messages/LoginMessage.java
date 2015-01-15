package orb.p.network;

import orb.p.network.messages.Message;

/**
 *
 * @author blainezor
 */
public class LoginMessage extends Message{ 

    private String playerId;
    
    public static final String TYPE = "Login";
    
    /**
     * Generate LoginMessage from String
     * @param message 
     */
    public LoginMessage(String message)
    {    
        String values[] = message.split(Message.DELIMITER);     
        playerId = values[1];
    }
   
    /**
     * Converts the object into a string to be sent over the network
     * @return 
     * 
     * 
     * Format:
     * 
     * messageType id
     *
     */
    @Override
    public String toString()
    {
        return TYPE + Message.DELIMITER + playerId;
    }
    
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
