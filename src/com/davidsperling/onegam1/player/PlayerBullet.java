package com.davidsperling.onegam1.player;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import com.davidsperling.onegam1.enemies.Target;
import com.davidsperling.onegam1.gamestates.LevelState;
import com.davidsperling.onegam1.slickFramework.GameObject;
import com.davidsperling.onegam1.slickFramework.GameState;

public class PlayerBullet extends GameObject {
	public enum Direction {
		UP, LEFT, DOWN, RIGHT
	}

	private static final float radius = 3;
	
	private float x;
	private float y;
	
	private float prevX;
	private float prevY;
	
	private float xSpeed;
	private float ySpeed;
	
	private Direction direction;
	
	public PlayerBullet(GameContainer container, Direction direction, float x, float y,
			GameState parentState) {
		this.direction = direction;
		this.x = x;
		this.y = y;
		
		this.prevX = x;
		this.prevY = y;
		
		this.parentState = parentState;
		
		if (parentState.getClass() != LevelState.class) {
			dead = true;
			xSpeed = 0;
			ySpeed = 0;
		} else {
			float msPerBeat = ((LevelState)parentState).getMsPerBeat();
			switch (direction) {
			case UP:
				xSpeed = 0;
				ySpeed = -(container.getHeight() / 2 - Target.getRadius()) / msPerBeat;
				break;
			case LEFT:
				xSpeed = -(container.getWidth() / 2 - Target.getRadius()) / msPerBeat;
				ySpeed = 0;
				break;
			case DOWN:
				xSpeed = 0;
				ySpeed = (container.getHeight() / 2 - Target.getRadius()) / msPerBeat;
				break;
			case RIGHT:
				xSpeed = (container.getWidth() / 2 - Target.getRadius()) / msPerBeat;
				ySpeed = 0;
				break;
			}
		}
	}

	@Override
	public void update(GameContainer container, int delta) {
		prevX = x;
		prevY = y;
		
		x += xSpeed * delta;
		y += ySpeed * delta;
		
		if (x < -radius || x > container.getWidth() + radius ||
				y < -radius || y > container.getHeight() + radius) {
			dead = true;
		}
		
		ArrayList<GameObject> gameObjectList = parentState.getGameObjectList();
		for (GameObject o : gameObjectList) {
			if (o.getClass() == Target.class) {
				float otherX = ((Target)o).getX();
				float otherY = ((Target)o).getY();
				if (Math.sqrt(Math.pow(x - otherX, 2) + Math.pow(y - otherY, 2)) < Target.getRadius()) {
					((Target)o).hit();
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		switch (direction) {
		case UP:
			g.setColor(Color.red);
			break;
		case LEFT:
			g.setColor(Color.green);
			break;
		case DOWN:
			g.setColor(Color.blue);
			break;
		case RIGHT:
			g.setColor(Color.yellow);
			break;
		}
		
		boolean done = false;
		int drawX = (int)Math.floor(prevX);
		int drawY = (int)Math.floor(prevY);
		while (!done) {
			g.fill(new Circle(x, y, radius));
			done = true;
			if (drawX < (int)Math.floor(x)) {
				drawX++;
				done = false;
			} else if (drawX > (int) Math.floor(x)) {
				drawX--;
				done = false;
			}
			if (drawY < (int) Math.floor(y)) {
				drawY++;
				done = false;
			} else if (drawY > (int) Math.floor(y)) {
				drawY--;
				done = false;
			}
			g.fill(new Circle(drawX, drawY, radius));
			System.out.println("Hi");
		}
		g.fill(new Circle(x, y, radius));
		
	}

}
