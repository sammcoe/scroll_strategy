package com.codecoe.project.box2d;

import com.badlogic.gdx.math.Vector2;
import com.codecoe.project.enums.UserDataType;
import com.codecoe.project.utils.*;

public class ResourceUserData extends UserData{
	
	public ResourceUserData(float width, float height){
		super(width, height);
		userDataType = UserDataType.STRUCT;
	}
}