package io.github.johncipponeri.squashrain;

import io.github.johncipponeri.squashrain.screens.MenuScreen;

import com.badlogic.gdx.Game;

public class SquashRain extends Game {
	
	@Override
	public void create() {
		Assets.load();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		
		Assets.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}