package orb.p.panels;

import orb.p.OnScreenObjects.*;



/**
 * @author MWatkins
 */
public class MenuPanel extends MPanel{

    public MenuPanel() {
        super();
    }



@Override
    protected void buildHUD() {
        HudObject chars = new HudObject(canvasWidth/2-200, 0, 400, 200,"pics/hud/titlemenu/options/Characters.png", "chars");
        HudObject ld = new HudObject(canvasWidth/2-200, 200, 400, 200,"pics/hud/titlemenu/options/LevelDesigner.png", "ld");
        HudObject play = new HudObject(canvasWidth/2-200,400, 400, 200,"pics/hud/titlemenu/options/Play.png", StartWizardPanel.PANEL_ID);
        HudObject shop = new HudObject(canvasWidth/2-200,600, 400, 200,"pics/hud/titlemenu/options/Shop.png", "shop");
        
        hudObjects.add(ld);
        hudObjects.add(chars);
        hudObjects.add(shop);
        hudObjects.add(play);
    }

    protected void hudAction(HudObject hudOb) {
        if (hudOb.matches("ld")) {
            status = "ld";
        }
        if (hudOb.matches("chars")) {
            status = "chars";
        }
        if (hudOb.matches("shop")) {
            status = "shop";
        }
        if (hudOb.matches(StartWizardPanel.PANEL_ID)) {
            status = StartWizardPanel.PANEL_ID;
        }
    }


}
