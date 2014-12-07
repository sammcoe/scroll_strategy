package com.codecoe.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.codecoe.project.GameActor;
import com.codecoe.project.box2d.PlayerUserData;
import com.codecoe.project.box2d.UserData;
import com.codecoe.project.resources.Resources;
import com.codecoe.project.utils.Constants;

public class Background extends GameActor{
	private final TextureRegion textureRegion;
	private Rectangle textureRegionBounds1;
	private Rectangle textureRegionBounds2;
	private Texture bgImg = new Texture(Gdx.files.internal("background.png"));
	
	public Background(){
		textureRegion = new TextureRegion(bgImg);
		textureRegionBounds1 = new Rectangle(0 - Constants.VIEWPORT_WIDTH / 2, 0, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		textureRegionBounds2 = new Rectangle(Constants.VIEWPORT_WIDTH / 2, 0, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
	}
	
	@Override
	public void act(float delta){
		if(!pause){
			if(!reverse){
				if(leftBoundsReached(delta)){
					resetBoundsRight();
				}else{
					updateXBounds(-delta);
				}
			}else{
				if(rightBoundsReached(delta)){
					resetBoundsLeft();
				}else{
					updateXBounds(delta);
				}
			}
		}
	}

	@Override
	public UserData getUserData(){
		return userData;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
	}
	
	private boolean leftBoundsReached(float delta){
		return (textureRegionBounds2.x - (delta * speed)) <= 0;
	}
	
	private boolean rightBoundsReached(float delta){
		return (textureRegionBounds1.x - (delta * speed)) >= 0;
	}
	
	private void updateXBounds(float delta){
		textureRegionBounds1.x += delta * speed;
		textureRegionBounds2.x += delta * speed;
	}
	
	private void resetBoundsRight(){
		textureRegionBounds1 = textureRegionBounds2;
		textureRegionBounds2 = new Rectangle(Constants.VIEWPORT_WIDTH, 0, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
	}
	
	private void resetBoundsLeft(){
		textureRegionBounds2 = textureRegionBounds1;
		textureRegionBounds1 = new Rectangle(-Constants.VIEWPORT_WIDTH + 5, 0, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
	}
}
