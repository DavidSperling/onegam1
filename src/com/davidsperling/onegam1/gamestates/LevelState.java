package com.davidsperling.onegam1.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.davidsperling.onegam1.beatparser.Metronome;
import com.davidsperling.onegam1.constants.FilePaths;
import com.davidsperling.onegam1.enemies.EnemyBullet;
import com.davidsperling.onegam1.enemies.Target;
import com.davidsperling.onegam1.globals.KeyBindings;
import com.davidsperling.onegam1.globals.Score;
import com.davidsperling.onegam1.player.Player;
import com.davidsperling.onegam1.prompts.Prompts;
import com.davidsperling.onegam1.slickFramework.GameObject;
import com.davidsperling.onegam1.slickFramework.GameState;
import com.davidsperling.onegam1.song.Measure;
import com.davidsperling.onegam1.song.MeasureType;
import com.davidsperling.onegam1.song.Song;
import com.davidsperling.onegam1.song.SongEvent;
import com.davidsperling.onegam1.song.Stutter;
import com.davidsperling.onegam1.util.InputChecker;
import com.davidsperling.onegam1.util.SoundPlayer;

public class LevelState extends GameState {

	private Song song;
	private Prompts prompts;
	private Metronome metronome;
	private Player player;
	private Stutter stutter;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		song = new Song(FilePaths.SONG_FUNK);
		
		prompts = new Prompts();
		prompts.init(container, game);
		
		metronome = new Metronome(108 * 4, 64);
		
		player = new Player(this);
		player.init(container, game);
		
		stutter = new Stutter();
		
		Score.points = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		player.render(container, g);
		prompts.render(container, g);
		
		for (GameObject o : gameObjectList) {
			o.render(container, g);
		}
		
		g.setColor(Color.white);
		g.drawString("Score: " + Score.points, 5, 5);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		metronome.update(container, delta);
		player.update(container, delta);
		
		if (metronome.isNewBeat()) {
			processBeat(container);
		}
		prompts.update(container, delta);
		
		for (GameObject o : gameObjectList) {
			o.update(container, delta);
		}
		for (int i = gameObjectList.size() - 1; i >= 0; i--) {
			if (gameObjectList.get(i).dead) {
				gameObjectList.remove(i);
			}
		}
		
		if (InputChecker.isInputPressed(container, KeyBindings.RESTART)) {
			init(container, game);
		}
		
		if (InputChecker.isInputDown(container, KeyBindings.QUIT)) {
			container.exit();
		}
	}
	
	public void processBeat(GameContainer container) {
		if (!song.isEmpty()) {
			Measure first = song.first();
			if (first.getMeasureType() == MeasureType.STUTTER) {
				stutter.addPattern(first.getStutterPattern());
				song.popMeasure();
			}
		}
		
		if (stutter.pop()) {
			SoundPlayer.playMusicFunk();
		}
		
		SongEvent songEvent = song.popBeat();
		if (songEvent != SongEvent.HOLD) {
			prompts.clearPrompts();
		}
		switch (songEvent) {
		case SAY_SHOOT_UP:
			prompts.showUp();
			SoundPlayer.playNoteG4();
			break;
		case SAY_SHOOT_LEFT:
			prompts.showLeft();
			SoundPlayer.playNoteF4();
			break;
		case SAY_SHOOT_DOWN:
			prompts.showDown();
			SoundPlayer.playNoteC4();
			break;
		case SAY_SHOOT_RIGHT:
			prompts.showRight();
			SoundPlayer.playNoteD4();
			break;
		case SAY_BLOCK_UP:
			prompts.showW();
			SoundPlayer.playNoteF5();
			break;
		case SAY_BLOCK_LEFT:
			prompts.showA();
			SoundPlayer.playNoteD5();
			break;
		case SAY_BLOCK_DOWN:
			prompts.showS();
			SoundPlayer.playNoteBb4();
			break;
		case SAY_BLOCK_RIGHT:
			prompts.showD();
			SoundPlayer.playNoteC5();
			break;
		case TITLE_CARD:
			prompts.showTitle(song.popTitleCard());
			break;
		case END:
			prompts.showTitle("Game Over. Press Enter to play again, or Esc to quit.");
		default:
			break;
		}
		
		SongEvent nextBeat = song.nextBeat();
		switch (nextBeat) {
		case DO_SHOOT_UP:
			this.gameObjectList.add(new Target(Target.Side.TOP,
					metronome.getMsPerBeat(), container));
			break;
		case DO_SHOOT_LEFT:
			this.gameObjectList.add(new Target(Target.Side.LEFT,
					metronome.getMsPerBeat(), container));
			break;
		case DO_SHOOT_DOWN:
			this.gameObjectList.add(new Target(Target.Side.BOTTOM,
					metronome.getMsPerBeat(), container));
			break;
		case DO_SHOOT_RIGHT:
			this.gameObjectList.add(new Target(Target.Side.RIGHT,
					metronome.getMsPerBeat(), container));
			break;
		case DO_BLOCK_UP:
			this.gameObjectList.add(new EnemyBullet(EnemyBullet.Side.TOP,
					metronome.getMsPerBeat(), container, this));
			break;
		case DO_BLOCK_LEFT:
			this.gameObjectList.add(new EnemyBullet(EnemyBullet.Side.LEFT,
					metronome.getMsPerBeat(), container, this));
			break;
		case DO_BLOCK_DOWN:
			this.gameObjectList.add(new EnemyBullet(EnemyBullet.Side.BOTTOM,
					metronome.getMsPerBeat(), container, this));
			break;
		case DO_BLOCK_RIGHT:
			this.gameObjectList.add(new EnemyBullet(EnemyBullet.Side.RIGHT,
					metronome.getMsPerBeat(), container, this));
			break;
		default:
			break;
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public float getMsPerBeat() {
		return metronome.getMsPerBeat();
	}
	
	public Player getPlayer() {
		return player;
	}
}
