package com.davidsperling.onegam1.prompts;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.davidsperling.onegam1.constants.FilePaths;
import com.davidsperling.onegam1.slickFramework.GameObject;

public class Prompts extends GameObject {
	private static boolean spritesLoaded = false;
	private static float spriteRadius = 32.0f;
	private static float spriteScale = 0.5f;
	private static float fadeMillis = 270;
	private static Image wSprite;
	private static Image aSprite;
	private static Image sSprite;
	private static Image dSprite;
	private static Image upSprite;
	private static Image downSprite;
	private static Image leftSprite;
	private static Image rightSprite;
	
	private boolean showingW;
	private boolean showingA;
	private boolean showingS;
	private boolean showingD;
	private boolean showingUp;
	private boolean showingLeft;
	private boolean showingDown;
	private boolean showingRight;
	
	private static final float centerOffset = 128;
	
	private float upX;
	private float upY;
	private float leftX;
	private float leftY;
	private float downX;
	private float downY;
	private float rightX;
	private float rightY;
	
	private String title;
	private boolean showingTitle;
	
	public void init(GameContainer container, StateBasedGame game) {
		loadSprites();
		clearPrompts();
		setDefaultPositions(container);
	}
	
	public void clearPrompts() {
		this.showingW = false;
	    this.showingA = false;
        this.showingS = false;
        this.showingD = false;
        this.showingUp = false;
        this.showingLeft = false;
        this.showingDown = false;
        this.showingRight = false;
        this.showingTitle = false;
        
        /*wSprite.setAlpha(0.0f);
        aSprite.setAlpha(0.0f);
        sSprite.setAlpha(0.0f);
        dSprite.setAlpha(0.0f);
        upSprite.setAlpha(0.0f);
        leftSprite.setAlpha(0.0f);
        downSprite.setAlpha(0.0f);
        rightSprite.setAlpha(0.0f);*/
	}
	
	public static void loadSprites() {
		if (!spritesLoaded) {
			try {
				wSprite = new Image(FilePaths.PROMPTS_W);
				aSprite = new Image(FilePaths.PROMPTS_A);
				sSprite = new Image(FilePaths.PROMPTS_S);
				dSprite = new Image(FilePaths.PROMPTS_D);
				upSprite = new Image(FilePaths.PROMPTS_UP);
				leftSprite = new Image(FilePaths.PROMPTS_LEFT);
				downSprite = new Image(FilePaths.PROMPTS_DOWN);
				rightSprite = new Image(FilePaths.PROMPTS_RIGHT);
				spritesLoaded = true;
			} catch (SlickException e) {
				System.err.println("Exception encountered while loading " +
						"sprites for Prompts: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void setDefaultPositions(GameContainer container) {
		float centerX = container.getWidth() / 2;
		float centerY = container.getHeight() / 2;
		
		this.upX = centerX - spriteRadius;
		this.upY = centerY - centerOffset - spriteRadius;
		
		this.leftX = centerX - centerOffset - spriteRadius;
		this.leftY = centerY - spriteRadius;
		
		this.downX = centerX - spriteRadius;
		this.downY = centerY + centerOffset - spriteRadius;
		
		this.rightX = centerX + centerOffset - spriteRadius;
		this.rightY = centerY - spriteRadius;
	}
	
	public void showW() {
		this.showingW = true;
	}
	
	public void showA() {
		this.showingA = true;
	}
	
	public void showS() {
		this.showingS = true;
	}
	
	public void showD() {
		this.showingD = true;
	}
	
	public void showUp() {
		this.showingUp = true;
	}
	
	public void showLeft() {
		this.showingLeft = true;
	}
	
	public void showDown() {
		this.showingDown = true;
	}
	
	public void showRight() {
		this.showingRight = true;
	}
	
	public void showTitle(String title) {
		this.title = title;
		this.showingTitle = true;
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (showingW) {
			wSprite.setAlpha(1.0f);
		} else {
			fadeSprite(wSprite, delta);
		}
		
		if (showingA) {
			aSprite.setAlpha(1.0f);
		} else {
			fadeSprite(aSprite, delta);
		}
		
		if (showingS) {
			sSprite.setAlpha(1.0f);
		} else {
			fadeSprite(sSprite, delta);
		}
		
		if (showingD) {
			dSprite.setAlpha(1.0f);
		} else {
			fadeSprite(dSprite, delta);
		}
		
		if (showingUp) {
			upSprite.setAlpha(1.0f);
		} else {
			fadeSprite(upSprite, delta);
		}
		
		if (showingLeft) {
			leftSprite.setAlpha(1.0f);
		} else {
			fadeSprite(leftSprite, delta);
		}
		
		if (showingDown) {
			downSprite.setAlpha(1.0f);
		} else {
			fadeSprite(downSprite, delta);
		}
		
		if (showingRight) {
			rightSprite.setAlpha(1.0f);
		} else {
			fadeSprite(rightSprite, delta);
		}
		
	}
	
	private void fadeSprite(Image sprite, int delta) {
		float alpha = sprite.getAlpha();
		alpha -= delta / fadeMillis;
		if (alpha < 0) {
			alpha -= 0;
		}
		sprite.setAlpha(alpha);
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		/*if (showingW) {
			wSprite.draw(upX, upY, spriteScale);
		}
		if (showingA) {
			aSprite.draw(leftX, leftY, spriteScale);
		}
		if (showingS) {
			sSprite.draw(downX, downY, spriteScale);
		}
		if (showingD) {
			dSprite.draw(rightX, rightY, spriteScale);
		}
		if (showingUp) {
			upSprite.draw(upX, upY, spriteScale);
		}
		if (showingLeft) {
			leftSprite.draw(leftX, leftY, spriteScale);
		}
		if (showingDown) {
			downSprite.draw(downX, downY, spriteScale);
		}
		if (showingRight) {
			rightSprite.draw(rightX, rightY, spriteScale);
		}*/
		wSprite.draw(upX, upY, spriteScale);
		aSprite.draw(leftX, leftY, spriteScale);
		sSprite.draw(downX, downY, spriteScale);
		dSprite.draw(rightX, rightY, spriteScale);
		upSprite.draw(upX, upY, spriteScale);
		leftSprite.draw(leftX, leftY, spriteScale);
		downSprite.draw(downX, downY, spriteScale);
		rightSprite.draw(rightX, rightY, spriteScale);
		
		if (showingTitle) {
			g.setColor(Color.white);
			g.drawString(title, container.getWidth() / 3,
					container.getHeight() / 3);
		}
		
	}
}