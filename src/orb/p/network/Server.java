/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import orb.p.panels.GamePanel;

/**
 *
 * @author blainezor
 */
public class Server extends Communicator {

    private boolean isPlayerConnected = false;
    private boolean isRunning = true;
    private Socket connectedPlayer;

    GamePanel gPanel;

    public Server(GamePanel gPanel) {

        this.gPanel = gPanel;
    }
    
    @Override
    public void sendMessage(String message) {
        try {
            DataOutputStream outToServer = new DataOutputStream(connectedPlayer.getOutputStream());
            outToServer.writeBytes(message + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {

        String message = null;
        ServerSocket welcomeSocket = null;

        try {
            welcomeSocket = new ServerSocket(9001);

        } catch (Exception e) {
            e.printStackTrace();
        }

        while (isRunning) {
            try {
                Socket connectionSocket = null;

                connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient
                        = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                connectedPlayer = connectionSocket;
                while (connectionSocket.isConnected()) {
                    message = inFromClient.readLine();
                    if (!isPlayerConnected) {
                        isPlayerConnected = true;                        
                        gPanel.testCharacter(message, 3, 49);
                        sendMessage(gPanel.getLocalPlayerId());
                    } else {
                        //Needs to be done in a separate class
                        String[] values = message.split(",");

                        String charId = values[0];
                        int x = Integer.parseInt(values[1]);
                        int y = Integer.parseInt(values[2]);
                        gPanel.moveCharacter(charId, x, y);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
