package com.davidsperling.onegam1.beatparser;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import com.davidsperling.onegam1.constants.GameConstants;
import com.davidsperling.onegam1.constants.RhythmAction;
import com.davidsperling.onegam1.globals.KeyBindings;
import com.davidsperling.onegam1.util.InputChecker;

public class BeatBuffer {
	ArrayList<RhythmEvent> eventList;
	
	public BeatBuffer() {
		eventList = new ArrayList<RhythmEvent>();
	}
	
	public void update(GameContainer container, int delta) {
		boolean isOn = InputChecker.isInputDown(container, KeyBindings.P1_INPUT);
		if (!eventList.isEmpty() && eventList.get(0).isOn() == isOn) {
			eventList.get(0).pushLength(delta);
		} else {
			eventList.add(0, new RhythmEvent(isOn, delta));
		}
	}
	
	public RhythmAction processBeat(float msPerBeat) {
		int msToProcess = (int)(msPerBeat * GameConstants.EARLY_BEAT_MULTIPLIER);
		int currentMs = 0;
		int onEventsFound = 0;
		boolean isLong = false;
		int eventIndex = 0;
		while ((eventIndex < eventList.size()) && (currentMs < msToProcess)) {
			RhythmEvent event = eventList.get(eventIndex);
			if (currentMs + event.getLength() < msToProcess) {
				if (event.isOn() && (currentMs + event.getLength() > msPerBeat * (GameConstants.EARLY_BEAT_MULTIPLIER - 1))) {
					onEventsFound++;
					if (event.getLength() > msPerBeat * GameConstants.LONG_BEAT_LENGTH) {
						isLong = true;
					}
				}
			}
			currentMs += event.getLength();
			eventIndex++;
		}
		for (int i = eventList.size() - 1; i > eventIndex; i--) {
			eventList.remove(i);
		}
		if (isLong) {
			return RhythmAction.LONG;
		}
		if (onEventsFound == 0) {
			return RhythmAction.BLANK;
		}
		switch(onEventsFound) {
			case 1:
				return RhythmAction.SHORT1;
			case 2:
				return RhythmAction.SHORT2;
			default :
				return RhythmAction.SHORT3;
		}
	}
}
