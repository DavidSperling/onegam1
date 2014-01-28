package com.davidsperling.onegam1.song;

import java.util.ArrayList;

public class Stutter {
	private ArrayList<Boolean> restarts;
	
	public Stutter() {
		this("");
	}
	
	public Stutter(String pattern) {
		restarts = new ArrayList<Boolean>();
		addPattern(pattern);
	}
	
	public void addPattern(String pattern) {
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == '*') {
				restarts.add(true);
			} else if (pattern.charAt(i) == '.') {
				restarts.add(false);
			}
		}
	}
	
	public void fourCount() {
		addPattern("*...*...*...*...*");
	}
	
	public boolean pop() {
		if (restarts.isEmpty()) {
			return false;
		} else {
			return restarts.remove(0);
		}
	}
}
