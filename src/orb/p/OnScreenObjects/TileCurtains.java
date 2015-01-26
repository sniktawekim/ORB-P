/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.OnScreenObjects;

import orb.p.Properties;

/**
 *
 * @author Michael Watkins
 */
public class TileCurtains extends OnScreenObject {

    Tile hostTile;
    String path = Properties.DEFAULT_CPATH;
   

    public TileCurtains(Tile host, String fpath) {
        super(0, 0, Properties.CURTAIN_WIDTH, Properties.CURTAIN_HEIGHT);
        hostTile = host;
        try{
            path = fpath;
            setGraphic(Properties.CURTAIN_PREFIX + path);
        } catch(Exception e){
            System.out.println("Curtain Error:" + path + " "+ host.getLoc());
            setGraphic(Properties.DEFAULT_CPATH);
        }
        
    }

    void setHost(Tile host) {
        hostTile = host;
        xmin = hostTile.getXMin();
        ymin = hostTile.getYMin() + (hostTile.getYSize() / 2);
    }

    public TileCurtains copy() {
        TileCurtains newTC = new TileCurtains(hostTile, path);
        return newTC;
    }

}
