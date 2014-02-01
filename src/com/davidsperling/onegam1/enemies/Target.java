package com.davidsperling.onegam1.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import com.davidsperling.onegam1.globals.Score;
import com.davidsperling.onegam1.slickFramework.GameObject;
import com.davidsperling.onegam1.util.SoundPlayer;

public class Target extends GameObject {
	public static final float radius = 64;
	public static enum Side {
		TOP, BOTTOM, LEFT, RIGHT
	}
	
	private Side side;
	private float x;
	private float y;
	private float xSpeedPerMs;
	private float ySpeedPerMs;
	
	public Target(Side side, float msPerBeat, GameContainer container) {
		this.side = side;
		switch(side) {
		case TOP:
			x = container.getWidth();
			xSpeedPerMs = (-container.getWidth() / 2)/(2 * msPerBeat);
			y = radius;
			ySpeedPerMs = 0;
			break;
		case BOTTOM:
			x = 0;
			xSpeedPerMs = (container.getWidth() / 2)/(2 * msPerBeat);
			y = container.getHeight() - radius;
			ySpeedPerMs = 0;
			break;
		case LEFT:
			x = 0;
			xSpeedPerMs = 0;
			y = 0;
			ySpeedPerMs = (container.getHeight() / 2)/(2 * msPerBeat);
			break;
		case RIGHT:
			x = container.getWidth() - radius;
			xSpeedPerMs = 0;
			y = container.getHeight();
			ySpeedPerMs = (-container.getHeight() / 2)/(2 * msPerBeat);
			break;
		}
	}

	@Override
	public void update(GameContainer container, int delta) {
		x += xSpeedPerMs * delta;
		y += ySpeedPerMs * delta;
		
		if (x < -radius || x > container.getWidth() + radius ||
				y < -radius || y > container.getHeight() + radius) {
			dead = true;
			SoundPlayer.playMetronomeBell();
			Score.points -= 20;
		}

	}

	@Override
	public void render(GameContainer container, Graphics g) {
		switch (side) {
		case TOP:
			g.setColor(Color.red);
			break;
		case LEFT:
			g.setColor(Color.green);
			break;
		case BOTTOM:
			g.setColor(Color.blue);
			break;
		case RIGHT:
			g.setColor(Color.yellow);
			break;
		}
		
		g.fill(new Circle(x, y, radius));

	}
	
	public static float getRadius() {
		return Target.radius;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void hit() {
		dead = true;
		Score.points += 10;
	}

}
