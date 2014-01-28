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
	
	private static Audio noteF5;
	private static Audio noteD5;
	private static Audio noteC5;
	private static Audio noteBb4;
	private static Audio noteG4;
	private static Audio noteF4;
	private static Audio noteD4;
	private static Audio noteC4;
	
	private static Music funk;
	
	public static void init() {
		if (!loaded) {
			try {
				metronomeTick = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.METRONOME_TICK));
				metronomeBell = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.METRONOME_BELL));
				
				noteF5 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_F5));
				noteD5 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_D5));
				noteC5 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_C5));
				noteBb4 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_Bb4));
				noteG4 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_G4));
				noteF4 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_F4));
				noteD4 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_D4));
				noteC4 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(FilePaths.NOTE_C4));
				
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
		funk.loop(1.0f, 0.5f);
	}
	
	public static void playNoteF5() {
		if (!loaded) {init();}
		noteF5.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playNoteD5() {
		if (!loaded) {init();}
		noteD5.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playNoteC5() {
		if (!loaded) {init();}
		noteC5.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playNoteBb4() {
		if (!loaded) {init();}
		noteBb4.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playNoteG4() {
		if (!loaded) {init();}
		noteG4.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playNoteF4() {
		if (!loaded) {init();}
		noteF4.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playNoteD4() {
		if (!loaded) {init();}
		noteD4.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playNoteC4() {
		if (!loaded) {init();}
		noteC4.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	
}
