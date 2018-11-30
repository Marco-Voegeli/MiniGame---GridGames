package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {

	private FileSystem fileSystem;
	private Window window;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem) == true) {

//			Transform viewTransform = Transform.I.scaled(100).translated(new Vector(-5.5f, -5.5f));
//			window.setRelativeTransform(viewTransform);

			Room0 room0 = new Room0();
			Room1 room1 = new Room1();
			room0.begin(window, fileSystem);
			room1.begin(window, fileSystem);
			addArea(room0);
			addArea(room1);
			setCurrentArea(room0.getTitle(), false);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaTime) {
		getCurrentArea().update(deltaTime);
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 60;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo2";
	}

}
