package orb.p.OnScreenObjects;

import orb.p.listeners.IClick;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import orb.p.ORBP;

/**
 *
 * This class defines what every object that appears on the screen will need.
 *
 * @author MWatkins
 */
public abstract class OnScreenObject {

    private ImageIcon highGraph;//highlighted image
    private ImageIcon hoverGraph;//hovering image
    Image g;
    Image h;
    Image o;
    int xOffset = 0;
    protected int xmin;//left bound of object
    protected int ymin;//right bound of object
    protected int xsize;//horizontal size of object
    protected int ysize;//vertical size of object
    protected int rise;//vertical movement per redraw
    protected int run;//horizontal movement per redraw
    protected int containerYMax;//object cannot vertically go beyond
    protected int containerYMin;//object cannot vertically go under
    protected int containerXMax;//object cannot horizontally go beyond
    protected int containerXMin;//object cannot horizontally go under
    public String graphPath;//basic graphic path
    protected String highPath;//highlighted graphic path
    protected String hoverPath;//hovered graphic path
    protected boolean visible;//toggles existence of object

    protected Color color;//color of object, if not a graphic
    protected boolean highlight = false;

    private boolean hovered = false;

    public OnScreenObject(int x, int y, int sizeX, int sizeY) {
        xmin = x;
        ymin = y;
        xsize = sizeX;
        ysize = sizeY;
        containerXMax = ORBP.screenWidth;
        containerXMin = -sizeX;
        containerYMax = ORBP.screenHeight;
        containerYMin = -sizeY;

        //default the object to no movement
        rise = 0;
        run = 0;

        visible = true;
    }
      public OnScreenObject(int x, int y, int sizeX, int sizeY, boolean visible) {
        xmin = x;
        ymin = y;
        xsize = sizeX;
        ysize = sizeY;
        containerXMax = ORBP.screenWidth;
        containerXMin = -sizeX;
        containerYMax = ORBP.screenHeight;
        containerYMin = -sizeY;

        //default the object to no movement
        rise = 0;
        run = 0;

        this.setVisible(visible);
    }

    public int getXMin() {
        return xmin;
    }

    public int getYMin() {
        return ymin;
    }

    public int getXMax() {
        return xmin + xsize;
    }

    public int getYMax() {
        return ymin + ysize;
    }

    public int getXSize() {
        return xsize;
    }

    public int getYSize() {
        return ysize;
    }

    public Color getColor() {
        return color;
    }

    public boolean getVisible() {
        return visible;
    }

    public int getRun() {
        return run;
    }

    public Image getGraphic() {
        return g;
    }

    public Image getHigh() {
        return h;
    }

    public Image getHover() {
        return o;
    }

    /**
     *
     * @param x x coords of point
     * @param y y coords of point
     * @return true if the parameter coords are within this object.
     */
    public boolean isWithin(int x, int y) {
        return x >= xmin && x <= getXMax() && y >= ymin && y <= getYMax();
    }

    public void setXMin(int x) {
        xmin = x;
    }

    public void setYMin(int y) {
        ymin = y;
    }

    public void setXSize(int x) {
        xsize = x;
    }

    public void setYSize(int y) {
        ysize = y;
    }

    /**
     * Defines a picture for this object to display as
     *
     * @param setto is the path of the image to use
     */
    public void setGraphic(String setto) {
        graphPath = setto;
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            g = toolkit.getImage(ORBP.libraryPath + graphPath);
        } catch (Exception e) {
            System.out.print("OnScreenObject setGraphic caught: ");
            System.out.print(e);
            System.out.println(" " + graphPath);
            ArrayList<File> files = new ArrayList();
            listf(graphPath, files);
        }
    }

    public void listf(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
    }

    public void setHighGraphic(String setto) {
        highPath = ORBP.libraryPath +setto;
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            h = toolkit.getImage(highPath);

        } catch (Exception e) {
            System.out.print("OnScreenObject setGraphic caught: ");
            System.out.print(e);
            System.out.println(" " + highPath);

        }

    }

    public void setHoverGraphic(String setto) {
        hoverPath = setto;
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            o = toolkit.getImage(hoverPath);
        } catch (Exception e) {
            System.out.print("OnScreenObject setGraphic caught: ");
            System.out.print(e);
            System.out.println(" " + hoverPath);
        }

    }

    public String getGraphPath() {
        return graphPath;
    }

    public String getHoverPath() {
        return hoverPath;
    }
    public String getHighPath() {
        return highPath;
    }

    /**
     * This method changes the movement of the object
     *
     * @param riseM set the vertical change of the object
     * @param runM set the horizontal change of the object
     */
    public void setMovement(int riseM, int runM) {
        rise = riseM * -1;//rise means to go up, and negative will update it up
        run = runM;
    }

    /**
     * This method accelerates the object by the parameters
     *
     * @param horizontal if the acceleration should be horizontal or vertical
     * @param amount quantity to increment acceleration by
     */
    public void nudge(boolean horizontal, int amount) {
        if (horizontal) {
            run += amount;
        } else {
            rise += amount;
        }
    }

    public void setVisible(boolean vis) {
        visible = vis;
    }

    public void setColor(Color c) {
        color = c;
    }

    /**
     * used to process how the object should change with every screen refresh
     */
    public void update() {
        xmin += run;
        ymin += rise;
    }

    int getRise() {
        return rise;
    }

    public void setRise(int amount) {
        rise = amount;
    }

    public void setRun(int amount) {
        run = amount;
    }

    public void setHighlight(boolean setto) {
        highlight = setto;
    }

    public boolean getHighlight() {
        return highlight;
    }

    protected void setHovered(boolean setto) {
        hovered = setto;
    }

    public boolean getHovered() {
        return hovered;
    }

    public void paint(int xOffset, int yOffset, Graphics g, ImageObserver lulz, IClick mouse) {
       
            int xpos = getXMin() + xOffset;
            int ypos = getYMin() + yOffset;
            if (isOnScreen(xpos, ypos)) {
                g.drawImage(getGraphic(), xpos, ypos, lulz);
                if (highlight) {
                    g.drawImage(getHigh(), xpos, ypos, lulz);
                } 
                if (calcHoveredStatus(xOffset, yOffset, mouse)) {
                    g.drawImage(getHover(), xpos, ypos, lulz);
                    setHovered(true);
                    calcHighlightStatus(mouse);
                } else {
                    setHovered(false);
                }
            }
        
    }

    public boolean isOnScreen(int x, int y) {
        boolean isntRight = x < containerXMax;
        boolean isntLeft = (x + xsize) > containerXMin;
        boolean isntBelow = y < containerYMax;
        boolean isntAbove = (y + ysize) > 0;
        return isntRight && isntLeft && isntBelow && isntAbove && this.getVisible();
    }


    protected void calcHighlightStatus(IClick mouse) {
        
    }

    protected boolean calcHoveredStatus(int xOffset, int yOffset, IClick mouse) {
        return isWithin(mouse.getX() - xOffset, mouse.getY() - yOffset) && !mouse.getLeft();
    }
}
