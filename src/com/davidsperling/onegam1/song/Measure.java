package com.davidsperling.onegam1.song;

import java.util.ArrayList;

public class Measure {
	
	private MeasureType measureType;
	private ArrayList<SongEvent> beats;
	
	public Measure(String measureDefinition) {
		measureType = null;
		beats = new ArrayList<SongEvent>();
		try {
			if (measureDefinition.length() < 2) {
				throw new Exception("Invalid measure definition: " + 
						measureDefinition);
			}
			
			switch (measureDefinition.charAt(0)) {
			case 'm':
				measureType = MeasureType.NORMAL;
				parseNormal(measureDefinition);
				break;
			case 'r':
				measureType = MeasureType.REST;
				parseRest(measureDefinition);
				break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void parseNormal(String measureDefinition) {
		
		ArrayList<SongEvent> sayBeats = new ArrayList<SongEvent>();
		ArrayList<SongEvent> doBeats = new ArrayList<SongEvent>();
		
		for (int i = 1; i < measureDefinition.length(); i++) {
			switch(measureDefinition.charAt(i)) {
			case 'i':
				sayBeats.add(SongEvent.SAY_SHOOT_UP);
				doBeats.add(SongEvent.DO_SHOOT_UP);
				break;
			case 'j':
				sayBeats.add(SongEvent.SAY_SHOOT_LEFT);
				doBeats.add(SongEvent.DO_SHOOT_LEFT);
				break;
			case 'k':
				sayBeats.add(SongEvent.SAY_SHOOT_DOWN);
				doBeats.add(SongEvent.DO_SHOOT_DOWN);
				break;
			case 'l':
				sayBeats.add(SongEvent.SAY_SHOOT_RIGHT);
				doBeats.add(SongEvent.DO_SHOOT_RIGHT);
				break;
			case 'w':
				sayBeats.add(SongEvent.SAY_BLOCK_UP);
				doBeats.add(SongEvent.DO_BLOCK_UP);
				break;
			case 'a':
				sayBeats.add(SongEvent.SAY_BLOCK_LEFT);
				doBeats.add(SongEvent.DO_BLOCK_LEFT);
				break;
			case 's':
				sayBeats.add(SongEvent.SAY_BLOCK_DOWN);
				doBeats.add(SongEvent.DO_BLOCK_DOWN);
				break;
			case 'd':
				sayBeats.add(SongEvent.SAY_BLOCK_RIGHT);
				doBeats.add(SongEvent.DO_BLOCK_RIGHT);
				break;
			case 't':
				sayBeats.add(SongEvent.TITLE_CARD);
				doBeats.add(SongEvent.REST);
				break;
			case '.':
				sayBeats.add(SongEvent.REST);
				doBeats.add(SongEvent.REST);
				break;
			case '-':
				sayBeats.add(SongEvent.HOLD);
				doBeats.add(SongEvent.HOLD);
				break;
			default:
				break;
			}
		}
		
		beats.addAll(sayBeats);
		beats.addAll(doBeats);
	}
	
	private void parseRest(String measureDefinition) {
		for (int i = 1; i < measureDefinition.length(); i++) {
			switch(measureDefinition.charAt(i)) {
			case 't':
				beats.add(SongEvent.TITLE_CARD);
				break;
			case '.':
				beats.add(SongEvent.REST);
				break;
			case '-':
				beats.add(SongEvent.HOLD);
				break;
			default:
				break;
			}
		}
	}
	
	public MeasureType getMeasureType() {
		return measureType;
	}
	
	public SongEvent getBeat(int beatNumber) {
		return beats.get(beatNumber);
	}
	
	public SongEvent pop() {
		return beats.remove(0);
	}
	
	public boolean isEmpty() {
		return beats.isEmpty();
	}
}
