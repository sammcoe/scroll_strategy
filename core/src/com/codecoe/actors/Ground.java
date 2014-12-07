package com.codecoe.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.codecoe.project.GameActor;
import com.codecoe.project.box2d.GroundUserData;

public class Ground extends GameActor {
	public Ground(Body body){
		super(body);
	}
	
	@Override
	public GroundUserData getUserData() {
		return (GroundUserData) userData;
	}
}
