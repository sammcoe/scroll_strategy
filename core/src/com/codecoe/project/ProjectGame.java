package com.codecoe.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codecoe.project.GameStage;
import com.codecoe.project.screens.GameScreen;
import com.codecoe.project.utils.Constants;
import com.codecoe.project.resources.*;

public class ProjectGame extends Game {
	
	//Timekeeper
	private float stateTime;
	
	@Override
	public void create () {
		setScreen(new GameScreen());
		//Load game image files
		Resources.warriorImg = new Texture(Gdx.files.internal("warriorFrame.png"));
		Resources.warriorSwingImg = new Texture(Gdx.files.internal("warriorSwing.png"));
		Resources.bgImg = new Texture(Gdx.files.internal("background.png"));
		Resources.bgImg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		Resources.wallImg = new Texture(Gdx.files.internal("wall.png"));
		Resources.defImg = new Texture(Gdx.files.internal("airDef.png"));
		Resources.goldImg = new Texture(Gdx.files.internal("goldStore.png"));
		Resources.bgSprite1 = new Sprite(Resources.bgImg, 0, 0, Constants.WIDTH, Constants.HEIGHT);
		Resources.bgSprite2 = new Sprite(Resources.bgSprite1);
		
		//Make walking animations
		//Warrior
		TextureRegion[][] tmp = TextureRegion.split(Resources.warriorImg,
				Resources.warriorImg.getWidth()/Constants.FRAME_COLS,
				Resources.warriorImg.getHeight()/Constants.FRAME_ROWS);
		Resources.walkFrames = new TextureRegion[Constants.FRAME_COLS * Constants.FRAME_ROWS];
		int index = 0;
		for(int i = 0; i < Constants.FRAME_ROWS; i++){
			for (int j = 0; j < Constants.FRAME_COLS; j++){
				Resources.walkFrames[index++] = tmp[i][j];
			}
		}
		Resources.walkAnimation = new Animation(0.25f, Resources.walkFrames);
		Resources.userSprite = new Sprite(Resources.walkAnimation.getKeyFrame(stateTime, true));
		
		//Sound Effects
		Resources.swordClash = Gdx.audio.newSound(Gdx.files.internal("Sword-Hit2.mp3"));
		Resources.themeMusic = Gdx.audio.newMusic(Gdx.files.internal("TheForestAwakes.mp3"));
		
		//Start playing background music
		Resources.themeMusic.setLooping(true);
		Resources.themeMusic.play();
		
		//Create sprites
		Resources.batch = new SpriteBatch();
		
		//Add user troop
		Resources.userSprite.setX(Constants.WIDTH/2);
		Resources.userSprite.setY(Constants.GROUND);
		
		//Set Time
		stateTime = 0f;
		
	}
/*
	@Override
	public void render() {
		//Set clear color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Update time
		stateTime += Gdx.graphics.getDeltaTime();
		
		//Update the camera
		//camera.update();
		
		//Render scrolling background
		/*
		if(camera.position.x - Constants.WIDTH/2 > bgSprite2.getX()){
			bgSprite1.setPosition(bgSprite2.getX(), 0);
			bgSprite2.setPosition(bgSprite1.getX()+Constants.WIDTH, 0);
		}
		
		//Allow user control of user troop
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			Resources.userSprite.setX(Resources.userSprite.getX() + (Constants.MOVEFACT * Gdx.graphics.getDeltaTime()));
			Resources.userSprite.setRegion(Resources.walkAnimation.getKeyFrame(stateTime, true));
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			Resources.userSprite.setX(Resources.userSprite.getX() - (Constants.MOVEFACT * Gdx.graphics.getDeltaTime()));
			Resources.userSprite.setRegion(Resources.walkAnimation.getKeyFrame(stateTime, true));
			Resources.userSprite.flip(true, false);
		}
		if(Resources.userSprite.getX() < 0) Resources.userSprite.setX(0);
		if(Resources.userSprite.getX() > Constants.WIDTH - Resources.userSprite.getWidth()) Resources.userSprite.setX(Constants.WIDTH - Resources.userSprite.getWidth());
		
		//Draw scene
		//batch.setProjectionMatrix(camera.combined);
		//batch.begin();
		//bgSprite1.draw(batch);
		//bgSprite2.draw(batch);
		//userSprite.draw(batch);
		//batch.end();
		//stage.draw();
		//stage.act(delta);
	}
*/
}
