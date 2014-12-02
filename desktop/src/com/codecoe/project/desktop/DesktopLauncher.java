package com.codecoe.project.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codecoe.project.ProjectGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Battlefield";
		config.width = 800;
		config.height = 400;
		new LwjglApplication(new ProjectGame(), config);
	}
}
