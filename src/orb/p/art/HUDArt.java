/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.art;

import java.util.ArrayList;
import orb.p.OnScreenObjects.HudObject;
import orb.p.Properties;
import orb.p.panels.SkirmishSetupPanel;

/**
 *
 * @author Blainezor
 */
public class HUDArt {

    //General Panels
    public static final String GENERAL = "pics/hud/titlemenu/options/";
    public static final String PLAY = GENERAL + "Play.png";

// <editor-fold defaultstate="collapsed" desc=" Blaine's Start Wizard Panel ">
    //StartWizardPanel
    public static final String START_WIZARD_PANEL = "pics/hud/startwizard/";
    public static final String NETWORK = START_WIZARD_PANEL + "Network.png";
    public static final String NETWORK_HIGHLIGHT = START_WIZARD_PANEL + "NetworkHighlight.png";
    public static final String LOCAL = START_WIZARD_PANEL + "Local.png";
    public static final String LOCAL_HIGHLIGHT = START_WIZARD_PANEL + "LocalHighlight.png";

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" hud action bar ">
    //hudActionBar
    public static final String HUD_ACTION_BAR = "pics/hud/gamePanelHud/";
    //tool bar 
    public static final String BAR_BACKGROUND = HUD_ACTION_BAR + "topBar.png";
    public static final int BAR_WIDTH = 1300;
    public static final int BAR_HEIGHT = 40;
    public static final int BAR_XSTART = 00;
    public static final int BAR_YSTART = 0;
    //move button
    public static final String MOVE_BUTTON = HUD_ACTION_BAR + "moveButton.png";
    public static final String MOVE_BUTTON_HIGHLIGHT = HUD_ACTION_BAR + "moveButtonHighlight.png";
    public static final int MOVE_BUTTON_WIDTH = 120;
    public static final int MOVE_BUTTON_HEIGHT = 40;
    public static final int MOVE_BUTTON_XSTART = 10;
    public static final int MOVE_BUTTON_YSTART = 0;
    //quit button
    public static final String QUIT_BUTTON = HUD_ACTION_BAR + "saveButton.png";
    public static final int QUIT_BUTTON_WIDTH = 100;
    public static final int QUIT_BUTTON_HEIGHT = 40;
    public static final int QUIT_BUTTON_XSTART = MOVE_BUTTON_XSTART + MOVE_BUTTON_WIDTH;
    public static final int QUIT_BUTTON_YSTART = 0;
    //attack button
    public static final String ATTACK_BUTTON = HUD_ACTION_BAR + "attackButton.png";
    public static final String ATTACK_BUTTON_HIGHLIGHT = HUD_ACTION_BAR + "attackButtonHighlight.png";
    public static final int ATTACK_BUTTON_WIDTH = 120;
    public static final int ATTACK_BUTTON_HEIGHT = 40;
    public static final int ATTACK_BUTTON_XSTART = QUIT_BUTTON_XSTART + QUIT_BUTTON_WIDTH;
    public static final int ATTACK_BUTTON_YSTART = 0;

    //mute button
    public static final String MUTE_BUTTON = HUD_ACTION_BAR + "volumeButton.png";
    public static final int MUTE_BUTTON_WIDTH = 40;
    public static final int MUTE_BUTTON_HEIGHT = 40;
    public static final int MUTE_BUTTON_XSTART = BAR_WIDTH - MUTE_BUTTON_WIDTH;
    public static final int MUTE_BUTTON_YSTART = 0;

// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc=" Universal Menu ">
    //universal Menu
    public static final String UM = "pics/hud/universalMenu/";
    //background image
    public static final String UM_BG = UM + "MenuBG.png";
    public static final int UM_BG_W = 400;
    public static final int UM_BG_H = 640;
    public static final int UM_BG_XO = (Properties.FRAME_WIDTH / 2) - (UM_BG_W / 2);
    public static final int UM_BG_YO = (Properties.FRAME_HEIGHT / 2) - (UM_BG_H / 2);
    //button sizes
    public static final int UM_B_W = 320;
    public static final int UM_B_H = 74;
    public static final int UM_B_XO = UM_BG_XO + (UM_BG_W / 2) - (UM_B_W / 2);
    //button y offsets
    public static final int UM_B_PAD = 3;
    public static final int UM_1_YO = UM_BG_YO + 35;
    public static final int UM_2_YO = UM_1_YO + UM_B_H + UM_B_PAD;
    public static final int UM_3_YO = UM_2_YO + UM_B_H + UM_B_PAD;
    public static final int UM_4_YO = UM_3_YO + UM_B_H + UM_B_PAD;
    public static final int UM_5_YO = UM_4_YO + UM_B_H + UM_B_PAD;
    public static final int UM_6_YO = UM_5_YO + UM_B_H + UM_B_PAD;

    //save button
    public static final String UM_SAVE = UM + "saveButton.png";
    public static final String UM_MM = UM + "mainMenuButton.png";

    //exit button
    public static final String UM_EXIT = UM + "exitButton.png";
    public static final int UM_EXIT_W = 200;
    public static final int UM_EXIT_H = 50;
    public static final int UM_EXIT_XO = UM_BG_XO + (UM_BG_W / 2) - (UM_EXIT_W / 2);
    public static final int UM_EXIT_YO = UM_BG_YO + 500;

// </editor-fold>    
// <editor-fold defaultstate="collapsed" desc="Title Menu">
    //Main Menu Buttons
    public static final String MM = "pics/hud/titleMenu/";
    public static final String MM_BG = MM + "MenuBG.png";
    public static final String MM_SKIRMISH = MM + "skirmishButton.png";
    public static final String MM_CHARACTERS = MM + "charButton.png";
    public static final String MM_SCRAPYARD = MM + "scrapyardButton.png";
    public static final String MM_LEVELS = MM + "levelsButton.png";

// </editor-fold>     
// <editor-fold defaultstate="collapsed" desc=" Skirmish Setup Panel ">
    //network panel
    public static final int NP_PAD = 15;
    public static final String NP = "pics/hud/networkPanel/";//root
    public static final String NP_BG = NP + "doubleRowBG.png";//background image
    public static final int NP_BG_XO = 50;//background image x offset
    public static final int NP_BG_YO = 50;//y offset
    public static final int NP_BG_W = 700;//width
    public static final int NP_BG_H = 100;//height

    //y offset
    public static final int NP_B_YO = NP_BG_YO + NP_PAD;//y offset for buttons
    public static final String NP_B_HG = NP + "button.png";//highlight graphic for buttons

    //button 1
    public static final String NP_B1 = NP + "localButton.png";
    public static final int NP_B1_XO = NP_BG_XO + NP_PAD;//background image x offset

    //button 2
    public static final String NP_B2 = NP + "networkButton.png";
    public static final int NP_B2_XO = NP_B1_XO + UM_B_W + NP_PAD;//background image x offset

// </editor-fold>
    
    
    
    public static final String STYLE_PREFIX = "pics/hud/numbers/";
    
    public static final String SK_CC = STYLE_PREFIX+"45.png";
    public static final int SK_CC_DW = 45;
    public static final int SK_CC_H = 57;
    
    
    
    
    

    public static ArrayList<HudObject> displayUM(String code) {
        ArrayList<HudObject> toReturn = new ArrayList();
        HudObject buttonBg = new HudObject(UM_BG_XO, UM_BG_YO, UM_BG_W, UM_BG_H, UM_BG, "");
        HudObject saveButton = new HudObject(UM_B_XO, UM_1_YO, UM_B_W, UM_B_H, UM_SAVE, "save");
        HudObject mainMenuButton = new HudObject(UM_B_XO, UM_2_YO, UM_B_W, UM_B_H, UM_MM, "menu");
        HudObject exitButton = new HudObject(UM_EXIT_XO, UM_EXIT_YO, UM_EXIT_W, UM_EXIT_H, UM_EXIT, "exit");
        toReturn.add(buttonBg);
        toReturn.add(saveButton);
        toReturn.add(mainMenuButton);
        toReturn.add(exitButton);
        return toReturn;

    }

    public static ArrayList<HudObject> displayMainMenu(String code) {
        ArrayList<HudObject> toReturn = new ArrayList();
        HudObject buttonBg = new HudObject(UM_BG_XO, UM_BG_YO, UM_BG_W, UM_BG_H, MM_BG, "");
        HudObject skirmishButton = new HudObject(UM_B_XO, UM_1_YO, UM_B_W, UM_B_H, MM_SKIRMISH, SkirmishSetupPanel.PANEL_ID);
        HudObject charButton = new HudObject(UM_B_XO, UM_2_YO, UM_B_W, UM_B_H, MM_CHARACTERS, "chars");
        HudObject levelsButton = new HudObject(UM_B_XO, UM_3_YO, UM_B_W, UM_B_H, MM_LEVELS, "ld");
        HudObject scrapButton = new HudObject(UM_B_XO, UM_4_YO, UM_B_W, UM_B_H, MM_SCRAPYARD, "scrap");
        HudObject exitButton = new HudObject(UM_EXIT_XO, UM_EXIT_YO, UM_EXIT_W, UM_EXIT_H, UM_EXIT, "exit");
        toReturn.add(buttonBg);
        toReturn.add(skirmishButton);
        toReturn.add(charButton);
        toReturn.add(levelsButton);
        toReturn.add(scrapButton);
        toReturn.add(exitButton);
        return toReturn;
    }

    public static ArrayList<HudObject> displayNetworkBar(String code) {
        ArrayList<HudObject> toReturn = new ArrayList();
        HudObject buttonBg = new HudObject(NP_BG_XO, NP_BG_YO, NP_BG_W, NP_BG_H, NP_BG, "");
        HudObject localButton = new HudObject(NP_B1_XO, NP_B_YO, UM_B_W, UM_B_H, NP_B1, "local");
        localButton.setHighGraphic(NP_B_HG);
        localButton.setHighlight(true);
        HudObject networkButton = new HudObject(NP_B2_XO, NP_B_YO, UM_B_W, UM_B_H, NP_B2, "network");
        networkButton.setHighGraphic(NP_B_HG);
        toReturn.add(buttonBg);
        toReturn.add(localButton);
        toReturn.add(networkButton);
        return toReturn;
    }

    public static int getNumberOfDigits(int n) {
        if (n < 100000) {
            // 5 or less
            if (n < 100) {
                // 1 or 2
                if (n < 10) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                // 3 or 4 or 5
                if (n < 1000) {
                    return 3;
                } else {
                    // 4 or 5
                    if (n < 10000) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        } else {
            // 6 or more
            if (n < 10000000) {
                // 6 or 7
                if (n < 1000000) {
                    return 6;
                } else {
                    return 7;
                }
            } else {
                // 8 to 10
                if (n < 100000000) {
                    return 8;
                } else {
                    // 9 or 10
                    if (n < 1000000000) {
                        return 9;
                    } else {
                        return 10;
                    }
                }
            }
        }
    }

}
