package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Demo2Player extends MovableAreaEntity  {

	private boolean passingdoor = false;

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {

		return null;
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub

	}
	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		this.update(24f);
	}

}
