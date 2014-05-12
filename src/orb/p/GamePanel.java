package orb.p;

import java.awt.Graphics;

/**
 * @author MWatkins
 */
public class GamePanel extends LevelPanel {

    private Tile selected = null;
    private boolean selectMode = false;
    //TEST
    Character currentPlayer;
    
    GamePanel() {
        super();
        for(int i=0;i<6;i++){
        ItemBox test = new ItemBox(tiles.get(i).getXMin(),tiles.get(5).getYMin());
        tiles.get(i).setOnTop(test);
        }
        playMusic();
        testCharacter();
    }

    @Override
    protected void buildHUD() {
        super.buildHUD();

    }
    @Override
    protected void paintComponent(Graphics g) {
        checkMusic();
        super.paintComponent(g);
    }

    @Override
    protected void hudAction(hudObject hudOb) {
        super.hudAction(hudOb);

    }
    
    protected void handleClickedTile(Tile clicked) {

        int pX = currentPlayer.getCurrentTile().xLoc;
        int pY = currentPlayer.getCurrentTile().yLoc;
        
        boolean movePlayer = false;
        if (((clicked.xLoc + 1 == pX) || (clicked.xLoc - 1 == pX)) && clicked.yLoc == pY) {

            movePlayer = true;
        } else if (((clicked.yLoc + 1 == pY) || (clicked.yLoc - 1 == pY)) && clicked.xLoc == pX) {
            movePlayer = true;
        }
        if (movePlayer) {
            //Two way reference
            currentPlayer.setCurrentTile(clicked);
            clicked.setOnTop(currentPlayer);
        }
    }
    
    private void testCharacter()
    {  
      Tile startingTile = currentBoard.getTile(4, 49);
      currentPlayer = new Character(startingTile);
      startingTile.setOnTop(currentPlayer);
    }
    
    private void handleCharacterMove()
    {
        
    }

}
