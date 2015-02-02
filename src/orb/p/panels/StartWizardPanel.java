package orb.p.panels;

import java.awt.Font;
import java.util.ArrayList;
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

    
    JTextField ip;
    JTextField playerName;
    boolean local=true;
    
    public StartWizardPanel() {
        super();
        //Need a resource loader or a class to take care of file paths.
        bgiPath = ORBP.libraryPath + "pics/hud/startwizard/bg.png";
    }

@Override
    protected void buildHUD() {
        //Create Buttons
        HudObject play = new HudObject(canvasWidth-400,canvasHeight-300, 400, 200,HUDArt.PLAY, "play");
       
        
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(15, 200, 400, 140);
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
        ArrayList<HudObject> panel = new ArrayList<>();
        panel = HUDArt.displayNetworkBar("lulz");
        for(int i=0;i<panel.size();i++){
            hudObjects.add(panel.get(i));
        }
    }

    private void setNetworkValues()    
    {
        TempNetworkStarter.characterName = playerName.getText();
        TempNetworkStarter.host = ip.getText();
        TempNetworkStarter.isLocal = local;
    }
    
    @Override
    protected void hudAction(HudObject hudOb) {
        super.hudAction(hudOb);
        if (hudOb.matches("play")) {
            setNetworkValues();
            status = "play";
        }
        else if (hudOb.matches("network"))
        {
             //System.out.println("network"); 
             resetHighlights();
             hudOb.setHighlight(true);
            // local.setHighlight(false);//now old
        }
        else if (hudOb.matches("local"))
        {
             resetHighlights();
             hudOb.setHighlight(true);
        }
    }

    @Override
    protected boolean handleDrag() {
        ///GET FUCKED MPANEL
        return false;
    }

    @Override
    protected void checkClick() {
        super.checkClick();
    }

    private void resetHighlights() {
        System.out.println("RESETTED");
        for(int i=0;i<hudObjects.size();i++){
            hudObjects.get(i).setHighlight(false);
        }
    }
}
