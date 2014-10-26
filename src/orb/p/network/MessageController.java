/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orb.p.network;

import orb.p.panels.GamePanel;

/**
 *
 * @author blainezor
 */
public abstract class MessageController {
    
    private final GamePanel gamePanel;
    private boolean isServer = false;

    
    /**
     * 
     * @param gamePanel 
     * @param isServer 
     */
    public MessageController(GamePanel gamePanel, boolean isServer)
    {
      this.gamePanel = gamePanel;  
      this.isServer = isServer; 
    }
    
    abstract void handleMessage(String message);
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public boolean getIsServer() {
        return isServer;
    }
    
}
