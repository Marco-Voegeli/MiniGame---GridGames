package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo2Player extends MovableAreaEntity {

	private boolean passingdoor = false;
	private Window window;
	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		new Sprite("ghost.1", 1, 1.f, this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {

		return Collections.singletonList(getCurrentMainCellCoordinates());
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

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		Keyboard keyboard = window.getKeyboard();
		if (keyboard.get(Keyboard.LEFT).isDown()) {
			this.move(ANIMATION_DURATION);
		} else {
			this.setOrientation(this.getOrientation().hisLeft());
		}
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		this.update(24f);
		this.resetMotion();
	}

}
