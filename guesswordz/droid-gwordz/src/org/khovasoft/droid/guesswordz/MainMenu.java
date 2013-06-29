/*
 * GuessWordz
 * 
 * MaineMenu.Java
 * Handles Main Menu of Game
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */

package org.khovasoft.droid.guesswordz;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;
import android.util.Log;

public class MainMenu extends BaseGameActivity implements IOnMenuItemClickListener  {
	
    // ===========================================================
    // Constants
    // ===========================================================
	

    protected static final int MENU_STARTGAME = 0;
    protected static final int MENU_CREDITS = MENU_STARTGAME + 1;
    protected static final int MENU_QUIT = MENU_CREDITS + 1;
	
	
	// ===========================================================
	// Fields
	// ===========================================================

    protected Camera mCamera;

    protected Scene mMainScene;

    protected MenuScene mMenuScene;

    private BitmapTextureAtlas mMenuTexture;
    protected TextureRegion mMenuStartGameTextureRegion;
    protected TextureRegion mMenuCreditsTextureRegion;
    protected TextureRegion mMenuQuitGameTextureRegion;
    
	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    
    @Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public Engine onLoadEngine() {
		Log.i(GuessWordz.TAG, "[MainMenu] Starting onLoadEngine()");
        this.mCamera = new Camera(0, 0, GuessWordz.CAMERA_WIDTH, GuessWordz.CAMERA_HEIGHT);
        
        return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(GuessWordz.CAMERA_WIDTH, GuessWordz.CAMERA_HEIGHT), this.mCamera));
	}
	
	@Override
	public void onLoadResources() {
		Log.i(GuessWordz.TAG, "[MainMenu] Starting onLoadResources()");
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/"); 
        
        this.mMenuTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mMenuStartGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, this, "menu/start_game.png", 0, 0);
        this.mMenuCreditsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, this, "menu/credits.png", 0, 50);
        this.mMenuQuitGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, this, "menu/quit_game.png", 0, 100);
        this.mEngine.getTextureManager().loadTexture(this.mMenuTexture);
        
        Log.i(GuessWordz.TAG, "[MainMenu] Resources Loaded");		
	}
	
	@Override
	public Scene onLoadScene() {
		Log.i(GuessWordz.TAG, "[MainMenu] Starting onLoadScene()");
        this.mEngine.registerUpdateHandler(new FPSLogger());

        this.createMenuScene();

        this.mMainScene = new Scene();
        this.mMainScene.setBackground(new ColorBackground(0, 0, 0));

        /* Attach the menu. */
        this.mMainScene.setChildScene(this.mMenuScene, false, true, true);

        return this.mMainScene;

	}
	
	@Override
    public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
			Log.i(GuessWordz.TAG, "[MainMenu] Starting onMenuItemClicked()");
            switch(pMenuItem.getID()) {
                    case MENU_STARTGAME:
                    	Intent myIntent = new Intent(MainMenu.this, GuessWordzGame.class);
	                	Log.i(GuessWordz.TAG, "About to Handel Intent");
	                	startActivity(myIntent);
	                	Log.i(GuessWordz.TAG, "Intent Handeled");	                	
	                	finish();
                            return true;
                    case MENU_CREDITS:
                        //TODO Add Credits Section
                            this.finish();                            
                            return true;
                    case MENU_QUIT:
                    	//TODO Android Apps do not have Quit - Will Need to Modify
                    		return true;
                    default:
                            return false;
            }
    }



	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
    // ===========================================================
    // Methods
    // ===========================================================

	/*
	 * Create Menu Layout for Main Menu
	 */
    protected void createMenuScene() {
    	Log.i(GuessWordz.TAG, "[MainMenu] Starting createMenuScene()");
        this.mMenuScene = new MenuScene(this.mCamera);

        final SpriteMenuItem startGameMenuItem = new SpriteMenuItem(MENU_STARTGAME, this.mMenuStartGameTextureRegion);
        startGameMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        this.mMenuScene.addMenuItem(startGameMenuItem);

        final SpriteMenuItem creditsMenuItem = new SpriteMenuItem(MENU_CREDITS, this.mMenuCreditsTextureRegion);
        creditsMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        this.mMenuScene.addMenuItem(creditsMenuItem);
        
        final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT, this.mMenuQuitGameTextureRegion);
        quitMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        this.mMenuScene.addMenuItem(quitMenuItem);

        this.mMenuScene.buildAnimations();

        this.mMenuScene.setBackgroundEnabled(false);

        this.mMenuScene.setOnMenuItemClickListener(this);
    }


}
