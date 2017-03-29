package com.my.black.jack;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

public class Credit extends Scene{
	BaseActivity activity;
	public Credit()
	{
		
		setBackground(new Background(0f, 0f, 0f));
		final Text anh = new Text(50, 150, activity.mFont, "Lam Tuan Anh 0913148", new TextOptions(HorizontalAlign.CENTER), activity.getVertexBufferObjectManager());
		final Text trinh = new Text(50, 150, activity.mFont, "Trinh", new TextOptions(HorizontalAlign.CENTER), activity.getVertexBufferObjectManager()); 
		activity = BaseActivity.getSharedInstance();
		loadResources();
		attachChild(anh);
		attachChild(trinh);
	}
	
	private void loadResources()
	{
		DelayModifier dMod = new DelayModifier(8){
		    @Override
		    protected void onModifierFinished(IEntity pItem) {
		     //   activity.setCurrentScene(MainMenuScene.c);
		    }
		};
		registerEntityModifier(dMod);
	}
}
