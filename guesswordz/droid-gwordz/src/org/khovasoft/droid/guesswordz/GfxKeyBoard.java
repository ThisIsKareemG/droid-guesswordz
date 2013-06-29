/*
 * GuessWordz
 * 
 * GfxKeyBoard.Java
 * Handles OnScreen KeyBoard Graphics Manipulation
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */


package org.khovasoft.droid.guesswordz;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.inputmethodservice.Keyboard;
import android.util.Log;

//===========================================================
// Constructors
// ===========================================================
	

/*
 * TXTKeyBoardBlock Class
 * Set Textures and Bitmaps for Alphabet Block
 */
class TXTKeyBoardBlock{
	TiledTextureRegion mKeyBoardTextureRegion;
	BitmapTextureAtlas mKeyBoardTexture;
	TextureRegion[] mKeyBoard = new TextureRegion[26];
	}

/*
 * Set KeyBoard Objects
 */
class KeyBoardData{
	TextureRegion mKeyBoardTR;
	Texture mTexture;
	Sprite sprite;
	int XPOS;
	int YPOS;
	char Value;
	boolean active;
	}

// ===========================================================
// Methods for/from SuperClass/Interfaces
// ===========================================================

/*
 * GfxKeyBoard Class
 * Handles Large Keyboard for Graphical Game Input
 */
public class GfxKeyBoard {
	
    // Constants
    final int MAXLETTERS = 26; //Letters in Alphabet (0 - 25)
    final int BOARD_NUM_OF_ROWS = 4;
    final int BOARD_ROW_LENGTH = 7;
    final int PADDING = 10;
    final char XAXIS = 'x';
    final char YAXIS ='y';
   
    // Vars
    TextureRegion[] mScrollBlock;
    Texture mTexture;
   
 
    int BlockWidth;
    int BlockHeight;

    public int chosenLetter; // Letter chosen via the HUD
    public boolean keyBoardPressed = false;
   
    int uiPosX; // Set position of UI on screen
    int uiPosY;
   
    float lastTouchX; // Last Touch Position X
    float lastTouchY; // Last Touch Position Y

    Blocks[] block = new Blocks[MAXLETTERS];
    KeyBoardData[] keyBoard = new KeyBoardData[MAXLETTERS];
   
    /*
     * Initialize the Game KeyBoard
     */
    public GfxKeyBoard(){
        	
    	Log.i(GuessWordz.TAG, "[GfxKeyBoard] Start Init Class");
    	
        // Initializing the Blocks
        for (int x = 0; x < MAXLETTERS; x++){  
        	    keyBoard[x] = new KeyBoardData();
        	    keyBoard[x].active = true;
                
        }      
       
        Log.i(GuessWordz.TAG, "[GfxKeyBoard] Block Building Initialized");
        
        BlockWidth = 60; // Default Block Width
        BlockHeight = 60;// Default Block Height
       
        uiPosX = 10; uiPosY = 10; // Default Position  
        
        Log.i(GuessWordz.TAG, "[GfxKeyBoard] Block Building Finished");

    }
    
    /*
     * Load and sorts the Texture for the letters
     */
    
    public void setTexture(Texture fTexture, TextureRegion[] fTextureRegion){
           
    	Log.i(GuessWordz.TAG, "[GfxKeyBoard] SetTexture Initialized");
            mTexture =  fTexture;
            mScrollBlock = fTextureRegion;
            loadScrollBlock(); // Breaks up the texture regions to correspond with letters
            Log.i(GuessWordz.TAG, "[GfxKeyBoard] SetTexture Finished");
    }
  
    /*
     * Change Block Width and Height Default settings
     */
    public void setBlockDim(int width, int height){
   
            BlockWidth = width;
            BlockHeight = height;
           
    }
   
    /*
     * Change UI Position on the screen Default settings
     */
    public void setUIPosition(int posX, int posY){
           
            uiPosX = posX;
            uiPosY = posY;
           
    }
   

    
    /*
     * Load KeyBoard to Screen
     */
    public Scene loadDisplay(Scene scene, GameBoard pGameBoard){
 
    	 Log.i(GuessWordz.TAG, "[GfxKeyBoard] LoadDisplay Started");
    	 int x;
         int y;
         int i = 0;

         x = uiPosX;
         y = uiPosY;
	
    	// Initializing the Blocks
        for (int row = 1; row <= BOARD_NUM_OF_ROWS; row++){  
        	for (int col = 1; col <= BOARD_ROW_LENGTH; col++){
        		if (i < MAXLETTERS)
        		{
        			keyBoard[i].XPOS = x;
        			keyBoard[i].YPOS = y;
        			
        			keyBoard[i].sprite = new Sprite(x, y, keyBoard[i].mKeyBoardTR)
        			{  				
        				@Override
        				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
        					
        					int letterTouched;
        					
        					Log.i(GuessWordz.TAG, "[GfxKeyBoard] Block Touched id:" + pSceneTouchEvent.hashCode() + " x:" + pTouchAreaLocalX + " Y " + pTouchAreaLocalY);
        					float[] blockCoordinates = null;
        					blockCoordinates = new float[2];
        					blockCoordinates = this.convertLocalToSceneCoordinates(pTouchAreaLocalX, pTouchAreaLocalY, blockCoordinates);
        					Log.i(GuessWordz.TAG, "[GfxKeyBoard] SceneCoord X" + blockCoordinates[0] + " Y" + blockCoordinates[1]);
        					letterTouched = matchLetter(blockCoordinates[0],blockCoordinates[1]);
        					setLetterTouched(letterTouched);
        					
        					
        					return true;
        				}
        				
        			};
        			scene.attachChild(keyBoard[i].sprite);
        			x += PADDING;
        	
        			scene.registerTouchArea(keyBoard[i].sprite);
        			
        			
                    x += BlockWidth;
                    i++;
        		}// END IF Statement
        		
        	}// End For Loop COL
    	
        	 x = uiPosX;
             y += BlockHeight;
     		 y += PADDING;
    	

        }
        
        Log.i(GuessWordz.TAG, "[GfxKeyBoard] LoadDisplay Ended");
        
    	return scene;
    }
 
    // ===========================================================
    // Methods
    // ===========================================================   
 
    /*
     * Pass value of touched letter
     */
    private void setLetterTouched(int value)
    {
    	Log.i(GuessWordz.TAG, "[GfxKeyBoard] I just Set Letter Touched!!" + value);
    	chosenLetter = value;
    	keyBoardPressed = true;
    	
    }
    
    /*
     * Match Letter against sprite coordinates
     */
    private int matchLetter(float xpos, float ypos)
    {
    	float x1,x2,y1,y2;
    	char letter;
    	int value = 999;
    	
    	//TODO Set up while loop 
    	for (int i = 0; i < MAXLETTERS; i++){  
        	x1 = keyBoard[i].XPOS;
        	x2 = keyBoard[i].XPOS + 60;
        	y1 = keyBoard[i].YPOS;
        	y2 = keyBoard[i].YPOS + 60;
        	
           	if (GameFunctions.isInBox(xpos, ypos, x1, x2, y1, y2)){
        		letter = GameFunctions.number2letter(i);
        		Log.i(GuessWordz.TAG, "Letter Touched :" + letter);
        		value = i;
        		
        	}
    		
    	}
    	
    	return value;
    	
    }
   
    
    /*
     * Assign the Texture Location from the letter map to the Letter variable
     */
    private void loadScrollBlock()
    {
    	Log.i(GuessWordz.TAG, "[GfxKeyBoard] Configuring Textures Started");
           
            keyBoard[0].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'a', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'a',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[1].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'b', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'b',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[2].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'c', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'c',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[3].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'d', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'d',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[4].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'e', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'e',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[5].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'f', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'f',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[6].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'g', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'g',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[7].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'h', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'h',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[8].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'i', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'i',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[9].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'j', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'j',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[10].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'k', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'k',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[11].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'l', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'l',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[12].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'m', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'m',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[13].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'n', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'n',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[14].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'o', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'o',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[15].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'p', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'p',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[16].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'q', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'q',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[17].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'r', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'r',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[18].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'s', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'s',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[19].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'t', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'t',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[20].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'u', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'u',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[21].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'v', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'v',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[22].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'w', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'w',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[23].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'x', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'x',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[24].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'y', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'y',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            keyBoard[25].mKeyBoardTR = TextureRegionFactory.extractFromTexture(mTexture, GameFunctions.findLetterGFX(XAXIS,'z', BlockWidth, BlockHeight), GameFunctions.findLetterGFX(YAXIS,'z',BlockWidth, BlockHeight), BlockWidth, BlockHeight, true);
            Log.i(GuessWordz.TAG, "[GfxKeyBoard] Configuring Textures Ended");
            
            for (int i = 0; i < MAXLETTERS; i++)
            {
            	keyBoard[i].Value = GameFunctions.number2letter(i);
            }
            
            Log.i(GuessWordz.TAG, "[GfxKeyBoard] Configuring Textures Ended");
    }
   

	
}
