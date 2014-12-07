package com.codecoe.project.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static final int VIEWPORT_WIDTH = 800;
	public static final int VIEWPORT_HEIGHT = 400;
	public final float TIME_STEP = 1 / 300f;
	//Dimensions
	public static final int WIDTH = 800;		
	public static final int HEIGHT = 400;		
	public static final int MOVEFACT = 200;		
	public static final int GROUND = 40;		
	public static final int FRAME_COLS = 2; 	
	public static final int FRAME_ROWS = 1; 	
	public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);
	public static final float WORLD_TO_SCREEN = 1;
	//Ground attributes
	public static final float GROUND_X = WIDTH / 2;
	public static final float GROUND_Y = 0;
	public static final float GROUND_WIDTH = WIDTH;
	public static final float GROUND_HEIGHT = HEIGHT / 4;
	public static final float GROUND_DENSITY = 0f;
	//Unit attributes
	public static final float UNIT_WIDTH = 50;
	public static final float UNIT_HEIGHT = 100;
	public static final float UNIT_X = GROUND_WIDTH / 2;
	public static final float UNIT_Y = GROUND_Y + GROUND_HEIGHT;
	public static final float UNIT_DENSITY = 0.5f;
	//Player
	public static final Vector2 PLAYER_JUMP_IMPULSE = new Vector2(0, 120f);
	public static final float PLAYER_GRAVITY_SCALE = 25f;
	public static final String PLAYER_ATLAS_PATH = "sprites.txt";
	public static final String[] PLAYER_RUNNING_REGION_NAMES = new String [] {
		"warrior1",
		"warrior2"
	};
	public static final String PLAYER_SWING_REGION_NAME = "warriorSwing";
	public static final int PLAYER_HIT = 10;
	//Info
	public static final String HEALTH_PATH = "greenhit.png";
	public static final String HIT_PATH = "redhit.png";
	public static final int BAR_HEIGHT = 10;
	public static final int BAR_WIDTH = 60;
	//Structures
	public static final String AIR_DEF_REGION_NAME = "airDef";
	public static final String GOLD_STORE_REGION_NAME = "goldStore";
	public static final String WALL_REGION_NAME = "wall";
	//Wall
	public static final float WALL_WIDTH = 35;
	public static final float WALL_HEIGHT = 200;
	public static final float WALL_Y = GROUND_Y + GROUND_HEIGHT * 1.5f;
	//Ballistae
	public static final float BALLISTAE_WIDTH = 50;
	public static final float BALLISTAE_HEIGHT = 75;
	public static final float BALLISTAE_Y = GROUND_Y + GROUND_HEIGHT;
	//Resource
	public static final float RESOURCE_WIDTH = 50;
	public static final float RESOURCE_HEIGHT = 100;
	public static final float RESOURCE_Y = GROUND_Y + GROUND_HEIGHT*.85f;
}
