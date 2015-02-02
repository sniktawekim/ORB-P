package orb.p.panels;

import orb.p.core.Parallaxer;
import java.awt.Graphics;
import java.util.ArrayList;
import orb.p.core.Board;
import orb.p.sounds.Music;
import orb.p.ORBP;
import orb.p.OnScreenObjects.*;
import orb.p.Properties;

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
    Parallaxer backgrounds;
    protected ArrayList<Tile> seeds;

    LevelPanel() {
        super();
        seeds = new ArrayList<>();
        currentBoard = new Board(ORBP.libraryPath + "levels/default.lvl");
        tiles = new ArrayList<>();
        music = new Music("test");//might be mistake
        loadBoard(currentBoard);
        seeds = currentBoard.getSeeds();
    }

    public void playMusic() {
        music.play();
    }

    @Override
    protected void paintBackground(Graphics g) {
        backgrounds.paint(g, xOffset, yOffset, currentBoard.getRightBarrier(), currentBoard.getLowerBarrier(), this);
    }

    protected void paintObjects(Graphics g) {
        for (int i = 0; i < tiles.size(); i++) {
            Tile current = tiles.get(i);
            current.paint(xOffset, yOffset, g, this, myClick);

        }
        super.paintObjects(g);
    }

    protected void checkClick() {

        super.checkClick();//checks hud clicking
        if (anyClicked&&!didDrag) {
            if (!hudclicked) {//if hud wasn't clicked
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
        super.checkKey();
        
        /** OLD DRAGGING METHOD
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
        }*/

    }

    @Override
    protected void buildHUD() {
        // super.buildHUD();
    }

    protected abstract void handleClickedTile(Tile clicked);

    private void loadBoard(Board gBoard) {
        gBoard.getAllTiles(tiles);
        bgiPath = gBoard.getBGI();
        title = gBoard.title;
        ArrayList<String> bgPaths = new ArrayList();
        ArrayList<Integer> startingOffsets = new ArrayList();
        ArrayList<Integer> sizes = new ArrayList();

        String backdrop = ORBP.libraryPath + "pics/backgrounds/backDrop.png";
        String layer1 = ORBP.libraryPath + "pics/backgrounds/layer1.png";
        String layer2 = ORBP.libraryPath + "pics/backgrounds/layer2.png";
        bgPaths.add(backdrop);
        bgPaths.add(layer1);
        bgPaths.add(layer2);
        startingOffsets.add(0);
        startingOffsets.add(0);
        startingOffsets.add(-1 * 3667 / 2);
        startingOffsets.add(0);
        startingOffsets.add(0);
        startingOffsets.add(-11);
        sizes.add(1300);//backdrop x
        sizes.add(867);//backdrop y
        sizes.add(3667);//layer1 x
        sizes.add(3667);//layer1 y
        sizes.add(3667);//layer2 x
        sizes.add(7334);//layer2 y (get it yet?)         

        backgrounds = new Parallaxer(bgPaths, startingOffsets, sizes);

    }

    @Override
    protected void hudAction(HudObject hudOb) {
        super.hudAction(hudOb);
    }

    private void shift(int x, int y) {
        if (!(-1 * xOffset + canvasWidth > currentBoard.getRightBarrier()) && x < 0) {//RIGHT ARROW PRESSED, SHIFTING BOARD LEFT
            xOffset += x;
            backgrounds.shift((double) x, 0);
        }
        if (!(xOffset + canvasWidth > currentBoard.getRightBarrier()) && x > 0) {//LEFT ARROW PRESSED, SHIFTING BOARD RIGHT
            xOffset += x;
            backgrounds.shift((double) x, 0);
        }

        if ((yOffset < currentBoard.getUpperBarrier()) && y > 0) {//UP ARROW PRESSED, SHIFTING BOARD DOWN
            yOffset += y;
            backgrounds.shift(0, (double) y);
        }
        if (!((-1 * yOffset) + canvasHeight > currentBoard.getLowerBarrier()) && y < 0) {//DOWN ARROW PRESSED, SHIFTING BOARD UP
            yOffset += y;
            backgrounds.shift(0, (double) y);
        }

    }

    protected void checkMusic() {
        if (!music.isPlaying()) {
            playMusic();
        }
    }

    @Override
    protected boolean handleDrag() {
        boolean didDrag = false;
        int xShift = myClick.getXDrag();
        int yShift = myClick.getYDrag();
        int posThresh = Properties.DRAG_THRESHOLD;
        int negThresh = -1 * posThresh;
        int shiftSpeed = Properties.SHIFT_SPEED;
        int negShiftSpeed = -1 * shiftSpeed;

        if (xShift < negThresh) {
            xShift = negShiftSpeed;
        } else if (xShift > posThresh) {
            xShift = shiftSpeed;
        } else {
            xShift = 0;
        }
        if (yShift < negThresh) {
            yShift = negShiftSpeed;
        } else if (yShift > posThresh) {
            yShift = shiftSpeed;
        } else {
            yShift = 0;
        }

        if (Math.abs(xShift) > 0 || Math.abs(yShift) > 0) {
            didDrag = true;
            shift(xShift, yShift);
        }
        myClick.resetDrag();
        return didDrag;
    }

}
