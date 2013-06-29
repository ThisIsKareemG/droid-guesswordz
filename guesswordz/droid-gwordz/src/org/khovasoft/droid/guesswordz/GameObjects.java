/*
 * GuessWordz
 * 
 * GameObjects.Java
 * Assorted Game Dependent Calculations
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */

package org.khovasoft.droid.guesswordz;

import java.util.concurrent.atomic.AtomicInteger;



/*
 * Base Objects for all other game objects
 */
public class GameObjects {
        static AtomicInteger nextId = new AtomicInteger();
        private int id;
        
        // Set Game Objects Name
        public GameObjects () {
                id = nextId.incrementAndGet();
        }
        
        
        // Help display Object
        public int dspID() {   
                return id;              
        }
	} // End Class GameObjects

/*
 * Blocks Class
 * Determine if Block is active or not
 */
class Blocks{
         boolean active; // Once clicked blocks go from active to inactive
	}
