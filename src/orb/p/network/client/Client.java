/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import orb.p.network.Communicator;
import orb.p.network.messages.MoveMessage;
import orb.p.panels.GamePanel;

/**
 *
 * @author blainezor
 */
public class Client extends Communicator {

    private final boolean isRunning = true;
    private Socket clientSocket;
    //Host Address
    private final String host;
    
    private GamePanel gPanel;

    public Client(GamePanel gPanel, String host) {
        this.gPanel = gPanel;
        this.host = host;
    }

    @Override
    public void sendMessage(String message) {
        try {
            DataOutputStream outToServer = new DataOutputStream(getSocket().getOutputStream());

            outToServer.writeBytes(message + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * getSocket() - connects to the server if the socket is closed or null
     * @return
     * @throws Exception 
     */
    private Socket getSocket() throws Exception {
        if (clientSocket == null || !clientSocket.isConnected()) {
            clientSocket = new Socket(host, 9001);
        }

        return clientSocket;
    }

    @Override
    public void run() {

        String message;
        
        while (isRunning) {
            try {

                BufferedReader inFromClient
                        = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                message = inFromClient.readLine();
                //Create class to handle message types 
                 MoveMessage newMoveMessage = MoveMessage.fromString(message);

                if (!gPanel.onlinePlayers.containsKey(newMoveMessage.getPlayerId())) {

                    gPanel.testPerson(newMoveMessage.getPlayerId(), newMoveMessage.getxLoc(), newMoveMessage.getyLoc());

                } else {
                    //Do not move the local player... I am not sure if this check is necessary 
                    if(!newMoveMessage.getPlayerId().equalsIgnoreCase(gPanel.getLocalPlayerId()))
                    {
                        gPanel.movePerson(newMoveMessage.getPlayerId(), newMoveMessage.getxLoc(), newMoveMessage.getyLoc());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
