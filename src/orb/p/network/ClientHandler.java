/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import orb.p.network.temp.TempNetworkStarter;

/**
 *
 * @author Blainezor
 */
public class ClientHandler implements Runnable {

    Server server = null;
    Boolean playerConnected = false;
    Socket connection;

    ClientHandler(Server server, Socket connection) {
        this.server = server;
        this.connection = connection;
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
                        server.loadCharacter(message, 12, 12);                    
                        server.sendMessage(TempNetworkStarter.characterName);
                    } else {
                        //Needs to be done in a separate class
                        String[] values = message.split(",");

                        String charId = values[0];
                        int x = Integer.parseInt(values[1]);
                        int y = Integer.parseInt(values[2]);
                        server.moveCharacter(charId, x, y);
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
