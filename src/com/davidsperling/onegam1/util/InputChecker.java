package com.davidsperling.onegam1.util;

import java.util.List;

import org.newdawn.slick.GameContainer;

public class InputChecker {
	public static boolean isInputPressed(GameContainer container, List<Integer> input) {
		for (Integer key : input) {
			if (container.getInput().isKeyPressed(key)) {
				return true;
			}
		}
		return false;
	}
	public static boolean isInputDown(GameContainer container, List<Integer> input) {
		for (Integer key : input) {
			if (container.getInput().isKeyDown(key)) {
				return true;
			}
		}
		return false;
	}
}
