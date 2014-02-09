package io.github.johncipponeri.squashrain.levels;

import io.github.johncipponeri.squashrain.Assets;
import io.github.johncipponeri.squashrain.entities.Laser;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class LevelInput extends InputAdapter {

	private Level level;
	
	public LevelInput(Level level) {
		this.level = level;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (!level.canShoot) return false;
		
		if (keycode == Keys.LEFT) {
			if (!level.turretLeft.isDisabled()) {
				level.lasers.add(new Laser(level.turretLeft.getX() + level.turretLeft.getWidth() / 2 - 1, level.turretLeft.getY(), 160));
				Assets.laserShot.play(1.0f);
				level.canShoot = false;
			} else {
				Assets.laserDud.play(1.0f);
			}
		}
		
		if (keycode == Keys.UP) {
			if (!level.turretMid.isDisabled()) {
				level.lasers.add(new Laser(level.turretMid.getX() + level.turretMid.getWidth() / 2 - 1, level.turretMid.getY(), 160));
				Assets.laserShot.play(1.0f);
				level.canShoot = false;
			} else {
				Assets.laserDud.play(1.0f);
			}
		}
		
		if (keycode == Keys.RIGHT) {
			if (!level.turretRight.isDisabled()) {
				level.lasers.add(new Laser(level.turretRight.getX() + level.turretRight.getWidth() / 2 - 1, level.turretRight.getY(), 160));
				Assets.laserShot.play(1.0f);
				level.canShoot = false;
			} else {
				Assets.laserDud.play(1.0f);
			}
		}
		
		return false;
	}
}