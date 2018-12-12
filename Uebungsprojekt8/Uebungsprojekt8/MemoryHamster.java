import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Hamster;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Location;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Territory;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Direction;
import java.io.IOException;
/**
 * Write a description of class MemoryHamster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MemoryHamster extends SimpleHamsterGame{
    
    
    public MemoryHamster(){
       game.displayInNewGameWindow();
        try {
            game.initialize("/territories/territory-ub8.ter");
            
        } catch (IOException ex){
            throw new RuntimeException(ex);
        } 
    }
    

    public void reverseOrder(){
          
  
    }
    
    public void inOrder(){

    }
}