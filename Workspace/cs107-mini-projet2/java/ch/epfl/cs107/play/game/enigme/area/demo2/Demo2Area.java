package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class Demo2Area extends Area {
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setBehavior(new Demo2Behavior(window, getTitle())); // setBehavior
		return registerActor(new Background(this));

	}
	@Override
	public void update(float deltaTime) {
	super.update(deltaTime);	
	}

	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 20;
	}


}
