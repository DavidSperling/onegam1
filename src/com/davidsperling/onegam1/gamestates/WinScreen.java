package com.davidsperling.onegam1.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.davidsperling.onegam1.constants.FilePaths;
import com.davidsperling.onegam1.slickFramework.GameState;

public class WinScreen extends GameState {
	private Image titleImage;
	private boolean initSoundPlayed;
	
	private int timer;

	@Override
	public void init(GameContainer arg0, StateBasedGame game)
			throws SlickException {
		titleImage = new Image(FilePaths.TITLE_WIN);
		initSoundPlayed = false;
		timer = 5000;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics arg2)
			throws SlickException {
		titleImage.draw(0, 0, container.getWidth(), container.getHeight());
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (!initSoundPlayed) {
			initSoundPlayed = true;
		}
		/*if (InputChecker.isInputPressed(container, KeyBindings.P1_INPUT)) {
			container.exit();
		}*/
		timer -= delta;
		if (timer < 0) {
			container.exit();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
