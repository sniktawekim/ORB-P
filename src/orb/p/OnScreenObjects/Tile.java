package orb.p.OnScreenObjects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import orb.p.listeners.IClick;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import orb.p.ORBP;
import orb.p.Properties;

/**
 *
 * @author MWatkins
 */
public class Tile extends OnScreenObject {

    public int terrainCost;
    public int xLoc;
    public int yLoc;
    public int id;
    OnScreenObject onTop = null;
    TileCurtains walls = null;

    //tile width is 120 and height is 60
    public Tile(int xLocation, int yLocation) {
        super(xLocation, yLocation, Properties.TILE_WIDTH, Properties.TILE_HEIGHT);
        containerXMin = -1 * Properties.TILE_RIGHT_BIND;
        containerYMin = -1 * Properties.TILE_LOWER_BIND;
        setGraphic(Properties.HOLE);
        setHighGraphic(Properties.HIGHLIGHT);
        setHoverGraphic(Properties.HOVER);
    }

    @Override
    public boolean isWithin(int xLoc, int yLoc) {
        int cFromLeft = xLoc - xmin;
        if (cFromLeft < 0) {
            return false;
        }
        int cFromRight = getXMax() - xLoc;
        if (cFromRight < 0) {
            return false;
        }

        //if left half of diamond
        if (xLoc <= xmin + 60 && xLoc > xmin) {
            //remember that less than means "above" because 0 is top of screen
            //we can calculate the y values of the diamond based on the x value
            //of where we clicked

            //(above bottom left side) %% (below top left side)
            if (yLoc < ymin + (cFromLeft / 2) + 50 && yLoc > ymin - (cFromLeft / 2) + 50) {
                return true;
            }

            //if right half of diamond
        } else if (xLoc > xmin + 60 && xLoc < xmin + 120) {

            //(above bottom right side %% below top right side)
            if (yLoc < ymin + (cFromRight / 2) + 50 && yLoc > ymin + 50 - (cFromRight / 2)) {
                return true;
            }
        }
        return false;
    }

    public Tile copy() {
        Tile copy = new Tile(getXMin(), getYMin());
        copy.setGraphic(graphPath);
        copy.terrainCost = terrainCost;
        if (walls != null) {
            copy.setWalls(walls.copy());
        }
        return copy;
    }

    public String getLoc() {
        return xLoc + "," + yLoc;
    }

    public int getNESWLoc() {
        return xLoc;
    }

    public int getNWSELoc() {
        return yLoc;
    }

    public void setLoc(int x, int y) {
        xLoc = x;
        yLoc = y;
    }

    public void setID(int x) {
        id = x;
    }

    public int getID() {
        return id;
    }

    public void replaceWith(Tile newInfo) {
        setGraphic(newInfo.getGraphPath());
        setID(newInfo.getID());
        terrainCost = newInfo.terrainCost;
    }

    @Override
    protected void calcHighlightStatus(IClick mouse) {
        //if the mouse is currently pressed and we are on the level designer panel
        if (mouse.getLeft() && ORBP.currentCanvas.compareToIgnoreCase("ld") == 0) {
            setHighlight(true);
        }
    }

    protected boolean calcHoveredStatus(int xOffset, int yOffset, IClick mouse) {
        boolean inThisTile = isWithin(mouse.getX() - xOffset, mouse.getY() - yOffset);
        if (ORBP.currentCanvas.compareToIgnoreCase("ld") == 0) {
            return inThisTile;
        } else {
            return (inThisTile && !mouse.getLeft() && terrainCost > 0);
        }

    }

    public void setOnTop(OnScreenObject toPlace) {
        onTop = toPlace;
        onTop.setXMin(xmin + toPlace.xOffset);
        onTop.setYMin(ymin + ysize - toPlace.getYSize() + 8);
    }

    public void removeFromTop() {
        onTop = null;
    }

    public void paint(int xOffset, int yOffset, Graphics g, ImageObserver lulz, IClick mouse) {
        super.paint(xOffset, yOffset, g, lulz, mouse);
        if (onTop != null) {
            if (terrainCost != 0) {
                onTop.paint(xOffset, yOffset, g, lulz, mouse);
            }
        }
        if (walls != null) {
            walls.paint(xOffset, yOffset, g, lulz, mouse);

        }
    }

    @Override
    public void setXMin(int x) {
        super.setXMin(x);
        if (walls != null) {
            walls.setHost(this);
        }
    }

    @Override
    public void setYMin(int y) {
        super.setYMin(y);
        if (walls != null) {
            walls.setHost(this);
        }
    }

    public boolean checkEmpty() {
        if (onTop == null) {
            return true;
        } else {
            return false;
        }
    }

    public void setWalls(TileCurtains toCurt) {
        walls = toCurt;
    }
}
