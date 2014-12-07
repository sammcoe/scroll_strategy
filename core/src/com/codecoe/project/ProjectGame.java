package com.codecoe.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.codecoe.project.screens.GameScreen;
import com.codecoe.project.resources.*;

public class ProjectGame extends Game {
	
	@Override
	public void create () {
		setScreen(new GameScreen());
		
		//Sound Effects
		Resources.swordClash = Gdx.audio.newSound(Gdx.files.internal("Sword-Hit2.mp3"));
		Resources.themeMusic = Gdx.audio.newMusic(Gdx.files.internal("FromHere.mp3"));
		
		//Start playing background music
		Resources.themeMusic.setLooping(true);
		Resources.themeMusic.play();
		
	}

}
