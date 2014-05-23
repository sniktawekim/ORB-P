package orb.p.core;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 *
 * @author MWatkins
 */
public class HudString {
int x = 0;
int y = 0;
String text = "";
public HudString(String string, int xLoc, int yLoc){
    text = string;
    x=xLoc;
    y=yLoc;
}
public void paint(int xOffset, int yOffset, Graphics g, ImageObserver lulz){
    g.drawString(text,x+xOffset,y+yOffset);
}
    
}
