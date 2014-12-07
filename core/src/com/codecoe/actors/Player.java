package com.codecoe.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.GameActor;
import com.codecoe.project.box2d.PlayerUserData;
import com.codecoe.project.utils.Constants;
import com.codecoe.project.resources.*;

public class Player extends GameActor {
	
	private boolean jumping;
	private boolean swinging;
	private boolean running;
	private boolean flip;
	private Animation runningAnimation;
	private TextureRegion swingTexture;
	private TextureRegion standTexture;
	private TextureRegion jumpTexture;
	private float stateTime;
	private float swingTime;
	
	public Player(Body body){
		super(body);
		TextureAtlas textureAtlas = new TextureAtlas(Constants.PLAYER_ATLAS_PATH);
		TextureRegion[] runningFrames = new TextureRegion[Constants.PLAYER_RUNNING_REGION_NAMES.length];
		for (int i = 0; i < Constants.PLAYER_RUNNING_REGION_NAMES.length; i++){
			String path = Constants.PLAYER_RUNNING_REGION_NAMES[i];
			runningFrames[i] = textureAtlas.findRegion(path);
		}
		swinging = false;
		runningAnimation = new Animation(0.1f, runningFrames);
		stateTime = 0f;
		swingTexture = textureAtlas.findRegion(Constants.PLAYER_SWING_REGION_NAME);
		standTexture = textureAtlas.findRegion(Constants.PLAYER_RUNNING_REGION_NAMES[0]);
		jumpTexture = textureAtlas.findRegion(Constants.PLAYER_RUNNING_REGION_NAMES[0]);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		if(swingTime < 20 && swinging){
			swingTime++;
		}else swinging = false;
		if(swinging){
			if(!flip){
				batch.draw(swingTexture, screenRectangle.x + 10, screenRectangle.y * .6f, screenRectangle.getWidth() + 25, screenRectangle.getHeight());
			}else{
				batch.draw(swingTexture, screenRectangle.x - 40, screenRectangle.y * .6f, screenRectangle.getWidth() + 25, screenRectangle.getHeight());
			}
		}else if (jumping) {
            batch.draw(jumpTexture, screenRectangle.x, screenRectangle.y * .6f, screenRectangle.width, screenRectangle.height);
        }else if(running){
			batch.draw(runningAnimation.getKeyFrame(stateTime, true), screenRectangle.x, screenRectangle.y * .6f, screenRectangle.getWidth(), screenRectangle.getHeight());
    		stateTime += Gdx.graphics.getDeltaTime();
		}
		else{
			//Standing
			batch.draw(standTexture, screenRectangle.x, screenRectangle.y * .6f, screenRectangle.width, screenRectangle.height);
		}
	}
	
	@Override
	public PlayerUserData getUserData(){
		return (PlayerUserData) userData;
	}
	
	public void jump() {
		if(!jumping){
			//body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(),	true);
			body.setLinearVelocity(getUserData().getJumpingLinearImpulse());
			jumping = true;
		}
	}
	
	public void lilJump() {
		if(!jumping){
			body.setLinearVelocity(getUserData().getJumpingLinearImpulse().x,getUserData().getJumpingLinearImpulse().y * .05f);
			jumping = true;
		}
	}
	
	public void swing(){
		if(!swinging){
			swingTime = 0;
			swinging = true;
		}
	}
	
	public boolean isSwinging(){
		return swinging;
	}
	
	public void runRight(){
		running = true;
		if(flip){
			flip();
		}
		flip = false;
	}
	
	public void runLeft(){
		running = true;
		if(!flip){
			flip();
		}
		flip = true;
	}
	
	private void flip(){
		TextureRegion[] temp = runningAnimation.getKeyFrames();
		for(int i = 0; i < temp.length; i++){
			temp[i].flip(true, false);
		}
		swingTexture.flip(true, false);
		jumpTexture.flip(true, false);
		standTexture.flip(true,false);
	}
	
	public void stop(){
		running = false;
	}
	
	public void landed() {
		jumping = false;
	}
}
