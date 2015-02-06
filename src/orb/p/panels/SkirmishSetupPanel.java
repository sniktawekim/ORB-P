package orb.p.panels;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import orb.p.ORBP;
import orb.p.art.HUDArt;
import orb.p.OnScreenObjects.*;
import orb.p.network.temp.TempNetworkStarter;

/**
 * @author MWatkins
 */
public class SkirmishSetupPanel extends MPanel {

    public static final String PANEL_ID = "startWizard";

    //Hud Objects
    JTextField ip;
    JTextField playerName;
    JTextField publicIp;
    boolean local = true;

    public SkirmishSetupPanel() {
        super();
        //Need a resource loader or a class to take care of file paths.
        bgiPath = ORBP.libraryPath + "pics/hud/startwizard/bg.png";
    }

    @Override
    protected void buildHUD() {
        //Create Buttons
        HudObject play = new HudObject(canvasWidth - 400, canvasHeight - 300, 400, 200, HUDArt.PLAY, "play");

        Font labelFont = new Font("Serif", Font.BOLD, 30);
        JLabel ipLabel = new JLabel("IP (Leave Blank to Host): ");
        ipLabel.setFont(labelFont);
        ipLabel.setBounds(20, 150, 400, 60);
        ipLabel.setForeground(Color.white);

        ip = new JTextField(40);
        ip.setFont(labelFont);
        ip.setBounds(20, 210, 400, 60);
        
        JLabel publicIpLabel = new JLabel("Internet IP: ");
        publicIpLabel.setFont(labelFont);
        publicIpLabel.setBounds(600, 150, 400, 60);
        publicIpLabel.setForeground(Color.white);
        
        publicIp = new JTextField(40);
        publicIp.setFont(labelFont);
        publicIp.setBounds(600, 210, 400, 60);
        publicIp.setEditable(false);

        JLabel playerNameLabel = new JLabel("Name: ");
        playerNameLabel.setBounds(20, 260, 200, 60);
        playerNameLabel.setFont(labelFont);
        playerNameLabel.setForeground(Color.white);

        playerName = new JTextField(80);
        playerName.setFont(labelFont);
        playerName.setBounds(20, 310, 400, 60);

        this.setLayout(null);
        this.add(ipLabel);
        this.add(ip);
        this.add(publicIpLabel);
        this.add(publicIp);
        this.add(playerNameLabel);
        this.add(playerName);

        //Add Local/Network Toggle
        hudObjects.add(play);
        ArrayList<HudObject> panel = new ArrayList<>();
        panel = HUDArt.displayNetworkBar("lulz");
        for (int i = 0; i < panel.size(); i++) {
            hudObjects.add(panel.get(i));
        }

        //Obtain Public IP Address using Amazon's WS
        try {
            URL amazonCheckIp = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    amazonCheckIp.openStream()));
            publicIp.setText(in.readLine());
        } catch (UnknownHostException ex) {
            System.out.println("IP READ ERROR");
            Logger.getLogger(SkirmishSetupPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SkirmishSetupPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SkirmishSetupPanel.class.getName()).log(Level.SEVERE, null, ex);
        }  
//        String[] parts = sIP.split("\\.");
//        String combineParts = "";
//        if (parts.length == 0) {
//            System.out.println(sIP);
//            System.out.println("NO PARTS");
//        }
//        for (int i = 0; i < parts.length; i++) {
//            combineParts += parts[i];
//        }
//        int strippedIP = Integer.parseInt(combineParts);
//        MInteger testMInt = new MInteger(1000, 200, strippedIP, HUDArt.SK_CC);
//        mInts.add(testMInt);
    }

    private void setNetworkValues() {
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
        } else if (hudOb.matches("network")) {
            resetHighlights();
            hudOb.setHighlight(true);
            // System.out.println(hudOb.getAction() + " is highlighted");
            local = false;
        } else if (hudOb.matches("local")) {
            resetHighlights();
            hudOb.setHighlight(true);
            //System.out.println(hudOb.getAction() + " is highlighted");
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
        //System.out.println("RESET");
        for (int i = 0; i < hudObjects.size(); i++) {
            hudObjects.get(i).setHighlight(false);
        }
    }
}
