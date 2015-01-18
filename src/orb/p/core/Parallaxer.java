package orb.p.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
    Image current;
    private double yShift = 0;
    private double xShift = 0;

    public Parallaxer(ArrayList<String> paths, ArrayList<Integer> sOffsets, ArrayList<Integer> dimensions) {
        backgrounds = new ArrayList();
        sizes = new ArrayList();
        startingOffsets = new ArrayList();
        for (int i = 0; i < paths.size(); i++) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            try {
                current = toolkit.getImage(paths.get(i));

            } catch (Exception e) {
                System.out.println("CURRENT IMAGE ERROR:" + i);
                System.exit(0);
            }
            backgrounds.add(current);

        }
        for (int i = 0; i < dimensions.size(); i++) {
            sizes.add(dimensions.get(i));
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
                    if (sizes.get(i) == 1||inBounds) {
                        bgxOffset += .2 * (double) i * xShift;
                        bgyOffset +=  .9 * Math.sqrt((double) i) * yShift;
                    }
                    //scale for layer size
                    bgxOffset *= boardWidth / (double) bgWidth;
                    bgyOffset *= boardHeight / (double) bgHeight;
                   // System.out.println("bg/bh" + bgHeight + "/" + boardHeight);
                }

                bgxOffset += startingOffsets.get(i * 2);
                bgyOffset += startingOffsets.get((i * 2) + 1);

                g.drawImage(backgrounds.get(i), (int) bgxOffset, (int) bgyOffset, obs);
            }
        } catch (Exception e) {//if it fails, paint a blue rectangle
            System.out.println("PARALLAX ERROR");
            for(int i=0; i<e.getStackTrace().length; i++){
                System.out.println(e.getStackTrace()[i]);
            }
            System.exit(0);
        }
    }

    public void shift(double x, double y) {
        xShift += x;
        yShift += y;
    }
}
