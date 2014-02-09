package io.github.johncipponeri.squashrain.screens;

import io.github.johncipponeri.squashrain.Assets;
import io.github.johncipponeri.squashrain.SquashRain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends BaseScreen {

	private Sprite logo,
				   playButton;
	
	public MenuScreen(SquashRain game) {
		super(game);
		
		logo = new Sprite(Assets.logo);
		logo.scale(-0.5f);
		logo.setPosition(BaseScreen.SCREEN_WIDTH / 2 - logo.getWidth() / 2, 250);
		
		playButton = new Sprite(Assets.playButton);
		playButton.setPosition(BaseScreen.SCREEN_WIDTH / 2 - playButton.getWidth() / 2, BaseScreen.SCREEN_HEIGHT / 2 - playButton.getHeight() / 2);
	}

	public void update(float delta) {
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touchPos);
			
			if (new Rectangle(touchPos.x, touchPos.y, 1, 1).overlaps(playButton.getBoundingRectangle())) {
				Assets.button.play(1.0f);
				game.setScreen(new GameScreen(game));
			}
		}
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 0);
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		
		logo.draw(batch);
		playButton.draw(batch);
		
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}