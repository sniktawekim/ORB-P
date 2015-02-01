package orb.p.core;

import java.io.PrintWriter;
import java.util.ArrayList;
import orb.p.ORBP;
import orb.p.Properties;

/**
 *
 * @author mwatkins
 */
public class PersonStats {

    private String name;//the name of the character
    private int agillity = -6;
    private int strength = -1;
    private int defense = -1;
    private int draw = -1;
    private int sense = -1;
    private int maxHP = -1;
    private int mastery = -1;
    private int maxCharge = -1;
    private String root;//the path to the graphics folder for the character

    public PersonStats(String name, int agillity, int strength, int defense, int draw, int maxHP, int sense, int mastery, int maxCharge, String root) {
        this.name = name;
        this.agillity = agillity;
        this.strength = strength;
        this.defense = defense;
        this.draw = draw;
        this.maxHP = maxHP;
        this.root = root;
        this.sense = sense;
        this.mastery = mastery;
        this.maxCharge = maxCharge;
    }

    public void save(String saveTo) {
        ArrayList<String> toFile = new ArrayList();
        toFile.add("<character>");
        toFile.add("<name>" + name + "</name>");
        toFile.add("<agillity>" + agillity + "</agillity>");
        toFile.add("<strength>" + strength + "</strength>");
        toFile.add("<sense>" + sense + "</sense>");
        toFile.add("<mastery>" + mastery + "</mastery>");
        toFile.add("<maxCharge>" + maxCharge + "</maxCharge>");
        toFile.add("<defense>" + defense + "</defense>");
        toFile.add("<draw>" + draw + "</draw>");
        toFile.add("<hp>" + maxHP + "</hp>");
        toFile.add("<root>" + root + "</root>");

        //write the array to file (library/characters)
        for (int i = 0; i < toFile.size(); i++) {
            System.out.println(toFile.get(i));
        }

        try {
            PrintWriter writer = new PrintWriter(ORBP.libraryPath + "characters/" + name + ".txt", "UTF-8");//for output file

            for (int i = 0; i < toFile.size(); i++) {
                writer.println(toFile.get(i));
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        toFile = new ArrayList();
    }

    public PersonStats(String path) {
        path = ORBP.libraryPath + path;
        ArrayList<String> fileContents = Properties.readFile(path);
        System.out.println("LOADING PERSON AT: " + path);
        if (!fileContents.get(0).contains("<character>")) {
            System.out.println("CORRUPT CHARACTER FILE");
            return;
        }
        for (int i = 1; i < fileContents.size() - 1; i++) {
            String cLine = fileContents.get(i);
            String tag = "";
            if (cLine.contains("<name>")) {
                tag = "name";
                cLine = Properties.removeXML(cLine);
                name = cLine;
            } else if (cLine.contains("<agillity>")) {
                tag = "agillity";
                cLine = Properties.removeXML(cLine);
                agillity = Integer.parseInt(cLine);
            } else if (cLine.contains("<strength>")) {
                tag = "strength";
                cLine = Properties.removeXML(cLine);
                strength = Integer.parseInt(cLine);
            } else if (cLine.contains("<defense>")) {
                tag = "defense";
                cLine = Properties.removeXML(cLine);
                defense = Integer.parseInt(cLine);
            } else if (cLine.contains("<draw>")) {
                tag = "draw";
                cLine = Properties.removeXML(cLine);
                draw = Integer.parseInt(cLine);
            } else if (cLine.contains("<hp>")) {
                tag = "hp";
                cLine = Properties.removeXML(cLine);
                maxHP = Integer.parseInt(cLine);
            } else if (cLine.contains("<sense>")) {
                tag = "sense";
                cLine = Properties.removeXML(cLine);
                sense = Integer.parseInt(cLine);
            } else if (cLine.contains("<mastery>")) {
                tag = "mastery";
                cLine = Properties.removeXML(cLine);
                mastery = Integer.parseInt(cLine);
            } else if (cLine.contains("<maxCharge>")) {
                tag = "maxCharge";
                cLine = Properties.removeXML(cLine);
                maxCharge = Integer.parseInt(cLine);
            } else if (cLine.contains("<root>")) {
                tag = "root";
                cLine = Properties.removeXML(cLine);
                root = cLine;
            }
            //for TSing character loading:
            //System.out.println(tag+ ": " + cLine);
        }
        if (agillity < 0) {
            System.out.println("agillity load error");
        }
        if (strength < 0) {
            System.out.println("strength load error");
        }
        if (defense < 0) {
            System.out.println("defense load error");
        }
        if (draw < 0) {
            System.out.println("draw load error");
        }
        if (maxHP < 0) {
            System.out.println("maxHP load error");
        }
        if (mastery < 0) {
            System.out.println("mastery load error");
        }
        if (sense < 0) {
            System.out.println("sense load error");
        }
        if (maxCharge < 0) {
            System.out.println("maxCharge load error");
        }
        System.out.println("LOAD COMPLETED");
    }

    /////////////////its just turtles, all the way down\\\\\\\\\\\\\\\\\\\
    //gettin
    public String getName() {
        return name;
    }

    public String getRoot() {
        return root;
    }

    public int getMoves() {
        return agillity;
    }

    public int getAttack() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getDraw() {
        return draw;
    }

    public int getMaxHP() {
        return maxHP;
    }

    //'n settin
    public void setName(String name) {
        this.name = name;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setMoves(int movement) {
        this.agillity = movement;
    }

    public void setAttack(int attack) {
        this.strength = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setHP(int hp) {
        this.maxHP = hp;
    }
}
