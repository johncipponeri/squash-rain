package io.github.johncipponeri.squashrain.entities;

import io.github.johncipponeri.squashrain.Assets;

public class Squash extends Entity {

	private float velocity;
	
	public Squash(float xPos, float yPos) {
		this(xPos, yPos, 0);
	}
	
	public Squash(float xPos, float yPos, float velocity) {
		super(Assets.squash, xPos, yPos);
		
		this.velocity = velocity;
	}
	
	public void update(float delta) {
		yPos -= velocity * delta;
	}
}