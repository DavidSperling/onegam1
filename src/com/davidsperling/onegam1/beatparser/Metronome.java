package com.davidsperling.onegam1.beatparser;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.davidsperling.onegam1.constants.RhythmAction;
import com.davidsperling.onegam1.globals.KeyBindings;
import com.davidsperling.onegam1.slickFramework.GameObject;
import com.davidsperling.onegam1.util.InputChecker;
import com.davidsperling.onegam1.util.SoundPlayer;

public class Metronome extends GameObject {

	private float beatsPerMinute;
	private int beatsPerMeasure;

	private int measureNumber;
	private int beatNumber;

	private int msSinceStart;
	private int msSinceBeat;

	private boolean isNewMeasure;
	private boolean isNewBeat;
	
	private boolean isFirstFrame;
	
	private ArrayList<RhythmAction> measureActions;
	private BeatBuffer beatBuffer;
	
	private boolean musicStarted;

	public Metronome(float beatsPerMinute) {
		this(beatsPerMinute, 4);
	}

	public Metronome(float beatsPerMinute, int beatsPerMeasure) {
		this.beatsPerMinute = beatsPerMinute;
		this.beatsPerMeasure = beatsPerMeasure;
		init();
	}

	public void init() {
		this.musicStarted = false;
	}
	
	public void resetTimers() {
		this.measureNumber = 0;
		this.beatNumber = 0;
		this.msSinceStart = 0;
		this.isNewBeat = true;
		this.isNewMeasure = true;

		this.isFirstFrame = true;
		
		this.measureActions = new ArrayList<RhythmAction>(beatsPerMeasure);
		for (int i = 0; i < beatsPerMeasure; i++) {
			measureActions.add(RhythmAction.BLANK);
		}
		
		this.beatBuffer = new BeatBuffer();
		
		SoundPlayer.playMusicFunk();
		musicStarted = true;
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (InputChecker.isInputPressed(container, KeyBindings.P1_INPUT)) {
			resetTimers();
		}
		if(!musicStarted) {
			resetTimers();
		}
		incrementTimers(container, delta);
		checkForNewBeat();
		//playSoundEffects();
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		// TODO Auto-generated method stub

	}

	private void incrementTimers(GameContainer container, int delta) {
		msSinceStart += delta;
		msSinceBeat += delta;
		beatBuffer.update(container, delta);
	}

	private void checkForNewBeat() {
		if (msSinceBeat > getMsPerBeat()) {
			measureActions.set(beatNumber, beatBuffer.processBeat(getMsPerBeat()));
			isNewBeat = true;
			beatNumber++;
			if (beatNumber >= beatsPerMeasure) {
				beatNumber -= beatsPerMeasure;
				measureNumber++;
				isNewMeasure = true;
			} else {
				isNewMeasure = false;
			}
			msSinceBeat = Math.round(msSinceStart
					- (measureNumber * beatsPerMeasure + beatNumber)
					* getMsPerBeat());
		} else {
			isNewBeat = false;
			isNewMeasure = false;
		}
		
		if (isFirstFrame) {
			isNewBeat = true;
			isNewMeasure = true;
			isFirstFrame = false;
		}
	}
	
	private void playSoundEffects() {
		if (isNewMeasure) {
			SoundPlayer.playMetronomeBell();
		} 
		if (isNewBeat) {
			SoundPlayer.playMetronomeTick();
		}
	}

	public float getMsPerBeat() {
		return 60000 / beatsPerMinute;
	}
	
	public RhythmAction getLastAction() {
		if (beatNumber == 0) {
			return measureActions.get(beatsPerMeasure - 1);
		} else {
			return measureActions.get(beatNumber - 1);
		}
	}

	public float getBeatsPerMinute() {
		return beatsPerMinute;
	}

	public void setBeatsPerMinute(float beatsPerMinute) {
		this.beatsPerMinute = beatsPerMinute;
	}

	public int getBeatsPerMeasure() {
		return beatsPerMeasure;
	}

	public void setBeatsPerMeasure(int beatsPerMeasure) {
		this.beatsPerMeasure = beatsPerMeasure;
	}

	public int getMeasureNumber() {
		return measureNumber;
	}

	public void setMeasureNumber(int measureNumber) {
		this.measureNumber = measureNumber;
	}

	public int getBeatNumber() {
		return beatNumber;
	}

	public void setBeatNumber(int beatNumber) {
		this.beatNumber = beatNumber;
	}

	public int getMsSinceStart() {
		return msSinceStart;
	}

	public void setMsSinceStart(int msSinceStart) {
		this.msSinceStart = msSinceStart;
	}

	public int getMsSinceBeat() {
		return msSinceBeat;
	}

	public void setMsSinceBeat(int msSinceBeat) {
		this.msSinceBeat = msSinceBeat;
	}

	public boolean isNewMeasure() {
		return isNewMeasure;
	}

	public void setNewMeasure(boolean isNewMeasure) {
		this.isNewMeasure = isNewMeasure;
	}

	public boolean isNewBeat() {
		return isNewBeat;
	}

	public void setNewBeat(boolean isNewBeat) {
		this.isNewBeat = isNewBeat;
	}
	
	public ArrayList<RhythmAction> getMeasureActions() {
		return measureActions;
	}

}
