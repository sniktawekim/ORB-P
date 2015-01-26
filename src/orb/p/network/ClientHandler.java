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
                if (!playerConnected) {
                    playerConnected = true;                    
                    for (String playerId : gPanel.onlinePlayers.keySet()) {
                        Person player = gPanel.onlinePlayers.get(playerId);
                        server.sendMessage(playerId + "," + player.getCurrentTile().xLoc+ "," +player.getCurrentTile().yLoc);
                        //Ghetto work around until I find out why it's not working
                        Thread.sleep(2000);
                    }
                    server.loadPerson(message, 12, 12);
                    server.sendMessage(message+ "," + 12+ "," + 12);
                } else {
                    //Needs to be done in a separate class
                    String[] values = message.split(",");

                    String charId = values[0];
                    int x = Integer.parseInt(values[1]);
                    int y = Integer.parseInt(values[2]);
                    server.movePerson(charId, x, y);
                    server.sendMessage(message+ "," + x+ "," + y);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
