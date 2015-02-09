/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package orb.p.OnScreenObjects;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Michael Watkins
 */
public class MTextBox extends HudObject {

    JTextField text = new JTextField("TEST", 0);

    public MTextBox(int xPos, int yPos, int xSize, int ySize, String imagePath, String aName) {
        super(xPos, yPos, xSize, ySize, imagePath, aName);
        JPanel toPaint = new JPanel();
        toPaint.add(text);
    }

}
