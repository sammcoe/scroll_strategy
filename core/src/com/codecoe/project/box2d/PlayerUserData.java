package com.codecoe.project.box2d;

import com.badlogic.gdx.math.Vector2;
import com.codecoe.project.enums.UserDataType;
import com.codecoe.project.utils.*;

public class PlayerUserData extends UserData{
	private Vector2 jumpingLinearImpulse;
	
	public PlayerUserData(){
		super();
		jumpingLinearImpulse = Constants.PLAYER_JUMP_IMPULSE;
		userDataType = UserDataType.PLAYER;
	}
	
	public Vector2 getJumpingLinearImpulse() {
		return jumpingLinearImpulse;
	}
	
	public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse){
		this.jumpingLinearImpulse = jumpingLinearImpulse;
	}
}
