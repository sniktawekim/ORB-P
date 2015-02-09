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

/**
 *
 * @author Michael Watkins
 */
public class MTextBox extends HudObject {

    JTextField text;
    JPanel toPaint;

    public MTextBox(int xPos, int yPos, int xSize, int ySize, String imagePath, String aName) {
        super(xPos, yPos, xSize, ySize, imagePath, aName);
        toPaint = new JPanel();       
        text = new JTextField("TEST", 6);
        text.setLocation(xPos,yPos);
        text.setSize(xSize, ySize);
        
        toPaint.setLocation(xPos, yPos);
        toPaint.setSize(xSize, ySize);

    }

    @Override
    public void paint(int xOffset, int yOffset, Graphics g, ImageObserver lulz, IClick mouse) {
        toPaint.setLayout(null);
        text.setLayout(null);
        toPaint.add(text);
        toPaint.setVisible(true);
        text.setVisible(true);
        text.paint(g);    
    }

}
