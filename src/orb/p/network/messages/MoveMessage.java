/*
 * MoveMessage - Handles player movements over the network
 * Currently only handles X and Y, but more player information can be sent. .
 * 
 *
 * ARRAY VALUES
 * index[0] = playerId
 * index[1] = xLoc
 * index[2] = yLoc
 */
package orb.p.network.messages;

/**
 *
 * @author Blainezor
 */
public class MoveMessage extends Message {

    private String playerId;
    private int xLoc;
    private int yLoc;
    private static int VARS = 3;

    private MoveMessage() {
    }

    public MoveMessage(String playerId, int xLoc, int yLoc) {
        this.playerId = playerId;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    /**
     * @param message
     * @return
     */
    public static MoveMessage fromString(String message) {
        MoveMessage newMessage = new MoveMessage();
        String[] values = message.split(DELIMITER);

        if (values.length >= VARS) {
            newMessage.setPlayerId(values[0]);
            newMessage.setxLoc(Integer.parseInt(values[1]));
            newMessage.setyLoc(Integer.parseInt(values[2]));
        }
        return newMessage;
    }

    @Override
    /**
     * Generates a String to represent the data stored in this class
     */
    public String toString() {
        String returnString = "";

        returnString += playerId + DELIMITER + xLoc + DELIMITER + yLoc;
        return returnString;
    }

    //Getters and setters
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getxLoc() {
        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

}
