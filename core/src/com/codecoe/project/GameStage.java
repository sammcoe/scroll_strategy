package com.codecoe.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.codecoe.project.WorldUtils;
import com.codecoe.project.utils.Constants;
import com.codecoe.project.utils.BodyUtils;

public class GameStage extends Stage implements ContactListener{
	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	//Physics bodies
	private World world;
	private Ground ground;
	private Player player;
	
	public GameStage(){
		setupWorld();
		setupCamera();
		Gdx.input.setInputProcessor(this);
		renderer = new Box2DDebugRenderer();
		setupCamera();
	}
	
	private void setupCamera(){
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth /2, camera.viewportHeight /2, 0f);
		camera.update();
	}
	
	private void setupWorld(){
		world = WorldUtils.createWorld();
		world.setContactListener(this);
		setupGround();
		setupPlayer();
	}
	
	private void setupGround(){
		ground = new Ground(WorldUtils.createGround(world));
		addActor(ground);
	}
	
	private void setupPlayer(){
		player = new Player(WorldUtils.createUnit(world));
		addActor(player);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		accumulator += delta;
		
		while(accumulator >= delta){
			world.step(TIME_STEP, 6, 2);
			accumulator -= TIME_STEP;
		}
	}
	
	@Override
	public void draw(){
		super.draw();
		renderer.render(world, camera.combined);
	}
	
	@Override
	public boolean keyDown(int keycode){
		if(keycode == Keys.SPACE){
			player.jump();
		}
		
		return false;
	}
	
	@Override
	public void beginContact(Contact contact){
		Body a = contact.getFixtureA().getBody();
		Body b = contact.getFixtureB().getBody();
		
		if((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsGround(b)) 
				|| (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsPlayer(b))){
			player.landed();
		}
	}
	
	@Override
	public void endContact(Contact contact){
		
	}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold){
		
	}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse){
		
	}
	
}

