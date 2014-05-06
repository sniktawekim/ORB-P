/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orb.p;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;

/**
 *
 * @author mwatkins
 */
public class ORBP {

    static boolean developerMode = false;
    static String libraryPath = "library/";
    static String paneStatus = "menu";
    static int screenHeight = 0;
    static int screenWidth = 0;

    static MPanel canvas;
    static JFrame frame;
    static String currentCanvas = "menu";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
    }

    public static void init() {
        configureLibrary();
        getResolution();
        buildCanvas();
        canvasControl();

    }

    private static void configureLibrary() {
        String launchLoc = System.getProperty("user.dir");
        launchLoc = launchLoc.substring(launchLoc.length() - 9, launchLoc.length());

        if (launchLoc.compareToIgnoreCase("pacalypse") == 0) {
            developerMode = true;
            System.out.println("in IDE");
        } else {
            libraryPath = "library/";
            System.out.println("in jar");
        }

    }

    private static void getResolution() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setResolution(width, height);
        System.out.println("width: " + width + ", height: " + height);
    }

    private static void setResolution(int width, int height) {
        screenHeight = height;
        screenWidth = width;
        //in the future, once we have background images, 
        //we can test and implement supporting multpile resolutions
        screenWidth = 1300;
        screenHeight = 800;
    }

    private static void buildCanvas() {
        frame = new JFrame("Mike's Awesome Game, yo!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth-50, screenHeight-50);
        frame.setResizable(false);//lock game resolution
        determineCanvas();
        frame.setContentPane(canvas);
        frame.setVisible(true);
        canvas.requestFocusInWindow();
    }

    private static void canvasControl() {
        changeCursor();
        while (canvas.update().compareToIgnoreCase("good") == 0) {
            canvas.repaint();
            pause();
        }
        currentCanvas = canvas.update();
        determineCanvas();
        frame.dispose();
        frame = null;
        buildCanvas();
        canvasControl();
    }

    private static void determineCanvas() {
        if (currentCanvas.compareToIgnoreCase("menu") == 0) {
            canvas = new MenuPanel();
        }
        if (currentCanvas.compareToIgnoreCase("ld") == 0) {
            canvas = new LDPanel();
        }
    }

    private static void changeCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(libraryPath+ "pics/hud/cursor/cursor.png");
 
        Cursor c = toolkit.createCustomCursor(image, new Point(frame.getX() + 3,
                frame.getY() + 3), "img");
        frame.setCursor(c);
    }
    
    private static void pause() {
        try {
            Thread.sleep(5); // wait 5ms
        } catch (Exception e) {
            System.out.println("paint error");
        }
    }
}