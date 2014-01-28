package com.davidsperling.onegam1.song;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Song {
	private ArrayList<Measure> measures;
	private ArrayList<String> titleCards;
	
	public Song(String filePath) {
		measures = new ArrayList<Measure>();
		titleCards = new ArrayList<String>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			
			String line = null;

			while ((line = in.readLine()) != null) {
				if (line.length() > 0) {
					switch(line.charAt(0)) {
					case 'm':
					case 'r':
					case 's':
						measures.add(new Measure(line));
						break;
					case 't':
						parseTitleCard(line);
						break;
					}
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("Song file not found: " + filePath);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Problem loading song: " + filePath);
			e.printStackTrace();
		}
	}
	
	private void parseTitleCard(String line) {
		if (line.length() < 2) {
			System.err.println("Invalid title card definition: " +
					line);
		} else {
			String titleCard = line.substring(1).trim();
			titleCards.add(titleCard);
		}
	}
	
	public boolean isEmpty() {
		return measures.isEmpty();
	}
	
	public Measure first() {
		return measures.get(0);
	}
	
	public Measure popMeasure() {
		return measures.remove(0);
	}
	
	public SongEvent popBeat() {
		if (!measures.isEmpty()) {
			SongEvent beat = measures.get(0).pop();
			if (measures.get(0).isEmpty()) {
				popMeasure();
			}
			return beat;
		} else {
			return SongEvent.END;
		}
	}
	
	public String popTitleCard() {
		return titleCards.remove(0);
	}

}
