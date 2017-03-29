package com.my.black.jack;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.graphics.Typeface;



public class BaseActivity extends SimpleBaseGameActivity {

	public static Sprite cards[] = new Sprite[52];
	public static Sprite foldcards[] = new Sprite[5];
	public static Sprite gamebg;
	public static Sprite gamemenu;
	public static Sprite khtn;
	
	static final int CAMERA_WIDTH = 800;
	static final int CAMERA_HEIGHT = 480;
	public Font mFont;
	public Camera mCamera;	 
	//A reference to the current scene
	public Scene mCurrentScene;
	public static BaseActivity instance;
	
	private ITexture mTexture;
	private ITextureRegion mTextureRegion;
	
	public EngineOptions onCreateEngineOptions() {
		instance = this;
	    mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

	    return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}
	
    @Override
	protected void onCreateResources() {
    	 mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256,Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
    	 mFont.load();
    	 
    	 ////////////////////////////////////
    	
    	 for (int i =0;i<52;i++)
    	 {
    	 try {
    		final int j=i;
 			mTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
 				
 				public InputStream open() throws IOException {
 					return getAssets().open("gfx/"+String.valueOf(j)+".png");
 					
 				}
 			}
 			);
 			
 			this.mTexture.load();
 			mTextureRegion = TextureRegionFactory.extractFromTexture(this.mTexture);
 			cards[i] = new Sprite(0, 0, mTextureRegion , this.getVertexBufferObjectManager());
 			
 		} catch (IOException e) {
 			Debug.e(e);
 		}
    	 
    	}
    	// fold card
    	 try {
   			mTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
   				
   				public InputStream open() throws IOException {
   					return getAssets().open("gfx/52.png");
   					
   				}
   			}
   			);
   			
   			this.mTexture.load();
   			mTextureRegion = TextureRegionFactory.extractFromTexture(this.mTexture);
   			foldcards[0]  = new Sprite(0, 0, mTextureRegion , this.getVertexBufferObjectManager());
   			foldcards[1]  = new Sprite(0, 0, mTextureRegion , this.getVertexBufferObjectManager());
   			foldcards[2]  = new Sprite(0, 0, mTextureRegion , this.getVertexBufferObjectManager());
   			foldcards[3]  = new Sprite(0, 0, mTextureRegion , this.getVertexBufferObjectManager());
   			foldcards[4]  = new Sprite(0, 0, mTextureRegion , this.getVertexBufferObjectManager());
     	 } catch (IOException e) {
   			Debug.e(e);
     	 }
    	//bg
    	 
    	 try {
  			mTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
  				
  				public InputStream open() throws IOException {
  					return getAssets().open("gfx/gamebackground.png");
  					
  				}
  			}
  			);
  			
  			this.mTexture.load();
  			mTextureRegion = TextureRegionFactory.extractFromTexture(this.mTexture);
  			gamebg = new Sprite(0, 0, mTextureRegion , this.getVertexBufferObjectManager());
    	 } catch (IOException e) {
  			Debug.e(e);
    	 }
    	 
    	 try {
   			mTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
   				
   				public InputStream open() throws IOException {
   					return getAssets().open("gfx/BlackJackmenu.png");
   					
   				}
   			}
   			);
   			
   			this.mTexture.load();
   			mTextureRegion = TextureRegionFactory.extractFromTexture(this.mTexture);
   			gamemenu = new Sprite(0, 100, mTextureRegion , this.getVertexBufferObjectManager());
     	 } catch (IOException e) {
   			Debug.e(e);
     	 }
    	 
    	 try {
    			mTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
    				
    				public InputStream open() throws IOException {
    					return getAssets().open("gfx/khtn.png");
    					
    				}
    			}
    			);
    			
    			this.mTexture.load();
    			mTextureRegion = TextureRegionFactory.extractFromTexture(this.mTexture);
    			khtn = new Sprite(220, 100, mTextureRegion , this.getVertexBufferObjectManager());
      	 } catch (IOException e) {
    			Debug.e(e);
      	 }
    	 
	}
    
	@Override
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		mCurrentScene = new SplashScene();
		return mCurrentScene;
	}
    
	public static BaseActivity getSharedInstance() {
	    return instance;
	}

	// to change the current main scene
	public void setCurrentScene(Scene scene) {
	    mCurrentScene = scene;
	    getEngine().setScene(mCurrentScene);
	}
	
}
