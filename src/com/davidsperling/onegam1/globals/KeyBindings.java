package com.davidsperling.onegam1.globals;

import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Input;

public class KeyBindings {
	public static List<Integer> P1_INPUT = Arrays.asList(Input.KEY_ENTER);
	
	public static List<Integer> BLOCK_UP = Arrays.asList(Input.KEY_W);
	public static List<Integer> BLOCK_LEFT = Arrays.asList(Input.KEY_A);
	public static List<Integer> BLOCK_DOWN = Arrays.asList(Input.KEY_S);
	public static List<Integer> BLOCK_RIGHT = Arrays.asList(Input.KEY_D);
	
	public static List<Integer> SHOOT_UP = Arrays.asList(Input.KEY_UP);
	public static List<Integer> SHOOT_LEFT = Arrays.asList(Input.KEY_LEFT);
	public static List<Integer> SHOOT_DOWN = Arrays.asList(Input.KEY_DOWN);
	public static List<Integer> SHOOT_RIGHT = Arrays.asList(Input.KEY_RIGHT);
	
	public static List<Integer> RESTART = Arrays.asList(Input.KEY_ENTER);
	public static List<Integer> QUIT = Arrays.asList(Input.KEY_ESCAPE);
}
