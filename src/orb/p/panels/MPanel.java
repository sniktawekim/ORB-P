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
import orb.p.core.HudObject;
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
    ArrayList<HudString> hudFonts;
    protected ImageIcon bgIcon;
    protected Image bgImage;
    IClick myClick;//mouse listener, useful for menu options
    IPress myPress;
    boolean hudclicked = false;
    protected String status = "good";

    protected abstract void buildHUD();

    protected abstract void hudAction(HudObject current);

    MPanel() {
        hudObjects = new ArrayList<>();
        hudFonts = new ArrayList<>();
        myClick = new IClick();
        myPress = new IPress();
        this.addKeyListener(myPress);
        this.addMouseListener(myClick);
        addMouseMotionListener(myClick);
        setBorder(BorderFactory.createLineBorder(Color.black));
        buildHUD();
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
            if(current.getVisible())
            current.paint(0, 0, g, this, myClick);
        }

        for (int i = 0; i < hudFonts.size(); i++) {
            HudString current = hudFonts.get(i);
            current.paint(0, 17, g, this);
        }
    }

    protected void checkClick() {
        hudclicked = false;
        int xClicked = myClick.getEX();
        int yClicked = myClick.getEY();
        //Verify the button has been clicked
        if(myClick.getClicked())
        {
        for (int i = 0; i < hudObjects.size(); i++) {
            HudObject current = hudObjects.get(i);
            if (current.getVisible() == true) {
                if (current.isWithin(xClicked, yClicked) && !(current.getAction().compareToIgnoreCase("") == 0)) {
                    hudAction(current);
                    //Clear the state of the click listener                    
                    hudclicked = true;
                    return;
                } else if (current.isWithin(xClicked, yClicked)) {
                    // System.out.println("lame hud clicked");
                    hudclicked = true;
                }
            }
        }
        if (hudclicked) {
            return;
        }
       }
    }

    public String update() {
        return status;
    }

    protected void checkKey() {

    }
    
        private void printHudObjectsStats(){
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
        for(int i = 0; i<temp.size();i++){
            System.out.println(temp.get(i) + " " + numfound.get(i));
        }
    }

}
