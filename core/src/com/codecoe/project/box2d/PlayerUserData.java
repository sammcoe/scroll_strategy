package com.codecoe.project.box2d;

import com.badlogic.gdx.math.Vector2;
import com.codecoe.project.enums.UserDataType;
import com.codecoe.project.utils.*;

public class PlayerUserData extends UserData{
	private final Vector2 runningPosition = new Vector2(Constants.UNIT_X, Constants.UNIT_Y);
	private Vector2 jumpingLinearImpulse;
	
	public PlayerUserData(float width, float height){
		super(width, height);
		jumpingLinearImpulse = Constants.PLAYER_JUMP_IMPULSE;
		userDataType = UserDataType.PLAYER;
	}
	
	public Vector2 getJumpingLinearImpulse() {
		return jumpingLinearImpulse;
	}
	
	public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse){
		this.jumpingLinearImpulse = jumpingLinearImpulse;
	}
	
	public Vector2 getRunningPosition(){
		return runningPosition;
	}
}
