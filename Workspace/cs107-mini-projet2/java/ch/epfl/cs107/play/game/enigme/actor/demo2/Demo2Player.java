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
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo2Player extends MovableAreaEntity {

	private boolean passingdoor = false;
	private Sprite demoSprite;
	private AreaBehavior actorAreaBehavior;
	private FileSystem fileSystem;
	private Canvas canvas;
	private int direction = 0;
	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.setOrientation(Orientation.DOWN);
		this.demoSprite = new Sprite("ghost.1", 1, 1.f, this);
		// System.out.print(getPosition());

	}

	public boolean isPassingdoor() {
		return passingdoor;
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
		this.demoSprite.draw(canvas);
		this.canvas = canvas;
	}

	public boolean cellType(Demo2CellType type, Demo2Cell cell) {
		return (cell.getDemo2CellType() == type);
	}

	@Override
	protected boolean move(int framesForMove) { // redefine
		// TODO Auto-generated method stub;
		actorAreaBehavior = getOwnerArea().getAreaBehavior();
		if (getOwnerArea().getEnteringCells((Actor) this) == null) {
			switch (direction) {
			case 1:
				setCurrentPosition(new Vector(getPosition().x, getPosition().y + framesForMove)); // UP
				// currentPosition = getCurrentMainCellCoordinates();
				direction = 0;
			case 2:
				setCurrentPosition(new Vector(getPosition().x, getPosition().y - framesForMove)); // DOWN
				direction = 0;

			case 3:
				setCurrentPosition(new Vector(getPosition().x - framesForMove, getPosition().y)); // LEFT
				direction = 0;
			case 4:
				setCurrentPosition(new Vector(getPosition().x + framesForMove, getPosition().y));// RIGHT
				direction = 0;

			}
			return passingdoor;

		} else {

			List<DiscreteCoordinates> DClist = getOwnerArea().getEnteringCells((Actor) this);
			for (DiscreteCoordinates a : DClist) {
				if (cellType(Demo2CellType.DOOR, (Demo2Cell) actorAreaBehavior.getCell(a))) {
					passingdoor = true;
					return passingdoor;
				}
			}
		}
		return passingdoor;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		Keyboard keyboard = ((Window) canvas).getKeyboard();
		Button[] Arrows = { keyboard.get(Keyboard.UP), keyboard.get(Keyboard.DOWN), keyboard.get(Keyboard.LEFT),
				keyboard.get(Keyboard.RIGHT) };
		if (Arrows[0].isDown()) {
			System.out.println("Up");
			System.out.println("COORD BEFORE" + getPosition());
			direction = 1;
			up();
			System.out.println("COORD After" + getPosition());

		} else if (Arrows[1].isDown()) {
			direction = 2;
			down();
		} else if (Arrows[2].isDown()) {
			direction = 3;
			left();
		} else if (Arrows[3].isDown()) {
			direction = 4;
			right();

		}

	}

	public void up() {
		if (this.getOrientation() == Orientation.UP) {
			this.move(ANIMATION_DURATION);
			direction = 1;

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
