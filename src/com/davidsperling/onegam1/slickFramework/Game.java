package com.davidsperling.onegam1.slickFramework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.davidsperling.onegam1.constants.Defaults;
import com.davidsperling.onegam1.constants.FilePaths;
import com.davidsperling.onegam1.constants.GameConstants;
import com.davidsperling.onegam1.gamestates.LevelState;
import com.davidsperling.onegam1.prompts.Prompts;
import com.davidsperling.onegam1.util.SoundPlayer;

public class Game extends StateBasedGame {

	public Game(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		SoundPlayer.init();
		Prompts.loadSprites();
		
		//addState(new TestState());
		addState(new LevelState());
		//addState(new TitleScreen());
		//addState(new WinScreen());
		enterState(1);
	}

	public static void main(String[] args) throws SlickException {
		int windowWidth = Defaults.WINDOW_WIDTH;
		int windowHeight = Defaults.WINDOW_HEIGHT;
		boolean fullscreen = Defaults.FULLSCREEN;

		HashMap<String, String> configs = new HashMap<>();

		try (BufferedReader in = new BufferedReader(
				new FileReader(FilePaths.CONFIG))) {
			String line = null;
			while ((line = in.readLine()) != null) {
				String splitLine[];
				splitLine = line.split("=");
				configs.put(splitLine[0], splitLine[1]);
			}
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

		Set<String> s = configs.keySet();
		Iterator<String> it = s.iterator();

		while (it.hasNext()) {
			String key = it.next();
			if (key.equals("Resolution")) {
				String res = configs.get(key);
				String dimensions[] = res.split("x");
				windowWidth = Integer.parseInt(dimensions[0]);
				windowHeight = Integer.parseInt(dimensions[1]);
			}
			if (key.equals("Fullscreen")) {
				String full = configs.get(key);
				fullscreen = (full.equals("true")) ? true : false;
			}
		}

		AppGameContainer app = new AppGameContainer(new Game(
				GameConstants.TITLE));
		app.setShowFPS(false);
		app.setDisplayMode(windowWidth, windowHeight, fullscreen);
		app.setAlwaysRender(true);
		app.setTargetFrameRate(60);
		
		app.start();
	}

}
