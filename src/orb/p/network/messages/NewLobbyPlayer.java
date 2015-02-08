/*
 * NewLobbyPlayer - When a player joins a lobby, send a message to everyone
 * 
 *
 * ARRAY VALUES
 * index[0] = playerId
 * index[1] = position
 */
package orb.p.network.messages;

/**
 *
 * @author Blainezor
 */
public class NewLobbyPlayer extends Message {

    private String playerId;
    private int position;
    private static int VARS = 2;
    private NewLobbyPlayer() {
    }

    public NewLobbyPlayer(String playerId, int position) {
        this.playerId = playerId;
        this.position = position;
    }
    
    /**
     * @param message
     * @return 
     */
    public static NewLobbyPlayer fromString(String message) {
        NewLobbyPlayer newMessage = new NewLobbyPlayer();
        String[] values = message.split(DELIMITER);

        if (values.length >= VARS) {
            newMessage.setPlayerId(values[0]);
            newMessage.setPosition(Integer.parseInt(values[1]));
        }
        return newMessage;
    }

    @Override
    /**
     *  Generates a String to represent the data stored in this class
     */
    public String toString() {
        String returnString = "";

        returnString += playerId + DELIMITER + position;
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
    public int getPosition() {
        return position;
    }
    /**
     * @param position the password to set
     */
    public void setPosition(int position) {
        this.position = position;
    }
    
    
    
}
