/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p;

import java.util.ArrayList;

/**
 *
 * @author blainezor
 */
public class Character extends OnScreenObject {

    //TODO Character stats
    Tile currentTile;
    Tile prevTile; //previous tile, for direction calculations
    private int moves = 5;
    private int direction;
    private int prevDirection;
    
    private String charPath;
    

    public Character(Tile startTile, String characterPath) {
        //TODO Display position is off
        super(startTile.xLoc, startTile.yLoc, 120, 140);
        xOffset = 30;
        currentTile = startTile;

        //TODO AC
        direction = 4;
        charPath = characterPath;
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
        
        
        if(currentNWSE-prevNWSE>0){//moving towards bottom right
            setDirection(4);
        } else if(currentNWSE-prevNWSE<0){//moving towards top left
            setDirection(2);
        } else if(currentNESW-prevNESW>0){//moving towards top right
            setDirection(1);
        } else if(currentNESW-prevNESW<0){//moving towards bottom left
            setDirection(3);
        }
        System.out.println("Moved in direction: " + direction);
        
    }

    public void resetMoves() {
        moves = 5;
        
    }

    public void handleMove(int Direction) {      
        if(Direction==0){//a human player move
            prevTile = currentTile;          
        } else if(Direction == 1){ //Northeast
            setDirection(1);
            System.out.println("AI D1 move");
        } else if(Direction == 2){//Northwest
            setDirection(2);
            System.out.println("AI D2 move");
        } else if(Direction == 3){//Southwest
            setDirection(3);
            System.out.println("AI D3 move");
        } else if(Direction == 4){//Southeast
            setDirection(4);
            System.out.println("AI D4 move");
        }else{
            System.out.println("CHARACTER ERROR: invalid move direction");
            return;
        }
        moves--;
        
    }

    public int getMoves() {
        return moves;
    }

    public void setDirection(int toDirection){
        prevDirection = direction;
        direction = toDirection;
        if(prevDirection!=direction){
            changeDirection(direction);
        }
    }

    private void changeDirection(int newDirection) {
       setGraphic(charPath + newDirection +".png");
    }
}
