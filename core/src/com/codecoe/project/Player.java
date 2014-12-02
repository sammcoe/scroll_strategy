package com.codecoe.project;

import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.box2d.PlayerUserData;

public class Player extends GameActor {
	
	private boolean jumping;
	
	public Player(Body body){
		super(body);
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
	
	public void landed() {
		jumping = false;
	}
}
