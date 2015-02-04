/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    private MoveMessage() {
    }

    public MoveMessage(String playerId, int xLoc, int yLoc) {
        this.playerId = playerId;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    public static MoveMessage fromString(String message) {
        MoveMessage newMessage = new MoveMessage();
        String[] values = message.split(DELIMITER);

        if (values.length >= 3) {
            newMessage.setPlayerId(values[0]);

            newMessage.setxLoc(Integer.parseInt(values[1]));
            newMessage.setyLoc(Integer.parseInt(values[1]));
        }
        return newMessage;
    }

    @Override
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
