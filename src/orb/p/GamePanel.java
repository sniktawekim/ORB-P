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

        int pX = currentPlayer.getCurrentTile().xLoc;
        int pY = currentPlayer.getCurrentTile().yLoc;

        boolean movePlayer = false;
        if (((clicked.xLoc + 1 == pX) || (clicked.xLoc - 1 == pX)) && clicked.yLoc == pY) {

            movePlayer = true;
        } else if (((clicked.yLoc + 1 == pY) || (clicked.yLoc - 1 == pY)) && clicked.xLoc == pX) {
            movePlayer = true;
        }
        if (clicked.terrainCost != 0) {
            if (movePlayer) {
                //Two way reference
                currentPlayer.setCurrentTile(clicked);
                clicked.setOnTop(currentPlayer);
                currentPlayer.handleMove(0);

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
    }

    private void testCharacter() {
        Tile startingTile = currentBoard.getTile(3, 48);
        Character newChar = new Character(startingTile);
        startingTile.setOnTop(newChar);
        players.add(newChar);

        startingTile = currentBoard.getTile(4, 50);
        newChar = new Character(startingTile);
        startingTile.setOnTop(newChar);
        players.add(newChar);

        currentPlayer = players.get(0);
    }

    private void handleCharacterMove() {

    }

}
