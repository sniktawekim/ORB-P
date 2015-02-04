/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import orb.p.panels.GamePanel;
import orb.p.OnScreenObjects.*;
import orb.p.network.messages.MoveMessage;

/**
 *
 * @author Blainezor
 */
public class ClientHandler implements Runnable {

    Server server = null;
    GamePanel gPanel = null;
    Boolean playerConnected = false;
    Socket connection;

    ClientHandler(Server server, GamePanel gPanel, Socket connection) {
        this.server = server;
        this.connection = connection;
        this.gPanel = gPanel;

    }

    @Override
    public void run() {
        try {
            String message = null;
            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (connection != null && connection.isConnected()) {

                message = inFromClient.readLine();
                MoveMessage moveMessage = MoveMessage.fromString(message);
                if (!playerConnected) {
                    playerConnected = true;
                    for (String playerId : gPanel.onlinePlayers.keySet()) {
                        Person player = gPanel.onlinePlayers.get(playerId);
                        MoveMessage newMessage = new MoveMessage(playerId, player.getCurrentTile().xLoc, player.getCurrentTile().yLoc);
                        server.sendMessage(newMessage.toString());
                                //Ghetto work around until I find out why it's not working
                                Thread.sleep(2000);
                    }
                   
                    server.loadPerson(moveMessage.getPlayerId(), moveMessage.getxLoc(), moveMessage.getyLoc());
                    server.sendMessage(moveMessage.toString());
                } else {
                    server.movePerson(moveMessage.getPlayerId(), moveMessage.getxLoc(), moveMessage.getyLoc());
                    server.sendMessage(moveMessage.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
