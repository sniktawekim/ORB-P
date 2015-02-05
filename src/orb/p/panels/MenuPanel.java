package orb.p.panels;

import java.util.ArrayList;
import orb.p.OnScreenObjects.*;
import orb.p.art.HUDArt;



/**
 * @author MWatkins
 */
public class MenuPanel extends MPanel{

    public MenuPanel() {
        super();
    }



@Override
    protected void buildHUD() {
        ArrayList<HudObject> MM = new ArrayList<>();//to store main menu
        MM = HUDArt.displayMainMenu("lolzfailcode");
        for(int i = 0; i<MM.size();i++){
            hudObjects.add(MM.get(i));
        }
    }

    @Override
    protected void hudAction(HudObject hudOb) {
        super.hudAction(hudOb);
        if (hudOb.matches("ld")) {
            status = "ld";
        }
        if (hudOb.matches("chars")) {
            status = "chars";
        }
        if (hudOb.matches("scrap")) {
            status = "scrap";
        }
        if (hudOb.matches(SkirmishSetupPanel.PANEL_ID)) {
            status = SkirmishSetupPanel.PANEL_ID;
        }
    }
    @Override
        protected void checkKey() {
        //GIVE NO FUCKS!     
    }
    @Override
        protected void buildUM(){
        //THIS PLEASES ME
        }

    @Override
    protected boolean handleDrag() {
       //GET FUCKED, MPANEL
        return false;
    }
    


}
