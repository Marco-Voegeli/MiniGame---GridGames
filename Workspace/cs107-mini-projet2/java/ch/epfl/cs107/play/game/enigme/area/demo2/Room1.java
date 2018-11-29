package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Room1 extends Demo2Area {
	private static String level1 = "Level1";

	public Room1() {
		super(level1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setAreaBehavior(new Demo2Behavior(window, getTitle())); // setBehavior
		registerActor(new Background(this));

		return true;
	}

}
