package io.github.johncipponeri.squashrain;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Squash Rain";
		cfg.resizable = false;
		cfg.useGL20 = true;
		cfg.width = 300;
		cfg.height = 400;
		
		new LwjglApplication(new SquashRain(), cfg);
	}
}
