package io.github.johncipponeri.squashrain.screens;

import io.github.johncipponeri.squashrain.SquashRain;
import io.github.johncipponeri.squashrain.levels.Level;

public class GameScreen extends BaseScreen {

	private Level level;
	
	public GameScreen(SquashRain game) {
		super(game);
		
		level = new Level(this);
	}
	
	@Override
	public void render(float delta) {
		level.render(delta);
	}

	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		super.dispose();
		
		level.dispose();
	}
}