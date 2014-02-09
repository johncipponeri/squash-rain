package io.github.johncipponeri.squashrain.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
	
	Texture texture;
	float xPos, yPos;
	
	public Entity() {
		this(null, 0, 0);
	}
	
	public Entity(Texture texture) {
		this(texture, 0, 0);
	}
	
	public Entity(float xPos, float yPos) {
		this(null, xPos, yPos);
	}
	
	public Entity(Texture texture, float xPos, float yPos) {
		this.texture = texture;
		
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void render(SpriteBatch batch, float delta) {
		batch.draw(texture, xPos, yPos);
	}
	
	public float getX() {
		return xPos;
	}
	
	public float getY() {
		return yPos;
	}
	
	public int getWidth() {
		return texture.getWidth();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
	}
}