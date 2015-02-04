/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network;

import java.util.logging.Level;
import java.util.logging.Logger;
import orb.p.OnScreenObjects.Person;
import orb.p.network.messages.MoveMessage;
import orb.p.network.server.Server;
import orb.p.panels.GamePanel;

/**
 *
 * @author blainezor
 */
public class MoveController extends MessageController {

    private final GamePanel gamePanel;
    private boolean isServer = false;
    

    /**
     *
     * @param gamePanel
     * @param isServer
     */
    public MoveController(GamePanel gamePanel, boolean isServer) {
        super(gamePanel, isServer);
        this.gamePanel = gamePanel;
        this.isServer = isServer;
    }

    public synchronized void handleMessage(boolean playerConnected, Server server, String message) {
        MoveMessage moveMessage = MoveMessage.fromString(message);
        if (!playerConnected) {            
            //Send all current players to client
            for (String playerId : gamePanel.onlinePlayers.keySet()) {
                try {
                    Person player = gamePanel.onlinePlayers.get(playerId);
                    MoveMessage newMessage = new MoveMessage(playerId, player.getCurrentTile().xLoc, player.getCurrentTile().yLoc);
                    server.sendMessage(newMessage.toString());
                    //Ghetto work around until I find out why it's not working
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MoveController.class.getName()).log(Level.SEVERE, null, ex);
                }
                server.loadPerson(moveMessage.getPlayerId(), moveMessage.getxLoc(), moveMessage.getyLoc());
                server.sendMessage(moveMessage.toString());
            }
            //Send the new player info to each client
            server.sendMessage(message);
        } else {
            gamePanel.movePerson(moveMessage.getPlayerId(), moveMessage.getxLoc(), moveMessage.getyLoc());
            server.sendMessage(moveMessage.toString());
        }       
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public boolean getIsServer() {
        return isServer;
    }

    @Override
    void handleMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}