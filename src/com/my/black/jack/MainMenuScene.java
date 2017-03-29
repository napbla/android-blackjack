package com.my.black.jack;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.sprite.Sprite;




public class MainMenuScene extends MenuScene implements IOnMenuItemClickListener
{
	BaseActivity activity;
	final int MENU_START = 0;
	final int CREDIT = 1;
	public GameScene a = new GameScene();
	//public static Credit c  = new Credit();
	
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1, float arg2, float arg3) {
	    switch (arg1.getID()) {
	        case MENU_START:
	        	activity.setCurrentScene(a);
	            return true;
	      
	      //  case CREDIT:
	     //   	activity.setCurrentScene(c);
	     //   	return true;
	        default:
	            break;
	    }
	    return false;
	}
	
	public MainMenuScene()
	{
		super(BaseActivity.getSharedInstance().mCamera);
		activity = BaseActivity.getSharedInstance();

		setBackground(new Background(0f, 0f, 0f));
		attachChild(BaseActivity.gamemenu);
		
		IMenuItem startButton = new TextMenuItem(MENU_START, activity.mFont, activity.getString(R.string.start), activity.getVertexBufferObjectManager());
		startButton.setPosition(mCamera.getWidth() / 2 - startButton.getWidth() / 2, mCamera.getHeight() / 2 - startButton.getHeight() / 2 + 100);
		addMenuItem(startButton);
		
		IMenuItem creditButton = new TextMenuItem(CREDIT, activity.mFont, activity.getString(R.string.credit), activity.getVertexBufferObjectManager());
		creditButton.setPosition(mCamera.getWidth() / 2 - startButton.getWidth() / 2 + 50, mCamera.getHeight() / 2 - startButton.getHeight() / 2 + 300);
		addMenuItem(creditButton);

		setOnMenuItemClickListener(this);
		

	}
}
