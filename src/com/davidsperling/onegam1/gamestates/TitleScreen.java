package com.davidsperling.onegam1.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.davidsperling.onegam1.constants.FilePaths;
import com.davidsperling.onegam1.globals.KeyBindings;
import com.davidsperling.onegam1.slickFramework.GameState;
import com.davidsperling.onegam1.util.InputChecker;

public class TitleScreen extends GameState {
	private boolean initSoundPlayed;
	private Image titleImage;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame game)
			throws SlickException {
		
		initSoundPlayed = false;
		titleImage = new Image(FilePaths.TITLE_SCREEN);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		titleImage.draw(0, 0, container.getWidth(), container.getHeight());		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (!initSoundPlayed) {
			initSoundPlayed = true;
		}
		if (InputChecker.isInputPressed(container, KeyBindings.P1_INPUT)) {
			GameState levelState = new LevelState();
			levelState.init(container, game);
			game.addState(levelState);
			game.enterState(1);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
