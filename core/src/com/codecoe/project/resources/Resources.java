package com.codecoe.project.resources;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codecoe.project.Player;

public class Resources {
		//Animations
		public static Animation walkAnimation;		//Animation for walking
		//Textures
		public static Texture warriorImg;			//Texture for basic warrior
		public static Texture warriorSwingImg;		//Texture for basic warrior attack
		public static Texture bgImg;				//Texture for background image
		public static Texture wallImg;				//Texture for wall structure
		public static Texture defImg;				//Texture for air defense structure
		public static Texture goldImg;				//Texture for resource building structure
		public static TextureRegion[] walkFrames;  //Texture region array for walk animation
		public static TextureRegion currentFrame;  //Texture region for current frame
		//Sounds
		public static Sound swordClash;			//Sound for sword clash
		//Music
		public static Music themeMusic;			//Game theme music
		//Graphics
		public static OrthographicCamera camera;	//Basic camera
		public static SpriteBatch batch;			//Sprite batch for rendering
		public static Sprite bgSprite1;			//Frame 1 for scrolling background
		public static Sprite bgSprite2;			//Frame 2 for scrolling background
		//Player
		public static Player user;
		public static Sprite userSprite;
}
