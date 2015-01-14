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
    private ArrayList<Integer> startingOffsets;
    BufferedImage current;

    public Parallaxer(ArrayList<String> paths, ArrayList<Integer> sOffsets) {
        backgrounds = new ArrayList();
        sizes = new ArrayList();
        startingOffsets = new ArrayList();
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
        for (int i = 0; i < sOffsets.size(); i++) {
            startingOffsets.add(sOffsets.get(i));
        }
    }

    public void paint(Graphics g, int xOffset, int yOffset, int boardWidth, int boardHeight, ImageObserver obs) {
        double bgxOffset = 0;
        double bgyOffset = 0;
        double boardXOffset = (double) xOffset;
        double boardYOffset = (double) yOffset;

        try {//try to paint background image
            for (int i = 0; i < backgrounds.size(); i++) {
                int bgWidth = sizes.get(i * 2);//only the even numbers
                int bgHeight = sizes.get((i * 2) + 1);//only the odd numbers

                if (i != 0) {//we don't want the very backdrop to move
                    //using the size/offset ratio of the board to calc the bg offsets

                    bgxOffset += (double) i * .3 * boardXOffset * bgWidth / (double) boardWidth;

                    //System.out.println("board width:" + boardWidth + "bgWidth: "+bgWidth);
                    bgyOffset += boardYOffset * bgHeight / (double) boardHeight;

                }
                bgxOffset += startingOffsets.get(i * 2);
                bgyOffset += startingOffsets.get((i * 2) + 1);
                g.drawImage(backgrounds.get(i), (int) bgxOffset, (int) bgyOffset, obs);
            }
        } catch (Exception e) {//if it fails, paint a blue rectangle
            System.out.println("PARALLAX ERROR");
            System.exit(0);
        }
    }
}
