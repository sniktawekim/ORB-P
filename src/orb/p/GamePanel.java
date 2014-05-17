package orb.p;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import orb.p.network.Client;
import orb.p.network.Server;
import orb.p.network.Communicator;

/**
 * @author MWatkins
 */
public class GamePanel extends LevelPanel {

    private Tile selected = null;
    private boolean selectMode = false;
    private boolean isClient = true;
    private boolean isLocal = false;
    private String localPlayerId;
    Character localPlayer;
    Communicator comm;

    HashMap<String, Character> onlinePlayers = new HashMap<>();

    GamePanel() {
        super();
        for (int i = 0; i < 6; i++) {
            ItemBox test = new ItemBox(tiles.get(i).getXMin(), tiles.get(5).getYMin());
            tiles.get(i).setOnTop(test);
        }
        playMusic();

        //Obviously for testing
        getInput();
        //Load Communicator     
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

    /**
     *
     * @param characterId
     * @param tileX
     * @param tileY
     */
    public void moveCharacter(String characterId, int tileX, int tileY) {
        Character characterToMove = onlinePlayers.get(characterId);
        Tile currentTile = currentBoard.getTile(tileX, tileY);
        if (characterToMove.getMoves() != 0) {
            if (checkMoveLegal(characterToMove, currentTile)) {
                //Two way reference
                characterToMove.handleMove(0);
                characterToMove.setCurrentTile(currentTile);
                characterToMove.prevTile.removeFromTop();
                //Get Tile
                currentTile.setOnTop(characterToMove);
            }
        }

    }

    protected void handleClickedTile(Tile clicked) {
        moveCharacter(localPlayerId, clicked.xLoc, clicked.yLoc);
        if (!isLocal) {
            comm.sendMessage(localPlayerId + "," + clicked.xLoc + "," + clicked.yLoc);
        }
    }

    public boolean checkMoveLegal(Character currentPlayer, Tile clicked) {
        int pX = currentPlayer.getCurrentTile().getNESWLoc();
        int pY = currentPlayer.getCurrentTile().getNWSELoc();

        boolean NESWaxis = false;
        boolean NWSEaxis = false;
        boolean tileIsEmpty = true;
        boolean okTerrain = clicked.terrainCost != 0;

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
            if (NESWaxis) {
                System.out.print("(NESWaxis ");
            }
            if (NWSEaxis) {
                System.out.print("|| NWSEaxis)");
            }
            if (tileIsEmpty) {
                System.out.print("&&tileIsEmpty ");
            }
            if (okTerrain) {
                System.out.println("&& okTerrain");
            }

            System.out.println("");
            return false;
        }
    }

    private void getInput() {
        String host = JOptionPane.showInputDialog("Host (Leave Empty for Local)");
        localPlayerId = JOptionPane.showInputDialog("Player ID: ");

        if (host == null || host.isEmpty()) {

            int hostGame = JOptionPane.showConfirmDialog(null,
                    "Would you like to host a game?", "Please select",
                    JOptionPane.YES_NO_OPTION);
            if (hostGame == JOptionPane.YES_OPTION) {
                comm = new Server(this);
                comm.start();
                isClient = false;
                testCharacter(localPlayerId, 3, 48);
            } else {
                isLocal = true;
                testCharacter(localPlayerId, 3, 48);
            }
        } else {
            comm = new Client(this, host);
            comm.sendMessage(localPlayerId);
            comm.start();
            testCharacter(localPlayerId, 3, 49);
        }

    }

    public void testCharacter(String playerId, int x, int y) {

        Tile startingTile;

        startingTile = currentBoard.getTile(x, y);

        Character newChar = new Character(playerId, startingTile, "pics/character/testLady/");
        startingTile.setOnTop(newChar);
        onlinePlayers.put(playerId, newChar);

    }

    public String getLocalPlayerId() {
        return localPlayerId;
    }

}
