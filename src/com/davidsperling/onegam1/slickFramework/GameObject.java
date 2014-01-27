package com.davidsperling.onegam1.slickFramework;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public abstract class GameObject {
	public GameState parentState;
	public boolean dead = false;
	public float depth = 0;
	public abstract void update(GameContainer container, int delta);
	public abstract void render(GameContainer container, Graphics g);
	
	public float getDepth() {
		return depth;
	}
}
