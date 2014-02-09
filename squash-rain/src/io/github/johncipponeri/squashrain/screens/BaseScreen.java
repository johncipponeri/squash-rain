package io.github.johncipponeri.squashrain.screens;

import io.github.johncipponeri.squashrain.SquashRain;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BaseScreen extends ScreenAdapter {
	
	public static final int SCREEN_WIDTH  = 300,
							SCREEN_HEIGHT = 400;
	
	SquashRain game;
	
	OrthographicCamera cam;
	SpriteBatch batch;

	public BaseScreen(SquashRain game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		this.batch = new SpriteBatch();
	}
	
	public SquashRain getGame() {
		return game;
	}
	
	public OrthographicCamera getCamera() {
		return cam;
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}