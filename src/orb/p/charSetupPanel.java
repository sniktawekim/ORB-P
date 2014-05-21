/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p;

import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author mwatkins
 */
public class charSetupPanel extends MPanel {

    private int moveStat;
    private int attackStat;
    private int defenseStat;
    private int drawStat;
    private int hpStat;
    private int remainingBars;
    private String modelPath = "pics/character/testLady/4.png";

    charSetupPanel() {
        super();
        bgiPath = ORBP.libraryPath + "pics/hud/charSetup/bg.png";
        moveStat = 0;
        attackStat = 0;
        defenseStat = 0;
        drawStat = 0;
        hpStat = 0;
        remainingBars = 12;
        buildHUD();
    }

    @Override
    protected void buildHUD() {

        buildStatTable();
        buildModelPreviewer();
    }

    @Override
    protected void checkClick() {
        if (clearOldClickStatus()) {
            super.checkClick();
        }
    }

    @Override
    protected void hudAction(hudObject hudOb) {

        String f3 = hudOb.getAction().substring(0, 3);

        if (f3.matches("add")) {
            String rest = hudOb.getAction().substring(3);
            addBar(rest);

        } else if (f3.matches("rem")) {
            String rest = hudOb.getAction().substring(3);
            removeBar(rest);
        } else if (hudOb.matches("changeSkin")) {
            changeSkin();
        }

        buildHUD();
    }

    protected void buildStatTable() {
        //settings for left/right arrow buttons
        int arrowXSize = 15;
        int arrowYSize = 30;
        int lArrowXLoc = 410;
        int rArrowXLoc = 435;
        String lArrowPath = "pics/hud/charSetup/la.png";
        String rArrowPath = "pics/hud/charSetup/ra.png";
        
        //settings for vertical stat bars
        int vBarXStart = 120;
        int vBarXSize = 15;
        int vBarYSize = 30;
        String vBarPath = "pics/hud/charSetup/bar.png";
        
        hudObject buttonBg = new hudObject(4, 4, 400, 300, "pics/hud/charSetup/statTable.png", "");
        hudObject remStatBin = new hudObject(460, 60, 50, 200, "pics/hud/charSetup/remStatBin.png", "");
        hudObjects.add(buttonBg);
        hudObjects.add(remStatBin);

        for (int offset = 0; offset < moveStat; offset++) {
            hudObject movementBar = new hudObject(vBarXStart + (vBarXSize * offset), 60, vBarXSize, vBarYSize, vBarPath, "");
            hudObjects.add(movementBar);
        }

        for (int offset = 0; offset < attackStat; offset++) {
            hudObject attackBar = new hudObject(vBarXStart + (vBarXSize * offset), 104, vBarXSize, vBarYSize, vBarPath, "");
            hudObjects.add(attackBar);
        }
        for (int offset = 0; offset < defenseStat; offset++) {
            hudObject defenseBar = new hudObject(vBarXStart + (vBarXSize * offset), 150, vBarXSize, vBarYSize, vBarPath, "");
            hudObjects.add(defenseBar);
        }
        for (int offset = 0; offset < drawStat; offset++) {
            hudObject drawBar = new hudObject(vBarXStart + (vBarXSize * offset), 193, vBarXSize, vBarYSize, vBarPath, "");
            hudObjects.add(drawBar);
        }
        for (int offset = 0; offset < hpStat; offset++) {
            hudObject hpBar = new hudObject(vBarXStart + (vBarXSize * offset), 235, vBarXSize, vBarYSize, vBarPath, "");
            hudObjects.add(hpBar);
        }
        for (int offset = 0; offset < remainingBars; offset++) {
            hudObject remBar = new hudObject(470, 235 - (15 * offset), 30, 15, "pics/hud/charSetup/hbar.png", "");
            hudObjects.add(remBar);
        }

        hudObject movementRemove = new hudObject(lArrowXLoc, 60, arrowXSize, arrowYSize, lArrowPath, "remmove");
        hudObject movementAdd = new hudObject(rArrowXLoc, 60, arrowXSize, arrowYSize, rArrowPath, "addmove");
        hudObjects.add(movementRemove);
        hudObjects.add(movementAdd);

        hudObject attackRemove = new hudObject(lArrowXLoc, 104, arrowXSize, arrowYSize, lArrowPath, "remattack");
        hudObject attackAdd = new hudObject(rArrowXLoc, 104, arrowXSize, arrowYSize, rArrowPath, "addattack");
        hudObjects.add(attackRemove);
        hudObjects.add(attackAdd);

        hudObject defenseRemove = new hudObject(lArrowXLoc, 150, arrowXSize, arrowYSize, lArrowPath, "remdefense");
        hudObject defenseAdd = new hudObject(rArrowXLoc, 150, arrowXSize, arrowYSize, rArrowPath, "adddefense");
        hudObjects.add(defenseRemove);
        hudObjects.add(defenseAdd);

        hudObject drawRemove = new hudObject(lArrowXLoc, 193, arrowXSize, arrowYSize, lArrowPath, "remdraw");
        hudObject drawAdd = new hudObject(rArrowXLoc, 193, arrowXSize, arrowYSize, rArrowPath, "adddraw");
        hudObjects.add(drawRemove);
        hudObjects.add(drawAdd);

        hudObject hpRemove = new hudObject(lArrowXLoc, 235, arrowXSize, arrowYSize, lArrowPath, "remhp");
        hudObject hpAdd = new hudObject(rArrowXLoc, 235, arrowXSize, arrowYSize, rArrowPath, "addhp");
        hudObjects.add(hpRemove);
        hudObjects.add(hpAdd);

    }

    private void addBar(String stat) {
        if (remainingBars == 0) {
            return;
        }

        if (stat.compareToIgnoreCase("move") == 0) {
            moveStat++;
        }
        if (stat.compareToIgnoreCase("attack") == 0) {
            attackStat++;
        }
        if (stat.compareToIgnoreCase("defense") == 0) {
            defenseStat++;
        }
        if (stat.compareToIgnoreCase("draw") == 0) {
            drawStat++;
        }
        if (stat.compareToIgnoreCase("hp") == 0) {
            hpStat++;
        }
        remainingBars--;
        System.out.println("Remaining Stats:" + remainingBars);
    }

    private void removeBar(String stat) {
        if (stat.compareToIgnoreCase("move") == 0) {
            if (moveStat > 0) {
                moveStat--;
                remainingBars++;
            }
        }
        if (stat.compareToIgnoreCase("attack") == 0) {
            if (attackStat > 0) {
                attackStat--;
                remainingBars++;
            }
        }
        if (stat.compareToIgnoreCase("defense") == 0) {
            if (defenseStat > 0) {
                defenseStat--;
                remainingBars++;
            }
        }
        if (stat.compareToIgnoreCase("draw") == 0) {
            if (drawStat > 0) {
                drawStat--;
                remainingBars++;
            }
        }
        if (stat.compareToIgnoreCase("hp") == 0) {
            if (hpStat > 0) {
                hpStat--;
                remainingBars++;
            }
        }
        System.out.println("Remaining Stats:" + remainingBars);
    }

    private void buildModelPreviewer() {
        int windowxSize = 640;
        int windowySize = 400;
        int yposition = 0;
        int xposition = 570;
        int charxOffset = windowxSize / 2 - 25;
        int charyOffset = windowySize / 2;

        hudObject dispModel = new hudObject(xposition + charxOffset, yposition + charyOffset, 50, 100, modelPath, "changeSkin");
        hudObject modBg = new hudObject(xposition, yposition, windowxSize, windowySize, "pics/hud/charSetup/modBG.png", "");
        hudObject modOver = new hudObject(xposition, yposition, windowxSize, windowySize, "pics/hud/charSetup/modOver.png", "");
        hudObjects.add(modBg);
        hudObjects.add(dispModel);
        hudObjects.add(modOver);
    }

    private void changeSkin() {
        Random generator = new Random();
        int i = generator.nextInt(4) + 1;
        modelPath = "pics/character/testLady/" + i + ".png";
        
    }

}
