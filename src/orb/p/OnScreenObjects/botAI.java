package orb.p.OnScreenObjects;
/**
 * AI FOR TERMINATOR
 * @author Michael Watkins
 */
public class botAI extends Person{
    public double panic = 33;

    public botAI(String charId, Tile startTile, String characterPath, int panicAt) {
        super(charId, startTile, characterPath);
        panic = panicAt;
    }
    
    
    ///riskc>roll>destw>pathg>movew
    public void makeMove(){
        if(riskCheck()){
            //not panic
            int dMove = rollDice();
            
        } else {//panic
            if(restCheck()){
                rest();
            } else {//run
                runAway();
            }
            
        }
    }
    
    
    private void moveWeigher(){//weighs best paths and selects one.
      
    }
    private void pathFinder(){//calculates possible paths to destination options
        //for each destination option
         //populate possible route array
         //for each route array
           //assess trap risk
           //assign route score
         //assign best route for destination
        //assign destination route with best score (must include destination weight)
         
        
    }
    private void destinationWeigher(){//measure value of each possible destination tile
        //populates an array with total distance options, with different tiers
        //to consider the card usage.
        //for each distance options
          //calculate score for each tile 
          //subtract card spent value
          //add all tiles above "score threshold" to viable options array 
           //where score threshold is within a percentage of the best option
        
        //fills an array with best destination options
    }
    private boolean riskCheck(){//to rest or to roll?
        double hpPercent = currentHP/stats.getMaxHP();
        return hpPercent>panicPercent();//passes the risk check - rolls the movement dice
    }
    private boolean restCheck(){//chooses between resting or running
        return true; //passes to rest check - resting is smarter than running
    }

   

    private void runAway() {
      
    }

    private double panicPercent() {
        double total = panic;
        //total += consider gathered items
        //total += consider defense stats
        //total += consider intimidating enemies nearby
        return total;
    }

    private int rollDice() {
        //issue movement roll command
        return 3;//return amount rolled.
    }
     private void rest() {
       //issue rest command
    }
}
