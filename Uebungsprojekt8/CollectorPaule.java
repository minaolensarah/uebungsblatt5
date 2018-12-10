import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Hamster;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Location;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Territory;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Direction;
import java.io.IOException;

/**
 * Write a description of class PainterPaule here.
 *
 * @author  Sarah Breckner
 *          3425446
 *          st163632@stud.uni-stuttgart.de

 * @author  Kim Lingemann
 *          3380756
 *          st160814@stud.uni-stuttgart.de

 * @version 5
 */
public class CollectorPaule extends SimpleHamsterGame
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class PainterPaule
     */
    public CollectorPaule()
    {
        game.displayInNewGameWindow();
        try {
            game.initialize("/territories/territory-collector.ter");
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
    
    void multiMove (int numberOfSteps) {
           
        for (int i = 0; i<numberOfSteps; i++) {
            paule.move();
        }
    }
    
    /**
     * Paule collects ten grains
     * 
     * Paule picks up ten grains from his current tile
     * 
     * @requires    TEN GRAINS
     * @ensures \old.paule.getGrainCount = paule.getGrainCount - 10
     */
    void collectAll () {
           
        for (int i = 0; i<10; i++) {
            paule.pickGrain();
        }
    }
    
    /**
     * Paule collects all grains
     * 
     * Paule picks up all grains from his current tile
     * 
     * @requires    paule.isInitialised()
     * @ensures \old.paule.getGrainCount = paule.getGrainCount - GRAINS ON THE TILE
     */
    void collectAllGrains () {
           
        while (paule.grainAvailable()) {
            paule.pickGrain();
        }
    }
    
    /**
     * test of collectAll with a runtime exeption thrown because of to few grains
     */
    void test1 () {
        
        multiMove(3);
        collectAll();
        multiMove(3);
        collectAll();
        collectAll();
    }

    /**
     * test of collectAll, no exeptions thrown but paule only partially picks the grains
     * 
     * @requires    paule.isInitialised()
     * @ensures \old.paule.getGrainCount = paule.getGrainCount - 10
     */
    void test2 () {
        
        multiMove(3);
        collectAll();
        multiMove(3);
        collectAll();
    
    }
    
    /**
     * test of collectAll, no exeptions thrown and paule picks all the grains on his tile
     * 
     * @requires    paule.isInitialised()
     * @ensures \old.paule.getGrainCount = paule.getGrainCount - 10
     */
    void test3 () {
        
        multiMove(3);
        collectAll();
        
    
    }
    
    /**
     * test of collectAllGrains
     * 
     * Paule picks all the grains in the territory
     * 
     * @requires    paule.isInitialised()
     * @ensures \old.paule.getGrainCount = paule.getGrainCount - territory.getGrainCount
     */
    void test4 () {
        for (int i = 0; i < 2; i ++) {    
            multiMove(3);
            collectAllGrains();
        }
    }

}


