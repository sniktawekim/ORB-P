package orb.p.panels;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import orb.p.art.CHARArt;
import orb.p.art.HUDArt;
import orb.p.OnScreenObjects.*;
import orb.p.Properties;
import orb.p.core.PersonStats;
import orb.p.network.Communicator;
import orb.p.network.client.Client;
import orb.p.network.messages.MoveMessage;
import orb.p.network.server.Server;

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
    private int seedIndex = 0;
    private ArrayList<Person> persons;//arraylist of all persons
    private ArrayList<String> personsToLoad;//populated from the setup panel
    private ArrayList<String> namesToLoad;//populated from setup panel, parallel to personsToLoad

    Person localPlayer;
    Communicator comm;

    public HashMap<String, Person> onlinePlayers = new HashMap<>();

    public GamePanel() {
        super();
        persons = new ArrayList();
        personsToLoad = new ArrayList();
        /**
         * for (int i = 0; i < 6; i++) { ItemBox test = new
         * ItemBox(tiles.get(i).getXMin(), tiles.get(5).getYMin());
         * tiles.get(i).setOnTop(test); }
         */
        playMusic();

        //Obviously for testing
        getNetworkSettingsInput();
        //Load Communicator     
        loadPersons();// this method adds characters from the personsToLoad arraylist
        PersonStats testLoader = new PersonStats(CHARArt.D_CHAR);
        centerCamera();
    }

    @Override
    protected void buildHUD() {
        //super.buildHUD();
        hudObjects = new ArrayList<>();
        buildActionBar();

    }

    @Override
    protected void paintComponent(Graphics g) {
        checkMusic();
        super.paintComponent(g);
    }

    protected void hudAction(HudObject hudOb) {
        Person isMoving = onlinePlayers.get(localPlayerId);
        super.hudAction(hudOb);
        if (hudOb.getAction().compareToIgnoreCase("moveMode") == 0) {
            resetMoves = !moveMode;
            hudOb.setHighlight(!moveMode);
            moveMode = !moveMode;

            isMoving.toggleMoving();
        } else if (hudOb.getAction().compareToIgnoreCase("mute") == 0) {
            music.tMute();
        } else if (hudOb.getAction().compareToIgnoreCase("attackMode") == 0) {
            isMoving.setAnimation("attack");
        } else if (hudOb.getAction().compareToIgnoreCase("save") == 0) {
            saveGame();
        }

    }

    /**
     *
     * @param characterId
     * @param tileX
     * @param tileY
     */
    public void movePerson(String characterId, int tileX, int tileY) {
        Person characterToMove = onlinePlayers.get(characterId);
        Tile currentTile = currentBoard.getTile(tileX, tileY);
        if (resetMoves == true) {
            characterToMove.resetMoves();
            resetMoves = false;
        }
        if (characterToMove.getMoves() != 0 || !characterId.equalsIgnoreCase(localPlayerId)) {
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
        if (moveMode&&!didDrag&&!myClick.getRight()) {
            movePerson(localPlayerId, clicked.xLoc, clicked.yLoc);
            if (!isLocal) {
                comm.sendMessage(new MoveMessage(localPlayerId,clicked.xLoc ,clicked.yLoc).toString());
            } else{
               // movePerson(localPlayerId, clicked.xLoc, clicked.yLoc);
            }
        }
    }

    public boolean checkMoveLegal(Person currentPlayer, Tile clicked) {
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
            // System.out.println("legal move");
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
            testPerson(localPlayerId, Properties.CHARX, Properties.CHARY);
        } //Host Game
        else if (host == null || host.isEmpty()) { //
            comm = new Server(this);
            comm.start();
            isClient = false;
            testPerson(localPlayerId, 14, 14);
        } //Join Game
        else {
            comm = new Client(this, host);
            comm.sendMessage(new MoveMessage(localPlayerId,Properties.CHARX, Properties.CHARY).toString());
            comm.start();
            //testPerson(localPlayerId, Properties.CHARX, Properties.CHARY);
        }

    }

    synchronized public void testPerson(String playerId, int x, int y) {

        Tile startingTile;
        if(isLocal)
        {
          startingTile = getStartTile();
        }
        else
        {
          startingTile = currentBoard.getTile(x, y);
        }
        
        if(startingTile == null){
            System.out.println("ERROR: NULL START");
            System.exit(0);
        }

        Person newChar = new Person(playerId, startingTile, CHARArt.CLOUD);       
        startingTile.setOnTop(newChar);
        onlinePlayers.put(playerId, newChar);

    }

    public String getLocalPlayerId() {
        return localPlayerId;
    }

    private void buildActionBar() {
        HudObject topBar = new HudObject(HUDArt.BAR_XSTART, HUDArt.BAR_YSTART, HUDArt.BAR_WIDTH, HUDArt.BAR_HEIGHT, HUDArt.BAR_BACKGROUND, "");
        hudObjects.add(topBar);
        HudObject moveButton = new HudObject(HUDArt.MOVE_BUTTON_XSTART, HUDArt.MOVE_BUTTON_YSTART, HUDArt.MOVE_BUTTON_WIDTH, HUDArt.MOVE_BUTTON_HEIGHT, HUDArt.MOVE_BUTTON, "moveMode");
        moveButton.setHighGraphic(HUDArt.MOVE_BUTTON_HIGHLIGHT);
        hudObjects.add(moveButton);
        HudObject quitButton = new HudObject(HUDArt.QUIT_BUTTON_XSTART, HUDArt.QUIT_BUTTON_YSTART, HUDArt.QUIT_BUTTON_WIDTH, HUDArt.QUIT_BUTTON_HEIGHT, HUDArt.QUIT_BUTTON, "menu");
        hudObjects.add(quitButton);
        HudObject attackButton = new HudObject(HUDArt.ATTACK_BUTTON_XSTART, HUDArt.ATTACK_BUTTON_YSTART, HUDArt.ATTACK_BUTTON_WIDTH, HUDArt.ATTACK_BUTTON_HEIGHT, HUDArt.ATTACK_BUTTON, "attackMode");
        hudObjects.add(attackButton);
        HudObject muteButton = new HudObject(HUDArt.MUTE_BUTTON_XSTART, HUDArt.MUTE_BUTTON_YSTART, HUDArt.MUTE_BUTTON_WIDTH, HUDArt.MUTE_BUTTON_HEIGHT, HUDArt.MUTE_BUTTON, "mute");
        hudObjects.add(muteButton);
    }

    private void loadPersons() {
        for (int i = 0; i < personsToLoad.size(); i++) {
            //arbitrarily assigned starting tile, want to add
            //start locations to the level xml in the future.
            Tile start = getStartTile();
            Person toAdd = new Person(namesToLoad.get(i), start, personsToLoad.get(i));
            start.setOnTop(toAdd);
            onlinePlayers.put(namesToLoad.get(i), toAdd);
            persons.add(toAdd);
        }
    }

    private void saveGame() {
        System.out.println("SAVING GAME...NOT!");
    }

    private Tile getStartTile() {
        Tile toReturn = null;

        if (seedIndex < seeds.size()) {
            toReturn = seeds.get(seedIndex);
            seedIndex++;
        }
        return toReturn;
    }
    
        protected void checkKey() {
        super.checkKey();
        if (myPress.getKeyPressed("space")) {
            centerCamera();
        }
    }

    private void centerCamera() {
        Person characterToMove = onlinePlayers.get(localPlayerId);

         int xShift = -1*xOffset - (characterToMove.getCurrentTile().getXMin())+ ((Properties.SCREEN_WIDTH-characterToMove.getXSize())/2);
       
        int yShift = -1*yOffset - ((characterToMove.getCurrentTile().getYMin()) - ((Properties.SCREEN_HEIGHT-characterToMove.getYSize())/2));
        // yShift = yOffset - (characterToMove.getCurrentTile().getYMin()) + ((Properties.SCREEN_HEIGHT)/2);
        shift(xShift,yShift);
    }
}
