package io.github.johncipponeri.squashrain.entities;

import io.github.johncipponeri.squashrain.Assets;

public class Turret extends Entity {
	
	public static final int DISABLED_DELAY = 2;
	
	private float disabledTimer;
	private boolean isDisabled;
	
	public Turret(float xPos, float yPos) {
		super(Assets.turret, xPos, yPos);
		
		disabledTimer = 0;
		isDisabled = false;
	}
	
	public boolean isDisabled() {
		return isDisabled;
	}
	
	public void enable() {
		isDisabled = false;
		texture = Assets.turret;
		disabledTimer = 0;
	}
	
	public void disable() {
		isDisabled = true;
		texture = Assets.brokenTurret;
		disabledTimer = DISABLED_DELAY;
	}
	
	public void update(float delta) {
		System.out.println(disabledTimer);
		if (isDisabled() && (disabledTimer -= delta) <= 0)
			enable();
	}
}