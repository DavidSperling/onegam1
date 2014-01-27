package com.davidsperling.onegam1.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.davidsperling.onegam1.beatparser.Metronome;
import com.davidsperling.onegam1.constants.FilePaths;
import com.davidsperling.onegam1.player.Player;
import com.davidsperling.onegam1.prompts.Prompts;
import com.davidsperling.onegam1.slickFramework.GameState;
import com.davidsperling.onegam1.song.Song;
import com.davidsperling.onegam1.song.SongEvent;
import com.davidsperling.onegam1.util.SoundPlayer;

public class LevelState extends GameState {

	private Song song;
	private Prompts prompts;
	private Metronome metronome;
	private Player player;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		song = new Song(FilePaths.SONG_FUNK);
		
		prompts = new Prompts();
		prompts.init(container, game);
		
		metronome = new Metronome(108 * 2, 32);
		
		player = new Player();
		player.init(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		player.render(container, g);
		prompts.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		metronome.update(container, delta);
		player.update(container, delta);
		
		if (metronome.isNewBeat()) {
			SongEvent songEvent = song.popBeat();
			if (songEvent != SongEvent.HOLD) {
				prompts.clearPrompts();
			}
			switch (songEvent) {
			case SAY_SHOOT_UP:
				prompts.showUp();
				SoundPlayer.playMetronomeTick();
				break;
			case SAY_SHOOT_LEFT:
				prompts.showLeft();
				SoundPlayer.playMetronomeTick();
				break;
			case SAY_SHOOT_DOWN:
				prompts.showDown();
				SoundPlayer.playMetronomeTick();
				break;
			case SAY_SHOOT_RIGHT:
				prompts.showRight();
				SoundPlayer.playMetronomeTick();
				break;
			case SAY_BLOCK_UP:
				prompts.showW();
				break;
			case SAY_BLOCK_LEFT:
				prompts.showA();
				break;
			case SAY_BLOCK_DOWN:
				prompts.showS();
				break;
			case SAY_BLOCK_RIGHT:
				prompts.showD();
				break;
			case TITLE_CARD:
				prompts.showTitle(song.popTitleCard());
				break;
			default:
				break;
			}
		}
		prompts.update(container, delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
