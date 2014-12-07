package com.codecoe.project.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.box2d.UserData;
import com.codecoe.project.enums.UserDataType;

public class BodyUtils {
	public static boolean bodyIsPlayer(Body body){
		UserData userData = (UserData) body.getUserData();
		
		return userData != null && userData.getUserDataType() == UserDataType.PLAYER;
	}
	
	public static boolean bodyIsGround(Body body){
		UserData userData = (UserData) body.getUserData();
		
		return userData != null && userData.getUserDataType() == UserDataType.GROUND;
	}
	
	public static boolean bodyIsStructure(Body body){
		UserData userData = (UserData) body.getUserData();
		
		return userData != null && userData.getUserDataType() == UserDataType.STRUCT;
	}
	
}
