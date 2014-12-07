package com.codecoe.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.GameActor;
import com.codecoe.project.box2d.BallistaeUserData;
import com.codecoe.project.utils.Constants;

public class Ballistae extends GameActor {
	
	private TextureRegion ballistaeTexture;
	
	public Ballistae(Body body){
		super(body);
		TextureAtlas textureAtlas = new TextureAtlas(Constants.PLAYER_ATLAS_PATH);
		ballistaeTexture = textureAtlas.findRegion(Constants.AIR_DEF_REGION_NAME);
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
		batch.draw(ballistaeTexture, screenRectangle.x, screenRectangle.y, screenRectangle.width, screenRectangle.height);
		batch.draw(hitBar, screenRectangle.x - Constants.BALLISTAE_WIDTH/3.5f, screenRectangle.y + Constants.BALLISTAE_HEIGHT*1.1f, Constants.BAR_WIDTH, Constants.BAR_HEIGHT);
		batch.draw(healthBar, screenRectangle.x - Constants.BALLISTAE_WIDTH/3.5f, screenRectangle.y + Constants.BALLISTAE_HEIGHT*1.1f, Constants.BAR_WIDTH - (60 - health), Constants.BAR_HEIGHT);
	}
	
	@Override
	public BallistaeUserData getUserData(){
		return (BallistaeUserData) userData;
	}
	
	private void updateX(float delta){
		body.setTransform(body.getPosition().x += delta * speed, Constants.BALLISTAE_Y, 0);
	}
}
