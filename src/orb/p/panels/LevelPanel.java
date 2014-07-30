package orb.p.panels;

import java.awt.Graphics;
import java.util.ArrayList;
import orb.p.core.Board;
import orb.p.sounds.Music;
import orb.p.ORBP;
import orb.p.core.Tile;
import orb.p.core.HudObject;

/**
 *
 * @author MWatkins
 */
public abstract class LevelPanel extends MPanel {

    String title = "boardName";
    static int tilesLeft = 1;
    static int tilesRight = 1;
    ArrayList<Tile> tiles;
    Board currentBoard;
    int xOffset = 0;
    int yOffset = 0;
    public static String prePath = "";
    Music music;
    boolean repeatMusic = true;

    LevelPanel() {
        super();

        currentBoard = new Board(ORBP.libraryPath + "levels/default.lvl");
        tiles = new ArrayList<>();
        loadBoard(currentBoard);
    }

    public void playMusic() {
        music = new Music("test");
        music.play();
    }

    protected void paintObjects(Graphics g) {
        for (int i = 0; i < tiles.size(); i++) {
            Tile current = tiles.get(i);
            current.paint(xOffset, yOffset, g, this, myClick);

        }
        super.paintObjects(g);
    }

    protected void checkClick() {
        if (clearOldClickStatus()) {
            super.checkClick();//checks hud clicking
            if (!hudclicked) {
                int xClicked = myClick.getEX() - xOffset;
                int yClicked = myClick.getEY() - yOffset;
                for (int i = 0; i < tiles.size(); i++) {
                    Tile current = tiles.get(i);
                    if (current.isWithin(xClicked, yClicked)) {
                        handleClickedTile(current);
                        return;
                    }
                }
            }
        }
    }

    protected void checkKey() {
        if (myPress.getKeyPressed("left")) {
            shift(10, 0);
        }
        if (myPress.getKeyPressed("right")) {
            shift(-10, 0);
        }
        if (myPress.getKeyPressed("up")) {
            shift(0, 10);
        }
        if (myPress.getKeyPressed("down")) {
            shift(0, -10);
        }

    }

    @Override
    protected void buildHUD() {
        HudObject leftArrow = new HudObject(0, canvasHeight / 2, 40, 50, "pics/hud/arrows/lha.png", "lha");
        HudObject rightArrow = new HudObject(canvasWidth - 40, canvasHeight / 2, 40, 50, "pics/hud/arrows/rha.png", "rha");
        HudObject upArrow = new HudObject(canvasWidth / 2 - 25, 0, 50, 40, "pics/hud/arrows/uha.png", "uha");
        HudObject downArrow = new HudObject(canvasWidth / 2 - 25, canvasHeight - 40, 50, 40, "pics/hud/arrows/dha.png", "dha");

       // hudObjects.add(leftArrow);
        //  hudObjects.add(rightArrow);
        // hudObjects.add(upArrow);
        // hudObjects.add(downArrow);
    }

    protected abstract void handleClickedTile(Tile clicked);

    private void loadBoard(Board gBoard) {
        gBoard.getAllTiles(tiles);
        bgiPath = gBoard.getBGI();
        title = gBoard.title;
    }

    @Override
    protected void hudAction(HudObject hudOb) {
        if (hudOb.matches("lha")) {
            shift(400, 0);
        }
        if (hudOb.matches("rha")) {
            shift(-400, 0);
        }
        if (hudOb.matches("dha")) {
            shift(0, -400);
        }
        if (hudOb.matches("uha")) {
            shift(0, 400);
        }
    }

    private void shift(int x, int y) {

        if (!(-1 * xOffset + canvasWidth > currentBoard.getRightBarrier()) && x < 0) {//RIGHT ARROW PRESSED, SHIFTING BOARD LEFT
            xOffset += x;
        }
        if (!(xOffset + canvasWidth > currentBoard.getRightBarrier()) && x > 0) {//LEFT ARROW PRESSED, SHIFTING BOARD RIGHT
            xOffset += x;
        }

        if ((yOffset < currentBoard.getUpperBarrier()) && y > 0) {//UP ARROW PRESSED, SHIFTING BOARD DOWN
            yOffset += y;
        }
        if (!((-1 * yOffset) + canvasHeight > currentBoard.getLowerBarrier()) && y < 0) {//DOWN ARROW PRESSED, SHIFTING BOARD UP
            yOffset += y;
        }

    }

    protected void checkMusic() {
       if(!music.isPlaying()){
           playMusic();
       }
    }
}