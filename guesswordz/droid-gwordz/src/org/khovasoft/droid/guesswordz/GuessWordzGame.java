/*
 * GuessWordz
 * 
 * GuessWordzGame.Java
 * Main Game Code
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */

package org.khovasoft.droid.guesswordz;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.KeyEvent;

public class GuessWordzGame extends BaseGameActivity {
	
    // ===========================================================
    // Constants
    // ===========================================================

    
    /* Set the Position of the Game Guess Board */
    private static final int GAMEBOARD_POSX = 230;
    private static final int GAMEBOARD_POSY = 10;
    
    private static final int KEYBOARD_POSX = 230;
    private static final int KEYBOARD_POSY = 180;
    
    private static final int SCOREBOARD_POSX = 10;
    private static final int SCOREBOARD_POSY = 10;
    
    private static final int SCOREBOARD_FONT_MAIN = 16; // Scoreboard Font Size
    
   


    // ===========================================================
    // Fields
    // ===========================================================

    private Camera mCamera;

    private GameBoard theGame = new GameBoard(); 
    private GfxKeyBoard gfxKeyBoard = new GfxKeyBoard();
    private TXTKeyBoardBlock txtKeyBoard = new TXTKeyBoardBlock();
    private GfxGameBoard gfxGameBoard = new GfxGameBoard();
    private TXTGameBoard txtGameBoard = new TXTGameBoard();
    private InputGameBoard inputGuessWordz = new InputGameBoard();
    private GfxScoreBoard gfxScoreBoard = new GfxScoreBoard();
    private TXTScoreBoard txtScoreBoard = new TXTScoreBoard();
   // public GameScore theScore = new GameScore();                                               
    public int TestTouched;
    
    //Screen Test
    private BitmapTextureAtlas testTexture;
    private TextureRegion testScreenTestTextureRegion;


	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Engine onLoadEngine() {
        // Set up Camera
        
        this.mCamera = new Camera(0, 0, GuessWordz.CAMERA_WIDTH, GuessWordz.CAMERA_HEIGHT);
        
        Log.i(GuessWordz.TAG, "[GuessWordzGame] Engine Loaded");
        return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(GuessWordz.CAMERA_WIDTH, GuessWordz.CAMERA_HEIGHT), this.mCamera).setNeedsSound(true));
	}

	@Override
	public void onLoadResources() {
        /* Load Game Engine */
		
        theGame.loadPuzzle();
        Log.i(GuessWordz.TAG, "[GuessWordzGame] Puzzle Loaded");
        
        Log.i(GuessWordz.TAG, "[GuessWordzGame] Loading Textures");
        this.txtGameBoard.mTexture = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.txtGameBoard.mLetterBlockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.txtGameBoard.mTexture, this, "gfx/game_letter_blocks1.png", 0, 0, 5,7);
        Log.i(GuessWordz.TAG, "[GuessWordzGame] Loading Textures: GameBoardGFX Loaded");
        this.txtGameBoard = gfxGameBoard.loadTexture(this.txtGameBoard);
        
        Log.i(GuessWordz.TAG, "[GuessWordzGame] Loading Keyboard Textures");
        this.txtKeyBoard.mKeyBoardTexture = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.txtKeyBoard.mKeyBoardTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.txtKeyBoard.mKeyBoardTexture, this, "gfx/keypad_gfx.png",0,0,5,6);
        this.gfxKeyBoard.setTexture(this.txtKeyBoard.mKeyBoardTexture, this.txtKeyBoard.mKeyBoard);
        Log.i(GuessWordz.TAG, "[GuessWordzGame] Loading Textures: Keyboard Textures Loaded");

        this.txtScoreBoard.mFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR);
        this.txtScoreBoard.mFont = new Font(this.txtScoreBoard.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), SCOREBOARD_FONT_MAIN, true, Color.BLACK);
      //  this.txtScoreBoard.mFont = FontFactory.createFromAsset(this.txtScoreBoard.mFontTexture, this,"font/pf_tempesta_five.ttf", 60, true, Color.RED);
        this.gfxScoreBoard.setTexture(this.txtScoreBoard.mFont);
        
        this.mEngine.getTextureManager().loadTextures(this.txtGameBoard.mTexture, this.txtKeyBoard.mKeyBoardTexture, this.txtScoreBoard.mFontTexture);
        Log.i(GuessWordz.TAG, "[GuessWordzGame] mEngine Textures Loaded");
        this.mEngine.getFontManager().loadFont(this.txtScoreBoard.mFont);
        Log.i(GuessWordz.TAG, "[GuessWordzGame] getFontManager Loaded");

        Log.i(GuessWordz.TAG, "[GuessWordzGame] Resources Loaded");
	
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

        Scene scene = new Scene();
        scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

        gfxGameBoard.setUIPosition(GAMEBOARD_POSX, GAMEBOARD_POSY); // Set Position of GameBoard
        gfxKeyBoard.setUIPosition(KEYBOARD_POSX, KEYBOARD_POSY);
        gfxScoreBoard.setUIPosition(SCOREBOARD_POSX, SCOREBOARD_POSY);
        
        theGame = gfxGameBoard.buildGameBlocks(theGame);
        theGame = gfxGameBoard.assignGameBlockSprites(theGame);
        
        
        /* Draw Board to Screen */
        Log.i(GuessWordz.TAG, "[GuessWordzGame] Loading Scene");
        scene = gfxGameBoard.loadDisplay(scene, theGame);
        scene = gfxKeyBoard.loadDisplay(scene, theGame);
        scene = gfxScoreBoard.loadDisplay(scene, theGame, 0);
        

        Log.i(GuessWordz.TAG, "[GuessWordzGame] Scene Loaded");
        
        // Setting update Update Handler
        scene.registerUpdateHandler(new IUpdateHandler() {
            @Override
                    public void onUpdate(final float pSecondsElapsed) {
            		
            		if (gfxKeyBoard.keyBoardPressed == true)
            		{
            			Log.i(GuessWordz.TAG, "[GuessWordzGame] Handling gfxKeyBoard Pressed");
            			theGame = inputGuessWordz.checkGameBoardInput(99,gfxKeyBoard.chosenLetter, theGame);
            			gfxKeyBoard.keyBoardPressed = false;
            			theGame.doRefresh = true;
            		}
            	
                    if (theGame.doRefresh)
                    {
                       theGame = gfxGameBoard.assignGameBlockSprites(theGame);
                       refreshScene();
                       theGame.doRefresh = false;
      
                    }
            }
            @Override
                    public void reset() {}
        });
        

        scene.setOnSceneTouchListener(new IOnSceneTouchListener() {
        	@Override
        	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
        		//if(pSceneTouchEvent.isActionDown()) {
        			Log.i(GuessWordz.TAG, "[GuessWordzGame] Scene Touched");
        			Log.i(GuessWordz.TAG, "[GuessWordzGame] SCENE X"+ pSceneTouchEvent.getX()+ " Y"+ pSceneTouchEvent.getY());
        		//}
        		return true;
        	}
    
        });
        
            return scene;

	}
	
    @Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
           
            theGame = inputGuessWordz.checkGameBoardInput(pKeyCode,99, theGame);
            
                    return super.onKeyDown(pKeyCode, pEvent); //similarily, this will allow actions other than key press to be processed elsewhere.
    }
  

	
	// ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    private void refreshScene()
    {
            Scene scene = this.mEngine.getScene();
            //scene
            
            scene.detachChildren();
            
            Log.i(GuessWordz.TAG, "[GuessWordzGame] Current scene Count" + scene.getChildCount());
            scene = gfxGameBoard.loadDisplay(scene, theGame);
            scene = gfxKeyBoard.loadDisplay(scene, theGame);
            scene = gfxScoreBoard.loadDisplay(scene, theGame, 1);
            
            Log.i(GuessWordz.TAG, "[GuessWordzGame] Current scene Count" + scene.getChildCount());
            
            
       
            
            Log.i(GuessWordz.TAG, "[GuessWordzGame] Scene Refresh Updated");
            Log.i(GuessWordz.TAG, "[GuessWordzGame] Current Score" + theGame.theScore.getScore());
                     
    }


}
