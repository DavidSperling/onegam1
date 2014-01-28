package com.davidsperling.onegam1.player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import com.davidsperling.onegam1.slickFramework.GameObject;

public class PlayerBullet extends GameObject {
	public enum Direction {
		UP, LEFT, DOWN, RIGHT
	}
	
	private static final float speed = 25;
	private static final float radius = 3;
	
	private float x;
	private float y;
	
	private Direction direction;	
	
	public PlayerBullet(Direction direction, float x, float y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
	}

	@Override
	public void update(GameContainer container, int delta) {
		float modifiedSpeed = speed * 30 * delta / 1000;
		
		switch (direction) {
		case UP:
			y -= modifiedSpeed;
			break;
		case LEFT:
			x -= modifiedSpeed;
			break;
		case DOWN:
			y += modifiedSpeed;
			break;
		case RIGHT:
			x += modifiedSpeed;
			break;
		}
		
		if (x < -radius || x > container.getWidth() + radius ||
				y < -radius || y > container.getHeight() + radius) {
			dead = true;
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
		
		g.fill(new Circle(x, y, radius));
		
	}

}
