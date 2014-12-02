package com.codecoe.project;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.codecoe.project.utils.Constants;
import com.codecoe.project.box2d.*;

//The purpose of this class is to setup the world physics
public class WorldUtils {
	
	public static World createWorld(){
		return new World(Constants.WORLD_GRAVITY, true);
	}
	
	public static Body createGround(World world){
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
		Body body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Constants.GROUND_WIDTH /2 , Constants.GROUND_HEIGHT /2);
		body.createFixture(shape, Constants.GROUND_DENSITY);
		body.setUserData(new GroundUserData());
		shape.dispose();
		return body;
	}
	
	public static Body createUnit(World world){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(new Vector2(Constants.UNIT_X, Constants.UNIT_Y));
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Constants.UNIT_WIDTH, Constants.UNIT_HEIGHT);
		Body body = world.createBody(bodyDef);
		body.setGravityScale(Constants.PLAYER_GRAVITY_SCALE);
		body.createFixture(shape, Constants.UNIT_DENSITY);
		body.resetMassData();
		body.setUserData(new PlayerUserData());
		shape.dispose();
		return body;
	}
}
