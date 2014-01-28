package com.davidsperling.onegam1.player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.davidsperling.onegam1.globals.KeyBindings;
import com.davidsperling.onegam1.slickFramework.GameObject;
import com.davidsperling.onegam1.slickFramework.GameState;
import com.davidsperling.onegam1.util.InputChecker;
import com.davidsperling.onegam1.util.SoundPlayer;

public class Player extends GameObject {
	private float x;
	private float y;
	
	private boolean blockingUp;
	private boolean blockingLeft;
	private boolean blockingDown;
	private boolean blockingRight;
	
	public Player(GameState parentState) {
		this.parentState = parentState;
	}
	
	public void init(GameContainer container, StateBasedGame game) {
		x = container.getWidth() / 2;
		y = container.getHeight() / 2;
	}

	@Override
	public void update(GameContainer container, int delta) {
		blockingUp = false;
		blockingDown = false;
		blockingLeft = false;
		blockingRight = false;
		
		if (InputChecker.isInputDown(container, KeyBindings.BLOCK_UP)) {
			blockingUp = true;
		} else if (InputChecker.isInputDown(container, KeyBindings.BLOCK_LEFT)) {
			blockingLeft = true;
		} else if (InputChecker.isInputDown(container, KeyBindings.BLOCK_DOWN)) {
			blockingDown = true;
		} else if (InputChecker.isInputDown(container, KeyBindings.BLOCK_RIGHT)) {
			blockingRight = true;
		}
		
		if (InputChecker.isInputPressed(container, KeyBindings.BLOCK_UP)) {
			SoundPlayer.playNoteF5();
		} else if (InputChecker.isInputPressed(container, KeyBindings.BLOCK_LEFT)) {
			SoundPlayer.playNoteD5();
		} else if (InputChecker.isInputPressed(container, KeyBindings.BLOCK_DOWN)) {
			SoundPlayer.playNoteBb4();
		} else if (InputChecker.isInputPressed(container, KeyBindings.BLOCK_RIGHT)) {
			SoundPlayer.playNoteC5();
		} else if (InputChecker.isInputPressed(container, KeyBindings.SHOOT_UP)) {
			parentState.getGameObjectList().add(
					new PlayerBullet(PlayerBullet.Direction.UP, x, y));
			SoundPlayer.playNoteG4();
		} else if (InputChecker.isInputPressed(container, KeyBindings.SHOOT_LEFT)) {
			parentState.getGameObjectList().add(
					new PlayerBullet(PlayerBullet.Direction.LEFT, x, y));
			SoundPlayer.playNoteF4();
		} else if (InputChecker.isInputPressed(container, KeyBindings.SHOOT_DOWN)) {
			parentState.getGameObjectList().add(
					new PlayerBullet(PlayerBullet.Direction.DOWN, x, y));
			SoundPlayer.playNoteC4();
		} else if (InputChecker.isInputPressed(container, KeyBindings.SHOOT_RIGHT)) {
			parentState.getGameObjectList().add(
					new PlayerBullet(PlayerBullet.Direction.RIGHT, x, y));
			SoundPlayer.playNoteD4();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		float radius = 32;
		
		Circle circle = new Circle(x, y, radius);
		g.setColor(Color.white);
		g.fill(circle);
		
		if (blockingUp) {
			Rectangle upRect = new Rectangle(x - 32, y - 64, 64, 16);
			g.setColor(Color.red);
			g.fill(upRect);
		}
		if (blockingLeft) {
			Rectangle upRect = new Rectangle(x - 64, y - 32, 16, 64);
			g.setColor(Color.green);
			g.fill(upRect);
		}
		if (blockingDown) {
			Rectangle upRect = new Rectangle(x - 32, y + 48, 64, 16);
			g.setColor(Color.blue);
			g.fill(upRect);
		}
		if (blockingRight) {
			Rectangle upRect = new Rectangle(x + 48, y - 32, 16, 64);
			g.setColor(Color.yellow);
			g.fill(upRect);
		}
		
		
	}

}
