package io.github.johncipponeri.squashrain.entities;

import io.github.johncipponeri.squashrain.Assets;

public class Laser extends Entity {

	private float velocity;
	
	public Laser(float xPos, float yPos) {
		this(xPos, yPos, 0);
	}
	
	public Laser(float xPos, float yPos, float velocity) {
		super(Assets.laser, xPos, yPos);
		
		this.velocity = velocity;
	}
	
	public void update(float delta) {
		yPos += velocity * delta;
	}
}