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
import javax.swing.JFrame;
import orb.p.panels.PersonSetupPanel;
import orb.p.panels.GamePanel;
import orb.p.panels.LDPanel;
import orb.p.panels.MPanel;
import orb.p.panels.MenuPanel;
import orb.p.panels.StartWizardPanel;

/**
 *
 * @author mwatkins
 */
public class ORBP {

    static boolean developerMode = false;
    public static String libraryPath = "../testLibrary/";
    static String paneStatus = "menu";
    public static int screenHeight = 0;
    public static int screenWidth = 0;

    static MPanel canvas;
    static JFrame frame;
    public static String currentCanvas = "menu";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
                while (true) {//testloop to solve recursion
            canvasControl();
        }
    }

    public static void init() {
        configureLibrary();
        getResolution();
        buildCanvas();


    }

    private static void configureLibrary() {
        String launchLoc = System.getProperty("user.dir");
        launchLoc = launchLoc.substring(launchLoc.length() - 6, launchLoc.length());

        if (launchLoc.compareToIgnoreCase("\\ORB-P") == 0) {
            developerMode = true;
            libraryPath = "library/";
            System.out.println("in IDE, library at:" + libraryPath);
        } else {
            System.out.println(launchLoc);

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
        screenWidth = Properties.FRAME_WIDTH;
        screenHeight = Properties.FRAME_HEIGHT;
    }

    private static void buildCanvas() {
        determineCanvas();
        frame = new JFrame("ORB-P");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);//lock game resolution
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
        frame.dispose();
        frame = null;
        buildCanvas();
       
    }

    private static void determineCanvas() {
        if (currentCanvas.compareToIgnoreCase("menu") == 0) {
            canvas = new MenuPanel();
        } else if (currentCanvas.compareToIgnoreCase("ld") == 0) {
            canvas = new LDPanel();
        } else if (currentCanvas.compareToIgnoreCase("play") == 0) {
            canvas = new GamePanel();
        } else if (currentCanvas.compareToIgnoreCase("scrap") == 0) {
            //canvas = new PreGamePanel();
            System.out.println("SCRAPYARD SELECTED");
        } else if (currentCanvas.compareToIgnoreCase("chars") == 0) {
            canvas = new PersonSetupPanel();
        } else if (currentCanvas.compareToIgnoreCase(StartWizardPanel.PANEL_ID) == 0) {
            canvas = new StartWizardPanel();
        }
        else {
            System.out.println(currentCanvas);
        }
    }

    private static void changeCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(libraryPath + "pics/hud/cursor/cursor.png");

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
