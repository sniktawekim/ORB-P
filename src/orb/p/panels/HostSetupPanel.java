/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package orb.p.panels;

import orb.p.OnScreenObjects.MTextBox;

/**
 *
 * @author Michael Watkins
 */
public class HostSetupPanel extends MPanel{

    @Override
    protected void buildHUD() {
      MTextBox test = new MTextBox(50,50,300,50,"","tbClicked");
      hudObjects.add(test);
    }
    
}
