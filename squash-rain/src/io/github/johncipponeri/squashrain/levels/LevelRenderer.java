package io.github.johncipponeri.squashrain.levels;

import io.github.johncipponeri.squashrain.Assets;
import io.github.johncipponeri.squashrain.entities.Laser;
import io.github.johncipponeri.squashrain.entities.Squash;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelRenderer {

	private Level level;
	private SpriteBatch batch;
	
	public LevelRenderer(Level level) {
		this.level = level;
		batch = level.batch;
	}

	public void drawLasers(float delta) {
		for (Laser laser : level.lasers)
			laser.render(batch, delta);
	}

	public void drawTurrets(float delta) {
		level.turretLeft.render(batch, delta);
		level.turretMid.render(batch, delta);
		level.turretRight.render(batch, delta);
	}
	
	public void drawSquash(float delta) {
		for (Squash squash : level.squashes)
			squash.render(batch, delta);
	}
	
	public void drawScore(float delta) {
		Assets.liquidFont.draw(batch, String.valueOf(level.score), 10, 390);
	}
	
	public void render(float delta) {
		batch.draw(Assets.background, 0, 0);
		batch.draw(Assets.ground, 0, 0);
		
		drawLasers(delta);
		drawTurrets(delta);
		drawSquash(delta);
		
		if (level.state != Level.STATE_GAMEOVER)
			drawScore(delta);
	}
	
	public void dispose() {
		batch.dispose();
	}
}