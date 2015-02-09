/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package orb.p.OnScreenObjects;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
import javax.swing.JTextField;
import orb.p.listeners.IClick;
import orb.p.panels.HostSetupPanel;

/**
 *
 * @author Michael Watkins
 */
public class MTextBox extends HudObject {

    JTextField text;
    JPanel toPaint;
    HostSetupPanel paintTo;

    public MTextBox(int xPos, int yPos, int xSize, int ySize, String imagePath, String aName, HostSetupPanel paintTo, String startText) {
        super(xPos, yPos, xSize, ySize, imagePath, aName);
    
        text = new JTextField(startText, 6);
        text.setLocation(xPos,yPos);
        text.setSize(xSize, ySize);
        this.paintTo = paintTo;
        paintTo.add(text);
        text.setOpaque(false);
    }

    @Override
    public void setVisible(boolean visible){
        this.visible = visible;
        if(visible){
            paintTo.add(text);
            text.setOpaque(true);
        } else {
            paintTo.remove(text);
            text.setOpaque(false);
        }
    }
    @Override
    public void paint(int xOffset, int yOffset, Graphics g, ImageObserver lulz, IClick mouse) {

    }
}
