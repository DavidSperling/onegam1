package com.davidsperling.onegam1.beatparser;

public class RhythmEvent {
	private boolean isOn;
	private int length;
	
	public RhythmEvent(boolean isOn, int length) {
		this.isOn = isOn;
		this.length = length;
	}
	
	public void pushLength(int length) {
		this.length += length;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	
}
