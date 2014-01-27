package com.davidsperling.onegam1.util;

import java.io.IOException;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.davidsperling.onegam1.constants.FilePaths;

public class SoundPlayer {
	private static boolean loaded = false;
	
	private static Audio metronomeTick;
	private static Audio metronomeBell;
	
	private static Music funk;
	
	public static void init() {
		if (!loaded) {
			try {
				metronomeTick = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.METRONOME_TICK));
				metronomeBell = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.METRONOME_BELL));
				funk = new Music(FilePaths.MUS_FUNK);
				loaded = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static boolean isLoaded() {
		if (!loaded) {init();}
		return loaded;
	}

	public static void playMetronomeTick() {
		if (!loaded) {init();}
		metronomeTick.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playMetronomeBell() {
		if (!loaded) {init();}
		metronomeBell.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playMusicFunk() {
		if (!loaded) {init();}
		funk.loop();
	}
}
