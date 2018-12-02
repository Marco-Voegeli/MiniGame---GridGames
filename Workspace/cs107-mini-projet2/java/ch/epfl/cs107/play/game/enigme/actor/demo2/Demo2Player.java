package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo2Player extends MovableAreaEntity {

	private boolean passingdoor = false;
	private Window window;
	private Sprite demoSprite;
	private AreaBehavior actorAreaBehavior;
	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		demoSprite = new Sprite("ghost.1", 1, 1.f, this);
		this.setOrientation(Orientation.DOWN);
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
		demoSprite.draw(canvas);
	}

	public boolean cellType(Demo2CellType type, Demo2Cell cell) {
		return (cell.getDemo2CellType() == type);
	}

	@Override
	protected boolean move(int framesForMove) {
		// TODO Auto-generated method stub;
		actorAreaBehavior = getOwnerArea().getAreaBehavior();
		List<DiscreteCoordinates> DClist = getOwnerArea().getEnteringCells((Actor)this);
		for(DiscreteCoordinates a : DClist) {
			if(cellType(Demo2CellType.DOOR,(Demo2Cell)actorAreaBehavior.getCell(a))){
					passingdoor = true;
					return passingdoor;
			}
		}
		return passingdoor;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		Keyboard keyboard = window.getKeyboard();
		Button[] Arrows = { keyboard.get(Keyboard.UP), keyboard.get(Keyboard.DOWN), keyboard.get(Keyboard.LEFT),
				keyboard.get(Keyboard.RIGHT) };

		if (Arrows[0].isDown()) {
			up();

		} else if (Arrows[1].isDown()) {
			down();
		} else if (Arrows[2].isDown()) {
			left();
		} else if (Arrows[3].isDown()) {
			right();
		}

	}

	public void up() {
		if (this.getOrientation() == Orientation.UP) {
			this.move(ANIMATION_DURATION);
		} else {
			this.setOrientation(Orientation.UP);
		}
	}

	public void down() {
		if (this.getOrientation() == Orientation.DOWN) {
			this.move(ANIMATION_DURATION);
		} else {
			this.setOrientation(Orientation.DOWN);
		}
	}

	public void left() {
		if (this.getOrientation() == Orientation.LEFT) {
			this.move(ANIMATION_DURATION);
		} else {
			this.setOrientation(Orientation.LEFT);
		}
	}

	public void right() {
		if (this.getOrientation() == Orientation.RIGHT) {
			this.move(ANIMATION_DURATION);
		} else {
			this.setOrientation(Orientation.RIGHT);
		}
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		this.update(24f);
		this.resetMotion();
	}

}
