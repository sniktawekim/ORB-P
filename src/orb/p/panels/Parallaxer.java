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
    private ArrayList<Integer> free;
    BufferedImage current;
    private double yShift = 0;
    private double xShift = 0;

    public Parallaxer(ArrayList<String> paths, ArrayList<Integer> sOffsets, ArrayList<Integer> stops) {
        backgrounds = new ArrayList();
        sizes = new ArrayList();
        startingOffsets = new ArrayList();
        free = new ArrayList();
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
        for (int i = 0; i < stops.size(); i++) {
            free.add(stops.get(i));
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

                int bgWidth = sizes.get(i * 2);//only the even numbers int
                int bgHeight = sizes.get((i * 2) + 1);//only the odd numbers
                boolean inBounds = true;
                /**
                 * if (i != 0) {//we don't want the very backdrop to move
                 * //using the size/offset ratio of the board to calc the bg
                 * offsets
                 *
                 * bgxOffset += (double) i * .3 * boardXOffset * bgWidth /
                 * (double) boardWidth;
                 *
                 * //System.out.println("board width:" + boardWidth + "bgWidth:
                 * "+bgWidth); bgyOffset += i * .3 * boardYOffset * bgHeight /
                 * (double) boardHeight;
                 *
                 * }
                 */
                if (i != 0) {
                    if (free.get(i) == 1||inBounds) {
                        bgxOffset += .2 * (double) i * xShift;
                        bgyOffset +=  .9 * Math.sqrt((double) i) * yShift;
                    }
                    //scale for layer size
                    bgxOffset *= boardWidth / (double) bgWidth;
                    bgyOffset *= boardHeight / (double) bgHeight;
                    System.out.println("bg/bh" + bgHeight + "/" + boardHeight);
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

    public void shift(double x, double y) {
        xShift += x;
        yShift += y;
    }
}
