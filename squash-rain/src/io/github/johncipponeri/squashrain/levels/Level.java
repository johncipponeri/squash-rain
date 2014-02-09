package io.github.johncipponeri.squashrain.levels;

import io.github.johncipponeri.squashrain.Assets;
import io.github.johncipponeri.squashrain.SquashRain;
import io.github.johncipponeri.squashrain.entities.Laser;
import io.github.johncipponeri.squashrain.entities.Squash;
import io.github.johncipponeri.squashrain.entities.Turret;
import io.github.johncipponeri.squashrain.screens.BaseScreen;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Level {
	
	public static final int STATE_COUNT    = 0,
							STATE_PLAY     = 1,
							STATE_PAUSE    = 2,
							STATE_GAMEOVER = 3;
	
	public static final float LASER_DELAY  = 0.6f,
							  SQUASH_DELAY = 0.6f,
							  START_DELAY  = 3.5f;
	
	SquashRain game;
	
	OrthographicCamera cam;
	SpriteBatch batch;
	
	private LevelRenderer renderer;
	private LevelInput input;

	CopyOnWriteArrayList<Laser>  lasers;
	CopyOnWriteArrayList<Squash> squashes;
	
	SquashSpawner spawner;
	
	Turret turretLeft,
	   	   turretMid,
	   	   turretRight;

	private float laserTimer,
				  squashTimer,
				  startTimer;
	
	boolean canShoot,
			count;
	
	int state;
	int score;
	
	Sprite pauseButton,
		   resumeButton;
	
	public Level(BaseScreen screen) {
		game = screen.getGame();
		
		cam   = screen.getCamera();
		batch = screen.getBatch();
		
		renderer = new LevelRenderer(this);
		input    = new LevelInput(this);
		
		lasers   = new CopyOnWriteArrayList<Laser>();
		squashes = new CopyOnWriteArrayList<Squash>();
		
		spawner = new SquashSpawner(this);
		
		turretLeft  = new Turret(25, 30);
		turretMid   = new Turret(125, 30);
		turretRight = new Turret(225, 30);
		
		laserTimer  = LASER_DELAY; 
		squashTimer = SQUASH_DELAY;
		startTimer  = START_DELAY;
		
		canShoot = true;
		count    = true;
		
		state = STATE_COUNT;
		score = 0;
		
		pauseButton  = new Sprite(Assets.pauseButton);
		pauseButton.setPosition(BaseScreen.SCREEN_WIDTH - pauseButton.getWidth() - 5, 370);
		
		resumeButton = new Sprite(Assets.resumeButton);
		resumeButton.setPosition(BaseScreen.SCREEN_WIDTH / 2 - resumeButton.getWidth() / 2, BaseScreen.SCREEN_HEIGHT / 2 - resumeButton.getHeight() / 2);
	}
	
	public void checkGameOver() {
		if (turretLeft.isDisabled() && turretMid.isDisabled() && turretRight.isDisabled()) state = STATE_GAMEOVER;
	}
	
	public void updateTurrets(float delta) {
		turretLeft.update(delta);
		turretMid.update(delta);
		turretRight.update(delta);
	}
	
	public void updateLasers(float delta) {
		if ((laserTimer -= delta) <= 0) {
			laserTimer = LASER_DELAY;
			canShoot = true;
		}
		
		for (int l = 0; l < lasers.size(); l++) {
			Laser laser = lasers.get(l);
			
			if (laser.getY() > BaseScreen.SCREEN_HEIGHT) lasers.remove(l);
			else laser.update(delta);
		}
	}
	
	public void updateSquash(float delta) {
		if ((squashTimer -= delta) <= 0) {
			squashTimer = SQUASH_DELAY;
			spawner.spawnRandom();
		}
		
		for (int s = 0; s < squashes.size(); s++) {
			Squash squash = squashes.get(s);
			
			if (squash.getY() < 0) squashes.remove(s);
			else squash.update(delta);
		}
	}
	
	public void updateCollisions(float delta) {
		Iterator<Squash> squash = squashes.iterator();
		
		while (squash.hasNext()) {
			Squash curSquash = squash.next();
			Rectangle squashBounds = curSquash.getBounds();
			
			if (squashBounds.overlaps(turretLeft.getBounds())) {
				if (!turretLeft.isDisabled()) turretLeft.disable(); 
				squashes.remove(curSquash);
				score -= 5;
				Assets.explosion.play(1.0f);
				continue;
			}
			
			if (squashBounds.overlaps(turretMid.getBounds())) {
				if (!turretMid.isDisabled()) turretMid.disable();
				squashes.remove(curSquash);
				score -= 5;
				Assets.explosion.play(1.0f);
				continue;
			}
			
			if (squashBounds.overlaps(turretRight.getBounds())) {
				if (!turretRight.isDisabled()) turretRight.disable();
				squashes.remove(curSquash);
				score -= 5;
				Assets.explosion.play(1.0f);
				continue;
			}
			
			Iterator<Laser> laser = lasers.iterator();
			
			while (laser.hasNext()) {
				Laser curLaser = laser.next(); 
				if (curLaser.getBounds().overlaps(squashBounds)) {
					Assets.hit.play(1.0f);
					lasers.remove(curLaser);
					squashes.remove(curSquash);
					score += 10;
				}
			}
		}
	}
	
	public void update(float delta) {
		switch (state) {
		case STATE_COUNT:
			if ((startTimer -= delta) <= 0) {
				state = STATE_PLAY;
				Gdx.input.setInputProcessor(input);
			} break;
		case STATE_PLAY:
			checkGameOver();
			
			if (Gdx.input.isTouched()) {
				Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(touchPos);
				
				if (new Rectangle(touchPos.x, touchPos.y, 1, 1).overlaps(pauseButton.getBoundingRectangle()))
					state = STATE_PAUSE;
			}

			updateTurrets(delta);
			updateLasers(delta);
			updateSquash(delta);
			updateCollisions(delta);
			break;
		case STATE_PAUSE:
			if (Gdx.input.isTouched()) {
				Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(touchPos);
				
				if (new Rectangle(touchPos.x, touchPos.y, 1, 1).overlaps(resumeButton.getBoundingRectangle())) {
					Gdx.input.setInputProcessor(null);
					count = true;
					startTimer = START_DELAY;
					state = STATE_COUNT;
				}
			} break;
		case STATE_GAMEOVER:
			if (Gdx.input.isTouched()) {
				Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(touchPos);
				
				if (new Rectangle(touchPos.x, touchPos.y, 1, 1).overlaps(resumeButton.getBoundingRectangle())) {
					turretLeft.enable();
					turretMid.enable();
					turretRight.enable();
					
					spawner.reset();
					score = 0;
					
					lasers.clear();
					squashes.clear();
					
					count = true;
					startTimer = START_DELAY;
					state = STATE_COUNT;
				}
			} break;
		}
	}
	
	public void render(float delta) {
		update(delta);
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 0);
		
		batch.begin();
		
		switch (state) {
		case STATE_COUNT:
			renderer.render(delta);
			float countX = BaseScreen.SCREEN_WIDTH / 2 - Assets.liquidFont.getBounds("3").width / 2,
				  countY = BaseScreen.SCREEN_HEIGHT / 2 + Assets.liquidFont.getBounds("3").height / 2;
			
			if (startTimer > 2) {
				if (count) {
					Assets.count.play(1.0f);
					count = false;
				} Assets.liquidFont.draw(batch, "3", countX, countY);
			} else if (startTimer > 1) {
				if (!count) {
					Assets.count.play(1.0f);
					count = true;
				} Assets.liquidFont.draw(batch, "2", countX, countY);
			} else {
				if (count) {
					Assets.count.play(1.0f);
					count = false;
				} Assets.liquidFont.draw(batch, "1", countX, countY);
			} break;
		case STATE_PLAY:
			renderer.render(delta);
			pauseButton.draw(batch);
			break;
		case STATE_PAUSE:
			renderer.render(delta);
			resumeButton.draw(batch);
			break;
		case STATE_GAMEOVER:
			renderer.render(delta);
			
			String strScore = "SCORE: " + score;
			Assets.liquidFont.draw(batch, "GAME OVER", BaseScreen.SCREEN_WIDTH / 2 - Assets.liquidFont.getBounds("GAME OVER").width / 2, BaseScreen.SCREEN_HEIGHT / 2 + Assets.liquidFont.getBounds("GAME OVER").height / 2 + 150);
			Assets.liquidFont.draw(batch, strScore, BaseScreen.SCREEN_WIDTH / 2 - Assets.liquidFont.getBounds(strScore).width / 2, BaseScreen.SCREEN_HEIGHT / 2 + Assets.liquidFont.getBounds(strScore).height / 2 + 100);
			
			resumeButton.draw(batch);
		}
		
		batch.end();
	}
	
	public void dispose() {
		renderer.dispose();
	}
}