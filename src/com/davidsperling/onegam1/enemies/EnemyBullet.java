package com.davidsperling.onegam1.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.davidsperling.onegam1.gamestates.LevelState;
import com.davidsperling.onegam1.player.Player;
import com.davidsperling.onegam1.slickFramework.GameObject;
import com.davidsperling.onegam1.slickFramework.GameState;

public class EnemyBullet extends GameObject {
	public static final float radius = 64;
	
	public static final float SHIELD_RANGE = 56;
	
	public static enum Side {
		TOP, BOTTOM, LEFT, RIGHT
	}
	
	private Side side;
	private float x;
	private float y;
	private float xSpeedPerMs;
	private float ySpeedPerMs;
	
	private float msPerBeat;
	
	public EnemyBullet(Side side, float msPerBeat, GameContainer container, GameState parentState) {
		this.side = side;
		this.parentState = parentState;
		switch(side) {
		case RIGHT:
			x = container.getWidth();
			xSpeedPerMs = -((container.getWidth() / 2) - SHIELD_RANGE)/(msPerBeat);
			y = container.getHeight() / 2;
			ySpeedPerMs = 0;
			break;
		case LEFT:
			x = 0;
			xSpeedPerMs = ((container.getWidth() / 2) - SHIELD_RANGE)/(msPerBeat);
			y = container.getHeight() / 2;
			ySpeedPerMs = 0;
			break;
		case TOP:
			x = container.getWidth() / 2;
			xSpeedPerMs = 0;
			y = 0;
			ySpeedPerMs = ((container.getHeight() / 2) - SHIELD_RANGE)/(msPerBeat);
			break;
		case BOTTOM:
			x = container.getWidth() / 2;
			xSpeedPerMs = 0;
			y = container.getHeight();
			ySpeedPerMs = -((container.getHeight() / 2) - SHIELD_RANGE)/(msPerBeat);
			break;
		}
	}

	@Override
	public void update(GameContainer container, int delta) {
		x += xSpeedPerMs * delta;
		y += ySpeedPerMs * delta;
		
		/*if (x < -radius || x > container.getWidth() + radius ||
				y < -radius || y > container.getHeight() + radius) {
			dead = true;
			SoundPlayer.playMetronomeBell();
		}*/
		
		Player player;
		if (parentState.getClass() == LevelState.class) {
			player = ((LevelState) parentState).getPlayer();
		} else {
			dead = true;
			return;
		}
		if (xSpeedPerMs < 0 && x < container.getWidth() / 2 + SHIELD_RANGE) {
			if (player.isBlockingRight()) {
				dead = true;
			} else {
				if (x < container.getWidth() / 2) {
					dead = true;
					player.hit();
				}
			}
		} else if (xSpeedPerMs > 0 && x > container.getWidth() / 2 - SHIELD_RANGE) {
			if (player.isBlockingLeft()) {
				dead = true;
			} else {
				if (x > container.getWidth() / 2) {
					dead = true;
					player.hit();
				}
			}
		} else if (ySpeedPerMs > 0 && y > container.getHeight() / 2 - SHIELD_RANGE) {
			if (player.isBlockingUp()) {
				dead = true;
			} else {
				if (y > container.getHeight() / 2) {
					dead = true;
					player.hit();
				}
			}
		} else if (ySpeedPerMs < 0 && y < container.getHeight() / 2 + SHIELD_RANGE) {
			if (player.isBlockingDown()) {
				dead = true;
			} else {
				if (y < container.getHeight() / 2) {
					dead = true;
					player.hit();
				}
			}
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
		
		g.fill(new Rectangle(x - 8, y - 8, 16, 16));

	}
	
	public static float getRadius() {
		return EnemyBullet.radius;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void hit() {
		dead = true;
	}

}
