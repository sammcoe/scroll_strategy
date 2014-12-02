package com.codecoe.project;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Unit {
	private String direction = "right"; 			//Direction unit is facing
	private Texture sprite;			   			//Unit sprite
	
	//Default constructor
	public Unit(){
		
	}
	//Specialized constructor
	public Unit(Texture img){
		this.sprite = img;
	}
	//Returns the direction the unit is facing
	public String getDir(){
		return direction;
	}
	//Sets the direction the unit is facing
	public void setDir(String dir){
		this.direction = dir;
	}
	//Sets the unit sprite
	public Texture getSprite(){
		return sprite;
	}
	//Returns the unit sprite
	public void setSprite(Texture img){
		this.sprite = img;
	}
}
