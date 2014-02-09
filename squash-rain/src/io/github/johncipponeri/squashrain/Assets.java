package io.github.johncipponeri.squashrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {

	public static Texture background,
						  ground,
				  		  turret,
				  		  brokenTurret,
				  		  spark,
				  		  laser,
				  		  squash,
				  		  playButton,
				  		  pauseButton,
				  		  resumeButton,
				  		  logo;
	
	public static Sound laserShot,
						laserDud,
						hit,
						button,
						count,
						explosion;
	
	public static BitmapFont liquidFont;
	
	private static Texture loadTexture(String filename) {
		return new Texture(Gdx.files.internal(filename));
	}
	
	private static Sound loadSound(String filename) {
		return Gdx.audio.newSound(Gdx.files.internal(filename));
	}
	
	public static void load() {
		background   = loadTexture("data/background.png");
		ground 	     = loadTexture("data/ground.png");
		turret       = loadTexture("data/turret.png");
		brokenTurret = loadTexture("data/brokenTurret.png");
		spark  	     = loadTexture("data/spark.png");
		laser  	     = loadTexture("data/laser.png");
		squash 	     = loadTexture("data/squash.png");
		playButton   = loadTexture("data/playButton.png");
		pauseButton  = loadTexture("data/pauseButton.png");
		resumeButton = loadTexture("data/resumeButton.png");
		logo         = loadTexture("data/logo.png");
		
		laserShot = loadSound("data/laser.wav");
		laserDud  = loadSound("data/laser_dud.wav");
		hit       = loadSound("data/hit.wav");
		button    = loadSound("data/button.wav");
		count     = loadSound("data/count.wav");
		explosion = loadSound("data/explosion.wav");
		
		liquidFont = new BitmapFont(Gdx.files.internal("data/lqdkdz.fnt"), Gdx.files.internal("data/lqdkdz.png"), false);
	}
	
	public static void dispose() {
		background.dispose();
		ground.dispose();
		turret.dispose();
		brokenTurret.dispose();
		spark.dispose();
		laser.dispose();
		squash.dispose();
		playButton.dispose();
		pauseButton.dispose();
		resumeButton.dispose();
		logo.dispose();
		
		laserShot.dispose();
		laserDud.dispose();
		hit.dispose();
		button.dispose();
		count.dispose();
		explosion.dispose();
		
		liquidFont.dispose();
	}
}