package orb.p.panels;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import orb.p.ORBP;
import orb.p.art.HUDArt;
import orb.p.OnScreenObjects.*;
import orb.p.network.temp.TempNetworkStarter;




/**
 * @author MWatkins
 */
public class StartWizardPanel extends MPanel{

    public static final String PANEL_ID = "startWizard";
    
    //Hud Objects
    HudObject play;
    HudObject local;
    HudObject network;
    
    JTextField ip;
    JTextField playerName;
    public StartWizardPanel() {
        super();
        //Need a resource loader or a class to take care of file paths.
        bgiPath = ORBP.libraryPath + "pics/hud/startwizard/bg.png";
    }

@Override
    protected void buildHUD() {
        //Create Buttons
        play = new HudObject(canvasWidth-400,canvasHeight-300, 400, 200,HUDArt.PLAY, "play");
        local = new HudObject(10,30, 190, 38,HUDArt.LOCAL, "local");
        local.setHighGraphic(HUDArt.LOCAL_HIGHLIGHT);
        local.setHighlight(true);
        network = new HudObject(150,30, 121, 38,HUDArt.NETWORK, "network");
        network.setHighGraphic(HUDArt.NETWORK_HIGHLIGHT);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(61, 11, 81, 140);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        
        Font labelFont =  new Font("Serif", Font.BOLD, 30);
        ip = new JTextField(10);
        ip.setFont(labelFont);
        JLabel ipLabel = new JLabel("IP (Leave Blank to Host): ");
        ipLabel.setFont(labelFont);
        
        playerName = new JTextField(10);
        playerName.setFont(labelFont);
        JLabel playerNameLabel = new JLabel("Name: ");
        playerNameLabel.setFont(labelFont);
        
        controlPanel.add(ipLabel);
        controlPanel.add(ip);
        controlPanel.add(playerNameLabel);
        controlPanel.add(playerName);
        
        this.add(controlPanel);
        //Add Objects
        hudObjects.add(play);
        hudObjects.add(local);
        hudObjects.add(network);
    }

    private void setNetworkValues()    
    {
        TempNetworkStarter.characterName = playerName.getText();
        TempNetworkStarter.host = ip.getText();
        TempNetworkStarter.isLocal = local.getHighlight();
    }
    
    @Override
    protected void hudAction(HudObject hudOb) {

        if (hudOb.matches("play")) {
            setNetworkValues();
            status = "play";
        }
        else if (hudOb.matches("network"))
        {
             //System.out.println("network");              
             hudOb.setHighlight(true);
             local.setHighlight(false);
        }
        else if (hudOb.matches("local"))
        {
             //System.out.println("local");  
             network.setHighlight(false);
             hudOb.setHighlight(true);
        }
    }

    @Override
    protected boolean handleDrag() {
        ///GET FUCKED MPANEL
        return false;
    }


}
