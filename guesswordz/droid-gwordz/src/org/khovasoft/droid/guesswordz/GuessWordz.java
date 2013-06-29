/*
 * GuessWordz
 * 
 * GuessWords.Java
 * Handles Start of Game and loads Splash Screen
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */

package org.khovasoft.droid.guesswordz;


import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;


import android.content.Intent;
import android.util.Log;

public class GuessWordz extends BaseGameActivity {
	
	// ===========================================================
	// Constants
	// ===========================================================

	public static final String TAG = "GWordz";  // Debug Tag
	public static final boolean Logging = true; // Debugging Flag (True/False) 
	
	public final static int CAMERA_WIDTH = 800; // Set Game Camera Width Universal
	public final static int CAMERA_HEIGHT = 480; // Set Game Camera Height Universal
	
    public static final char REDBLOCK = '@'; // Define RedBlock for Game
    public static final char BLUEBLOCK = '#'; // Define BlueBlock for Game

	// ===========================================================
	// Fields
	// ===========================================================
	
	protected Camera mCamera;
	protected Scene mMainScene;
	
	private BitmapTextureAtlas mTexture;
	private TextureRegion mLogoTextureRegion;
	
	protected static int splashTime = 5000; // Splash Screen Interval in Miliseconds
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void onLoadComplete() {
		// Nothing to Do
		
	}

	@Override
	public Engine onLoadEngine() {
		Log.i(GuessWordz.TAG, "[GuessWordz] onLoadEngine Started");
		
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera).setNeedsSound(true));

	}

	@Override
	public void onLoadResources() {
		Log.i(GuessWordz.TAG, "[GuessWordz] onLoadResources Started");
		
		/* Load Sprite for Splash Screen. */
		this.mTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mLogoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mTexture, this, "gfx/logo_khovasoft.png", 0,0);
		this.mEngine.getTextureManager().loadTexture(this.mTexture);
		Log.i(GuessWordz.TAG, "[GuessWordz] Splash Screen Textures Loaded");
		
		/* Handle Auto Timer for Splash Screen */
		mEngine.registerUpdateHandler(new TimerHandler(0.02f, true, new ITimerCallback() {
	        @Override
	        public void onTimePassed(final TimerHandler pTimerHandler) {
	               
	                splashTime -= 20;
	                if (splashTime == 0) {
	                	Intent myIntent = new Intent(GuessWordz.this, MainMenu.class);
	         
	                	Log.i(GuessWordz.TAG, "[GuessWordz] Handling Intent for MainMenu");
	                	startActivity(myIntent);

	                }
	        }
		}));
		
		
	}

	@Override
	public Scene onLoadScene() {
		Log.i(GuessWordz.TAG, "[GuessWordz] Running Splash Screen");
		this.mMainScene = implement(this.mMainScene, this.mLogoTextureRegion); 
		
		return this.mMainScene;
	}
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
    // ===========================================================
    // Methods
    // ===========================================================

		
	private static Scene implement(Scene splash, TextureRegion texture) {
		
		Log.i(GuessWordz.TAG, "[GuessWordz] Loading Scene");
		splash = new Scene();
		splash.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
		final Sprite img_logo = new Sprite(251, 171, texture);
		splash.attachChild(img_logo);
		
		return splash;
	}
	
}