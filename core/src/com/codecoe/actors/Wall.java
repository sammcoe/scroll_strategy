package com.codecoe.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.GameActor;
import com.codecoe.project.box2d.PlayerUserData;
import com.codecoe.project.box2d.WallUserData;
import com.codecoe.project.utils.Constants;

public class Wall extends GameActor {
	
	private TextureRegion wallTexture;
	
	public Wall(Body body){
		super(body);
		TextureAtlas textureAtlas = new TextureAtlas(Constants.PLAYER_ATLAS_PATH);
		wallTexture = textureAtlas.findRegion(Constants.WALL_REGION_NAME);
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
		batch.draw(wallTexture, screenRectangle.x, screenRectangle.y, screenRectangle.width, screenRectangle.height);
		batch.draw(hitBar, screenRectangle.x - Constants.WALL_WIDTH/2.7f, screenRectangle.y + Constants.WALL_HEIGHT*1.1f, Constants.BAR_WIDTH, Constants.BAR_HEIGHT);
		batch.draw(healthBar, screenRectangle.x - Constants.WALL_WIDTH/2.7f, screenRectangle.y + Constants.WALL_HEIGHT*1.1f, Constants.BAR_WIDTH - (60 - health), Constants.BAR_HEIGHT);
	}
	
	@Override
	public WallUserData getUserData(){
		return (WallUserData) userData;
	}
	
	private void updateX(float delta){
		body.setTransform(body.getPosition().x += delta * speed, Constants.WALL_Y, 0);
	}
}
