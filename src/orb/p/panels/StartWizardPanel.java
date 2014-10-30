package orb.p.panels;

import orb.p.ORBP;
import orb.p.core.HudObject;



/**
 * @author MWatkins
 */
public class StartWizardPanel extends MPanel{

    public static final String PANEL_ID = "startWizard";
    
    public StartWizardPanel() {
        super();
        //Need a resource loader or a class to take care of file paths.
        bgiPath = ORBP.libraryPath + "pics/hud/startwizard/bg.png";
    }

@Override
    protected void buildHUD() {
        
        HudObject play = new HudObject(canvasWidth/2-200,400, 400, 200,"pics/hud/titlemenu/options/Play.png", "play");
        
        hudObjects.add(play);
    }

    protected void hudAction(HudObject hudOb) {

        if (hudOb.matches("play")) {
            status = "play";
        }
    }


}
