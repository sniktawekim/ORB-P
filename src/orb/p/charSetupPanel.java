/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p;

import java.awt.Graphics;

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
        }

        buildHUD();
    }

    protected void buildStatTable() {
        hudObject buttonBg = new hudObject(4, 4, 400, 300, "pics/hud/charSetup/statTable.png", "");
        hudObject remStatBin = new hudObject(460, 60, 50, 200, "pics/hud/charSetup/remStatBin.png", "");
        hudObjects.add(buttonBg);
        hudObjects.add(remStatBin);

        for (int offset = 0; offset < moveStat; offset++) {
            hudObject movementBar = new hudObject(120 + (15 * offset), 60, 15, 30, "pics/hud/charSetup/bar.png", "");
            hudObjects.add(movementBar);
        }

        for (int offset = 0; offset < attackStat; offset++) {
            hudObject attackBar = new hudObject(120 + (15 * offset), 104, 15, 30, "pics/hud/charSetup/bar.png", "");
            hudObjects.add(attackBar);
        }
        for (int offset = 0; offset < defenseStat; offset++) {
            hudObject defenseBar = new hudObject(120 + (15 * offset), 150, 15, 30, "pics/hud/charSetup/bar.png", "");
            hudObjects.add(defenseBar);
        }
        for (int offset = 0; offset < drawStat; offset++) {
            hudObject drawBar = new hudObject(120 + (15 * offset), 193, 15, 30, "pics/hud/charSetup/bar.png", "");
            hudObjects.add(drawBar);
        }
        for (int offset = 0; offset < hpStat; offset++) {
            hudObject hpBar = new hudObject(120 + (15 * offset), 235, 15, 30, "pics/hud/charSetup/bar.png", "");
            hudObjects.add(hpBar);
        }
        for(int offset = 0; offset<remainingBars; offset++){
            hudObject remBar = new hudObject(470, 235-(15*offset), 30, 15, "pics/hud/charSetup/hbar.png", "");
            hudObjects.add(remBar);
        }
        
        
        
        hudObject movementRemove = new hudObject(410, 60, 15, 30, "pics/hud/charSetup/la.png", "remmove");      
        hudObject movementAdd = new hudObject(435, 60, 15, 30, "pics/hud/charSetup/ra.png", "addmove");
        hudObjects.add(movementRemove);
        hudObjects.add(movementAdd);
        
        hudObject attackRemove = new hudObject(410, 104, 15, 30, "pics/hud/charSetup/la.png", "remattack");      
        hudObject attackAdd = new hudObject(435, 104, 15, 30, "pics/hud/charSetup/ra.png", "addattack");
        hudObjects.add(attackRemove);
        hudObjects.add(attackAdd);
        
        hudObject defenseRemove = new hudObject(410, 150, 15, 30, "pics/hud/charSetup/la.png", "remdefense");      
        hudObject defenseAdd = new hudObject(435, 150, 15, 30, "pics/hud/charSetup/ra.png", "adddefense");
        hudObjects.add(defenseRemove);
        hudObjects.add(defenseAdd);
        
        hudObject drawRemove = new hudObject(410, 193, 15, 30, "pics/hud/charSetup/la.png", "remdraw");      
        hudObject drawAdd = new hudObject(435, 193, 15, 30, "pics/hud/charSetup/ra.png", "adddraw");
        hudObjects.add(drawRemove);
        hudObjects.add(drawAdd);
        
        hudObject hpRemove = new hudObject(410, 235, 15, 30, "pics/hud/charSetup/la.png", "remhp");      
        hudObject hpAdd = new hudObject(435, 235, 15, 30, "pics/hud/charSetup/ra.png", "addhp");
        hudObjects.add(hpRemove);
        hudObjects.add(hpAdd);

    }

    private void addBar(String stat) {
        if(remainingBars==0){
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

}
