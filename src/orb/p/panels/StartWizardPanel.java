package orb.p.panels;

import orb.p.ORBP;
import orb.p.art.HUDArt;
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
        //Create Buttons
        HudObject play = new HudObject(canvasWidth-400,canvasHeight-300, 400, 200,HUDArt.PLAY, "play");
        HudObject local = new HudObject(-50,20, 400, 200,HUDArt.LOCAL, "local");
        HudObject network = new HudObject(150,20, 400, 200,HUDArt.NETWORK, "network");
        
        //Add Objects
        hudObjects.add(play);
        hudObjects.add(local);
        hudObjects.add(network);
    }

    @Override
    protected void hudAction(HudObject hudOb) {

        if (hudOb.matches("play")) {
            status = "play";
        }
        else if (hudOb.matches("network"))
        {
             System.out.println("network");   
        }
        else if (hudOb.matches("local"))
        {
             System.out.println("local");   
        }
    }


}
