package com.davidsperling.onegam1.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.davidsperling.onegam1.constants.GameConstants;
import com.davidsperling.onegam1.slickFramework.GameObject;
import com.davidsperling.onegam1.slickFramework.GameState;

public class LevelLoader {
	public static void loadLevel(String levelFile, GameState parentState,
			ArrayList<GameObject> gameObjectList) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(levelFile));
			
			String line = null;
			
			int y = 0;
			while ((line = in.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					char currentChar = line.charAt(i);
					int x = i * GameConstants.GRID_SIZE;
				}
				y += GameConstants.GRID_SIZE;
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("Level file not found: " + levelFile);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Problem loading level: " + levelFile);
			e.printStackTrace();
		}
	}
}
