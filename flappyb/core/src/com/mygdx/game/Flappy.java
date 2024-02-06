package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class Flappy extends ApplicationAdapter {
	//constates para ventana
	public static final int WIDTH=480;
	public static final int HEIGHT=720;
	public static final String TITLE="Flappy";

	private Music music;

	private GameStateManager gsm;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		ScreenUtils.clear(0, 1, 0, 1);
		gsm.push(new MenuState(gsm));
		setUpMusic();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime()); //Tiempo para actualizarse
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		//Dejar de usar un elemento. Eliminado de la memoria.
		batch.dispose();
		music.dispose();

	}

	private void setUpMusic(){
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	}
}
