package com.codecoe.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.box2d.UserData;
import com.codecoe.project.utils.Constants;

public abstract class GameActor extends Actor {

	protected boolean reverse;
	protected boolean pause;
	protected int speed = 100;
	protected int health = 60;
	protected Body body;
	protected UserData userData;
	protected Rectangle screenRectangle;
	protected Texture healthBar;
	protected Texture hitBar;
	
	public GameActor(){
		
	}
	
	public GameActor(Body body){
		this.body = body;
		this.userData = (UserData) body.getUserData();
		screenRectangle = new Rectangle();
		healthBar = new Texture(Gdx.files.internal(Constants.HEALTH_PATH));
		hitBar = new Texture(Gdx.files.internal(Constants.HIT_PATH));
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		
		if(body.getUserData() != null){
			updateRectangle();
		}else{
			remove();
		}
	}
	
	public abstract UserData getUserData();
	
	private void updateRectangle(){
		screenRectangle.x = transformToScreen(body.getPosition().x - userData.getWidth() / 2);
		screenRectangle.y = transformToScreen(body.getPosition().y - userData.getHeight() / 2);
		screenRectangle.width = transformToScreen(userData.getWidth());
		screenRectangle.height = transformToScreen(userData.getHeight());
	}
	
	protected float transformToScreen(float n){
		return Constants.WORLD_TO_SCREEN * n;
	}
	
	public void pause(){
		pause = true;
	}
	
	public void unpause(){
		pause = false;
	}
	
	public void reverse(){
		reverse = true;
	}
	
	public void forward(){
		reverse = false;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public int getHealth(){
		return health;
	}
}
