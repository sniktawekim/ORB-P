package orb.p.panels;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import orb.p.listeners.IClick;
import orb.p.listeners.IPress;
import orb.p.ORBP;
import orb.p.OnScreenObjects.*;
import orb.p.art.HUDArt;
import orb.p.core.HudString;

/**
 *
 * @author MWatkins
 */
public abstract class MPanel extends JPanel {

    final int canvasWidth = ORBP.screenWidth;
    final int canvasHeight = ORBP.screenHeight;
    final int backgroundWidth = 1300;
    public String bgiPath = ORBP.libraryPath + "pics/backgrounds/default.png";
    ArrayList<HudObject> hudObjects;
    ArrayList<HudObject> universalMenu;
    ArrayList<HudString> hudFonts;
    protected ImageIcon bgIcon;
    protected Image bgImage;
    IClick myClick;//mouse listener, useful for menu options
    IPress myPress;
    boolean hudclicked = false;
    boolean anyClicked = false;
    boolean didDrag = false;
    protected String status = "good";
    boolean um = false;

    protected abstract void buildHUD();

    protected void hudAction(HudObject current) {
        if (current.matches("exit")) {
            System.exit(0);
        }
        if (current.getAction().compareToIgnoreCase("menu") == 0) {
            status = "menu";
        }
    }

    MPanel() {
        universalMenu = new ArrayList<>();
        hudObjects = new ArrayList<>();
        hudFonts = new ArrayList<>();
        myClick = new IClick();
        myPress = new IPress();
        this.addKeyListener(myPress);
        this.addMouseListener(myClick);
        addMouseMotionListener(myClick);
        setBorder(BorderFactory.createLineBorder(Color.black));
        buildHUD();
        buildUM();
    }

    protected void paintBackground(Graphics g) {
        try {//try to paint background image

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            bgImage = toolkit.getImage(bgiPath);
            g.drawImage(bgImage, 0, 0, this);

        } catch (Exception e) {//if it fails, paint a blue rectangle
            g.setColor(Color.blue);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintObjects(g);
        checkClick();
        checkKey();
    }

    protected boolean clearOldClickStatus() {
        if (!myClick.getClicked()) {
            return false;
        } else {
            return true;
        }
    }

    protected void paintObjects(Graphics g) {
        for (int i = 0; i < hudObjects.size(); i++) {
            HudObject current = hudObjects.get(i);
            if (current.getVisible()) {
                current.paint(0, 0, g, this, myClick);
            }
        }

        for (int i = 0; i < hudFonts.size(); i++) {
            HudString current = hudFonts.get(i);
            current.paint(0, 17, g, this);
        }
        if (um) {
            for (int i = 0; i < universalMenu.size(); i++) {
                HudObject current = universalMenu.get(i);
                current.paint(0, 0, g, this, myClick);
            }
        }

    }

    protected void checkClick() {
        anyClicked = false;
        didDrag = false;
        anyClicked = myClick.getClicked();
        if (myClick.getDragged()) {
            if(handleDrag())
                didDrag = true;
                return;
        }
        
        
        if (anyClicked&&!didDrag) {//check if you clicked and resets click status
            hudclicked = false;
            int xClicked = myClick.getEX();
            int yClicked = myClick.getEY();
            if (um) {
                for (int i = 0; i < universalMenu.size(); i++) {
                    HudObject current = universalMenu.get(i);
                    if (current.getVisible() == true) {
                        if (current.isWithin(xClicked, yClicked) && !(current.getAction().compareToIgnoreCase("") == 0)) {
                            hudAction(current);
                            //System.out.println(current.getAction() + "clicked");
                            //Clear the state of the click listener                             
                            hudclicked = true;
                            return;
                        } else if (current.isWithin(xClicked, yClicked)) {
                            //System.out.println("lame UM clicked");
                            hudclicked = true;
                        }
                    }
                }
            }
            //System.out.println("UM IS " + um + " hudclicked is " + hudclicked);
            if (hudclicked) {//if the UM was clicked
                return; //return
            }
            for (int i = 0; i < hudObjects.size(); i++) {
                HudObject current = hudObjects.get(i);
                if (current.getVisible() == true) {
                    if (current.isWithin(xClicked, yClicked) && !(current.getAction().compareToIgnoreCase("") == 0)) {
                        hudAction(current);
                        // System.out.println(current.getAction() + "clicked");
                        hudclicked = true;
                        return;
                    } else if (current.isWithin(xClicked, yClicked)) {
                        //System.out.println("lame hud clicked");
                        hudclicked = true;
                    }
                }
            }
            if (hudclicked) {//if the UM was clicked
                return; //return
            }

        }
    }

    public String update() {
        return status;
    }

    protected void checkKey() {
        if (myPress.getKeyPressed("esc")) {
            toggleUM();
        }
    }

    private void toggleUM() {
        um = !um;
    }

    private void printHudObjectsStats() {
        ArrayList<String> temp = new ArrayList();
        ArrayList<Integer> numfound = new ArrayList();
        temp.add(hudObjects.get(0).getAction());
        numfound.add(0);

        for (int i = 0; i < hudObjects.size(); i++) {
            boolean found = false;
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).compareToIgnoreCase(hudObjects.get(i).getAction()) == 0) {
                    numfound.set(j, numfound.get(j) + 1);
                    found = true;
                }
            }
            if (!found) {
                temp.add(hudObjects.get(i).getAction());
                numfound.add(1);
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i) + " " + numfound.get(i));
        }
    }

    protected void buildUM() {
        universalMenu = HUDArt.displayUM("thisdoesnothingyet");
    }
    protected boolean handleDrag(){
        return false;
    }

}
