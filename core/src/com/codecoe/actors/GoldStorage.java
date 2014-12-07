package com.codecoe.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.GameActor;
import com.codecoe.project.box2d.ResourceUserData;
import com.codecoe.project.box2d.WallUserData;
import com.codecoe.project.utils.Constants;

public class GoldStorage extends GameActor {
	
	private TextureRegion resourceTexture;
	
	public GoldStorage(Body body){
		super(body);
		TextureAtlas textureAtlas = new TextureAtlas(Constants.PLAYER_ATLAS_PATH);
		resourceTexture = textureAtlas.findRegion(Constants.GOLD_STORE_REGION_NAME);
	}

	@Override
	public void act(float delta){
		super.act(delta);
		if(!pause){
			if(!reverse){
				updateX(-delta);
			}else{
				updateX(delta);
			}
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		batch.draw(resourceTexture, screenRectangle.x, screenRectangle.y, screenRectangle.width, screenRectangle.height);
		batch.draw(hitBar, screenRectangle.x - Constants.RESOURCE_WIDTH/6f, screenRectangle.y + Constants.RESOURCE_HEIGHT*.8f, Constants.BAR_WIDTH, Constants.BAR_HEIGHT);
		batch.draw(healthBar, screenRectangle.x - Constants.RESOURCE_WIDTH/6f, screenRectangle.y + Constants.RESOURCE_HEIGHT*.8f, Constants.BAR_WIDTH - (60 - health), Constants.BAR_HEIGHT);
	}
	
	@Override
	public ResourceUserData getUserData(){
		return (ResourceUserData) userData;
	}
	
	private void updateX(float delta){
		body.setTransform(body.getPosition().x += delta * speed, Constants.RESOURCE_Y, 0);
	}
}

