package com.davidsperling.onegam1.slickFramework;

import java.util.ArrayList;

import org.newdawn.slick.state.BasicGameState;


public abstract class GameState extends BasicGameState {
	protected ArrayList<GameObject> gameObjectList;
	protected float viewOffsetX = 0;
	protected float viewOffsetY = 0;
	protected ArrayList<GameObject> newObjectStack;
	
	public GameState() {
		super();
		gameObjectList = new ArrayList<GameObject>();
		newObjectStack = new ArrayList<GameObject>();
	}
	
	public ArrayList<GameObject> getGameObjectList() {
		return gameObjectList;
	}
	
	public ArrayList<GameObject> getNewObjectStack() {
		return newObjectStack;
	}
	
	public float getViewOffsetX() {
		return viewOffsetX;
	}
	
	public void setViewOffsetX(float viewOffsetX) {
		this.viewOffsetX = viewOffsetX;
	}
	
	public float getViewOffsetY() {
		return viewOffsetY;
	}
	
	public void setViewOffsetY(float viewOffsetY) {
		this.viewOffsetX = viewOffsetY;
	}
}
