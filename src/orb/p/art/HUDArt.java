/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.art;

import java.util.ArrayList;
import orb.p.OnScreenObjects.HudObject;
import orb.p.Properties;

/**
 *
 * @author Blainezor
 */
public class HUDArt {
    
    //General Panels
    public static final String GENERAL = "pics/hud/titlemenu/options/";
    public static final String PLAY = GENERAL + "Play.png";
   
    
    //StartWizardPanel
    public static final String START_WIZARD_PANEL = "pics/hud/startwizard/";
    public static final String NETWORK = START_WIZARD_PANEL + "Network.png";
    public static final String NETWORK_HIGHLIGHT = START_WIZARD_PANEL + "NetworkHighlight.png";
    public static final String LOCAL = START_WIZARD_PANEL + "Local.png";
    public static final String LOCAL_HIGHLIGHT = START_WIZARD_PANEL + "LocalHighlight.png";
    
    //hudActionBar
    public static final String HUD_ACTION_BAR = "pics/hud/gamePanelHud/";
        //tool bar 
    public static final String BAR_BACKGROUND =HUD_ACTION_BAR + "topBar.png";
    public static final int BAR_WIDTH = 1300;
    public static final int BAR_HEIGHT = 40;
    public static final int BAR_XSTART = 00;
    public static final int BAR_YSTART = 0;
        //move button
    public static final String MOVE_BUTTON = HUD_ACTION_BAR +"moveButton.png";
    public static final String MOVE_BUTTON_HIGHLIGHT =HUD_ACTION_BAR + "moveButtonHighlight.png";
    public static final int MOVE_BUTTON_WIDTH = 120;
    public static final int MOVE_BUTTON_HEIGHT = 40;
    public static final int MOVE_BUTTON_XSTART = 10;
    public static final int MOVE_BUTTON_YSTART = 0;
        //quit button
    public static final String QUIT_BUTTON = HUD_ACTION_BAR +"saveButton.png";
    public static final int QUIT_BUTTON_WIDTH = 100;
    public static final int QUIT_BUTTON_HEIGHT = 40;
    public static final int QUIT_BUTTON_XSTART = MOVE_BUTTON_XSTART + MOVE_BUTTON_WIDTH;
    public static final int QUIT_BUTTON_YSTART = 0;
        //attack button
    public static final String ATTACK_BUTTON = HUD_ACTION_BAR +"attackButton.png";
    public static final String ATTACK_BUTTON_HIGHLIGHT =HUD_ACTION_BAR + "attackButtonHighlight.png";
    public static final int ATTACK_BUTTON_WIDTH = 120;
    public static final int ATTACK_BUTTON_HEIGHT = 40;
    public static final int ATTACK_BUTTON_XSTART = QUIT_BUTTON_XSTART + QUIT_BUTTON_WIDTH;
    public static final int ATTACK_BUTTON_YSTART = 0;
    
        //mute button
    public static final String MUTE_BUTTON =HUD_ACTION_BAR +"volumeButton.png";
    public static final int MUTE_BUTTON_WIDTH = 40;
    public static final int MUTE_BUTTON_HEIGHT = 40;
    public static final int MUTE_BUTTON_XSTART = BAR_WIDTH-MUTE_BUTTON_WIDTH;
    public static final int MUTE_BUTTON_YSTART = 0;
    
    //universal Menu
    public static final String UM = "pics/hud/universalMenu/";
            //background image
    public static final String UM_BG = UM+"MenuBG.png";
    public static final int UM_BG_W = 400;
    public static final int UM_BG_H = 640;
    public static final int UM_BG_XO = (Properties.FRAME_WIDTH/2) - (UM_BG_W/2);
    public static final int UM_BG_YO = 100;
            //button sizes
    public static final int UM_B_W = 320;
    public static final int UM_B_H = 74;
    public static final int UM_B_XO = UM_BG_XO+(UM_BG_W/2)-(UM_B_W/2);;
    
    
            //save button
    public static final String UM_SAVE = UM + "saveButton.png";
    public static final int UM_SAVE_YO = UM_BG_YO + 35;
    
    public static final String UM_MM = UM + "mainMenuButton.png";
    public static final int UM_MM_YO = UM_B_H+UM_SAVE_YO + 3;
    
            //exit button
    public static final String UM_EXIT = UM + "exitButton.png";
    public static final int UM_EXIT_W = 200;
    public static final int UM_EXIT_H = 50;
    public static final int UM_EXIT_XO = UM_BG_XO+(UM_BG_W/2)-(UM_EXIT_W/2);
    public static final int UM_EXIT_YO = UM_BG_YO + 500;
    
    
    
    public static ArrayList<HudObject> displayUM(String code) {
        ArrayList<HudObject> toReturn = new ArrayList();
        HudObject buttonBg = new HudObject(UM_BG_XO, UM_BG_YO,UM_BG_W,UM_BG_H,UM_BG, "");
        HudObject saveButton = new HudObject(UM_B_XO, UM_SAVE_YO, UM_B_W, UM_B_H,UM_SAVE, "save");
        HudObject mainMenuButton = new HudObject(UM_B_XO, UM_MM_YO, UM_B_W, UM_B_H,UM_MM, "menu");
        HudObject exitButton = new HudObject(UM_EXIT_XO, UM_EXIT_YO, UM_EXIT_W, UM_EXIT_H,UM_EXIT, "exit");
        toReturn.add(buttonBg);
        toReturn.add(saveButton);
        toReturn.add(mainMenuButton);
        toReturn.add(exitButton);
        return toReturn;

    }
}
