package com.my.black.jack;


import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;





public class GameScene extends MenuScene implements IOnMenuItemClickListener
{
	
	BaseActivity activity;
	
	final int HIT = 0;
	final int STAND = 1;
	final int BACK = 2;
	final float centerX = (BaseActivity.CAMERA_WIDTH - BaseActivity.cards[0].getWidth()) / 6;
	final float centerY = (BaseActivity.CAMERA_HEIGHT - BaseActivity.cards[0].getHeight()) / 3 - 20;
	
	
	Text result;
	
	final Sprite cardsprite[] = new Sprite [5];
	private Card cards[] = new Card [5];
	
	final Sprite comsprite[] = new Sprite [5];
	private Card comp[] = new Card [5];
	
	int count = 0;
	int ccount = 0;
	
	int s=0;
	int n=0;
	
	DelayModifier dMod;
	
	int playerscore = 0;
	int playerrank = 0;
	int compscore = 0;
	int comprank = 0;
	
	private void compscore()
	{
		int aces = 0;
        int sum = 0;
        int temp = 0;
        comprank = 4;
        
        for (int i=0;i<ccount;i++)
        {
            temp = comp[i].getValue();
            if (temp == 11) aces++;
            sum+=temp;
        }
        if (sum>21) {
            while((sum>21)&&(aces>0))
            {
                aces--;
                sum-=10;
            }
        }
        compscore = sum;
        if (compscore>21) comprank=5;
        
        if (ccount == 2)
        	if ((comp[0].getValue() == comp[1].getValue())&&(comp[0].getValue()==11)) comprank = 1;
        	else
        		if ((comp[0].getValue() + comp[1].getValue())==21) comprank = 3;
        
        if ((ccount ==5 )&&(compscore<22)) comprank = 2;
    		
	}
	
	private void playerscore()
	{
		int aces = 0;
        int sum = 0;
        int temp = 0;
        playerrank = 4;
        
        for (int i=0;i<count;i++)
        {
            temp = cards[i].getValue();
        	System.out.println(i+" "+temp);
            if (temp == 11) aces++;
            sum+=temp;
        }
        
        System.out.println(sum);
        
        if (sum>21) {
            while((sum>21)&&(aces>0))
            {
                aces--;
                sum-=10;
            }
        }
        playerscore = sum;
        
        if (playerscore>21) playerrank=5;
        
        if (count == 2)
        	if ((comp[0].getValue() == comp[1].getValue())&&(comp[0].getValue()==11)) playerrank = 1;
        	else
        		if ((cards[0].getValue() + cards[1].getValue())==21) playerrank = 3;
        
        if ((count ==5 )&&(playerscore<22)) playerrank = 2;
        System.out.println(playerrank);
    		
	}
	
	private boolean check(int s, int n)
	{
		for (int i=0;i<count;i++)
			if ((cards[i].suit==s)&&(cards[i].number==n))
					return false;
		return true;
	}
	
	private boolean checkcomp(int s, int n)
	{
		for (int i=0;i<ccount;i++)
			if ((comp[i].suit==s)&&(comp[i].number==n))
					return false;
		return true;
	}
	
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1, float arg2, float arg3) {
		final Text winText = new Text(330, 150, activity.mFont, "You Win", new TextOptions(HorizontalAlign.CENTER), activity.getVertexBufferObjectManager());

		final Text loseText = new Text(320, 150, activity.mFont, "You Lose", new TextOptions(HorizontalAlign.CENTER), activity.getVertexBufferObjectManager());

		final Text drawText = new Text(350, 150, activity.mFont, "Draw", new TextOptions(HorizontalAlign.CENTER), activity.getVertexBufferObjectManager());

	    switch (arg1.getID()) {
	        case HIT:
	        	//players
	        	if (count < 5)
	        	{
	        	
	        	while ((check(s,n)==false)||(checkcomp(s,n)==false))
	        	{
	        	s = (int) (Math.random()*4);
	        	n = (int) (Math.random()*13);	
	        	}
	        	
	        	count++;
	        	
	        	if (cards[count-1]==null) cards[count-1] = new Card(s,n);
	        	else 
	        	{
	        		cards[count-1].number=n;
	        		cards[count-1].suit=s;
	        	}
	        	
	        	cardsprite[count-1] = BaseActivity.cards[s*13+n];
	        	cardsprite[count-1].setX(100*count);
	        	cardsprite[count-1].setY(centerY*3);
	    		attachChild(cardsprite[count-1]);
	        	}
	        	return true;
	        case STAND:
	        	//computer
	        	playerscore();
	        	System.out.print("PL " + playerscore+" ");
	        	
	        	compscore();
	        	System.out.print("COMP " +compscore+" ");
	        	
	        	while ((comprank==4)&&(compscore < 17 )&&(ccount<5))
	        		{        	
	        			while ((check(s,n)==false)||(checkcomp(s,n)==false))
		            	{
		    				s = (int) (Math.random()*4);
		    	        	n = (int) (Math.random()*13);	
		            	}
		    			
		    			ccount++;
		    			        	
		            	if (comp[ccount-1]==null) comp[ccount-1] = new Card(s,n);
		            	else 
		            	{
		            		comp[ccount-1].number=n;
		            		comp[ccount-1].suit=s;
		            	}
		            	 
		            	comsprite[ccount-1] = BaseActivity.foldcards[ccount-1];
		            	comsprite[ccount-1].setX(100*ccount);
		            	comsprite[ccount-1].setY(centerY*2);
		            	attachChild(comsprite[ccount-1]);
		            		        			        			
	        		
	        		   compscore();
	        	   }
	        	System.out.print("COMP after" +compscore+" ");
	        	dMod = new DelayModifier(2){
	    		    @Override
	    		    protected void onModifierFinished(IEntity pItem) {
	        	
	        	for (int i = 0;i<ccount;i++)
	        	{
	        		detachChild(comsprite[i]);
	        		comsprite[i] = BaseActivity.cards[comp[i].suit*13+comp[i].number];
	            	comsprite[i].setX(100*(i+1));
	            	comsprite[i].setY(centerY*2);
	            	attachChild(comsprite[i]);
	        	}
	        	compscore();
	        	System.out.println("COMP after2" +compscore);
	        	
	        	if (playerrank>comprank)
	        	{
	        		result =  loseText;
	        		
	        	}
	        	else 
	        		if (playerrank<comprank)
	        		{
	        			result = winText;
	    		    }
	        		else
	        			if (playerrank==1)
	        			{
	        				if (playerscore>compscore)
	        					result=loseText;
	        				else
	        					if (playerscore==compscore)
	        						result=drawText;
	        					else result = winText;
	        			}
	        			else 
	        				if (playerrank == 5)
	        					result = drawText;
	        				else
		        				if (playerscore<compscore)
		        					result=loseText;
		        				else
		        					if (playerscore==compscore)
		        						result=drawText;
		        					else result = winText;
	        	result.setColor(100, 100, 100);
	        	attachChild(result);
	        	//
	    		}
	        	};
	        	
	        	registerEntityModifier(dMod);
	        	
	        	dMod = new DelayModifier(8){
	    		    @Override
	    		    protected void onModifierFinished(IEntity pItem) {
	    		    
	    		    		detachChild(result);
	        	
				        	//comp restart
				        	for (int i = 0;i<ccount;i++)
				        		detachChild(comsprite[i]);
				        	ccount = 0;
				        	for (int i=0;i<2;i++)
				    		{
				    			while ((check(s,n)==false)||(checkcomp(s,n)==false))
				            	{
				    				s = (int) (Math.random()*4);
				    	        	n = (int) (Math.random()*13);	
				            	}
				    			
				    			ccount++;
				    			        	
				            	if (comp[ccount-1]==null) comp[ccount-1] = new Card(s,n);
				            	else 
				            	{
				            		comp[ccount-1].number=n;
				            		comp[ccount-1].suit=s;
				            	}
				            	 
				            	comsprite[ccount-1] = BaseActivity.foldcards[ccount-1];
				            	comsprite[ccount-1].setX(100*ccount);
				            	comsprite[ccount-1].setY(centerY*2);
				            	attachChild(comsprite[ccount-1]);
				    		}
				        	
				        	//player restart
				        	for (int i = 0;i<count;i++)
				        		detachChild(cardsprite[i]);
				        	
				        	count=0;
				        	
				        	for (int i=0;i<2;i++)
				    		{
				    			
				    			
				    			while ((check(s,n)==false)||(checkcomp(s,n)==false))
					        	{
				    				s = (int) (Math.random()*4);
				    	        	n = (int) (Math.random()*13);	
					        	}
				            	
				    			count++;
				    			
				            	if (cards[count-1]==null) cards[count-1] = new Card(s,n);
					        	else 
					        	{
					        		cards[count-1].number=n;
					        		cards[count-1].suit=s;
					        	}
					        	
					        	cardsprite[count-1] = BaseActivity.cards[s*13+n];
					        	cardsprite[count-1].setX(100*count);
					        	cardsprite[count-1].setY(centerY*3);
				            	attachChild(cardsprite[count-1]);
					        	System.out.println("card" +i+ "S : "+s +"N: "+n);

				    		}
	        	
	    		} //method
	    		    
	        	}; //declaration
	        	registerEntityModifier(dMod);
	    		
	        	return true;
	        case BACK:
	        	activity.setCurrentScene(SplashScene.b);
	        	return true;
	        default:
	            break;
	    }
	    return false;
	}
	
	public GameScene()
	{
		super(BaseActivity.getSharedInstance().mCamera);
		activity = BaseActivity.getSharedInstance();

		attachChild(BaseActivity.gamebg);

		
		
		for (int i=0;i<2;i++)
		{
			while ((check(s,n)==false)||(checkcomp(s,n)==false))
        	{
				s = (int) (Math.random()*4);
	        	n = (int) (Math.random()*13);	
        	}
			
			ccount++;
			        	
        	if (comp[ccount-1]==null) comp[ccount-1] = new Card(s,n);
        	else 
        	{
        		comp[ccount-1].number=n;
        		comp[ccount-1].suit=s;
        	}
        	 
        	comsprite[ccount-1] = BaseActivity.foldcards[ccount-1];
        	comsprite[ccount-1].setX(100*ccount);
        	comsprite[ccount-1].setY(centerY*2);
        	attachChild(comsprite[ccount-1]);
		}
		
		//
		for (int i=0;i<2;i++)
		{
			while ((check(s,n)==false)||(checkcomp(s,n)==false))
        	{
				s = (int) (Math.random()*4);
	        	n = (int) (Math.random()*13);	
        	}
			
			count++;
			        	
        	if (cards[count-1]==null) cards[count-1] = new Card(s,n);
        	else 
        	{
        		cards[count-1].number=n;
        		cards[count-1].suit=s;
        	}
        	 
        	cardsprite[count-1] = BaseActivity.cards[s*13+n];
        	cardsprite[count-1].setX(100*count);
        	cardsprite[count-1].setY(centerY*3);
        	attachChild(cardsprite[count-1]);
		}
		
		
		
		
		IMenuItem hitButton = new TextMenuItem(HIT, activity.mFont, activity.getString(R.string.hit), activity.getVertexBufferObjectManager());
		hitButton.setPosition(mCamera.getWidth() / 8 * 7  - hitButton.getWidth() / 8 * 7 + 18 , mCamera.getHeight() / 4 - hitButton.getHeight() / 4 + 20);
		addMenuItem(hitButton);
		
		IMenuItem standButton = new TextMenuItem(STAND, activity.mFont, activity.getString(R.string.stand), activity.getVertexBufferObjectManager());
		standButton.setPosition(mCamera.getWidth() / 2 - standButton.getWidth() / 2 + 10 , mCamera.getHeight() / 4 - standButton.getHeight() / 4 + 335);
		addMenuItem(standButton);

		IMenuItem backButton = new TextMenuItem(BACK, activity.mFont, activity.getString(R.string.back), activity.getVertexBufferObjectManager());
		backButton.setPosition(mCamera.getWidth() / 2 - backButton.getWidth() / 2 + 150, mCamera.getHeight() / 4 - backButton.getHeight() / 4 + 335);
		addMenuItem(backButton);
		
		setOnMenuItemClickListener(this);
		

	}
}