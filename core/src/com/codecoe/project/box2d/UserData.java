package com.codecoe.project.box2d;

import com.codecoe.project.enums.UserDataType;

public abstract class UserData {
	protected UserDataType userDataType;
	
	public UserData() {
		
	}
	
	public UserDataType getUserDataType(){
		return userDataType;
	}
}
