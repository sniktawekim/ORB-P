/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import orb.p.network.messages.MoveMessage;
import orb.p.panels.GamePanel;

/**
 *
 * @author blainezor
 */
public class Client extends Communicator {

    private boolean isPlayerConnected = false;
    private boolean isRunning = true;
    Socket clientSocket;
    //ArrayList<String> connctedPlayerNames = new ArrayList<String>();
    //Host Address
    private String host;
    GamePanel gPanel;

    public Client(GamePanel gPanel, String host) {
        this.gPanel = gPanel;
        this.host = host;
    }

    public Client(GamePanel aThis, String host, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sendMessage(String message) {
        try {
            DataOutputStream outToServer = new DataOutputStream(getSocket().getOutputStream());

            outToServer.writeBytes(message + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Socket getSocket() throws Exception {
        if (clientSocket == null || !clientSocket.isConnected()) {
            clientSocket = new Socket(host, 9001);
        }

        return clientSocket;
    }

    public void run() {

        String message = null;

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
                    gPanel.movePerson(newMoveMessage.getPlayerId(), newMoveMessage.getxLoc(), newMoveMessage.getyLoc());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
