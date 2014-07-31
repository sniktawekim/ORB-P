

package orb.p.core;

import java.io.PrintWriter;
import java.util.ArrayList;
import orb.p.ORBP;

/**
 *
 * @author mwatkins
 */
public class CharStats {
    private String name;//the name of the character
    private int movement=-1;
    private int attack=-1;
    private int defense=-1;
    private int draw=-1;
    private int hp=-1;
    private String root;//the path to the graphics folder for the character
    
    public CharStats(String name, int movement, int attack, int defense, int draw, int hp, String root){
        this.name = name;
        this.movement = movement;
        this.attack = attack;
        this.defense = defense;
        this.draw = draw;
        this.hp = hp;
        this.root = root;
    }
    
    public void saveChar(String saveTo){
        ArrayList<String> toFile = new ArrayList();
        toFile.add("<character>");
        toFile.add("<name>" + name + "</name>" );
        toFile.add("<movement>" + movement + "</movement>");
        toFile.add("<attack>" + attack + "</attack>");
        toFile.add("<defense>" + defense + "</defense>");
        toFile.add("<draw>" + draw + "</draw>");
        toFile.add("<hp>" + hp + "</hp>");
        toFile.add("<root>" + root +  "</root>");
        
        //write the array to file (library/characters)
        for(int i=0; i<toFile.size(); i++){
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
    
    public void loadChar(String path){
        //open the file
        //get "character" line
            
        //get "name" line
            //strip name line of "<name>" and "</name>"
            //set name variable to stripped string
        
        //get "movement" line
            //strip movement line of "<movement>" and "</movement>"
            //set movement variable to stripped and converted string
        
        //get "attack" line
            //strip attack line of "<attack>" and "</attack>"
            //set attack variable to stripped and converted string
        
        //get "defense" line
            //strip defense line of "<defense>" and "</defense>"
            //set defense variable to stripped and converted string
        
        //get "draw" line
            //strip draw line of "<draw>" and "</draw>"
            //set draw variable to stripped and converted string
        
        //get "hp" line
            //strip hp line of "<hp>" and "</hp>"
            //set hp variable to stripped and converted string
        
        //get "root" line
            //strip root line of "<root>" and "</root>"
            //set root variable to stripped and converted string
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /////////////////its just turtles, all the way down\\\\\\\\\\\\\\\\\\\


    //gettin
    public String getName(){
        return name;
    }
    public String getRoot(){
        return root;
    }
    public int getMoves(){
        return movement;
    }
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }
    public int getDraw(){
        return draw;
    }
    public int getHp(){
        return hp;
    }
    //'n settin
    public void setName(String name){
        this.name = name;
    }
    public void setRoot(String root){
        this.root = root;
    }
    public void setMoves(int movement){
        this.movement = movement;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    public void setDefense(int defense){
        this.defense = defense;
    }
    public void setDraw(int draw){
        this.draw = draw;
    }
    public void setHP(int hp){
        this.hp = hp;
    }
}

