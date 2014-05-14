package orb.p;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * @author MWatkins
 */
public class GamePanel extends LevelPanel {

    private Tile selected = null;
    private boolean selectMode = false;
    //TEST
    ArrayList<Character> players = new ArrayList<Character>();

    Character currentPlayer;

    GamePanel() {
        super();
        for (int i = 0; i < 6; i++) {
            ItemBox test = new ItemBox(tiles.get(i).getXMin(), tiles.get(5).getYMin());
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

  
            if (checkMoveLegal(clicked)) {
                //Two way reference
                currentPlayer.handleMove(0);
                currentPlayer.setCurrentTile(clicked);
                currentPlayer.prevTile.removeFromTop();
                clicked.setOnTop(currentPlayer);
                

                if (currentPlayer.getMoves() == 0) {
                    int index = players.indexOf(currentPlayer);

                    if (index == players.size() - 1) {
                        index = 0;
                    } else {
                        index++;
                    }
                    currentPlayer = players.get(index);
                    currentPlayer.resetMoves();
                }

            }
        
    }

    public boolean checkMoveLegal(Tile clicked) {
        int pX = currentPlayer.getCurrentTile().getNESWLoc();
        int pY = currentPlayer.getCurrentTile().getNWSELoc();

        boolean NESWaxis = false;
        boolean NWSEaxis = false;
        boolean tileIsEmpty = true;
        boolean okTerrain = clicked.terrainCost!=0;

        if (((clicked.getNESWLoc() + 1 == pX) || (clicked.getNESWLoc() - 1 == pX)) && clicked.getNWSELoc() == pY) {

            NESWaxis = true;
        } else if (((clicked.getNWSELoc() + 1 == pY) || (clicked.getNWSELoc() - 1 == pY)) && clicked.getNESWLoc() == pX) {
            NWSEaxis = true;
        }
        if (clicked.checkEmpty() == false) {
            tileIsEmpty = false;
        }
        if ((NESWaxis || NWSEaxis) && tileIsEmpty && okTerrain) {
            System.out.println("legal move");
            return true;
        } else {
            if(NESWaxis)
                System.out.print("(NESWaxis ");
            if(NWSEaxis)
                System.out.print("|| NWSEaxis)");
            if(tileIsEmpty)
                System.out.print("&&tileIsEmpty ");
            if(okTerrain)
                System.out.println("&& okTerrain");
            
            System.out.println("");
            return false;
        }
    }

    private void testCharacter() {
        Tile startingTile = currentBoard.getTile(3, 48);
        Character newChar = new Character(startingTile, "pics/character/testLady/");
        startingTile.setOnTop(newChar);
        players.add(newChar);

        startingTile = currentBoard.getTile(4, 50);
        newChar = new Character(startingTile, "pics/character/testLady/");
        startingTile.setOnTop(newChar);
        players.add(newChar);

        currentPlayer = players.get(0);
    }

    private void handleCharacterMove() {

    }

}
