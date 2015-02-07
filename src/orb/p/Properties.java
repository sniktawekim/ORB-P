/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Omnicis
 */
public class Properties {

// <editor-fold defaultstate="collapsed" desc=" starting location constants ">
    public static final int CHARX = 13;
    public static final int CHARY = 13;

// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc=" Scrolling Constants ">
    //scrolling constants
    public static final int DRAG_THRESHOLD = 5;
    public static final int SHIFT_RATE = 35;
    public static final int SHIFT_SPEED = SHIFT_RATE - (10 - DRAG_THRESHOLD);

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Screen Size Constants ">
    //screen size properties
    public static final int FRAME_WIDTH = 1366;
    public static final int FRAME_HEIGHT_MARGIN = 80;
    public static final int FRAME_HEIGHT = 768;
    public static final int SCREEN_X_START = 808;
    public static final int SCREEN_Y_START = 40;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 800;
    public static final int HALF_WIDTH = 650;
    public static final int HALF_HEIGHT = 400;
     //guess??

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Tile Properties ">
    //tile size properties
    public static final int TILE_RIGHT_BIND = 120; //pixels
    public static final int TILE_LOWER_BIND = 85; //pixels
    public static final int TILE_HEIGHT = 100;
    public static final int TILE_WIDTH = 120;

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Tile Paths ">
    //tile paths
    public static final String HOLE = ORBP.libraryPath + "pics/tiles/tile001.png";
    public static final String HIGHLIGHT = "pics/highlights/tileHighlight.png";
    public static final String HOVER = ORBP.libraryPath + "pics/highlights/tileHover.png";

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" curtain properties ">
    //curtain properties
    public static final int CURTAIN_HEIGHT = 534;
    public static final int CURTAIN_WIDTH = 60;

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" curtain paths ">
    //curtain paths
    public static final String DEFAULT_CPATH = "pics/curtains/default.png";
    public static final String CURTAIN_PREFIX = "pics/curtains/";

// </editor-fold>

    public static String removeXML(String toremove) {
        toremove = toremove.trim();
        int startpoint = toremove.indexOf(">") + 1;
        int endpoint = toremove.indexOf("</");
        toremove = toremove.substring(startpoint, endpoint);
        return toremove;
    }

    public static ArrayList<String> readFile(String filepath) {
        BufferedReader br = null;
        ArrayList<String> currentFile = new ArrayList<>();
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filepath));

            while ((sCurrentLine = br.readLine()) != null) {
                currentFile.add(sCurrentLine);
            }
        } catch (IOException e) {

        }
        try {
            br.close();
        } catch (Exception e) {

        }
        return currentFile;
    }
}
