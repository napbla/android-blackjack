package com.my.black.jack;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;

public class SplashScene extends Scene{
	BaseActivity activity;
	static public MainMenuScene b = new MainMenuScene();
	public SplashScene()
	{
		setBackground(new Background(1f, 1f, 1f));
		activity = BaseActivity.getSharedInstance();
		attachChild(BaseActivity.khtn);
		loadResources();
	}
	
	private void loadResources()
	{
		DelayModifier dMod = new DelayModifier(5){
		    @Override
		    protected void onModifierFinished(IEntity pItem) {
		        activity.setCurrentScene(b);
		    }
		};
		registerEntityModifier(dMod);
	}
}
