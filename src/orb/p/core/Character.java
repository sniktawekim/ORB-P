package orb.p.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import orb.p.ORBP;
import orb.p.art.CHARArt;
import orb.p.listeners.IClick;

/**
 * @author blainezor
 */
public class Character extends OnScreenObject {

    //TODO Character stats
    Tile currentTile;
    public Tile prevTile; //previous tile, for direction calculations
    protected CharStats stats;
    boolean currentlyMoving = false;
    private int moves = 5;
    private int direction;
    private int prevDirection;
    protected int currentHP;
    private String charPath;
    private String playerID;
    private int spriteState = 0;
    private boolean spriteLoop = true;
    private String spriteMode = "walk";
    private int spriteClock = 0;
    private int rotateRate = 135;

    public Character(String playerID, Tile startTile, String characterPath) {
        //TODO Display position is off
        super(startTile.xLoc, startTile.yLoc, 120, 140);
        stats = new CharStats("Name", 2, 2, 3, 1, 100, playerID);
        xOffset = 37;
        currentTile = startTile;
        this.playerID = playerID;
        direction = 4;
        charPath = characterPath;
        currentHP = stats.getMaxHP();
        changeDirection(4);
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile toCurrentTile) {
        currentTile = toCurrentTile;

        int prevNWSE = prevTile.getNESWLoc();
        int currentNWSE = currentTile.getNESWLoc();
        int prevNESW = prevTile.getNWSELoc();
        int currentNESW = currentTile.getNWSELoc();

        if (currentNWSE - prevNWSE > 0) {//moving towards bottom right
            setDirection(4);
        } else if (currentNWSE - prevNWSE < 0) {//moving towards top left
            setDirection(2);
        } else if (currentNESW - prevNESW > 0) {//moving towards top right
            setDirection(1);
        } else if (currentNESW - prevNESW < 0) {//moving towards bottom left
            setDirection(3);
        }
        System.out.println("Moved in direction: " + direction);

    }

    public void resetMoves() {
        System.out.println("Reset moves");
        moves = 5;

    }

    public void handleMove(int Direction) {
        if (Direction == 0) {//a human player move
            prevTile = currentTile;
        } else if (Direction == 1) { //Northeast
            setDirection(1);
            System.out.println("AI D1 move");
        } else if (Direction == 2) {//Northwest
            setDirection(2);
            System.out.println("AI D2 move");
        } else if (Direction == 3) {//Southwest
            setDirection(3);
            System.out.println("AI D3 move");
        } else if (Direction == 4) {//Southeast
            setDirection(4);
            System.out.println("AI D4 move");
        } else {
            System.out.println("AI ERROR: Invalid direction code: " + Direction);
            return;
        }
        moves--;
        System.out.println(moves + " moves remain");

    }

    public int getMoves() {
        return moves;
    }

    public void setDirection(int toDirection) {
        prevDirection = direction;
        direction = toDirection;
        if (prevDirection != direction) {
            changeDirection(direction);
        }
    }

    private void changeDirection(int newDirection) {

        direction = newDirection;

    }
// SPRITE CALCULATIONS IN PROGRESS

    public void toggleMoving() {
        currentlyMoving = !currentlyMoving;
        if (currentlyMoving) {
            spriteMode = "walk";
        }
    }

    @Override
    public void paint(int xOffset, int yOffset, Graphics g, ImageObserver lulz, IClick mouse) {
        super.paint(xOffset, yOffset, g, lulz, mouse);
        update();
    }

    @Override
    public void update() {
        super.update();

        BufferedImage bigImg = null;
        String walkString = determineAnimation();

        try {
            bigImg = ImageIO.read(new File(ORBP.libraryPath + charPath + walkString));
// The above line throws an checked IOException which must be caught.
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + " on: " + ORBP.libraryPath + charPath + walkString);
            return;
        }

        final int width = 50;
        final int height = 99;
        int frames = 3;
        frames = (bigImg.getWidth() % width) - 1;
        //1 for black border
        int imgNum = spriteState;
        if (imgNum > 2) {
            imgNum = 0;
        }
        int xStart = 1 + imgNum * 51;//51 for width+endborder
        int yStart = 1;

        g = bigImg.getSubimage(xStart, yStart, width, height);

        spriteClock++;

        if (spriteClock % (rotateRate / frames) == 0) {
            spriteState++;
        }
        if (spriteState == frames) {
            if (spriteLoop) {
                spriteState = 0;
            }
            else{
                setAnimation("walk");
            }
        }

    }
//**/

    public void setAnimation(String code) {
        spriteState = 0;
        spriteClock = 0;
        spriteMode = code;
    }

    public String getAnimation() {
        return spriteMode;
    }

    public String determineAnimation() {
        String spritePath = "";
        if (spriteMode.compareToIgnoreCase("walk") == 0) {
            spriteLoop = true;
            if (direction == 1) {
                spritePath = CHARArt.WALKING_1;
            } else if (direction == 2) {
                spritePath = CHARArt.WALKING_2;
            } else if (direction == 3) {
                spritePath = CHARArt.WALKING_3;
            } else if (direction == 4) {
                spritePath = CHARArt.WALKING_4;
            }
        } else if (spriteMode.compareToIgnoreCase("attack") == 0) {
            spriteLoop = false;
            if (direction == 1) {
                spritePath = CHARArt.ATTACK_1;
            } else if (direction == 2) {
                spritePath = CHARArt.ATTACK_2;
            } else if (direction == 3) {
                spritePath = CHARArt.ATTACK_3;
            } else if (direction == 4) {
                spritePath = CHARArt.ATTACK_4;
            }

        } else if (spriteMode.compareToIgnoreCase("flinch") == 0) {
            if (direction == 1) {
                spritePath = CHARArt.ATTACK_1;
            } else if (direction == 2) {
                spritePath = CHARArt.ATTACK_2;
            } else if (direction == 3) {
                spritePath = CHARArt.ATTACK_3;
            } else if (direction == 4) {
                spritePath = CHARArt.ATTACK_4;
            }

        }
        return spritePath;
    }
}
