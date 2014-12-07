package com.codecoe.project.box2d;


import com.codecoe.project.enums.UserDataType;

public class BallistaeUserData extends UserData{
	
	public BallistaeUserData(float width, float height){
		super(width, height);
		userDataType = UserDataType.STRUCT;
	}
}