package orb.p.network;

import orb.p.panels.GamePanel;

/**
 *
 * @author blainezor
 */
public class LoginController extends MessageController {
    
    /**
     * 
     * @param gamePanel
     * @param isServer 
     */
    public LoginController (GamePanel gamePanel, boolean isServer)
    {
        super(gamePanel,isServer);
    }

   
    @Override
    void handleMessage(String message) {
        
    }
    
    
}
