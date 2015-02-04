/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import orb.p.network.Communicator;
import orb.p.panels.GamePanel;

/**
 *
 * @author blainezor
 */
public class Server extends Communicator {

    private boolean isRunning = true;
    private ArrayList<Socket> connections = new ArrayList<Socket>();
    private Socket connectedPlayer;
    private InetSocketAddress testAddress;
    GamePanel gPanel;

    public Server(GamePanel gPanel) {

        this.gPanel = gPanel;
    }

    @Override
    public void sendMessage(String message) {
        try {
            for (Socket connection : connections) {
                DataOutputStream outToServer = new DataOutputStream(connection.getOutputStream());
                outToServer.writeBytes(message + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        ServerSocket welcomeSocket = null;

        try {
            welcomeSocket = new ServerSocket(9001);

        } catch (IOException e) {
            e.printStackTrace();

            //Do not run the server if an error occured. 
            isRunning = false;
        }

        while (isRunning) {
            try {
                Socket connectionSocket = null;

                connectionSocket = welcomeSocket.accept();
                connections.add(connectionSocket);
                ClientHandler clientHandler = new ClientHandler(this,gPanel, connectionSocket);
                
                //TODO: Better thread control
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Socket getSocket() throws Exception {
        if (connectedPlayer == null || connectedPlayer.isClosed()) {
            connectedPlayer = new Socket(testAddress.getHostName(), testAddress.getPort());
        }

        return connectedPlayer;
    }
    public void loadPerson(String charName, int x, int y)
    {
        gPanel.testPerson(charName, x, y);
    }
    
    public void movePerson(String charName, int x, int y)
    {
        gPanel.movePerson(charName, x, y);
    }
}
