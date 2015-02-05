/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package orb.p.OnScreenObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import orb.p.ORBP;
import orb.p.OnScreenObjects.OnScreenObject;
import orb.p.art.HUDArt;
import orb.p.listeners.IClick;

/**
 *
 * @author Omnicis
 */
public class MInteger extends OnScreenObject {

    int magnitude = 0;
    String style = "45.png";
    int widthPerCharacter = 45;
    int height = 57;
    int rightmostBarrier=0;
    ArrayList<Image> numString;

    /**
     *
     * @param rightMostBarrier is the starting point for right to left building
     * of images
     * @param magnitude the number to display
     * @param style the style code designates which pictures it will use
     */
    public MInteger(int rightmostBarrier,int ymin, int magnitude, String style) {
        super(0,ymin,0,0, true);
        numString = new ArrayList<>();
        this.rightmostBarrier = rightmostBarrier;
        this.magnitude = magnitude;
        setStyle(style);
        buildString();
    }

 

    public void setStyle(String style) {
        if (style.compareTo(HUDArt.SK_CC) == 0) {
            this.style = style;
            widthPerCharacter = HUDArt.SK_CC_DW;
            height = HUDArt.SK_CC_H;
        } else {
            System.out.println("Invalid Style Code: " + style);
        }
        buildString();
    }

    public void setMagnitude(int newMag) {
        magnitude = newMag;
    }

    @Override
    public void paint(int xOffset, int yOffset, Graphics g, ImageObserver lulz, IClick mouse) {
        int xpos = rightmostBarrier + xOffset;
        int ypos = getYMin() + yOffset;
        if (isOnScreen(xpos, ypos)) {
            for (int i = 0; i < numString.size(); i++) {
                g.drawImage(numString.get(i), xpos-(widthPerCharacter*i), ypos, lulz);
            }
        }
    }

    private void buildString() {

        ArrayList<Image> numbers = new ArrayList<>();
        BufferedImage bigImg = null;

        try {
            bigImg = ImageIO.read(new File(ORBP.libraryPath + style));
            // The above line throws an checked IOException which must be caught.
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + " on: " + ORBP.libraryPath + style);
            return;
        }
        for (int i = 0; i < HUDArt.getNumberOfDigits(magnitude); i++) {
            int currDigit = getDigit(HUDArt.getNumberOfDigits(magnitude)-i);
            
            
            int xStart = (currDigit) * widthPerCharacter+1;
            int yStart = 0;
            try{
            numbers.add(bigImg.getSubimage(xStart, yStart, widthPerCharacter-1, height));
            //System.out.println(magnitude + " xStart=" + xStart+" currDigit:" + currDigit);
            } catch(Exception e){
             //   System.out.println(magnitude + " xStart=" + xStart+" currDigit:" + currDigit);
            }
        }
        numString = numbers;
        
        setXMin();
        setXSize(rightmostBarrier - xmin);
        setYSize(height);
    }

    private int getDigit(int loc) {
        String fString = "" + magnitude;
        return Integer.parseInt(fString.substring(loc - 1, loc));
    }

    public void setXMin(){
        xmin = rightmostBarrier - HUDArt.getNumberOfDigits(magnitude)*widthPerCharacter;
    }
}
