package org.khovasoft.droid.guesswordz;

/*
 * GuessWordz
 * 
 * GameBlock.Java
 * Set GameBlock Properties
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;


/*
 * GameBlocks class for GameBoard Objects
 */
public class GameBlock extends GameObjects {

		/*
		 * Initialize GameBlock
		 */
        public GameBlock() {
                // GameBlock Initialization
        }
        
        private char value; // Block Value
        private boolean blockVisible;
        private boolean letterVisible;
        public Sprite sprite;
        public TextureRegion texture;
        public int posX;
        public int posY;
        
        /*
         * Get if the Block is currently visible on screen
         */
        public boolean isBlockVisible()
        {
                return blockVisible;
        }
        
        /*
         * Set Block Visibility State
         */
        public void setBlockVisible(boolean option)
        {
                blockVisible = option;
        }
        
        /*
         * Get if the Letter is currently visible on screen
         */
        public boolean isLetterVisible()
        {
                return letterVisible;
        }
        
        /*
         * Set Letter Visibility State
         */
        public void setLetterVisible(boolean option)
        {
                letterVisible = option;
        }
        
        /*
         * Returns Value of Character Block
         */
        public char dspValue() {
                
                return value;
                
        }
        
        /*
         * Set Value of Character Block
         */
        public void setValue(char val) {
                value = val;
        }
        

        
}
