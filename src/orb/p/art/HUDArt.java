/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.art;

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
    
}
