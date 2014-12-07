package com.codecoe.project;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.codecoe.actors.Background;
import com.codecoe.actors.Ballistae;
import com.codecoe.actors.Ground;
import com.codecoe.actors.Player;
import com.codecoe.actors.Wall;
import com.codecoe.project.utils.Constants;
import com.codecoe.project.utils.BodyUtils;
import com.codecoe.project.utils.WorldUtils;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.codecoe.project.utils.Constants;
import com.codecoe.project.resources.*;
import com.codecoe.actors.GoldStorage;

public class GameStage extends Stage implements ContactListener{
	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;
	private boolean stop = false;
	private boolean contacted = false;
	private Body attacked;
	private OrthographicCamera camera;
	private int lastKey;
	//Physics bodies
	private World world;
	private Ground ground;
	private Player player;
	private Background bg;
	List<Wall> walls = new ArrayList<Wall>();
	List<Ballistae> ballistaes = new ArrayList<Ballistae>();
	List<GoldStorage> resources = new ArrayList<GoldStorage>();
	
	public GameStage(){
		super(new ScalingViewport(Scaling.stretch, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT)));
		setupWorld();
		setupCamera();
		Gdx.input.setInputProcessor(this);
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
		setUpBackground();
		setupGround();
		setupPlayer();
	}
	
	private void setUpBackground(){
		bg = new Background();
		addActor(bg);
	}
	
	private void setupGround(){
		ground = new Ground(WorldUtils.createGround(world));
		addActor(ground);
	}
	
	private void setupPlayer(){
		player = new Player(WorldUtils.createUnit(world));
		addActor(player);
	}
	
	private void setupWall(int x){
		walls.add(new Wall(WorldUtils.createWall(world, x)));
		addActor(walls.get(walls.size() - 1));
	}
	
	private void setupBallistae(int x){
		ballistaes.add(new Ballistae(WorldUtils.createBallistae(world, x)));
		addActor(ballistaes.get(ballistaes.size() - 1));
	}
	
	private void setupGoldStorage(int x){
		resources.add(new GoldStorage(WorldUtils.createResource(world, x)));
		addActor(resources.get(resources.size() - 1));
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
		control();
		super.draw();
	}
	
	private void control(){
		if(stop)
			pause();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			forward();
			if(!stop)
				unpause();
			else if(lastKey == Keys.LEFT){
				unpause();
				stop = false;
				player.lilJump();
			}
			lastKey = Keys.RIGHT;
			player.runRight();
		}else if(Gdx.input.isKeyPressed(Keys.LEFT)){
			reverse();
			if(!stop)
				unpause();
			else if(lastKey == Keys.RIGHT){
				unpause();
				stop = false;
				player.lilJump();
			}
			lastKey = Keys.LEFT;
			player.runLeft();
		}else{
			pause();
			player.stop();
		}		
	}
	
	@Override
	public boolean keyDown(int keycode){
		if(keycode == Keys.SPACE){
			player.jump();
		}else if(keycode == Keys.SHIFT_LEFT){
			player.swing();
			if(contacted){
				Resources.swordClash.play();
				for(int i = 0; i < this.getActors().size ; i++){
					GameActor temp = (GameActor) this.getActors().get(i);
					if(attacked.equals(temp.body)){
						temp.setHealth(temp.getHealth() - Constants.PLAYER_HIT);
						if(temp.getHealth() == 0){
							temp.remove();
							contacted = false;
							stop = false;
							player.lilJump();
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button){
		if(button == Buttons.LEFT){
			setupWall(screenX);
		}else if(button == Buttons.RIGHT){
			setupBallistae(screenX);
		}else if(button == Buttons.MIDDLE){
			setupGoldStorage(screenX);
		}
		return false;
	}
	
	@Override
	public void beginContact(Contact contact){
		Body a = contact.getFixtureA().getBody();
		Body b = contact.getFixtureB().getBody();
		if((BodyUtils.bodyIsStructure(a) && BodyUtils.bodyIsPlayer(b)) 
				|| (BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsStructure(b))){
			if(BodyUtils.bodyIsStructure(a)){
				attacked = a;
			}else if(BodyUtils.bodyIsStructure(b)){
				attacked = b;
			}
			pause();
			stop = true; 
			contacted = true;
		}
		
		if((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsGround(b)) 
				|| (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsPlayer(b))){
			player.landed();
		}
	}
	
	@Override
	public void endContact(Contact contact){
		Body a = contact.getFixtureA().getBody();
		Body b = contact.getFixtureB().getBody();
		if((BodyUtils.bodyIsStructure(a) && BodyUtils.bodyIsPlayer(b)) 
				|| (BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsStructure(b))){
			contacted = false;
		}
	}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold){
		
	}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse){
		
		
	}
	
	private void unpause(){
		for(int i = 0; i < this.getActors().size ; i++){
			GameActor temp = (GameActor) this.getActors().get(i);
			temp.unpause();
		}
	}
	
	private void pause(){
		for(int i = 0; i < this.getActors().size ; i++){
			GameActor temp = (GameActor) this.getActors().get(i);
			temp.pause();
		}
	}
	
	private void forward(){
		for(int i = 0; i < this.getActors().size ; i++){
			GameActor temp = (GameActor) this.getActors().get(i);
			temp.forward();
		}
	}
	
	private void reverse(){
		for(int i = 0; i < this.getActors().size ; i++){
			GameActor temp = (GameActor) this.getActors().get(i);
			temp.reverse();
		}
	}
	
}

