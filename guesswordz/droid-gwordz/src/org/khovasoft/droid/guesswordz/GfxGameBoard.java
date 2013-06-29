/*
 * GuessWordz
 * 
 * GfxGameBoard.Java
 * Handles GameBoard Graphics Manipulation
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */


package org.khovasoft.droid.guesswordz;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import org.khovasoft.droid.guesswordz.GameFunctions;

import android.util.Log;

/*
 * Defines GfxGameBoard Texture Region
 */
class TXTGameBoard{
        TextureRegion mRedBlockTextureRegion;
        TextureRegion mBlueBlockTextureRegion;
        TiledTextureRegion mLetterBlockTextureRegion;
        BitmapTextureAtlas mTexture;    
}

/*
 * Handles scene rendering of game board
 */
public class GfxGameBoard{

        private static final int GAMEBLOCK_WIDTH = 39;
        private static final int GAMEBLOCK_HEIGHT = 39;
       
        private static final char XAXIS = 'x';
        private static final char YAXIS ='y';
       
        private static final char REDBLOCK = '@';
        private static final char BLUEBLOCK = '#';
       
        // Variables
        int uiPosX; // Set position of UI on screen
        int uiPosY;
       
        private TXTGameBoard gameBoardTexture = new TXTGameBoard();
       
        /*
         * Initialize GameBoard Class
         */
        public GfxGameBoard(){
               
        }
       
        /*
         * Load GameBoard Texture
         */
        public TXTGameBoard loadTexture(TXTGameBoard pTexture)
        {
                gameBoardTexture = pTexture;

                this.gameBoardTexture.mBlueBlockTextureRegion = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,BLUEBLOCK,GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,BLUEBLOCK,GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
                this.gameBoardTexture.mRedBlockTextureRegion = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,REDBLOCK,GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,REDBLOCK,GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                return this.gameBoardTexture;
        }
       
        /*
         * Set GameBoard Position on layout
         */
        public void setUIPosition(int posX, int posY){
               
                uiPosX = posX;
                uiPosY = posY;
               
        }

        /*
         * Build the GameBoard Layout
         */
        public GameBoard buildGameBlocks(GameBoard pGameBoard)
        {
        	int x;
            int y;
           
            x = uiPosX;
            y = uiPosY;
           
            //************ Determine and Set the Position of the Blocks
            int i = 0;

	        for (int row = 1; row <= GameBoard.BOARD_NUM_OF_ROWS; row++)
	        {
	            for (int col = 1; col <= GameBoard.BOARD_ROW_LENGTH; col++)
	            {
	                if (i < GameBoard.MAX_PIECES)
	                {
	                    pGameBoard.block[i].posX = x;
	                    pGameBoard.block[i].posY = y;
	                   
	                    x += GAMEBLOCK_WIDTH;
	                    i++;
	
	                }// END IF Statement
	               
	            }// End For Loop COL
	           
	            x = uiPosX;
	            y += GAMEBLOCK_HEIGHT;
	        }// END for Loop ROW
	               
        	return pGameBoard;
        }
	       
        /*
         * Assign Spites to GameBoard
         */
        public GameBoard assignGameBlockSprites(GameBoard pGameBoard)
        {
               
            char theLetter;
           
            //************ Configure Texture for all the Blocks
            Log.i(GuessWordz.TAG, "[GfxGameBoard] Configuring Textures for Blocks");
           
        	for (int c = 0; c < GameBoard.MAX_PIECES; c++)
        	{
        		if (pGameBoard.block[c].isBlockVisible() == true)
            	{
                    pGameBoard.block[c].texture = this.gameBoardTexture.mBlueBlockTextureRegion;            
                }
                else
                {
                    pGameBoard.block[c].texture = this.gameBoardTexture.mRedBlockTextureRegion;
                }
                       
                if (pGameBoard.block[c].isLetterVisible() == true)
                {
                        theLetter = GameFunctions.convertChar(pGameBoard.block[c].dspValue());
                
                        switch (theLetter)
                        {
                            case 'a':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'a',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'a',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
                                break;
                            case 'b':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'b',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'b',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
                                break;
                            case 'c':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'c',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'c',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'd':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'d',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'d',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'e':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'e',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'e',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'f':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'f',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'f',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'g':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'g',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'g',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'h':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'h',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'h',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'i':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'i',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'i',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'j':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'j',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'j',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'k':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'k',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'k',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'l':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'l',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'l',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'm':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'m',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'m',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'n':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'n',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'n',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'o':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'o',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'o',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'p':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'p',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'p',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'q':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'q',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'q',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'r':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'r',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'r',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 's':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'s',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'s',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 't':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'t',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'t',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'u':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'u',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'u',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'v':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'v',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'v',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'w':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'w',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'w',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'x':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'x',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'x',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'y':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'y',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'y',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case 'z':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'z',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'z',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case '-':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'-',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'-',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case '/':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'/',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'/',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case '\'':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,'\'',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,'\'',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                            case ',':
                                pGameBoard.block[c].texture = TextureRegionFactory.extractFromTexture(this.gameBoardTexture.mTexture, GameFunctions.findLetterGFX(XAXIS,',',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GameFunctions.findLetterGFX(YAXIS,',',GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT), GAMEBLOCK_WIDTH, GAMEBLOCK_HEIGHT, true);
               
                                break;
                        }
                       
                    }
                	// Load up sprites based on texture
                    pGameBoard.block[c].sprite = new Sprite (pGameBoard.block[c].posX , pGameBoard.block[c].posY, pGameBoard.block[c].texture );    
        	}
            Log.i(GuessWordz.TAG, "[GfxGameBoard] Textures for Blocks Configured");              
            return pGameBoard;
        }

        /*
         * Load GameBoard to Screen
         */
        public Scene loadDisplay(Scene scene, GameBoard pGameBoard){
               
                Log.i(GuessWordz.TAG, "Start Building Scene");
                for (int x = 0; x < GameBoard.MAX_PIECES; x++){
                        scene.attachChild(pGameBoard.block[x].sprite);
                        
                        }
               
                Log.i(GuessWordz.TAG, "Finished Building Scene");
                return scene;
        }
   
}


