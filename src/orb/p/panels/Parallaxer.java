package orb.p.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import orb.p.ORBP;
import orb.p.Properties;

/**
 *
 * @author Omnicis
 */
public class Parallaxer {

    private ArrayList<Image> backgrounds;
    private ArrayList<Integer> sizes;
    BufferedImage current;

    public Parallaxer(ArrayList<String> paths) {
        backgrounds = new ArrayList();
        sizes = new ArrayList();
        for (int i = 0; i < paths.size(); i++) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            try {
                current = ImageIO.read(new File(paths.get(i)));
            } catch (Exception e) {
                System.out.println("CURRENT IMAGE ERROR:" + i);
                System.exit(0);
            }
            backgrounds.add(current);
            int width = current.getWidth();
            int height = current.getHeight();
            sizes.add(width);
            sizes.add(height);
        }
    }

    public void paint(Graphics g, int boardXOffset, int boardYOffset, int boardWidth, int boardHeight, ImageObserver obs) {
        int bgxOffset = 0;
        int bgyOffset = 0;
        try {//try to paint background image
            for (int i = 0; i < backgrounds.size(); i++) {
                int bgWidth = sizes.get(i*2);
                int bgHeight = sizes.get((i*2)+1);
                //using the size/offset ratio of the board to calc the bg offsets
                bgxOffset = boardXOffset*bgWidth/boardWidth;
                bgyOffset = boardYOffset*bgHeight/boardHeight;
                //if the offset is going to push us past the edge, we need to stop it
                if(bgxOffset+bgWidth<Properties.SCREEN_WIDTH){//this catches the right bound
                    System.out.println("x edge");
                    bgxOffset =bgWidth - Properties.SCREEN_WIDTH;
                }
                if(bgxOffset>0){
                    bgxOffset = 0;
                }
                
                
                if(bgyOffset+bgHeight<Properties.SCREEN_HEIGHT){
                    System.out.println("y edge");
                    bgyOffset =bgHeight - Properties.SCREEN_HEIGHT;
                }
                
                
                g.drawImage(current, bgxOffset, bgyOffset, obs);
            }
        } catch (Exception e) {//if it fails, paint a blue rectangle
            System.out.println("PARALLAX ERROR");
            System.exit(0);
        }
    }
}
