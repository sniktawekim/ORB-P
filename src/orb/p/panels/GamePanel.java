package orb.p.panels;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import orb.p.ORBP;
import orb.p.art.CHARArt;
import orb.p.art.HUDArt;
import orb.p.core.ItemBox;
import orb.p.core.Tile;
import orb.p.network.Client;
import orb.p.network.Server;
import orb.p.network.Communicator;
import orb.p.core.Character;
import orb.p.core.HudObject;
import orb.p.network.temp.TempNetworkStarter;

/**
 * @author MWatkins
 */
public class GamePanel extends LevelPanel {

    private Tile selected = null;//contains the currently player-selected tile
    private boolean selectMode = false;//
    private boolean isClient = true;
    private boolean isLocal = false;
    private boolean moveMode = false;
    private boolean resetMoves = false;
    private String localPlayerId;
    Character localPlayer;
    Communicator comm;

    HashMap<String, Character> onlinePlayers = new HashMap<>();

    public GamePanel() {
        super();
        for (int i = 0; i < 6; i++) {
            ItemBox test = new ItemBox(tiles.get(i).getXMin(), tiles.get(5).getYMin());
            tiles.get(i).setOnTop(test);
        }
        playMusic();

        //Obviously for testing
        getNetworkSettingsInput();
        //Load Communicator     
    }

    @Override
    protected void buildHUD() {
        hudObjects = new ArrayList<>();
        buildActionBar();
        super.buildHUD();
    }

    @Override
    protected void paintComponent(Graphics g) {
        checkMusic();
        super.paintComponent(g);
    }

    protected void hudAction(HudObject hudOb) {
        Character isMoving = onlinePlayers.get(localPlayerId);
        super.hudAction(hudOb);
        if (hudOb.getAction().compareToIgnoreCase("menu") == 0) {
            status = "menu";
        } else if (hudOb.getAction().compareToIgnoreCase("moveMode") == 0 ) {
            if (!moveMode) {
                moveMode = true;
                hudOb.setHighlight(true);
                
                isMoving.toggleMoving();
            } else {
                moveMode = false;
                resetMoves = true;
                hudOb.setHighlight(false);
                 isMoving.toggleMoving();
            }
        } else if (hudOb.getAction().compareToIgnoreCase("mute") == 0) {
            music.tMute();
        }

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
        if (resetMoves == true) {
            characterToMove.resetMoves();
            resetMoves = false;
        }
        if (characterToMove.getMoves() != 0) {
            if (checkMoveLegal(characterToMove, currentTile)) {
                //Two way reference
                characterToMove.handleMove(0);
                characterToMove.setCurrentTile(currentTile);
                characterToMove.prevTile.removeFromTop();
                //Get Tile
                currentTile.setOnTop(characterToMove);
            }
        } else {
            System.out.println("OUT OF MOVES");
        }

    }

    @Override
    protected void handleClickedTile(Tile clicked) {
        if (moveMode) {
            moveCharacter(localPlayerId, clicked.xLoc, clicked.yLoc);
            if (!isLocal) {
                comm.sendMessage(localPlayerId + "," + clicked.xLoc + "," + clicked.yLoc);
            }
        }
    }

    public boolean checkMoveLegal(Character currentPlayer, Tile clicked) {
        int characterXLocation = currentPlayer.getCurrentTile().getNESWLoc();
        int characterYLocation = currentPlayer.getCurrentTile().getNWSELoc();

        boolean NESWaxis = false;
        boolean NWSEaxis = false;
        boolean tileIsEmpty = true;
        boolean okTerrain = clicked.terrainCost != 0;

        if (((clicked.getNESWLoc() + 1 == characterXLocation) || (clicked.getNESWLoc() - 1 == characterXLocation)) && clicked.getNWSELoc() == characterYLocation) {

            NESWaxis = true;
        } else if (((clicked.getNWSELoc() + 1 == characterYLocation) || (clicked.getNWSELoc() - 1 == characterYLocation)) && clicked.getNESWLoc() == characterXLocation) {
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
                System.out.print("failed NESWaxis ");
            }
            if (NWSEaxis) {
                System.out.print("failed NWSEaxis)");
            }
            if (tileIsEmpty) {
                System.out.print("failed tileIsEmpty ");
            }
            if (okTerrain) {
                System.out.println("failed  okTerrain");
            }

            System.out.println("");
            return false;
        }
    }

    private void getNetworkSettingsInput() {
        String host = TempNetworkStarter.host;
        localPlayerId = TempNetworkStarter.characterName;
        //Local Game
        if (TempNetworkStarter.isLocal) {
            isLocal = true;
            testCharacter(localPlayerId, 3, 48);
        } //Host Game
        else if (host == null || host.isEmpty()) { //
            comm = new Server(this);
            comm.start();
            isClient = false;
            testCharacter(localPlayerId, 3, 48);
        } //Join Game
        else {
            comm = new Client(this, host);
            comm.sendMessage(localPlayerId);
            comm.start();
            testCharacter(localPlayerId, 3, 49);
        }

    }

    public void testCharacter(String playerId, int x, int y) {

        Tile startingTile;

        startingTile = currentBoard.getTile(x, y);

        Character newChar = new Character(playerId, startingTile, CHARArt.CLOUD);
        startingTile.setOnTop(newChar);
        onlinePlayers.put(playerId, newChar);

    }

    public String getLocalPlayerId() {
        return localPlayerId;
    }

    private void buildActionBar() {
        HudObject topBar = new HudObject(0, 0, 1300, 40, HUDArt.BAR_BACKGROUND, "");
        hudObjects.add(topBar);
        HudObject moveButton = new HudObject(10, 0, 120, 40, HUDArt.MOVE_BUTTON, "moveMode");
        moveButton.setHighGraphic(HUDArt.MOVE_BUTTON_HIGHLIGHT);//WHY THE FUCK DOESNT THIS WORK!?
        hudObjects.add(moveButton);
        HudObject quitButton = new HudObject(130, 0, 100, 40, HUDArt.SAVE_BUTTON, "menu");
        hudObjects.add(quitButton);
        HudObject muteButton = new HudObject(canvasWidth - 80 - 40, 0, 40, 40, HUDArt.VOLUME_BUTTON, "mute");
        hudObjects.add(muteButton);
    }

}
