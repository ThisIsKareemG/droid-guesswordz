package org.khovasoft.droid.guesswordz;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.HorizontalAlign;

import android.util.Log;

/*
 * Defines GfxGameBoard Texture Region
 */
class TXTScoreBoard{

        BitmapTextureAtlas mFontTexture;    
        Font mFont;
}


public class GfxScoreBoard {
	
    // Variables
    int uiPosX; // Set position of UI on screen
    int uiPosY;
    
    Font mFont;
    
    private TXTScoreBoard scoreBoardTexture = new TXTScoreBoard();

    /*
     * Initialize GameBoard Class
     */
    public GfxScoreBoard(){
           
    }
	
    /*
     * Set GameBoard Position on layout
     */
    public void setUIPosition(int posX, int posY){
           
            uiPosX = posX;
            uiPosY = posY;
            Log.i(GuessWordz.TAG, "[GfxScoreBoard] UI Set");
           
    }
    
    public void setTexture(Font pFont)
    {
    	mFont = pFont;
    	
    	 Log.i(GuessWordz.TAG, "[GfxScoreBoard] Texture Loaded");
    	
    }
    
    public Scene loadDisplay(Scene pScene, GameBoard pGameBoard, int value)
    {
    	int testValue = 5;
    	final Text textGameScore = new Text(uiPosX, uiPosY, mFont, "Score: " + pGameBoard.theScore.getScore(), HorizontalAlign.LEFT);
       
        Log.i(GuessWordz.TAG, "[GfxScoreBoard] textGameScore");

        pScene.attachChild(textGameScore);
        
       

        
        Log.i(GuessWordz.TAG, "[GfxScoreBoard] Display Loaded");
        
    	return pScene;
    }

}
