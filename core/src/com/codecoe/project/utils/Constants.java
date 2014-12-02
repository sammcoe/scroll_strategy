package com.codecoe.project.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector2;
import com.codecoe.project.ProjectGame;
import com.codecoe.project.resources.Resources;

public class Constants {
	public static final int VIEWPORT_WIDTH = 800;
	public static final int VIEWPORT_HEIGHT = 400;
	public final float TIME_STEP = 1 / 300f;
	//Dimensions
	public static final int WIDTH = 800;		//Width of the view
	public static final int HEIGHT = 400;		//Height of the view
	public static final int MOVEFACT = 200;		//Factor by which the units move
	public static final int GROUND = 40;		//Height of the ground
	public static final int FRAME_COLS = 2; 	//Number of columns in an animation frame
	public static final int FRAME_ROWS = 1; 	//Number of rows in an animation frame
	public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);
	//Ground attributes
	public static final float GROUND_X = WIDTH / 2;
	public static final float GROUND_Y = 0;
	public static final float GROUND_WIDTH = WIDTH;
	public static final float GROUND_HEIGHT = HEIGHT / 4;
	public static final float GROUND_DENSITY = 0f;
	//Unit attributes
	public static final float UNIT_WIDTH = 25;//Resources.userSprite.getWidth();
	public static final float UNIT_HEIGHT = 75;//Resources.userSprite.getHeight();
	public static final float UNIT_X = GROUND_WIDTH / 2;
	public static final float UNIT_Y = GROUND_Y + GROUND_HEIGHT;
	public static final float UNIT_DENSITY = 0.5f;
	//Player
	public static final Vector2 PLAYER_JUMP_IMPULSE = new Vector2(0, 180f);
	public static final float PLAYER_GRAVITY_SCALE = 25f;
}
