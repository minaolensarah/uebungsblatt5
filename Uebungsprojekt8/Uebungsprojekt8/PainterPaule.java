import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Hamster;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Location;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Territory;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Direction;
import java.io.IOException;
import de.unistuttgart.iste.rss.oo.hamstersimulator.internal.model.InputInterface;
import de.unistuttgart.iste.rss.oo.hamstersimulator.ui.javafx.JavaFXInputInterface;

/**
 * Paule paints the numbers from 0 to 7 in binary digits.
 *
 * @author  Sarah Breckner
 *          3425446
 *          st163632@stud.uni-stuttgart.de

 * @author  Kim Lingemann
 *          3380756
 *          st160814@stud.uni-stuttgart.de

 * @version 1
 */ 
public class PainterPaule extends SimpleHamsterGame
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class PainterPaule
     */
    public PainterPaule()
    {
        game.displayInNewGameWindow();
        try {
            game.initialize("/territories/territory-painting.ter");
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Paule moves
     * 
     * Paule makes a certain number of steps.
     * @param       numberOfSteps, number of steps Paule should do
     * @requires    frontIsClear() for each of Paule's steps
     * @requires    paule.isInitialized()
     * @ensures     Paule is numberOfSteps tiles away from his old location
     */
     void multiMove (int numberOfSteps) {
           
        for (int i = 0; i<numberOfSteps; i++) {
            paule.move();
        }
    }
    
    /*
    /**
     * Paule is initialized
     * 
     * Paule lives in the territory, he has 324 grains in his mouth, stands on (0,0) and is facing east
     * @requires    territory exists
     * @ensures     paule.isInitialized()
     */
    /*void initialPosition() {
        Hamster paule = new Hamster();
        Territory territory = game.getTerritory();
        paule.init(territory, Location.from(0,0), Direction.EAST, 324);
        }
    */
   
    /**
     * Paule turns to the right
     * 
     * @requires the territory exists
     * @requires Paule is initialized
     * @ensures if Paule was facing north, he is now facing east
     * @ensures if Paule was facing east, he is now facing south
     * @ensures if Paule was facing south, he is now facing west
     * @ensures if Paule was facing west, he is now facing north
     */
     private void turnRight() {
        paule.turnLeft();
        paule.turnLeft();
        paule.turnLeft();
    }
    
    /**
     * Paule walks to the position so he can start painting
     * 
     * Paule moves to the third tile in the bottom row
     */
     private void pauleToStart() {
        turnRight();
        multiMove(4);
        paule.turnLeft();
        multiMove(2);
        paule.turnLeft();
    }
        
    /**
     * Paule fills the tile with 12 grains
     * 
     * Paule puts 12 grains on his current tile
     * @requires    paule.getGrainCount() >= 12
     * @ensures     \old paule.getGrainCount() = paule.getGrainCount + 12
     * @ensures     there are 12 grains more on the tile
     */    
    void fillTile() {
         for (int i = 0; i<12; i++) {
            paule.putGrain();
        }
        
    }
    
    /**
     * Paule paints the number 1
     * 
     * He starts in the bottom row in the middle and ends at the same tile facing the wall
     * @requires    paule.getGrainCount >= 36
     * @requires    frontIsClear() for the next 4 tiles
     * @ensures     \old paule.getGrainCount() = paule.getGrainCount + 36
     * @ensures     Paule painted the number 1
     */
    void paintOne() {
        paule.move();
        for (int i = 0; i<3; i++) {
            fillTile();
            paule.move();
        }
        paule.turnLeft();
        paule.turnLeft();
        multiMove(4);
    }
    
    /**
     * Paule paints one edge of the number 0
     * 
     * Paule starts in the corner of the number 0, turns to the right and fills the two tiles in front of him
     * @requires    paule.getGrainCount >= 24
     * @requires    frontIsClear() for the next 2 tiles
     * @ensures     \old paule.getGrainCount() = paule.getGrainCount + 24
     * @ensures     Paule painted two tiles   
     */
    private void paintEdge() {
        turnRight();
        paule.move();
        fillTile();
        paule.move();
        fillTile();
    }
    
    /**
     * Paule paints the number 0
     * 
     * He starts in the bottom row in the middle end ends at the same tile facing the wall
     * 
     */
    void paintZero() {
        paule.move();
        fillTile();
        paule.turnLeft();
        paule.move();
        fillTile();
        paintEdge();
        paintEdge();
        paintEdge();
        paule.move();
        turnRight();
        paule.move();
        paule.turnLeft();
    }
    
    /**
     * Paule creates space between the digits
     * 
     * Paule moves from the end position of the last number to the start position to paint the next number
     * @requires    frontIsClear() for each step
     * @ensures     \old paule.getLocation().getColumn() = paule.getLocation().getColumn() - 4
     */
     void space() {
        paule.turnLeft();
        multiMove(4);
        paule.turnLeft();
    }
    
    /**
     * Tests the painting of number one ("001") after implementing correctly:
     * initialPosition() - which puts paule in the first position ready to 
     *                     paint a digit
     * paintZero() - paints the digit 0
     * paintOne() - paints the digit 
     * space() - space after a digit one or zero put paule to ready position
     *           for the next digit
     */
     public void testPaintingOne(){
        //initialPosition();
        pauleToStart();
        paintZero();
        space();
        paintZero();
        space();
        paintOne();     
    }
    
    /**
     * Converts a decimal number to a binary number
     * 
     * @param   number, integer to be converted to binary representation
     * @return  binary representation of number
     */
     private String integerToBinary(int number) {
        return Integer.toBinaryString(number);
    }

    /**
     * Asks the user which number he wants Paule to convert into binary representation and converts it
     */
    void askNumber() {
        JavaFXInputInterface whatNumber = new JavaFXInputInterface();
        int number;
        do {
           number = whatNumber.readInteger("Welche Zahl zwischen 0 und 7 soll Paule umwandeln und malen?");
        } while (number > 8);
        integerToBinary(number);
    } 
    
    
    /**
     * returns the binary representation of an integer
     * 
     * @param   number, integer to be converted to binary number
     * @return  binary, binary representation of the given Integer
     * @requires  number has to be an Integer
     * @ensures binary representation of Integer
     */
     String umrechnen(int number) {
        String binary = "";
        for(int i =0; i<3; i++){
            int a = number%2;
            number = number/2;
            
            binary = Integer.toString(a) + binary;
            
        }
        return binary;
        
    }
   /**
     * Paule paints a number choosen by the user
     * 
     * The user chooses a number from 0 to 7 and Paule converts it to binary representation. After that he paints it through placing grains in the territory
     * 
     * @requires    the user's input has to be an integer
     * @requires    paule.getGrainCount >= 324
     * @requires    paule.getDirection = Direction.EAST
     * @requires    no walls in the territory
     * @ensures     \old paule.getGrainCount() > paule.getGrainCount 
     * @ensures     Paule painted a binary number   
     * @ensures     paule.getLocation = (10, 4)
     */
    void paintDigit() {
        JavaFXInputInterface whatNumber1 = new JavaFXInputInterface();
        int input;
        do {
           input = whatNumber1.readInteger("Bitte eine Zahl von 0 bis 7 eingeben");
        } while (!(input>=0 && input<=7));
        String binary = umrechnen(input);
        int a=0;
        pauleToStart();
        
        while(a<3) {
            if(String.valueOf(0).charAt(0)==(binary.charAt( a))) {
                paintZero();
            } else {
                paintOne();
            }
            if(a != 2) {
                space();
            }
            a++;
            
        }
        
    }
    
    
}
