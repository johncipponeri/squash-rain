package io.github.johncipponeri.squashrain.levels;

import io.github.johncipponeri.squashrain.entities.Squash;

import java.util.Random;


public class SquashSpawner {
	
	public static final int SPAWN_LEFT  = 0,
							SPAWN_MID   = 1,
							SPAWN_RIGHT = 2;
	
	private Level level;
	private Random rand;
	
	boolean canSpawnLeft,
			canSpawnMid,
			canSpawnRight;
	
	public SquashSpawner(Level level) {
		this.level = level;
		rand = new Random();
		
		canSpawnLeft  = true;
		canSpawnMid   = true;
		canSpawnRight = true;
	}
	
	public void reset() {
		canSpawnLeft  = true;
		canSpawnMid   = true;
		canSpawnRight = true;
	}
	
	public void spawn(float xPos, float yPos, float velocity) {
		level.squashes.add(new Squash(xPos, yPos, velocity));
	}

	public void spawnFromLeft(int columns, int rows) {
		if (!canSpawnLeft) return;
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				spawn(32 + (col * 100), 400 + (row * 100), 100);
			}
		}
	}
	
	public void spawnFromMiddle(int rows) {
		if (!canSpawnMid) return;
		
		for (int row = 0; row < rows; row++)
			spawn(132, 400 + (row * 100), 100);
	}
	
	public void spawnFromRight(int columns, int rows) {
		if (!canSpawnRight) return;
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				spawn(232 - (col * 100), 400 + (row * 100), 100);
			}
		}
	}
	
	public void spawnRandom() {
		int position = rand.nextInt(3);
		int columns  = rand.nextInt(3);
		
		switch (position) {
		case SPAWN_LEFT:
			if (!canSpawnLeft)
				if (!canSpawnMid && canSpawnRight) spawnFromRight(columns, 1);
				else spawnFromMiddle(1);
			else spawnFromLeft(columns, 1);
			break;
		case SPAWN_MID:
			if (!canSpawnMid)
				if (!canSpawnRight && canSpawnLeft) spawnFromLeft(columns, 1);
				else spawnFromRight(columns, 1);
			else spawnFromMiddle(1);
			break;
		case SPAWN_RIGHT:
			if (!canSpawnRight)
				if (!canSpawnLeft && canSpawnMid) spawnFromMiddle(1);
				else spawnFromLeft(columns, 1);
			else spawnFromRight(columns, 1);
			break;
		}
	}
}