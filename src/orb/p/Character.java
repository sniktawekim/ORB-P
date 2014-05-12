/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p;

/**
 *
 * @author blainezor
 */
public class Character extends OnScreenObject {

    //TODO Character stats
    Tile currentTile;    
    //String direction;
    
  public Character(Tile currentTile) {        
        //TODO Display position is off
        super(currentTile.xLoc, currentTile.yLoc, 120, 140);
        this.currentTile = currentTile;
        
        //TODO AC
        setGraphic("pics/character/dr.png");
    }

  public Tile getCurrentTile()
  {
     return currentTile; 
  }
  public void setCurrentTile(Tile currentTile)
  {
      this.currentTile = currentTile;
  }

}
