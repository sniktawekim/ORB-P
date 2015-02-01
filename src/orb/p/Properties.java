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
import static orb.p.ORBP.screenHeight;
import static orb.p.ORBP.screenWidth;

/**
 *
 * @author Omnicis
 */
public class Properties {

    public static final int CHARX = 13;
    public static final int CHARY = 13;

    //screen size properties
    public static final int FRAME_WIDTH = 1357;
    public static final int FRAME_HEIGHT = 800;
    public static final int SCREEN_X_START = 808;
    public static final int SCREEN_Y_START = 40;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 800;
    public static final int HALF_WIDTH = 650;
    public static final int HALF_HEIGHT = 400;
     //guess??

    //tile size properties
    public static final int TILE_RIGHT_BIND = 120; //pixels
    public static final int TILE_LOWER_BIND = 85; //pixels
    public static final int TILE_HEIGHT = 100;
    public static final int TILE_WIDTH = 120;

    //tile paths
    public static final String HOLE = ORBP.libraryPath + "pics/tiles/tile001.png";
    public static final String HIGHLIGHT = "pics/highlights/tileHighlight.png";
    public static final String HOVER = ORBP.libraryPath + "pics/highlights/tileHover.png";

    //curtain properties
    public static final int CURTAIN_HEIGHT = 534;
    public static final int CURTAIN_WIDTH = 60;

    //curtain paths
    public static final String DEFAULT_CPATH = "pics/curtains/default.png";
    public static final String CURTAIN_PREFIX = "pics/curtains/";

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
