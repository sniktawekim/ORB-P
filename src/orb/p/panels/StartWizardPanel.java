package orb.p.panels;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
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
                
        Font labelFont =  new Font("Serif", Font.BOLD, 30);
        JLabel ipLabel = new JLabel("IP (Leave Blank to Host): ");
        ipLabel.setFont(labelFont);
        ipLabel.setBounds(20,150,400,60);
        ipLabel.setForeground(Color.white);
       
        ip = new JTextField(40);
        ip.setFont(labelFont);
        ip.setBounds(20,210,400,60);
        
        JLabel playerNameLabel = new JLabel("Name: ");
        playerNameLabel.setBounds(20,260,200,60);
        playerNameLabel.setFont(labelFont);
        playerNameLabel.setForeground(Color.white);
        
        playerName = new JTextField(80);
        playerName.setFont(labelFont);
        playerName.setBounds(20,310,400,60);        
 
        this.setLayout(null);
        this.add(ipLabel);
        this.add(ip);
        this.add(playerNameLabel);
        this.add(playerName);
        
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
             resetHighlights();
             hudOb.setHighlight(true);
             local = false;
        }
        else if (hudOb.matches("local"))
        {
             resetHighlights();           
             hudOb.setHighlight(true);
             local = true;
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
        System.out.println("RESET");
        for(int i=0;i<hudObjects.size();i++){
            hudObjects.get(i).setHighlight(false);
        }
    }
}
