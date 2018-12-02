package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {

	private FileSystem fileSystem;
	private Window window;
	private Demo2Player player1;
	private Room0 room0;
	private Room1 room1;

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
			Demo2Player player1 = new Demo2Player(getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(5, 5));
			
			getCurrentArea().setViewCandidate(player1);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaTime) {
		getCurrentArea().update(deltaTime);
		player1.draw(window);
		player1.update(deltaTime);
		if (player1.isPassingdoor()) {
			if (player1.getOwnerArea() == room0) {
				setCurrentArea(room1.getTitle(), false);
				player1.enterArea(room1, new DiscreteCoordinates(5, 2));
			} else if (player1.getOwnerArea() == room1) {
				setCurrentArea(room0.getTitle(), false);
				player1.enterArea(room0, new DiscreteCoordinates(5, 2));
			}
		}
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
