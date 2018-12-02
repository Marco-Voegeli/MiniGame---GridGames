package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

	private int framesForMove;
	/// Indicate if the actor is currently moving
	private boolean isMoving;
	/// Indicate how many frames the current move is supposed
	// to take
	private int framesForCurrentMove;
	/// The target cell (i.e. where the mainCell will be after
	// the motion)
	private DiscreteCoordinates targetMainCellCoordinates;
	// TODO implements me #PROJECT #TUTO

	/**
	 * Default MovableAreaEntity constructor
	 * 
	 * @param area        (Area): Owner area. Not null
	 * @param position    (Coordinate): Initial position of the entity. Not null
	 * @param orientation (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.resetMotion();

	}

	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion() {
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
		// TODO implements me #PROJECT #TUTO
	}

	/**
	 * 
	 * @param frameForMove (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */
	protected boolean move(int framesForMove) {
		this.framesForMove = framesForMove;
		if (!isMoving || super.getCurrentMainCellCoordinates() == targetMainCellCoordinates) {
			if (!getOwnerArea().agreeToAdd(this)) {

				return false;
			} else { // Veto Allows

				if (framesForMove < 1) {
					framesForMove = 1;
				}
				framesForCurrentMove = framesForMove;
				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
				isMoving = true;
				return true;
			}

		}
		return false;
	}

	/// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime) {
		if (isMoving && getCurrentCells() != getEnteringCells()) {
			Vector distance = getOrientation().toVector();
			distance = distance.mul(1.0f / framesForCurrentMove);
			setCurrentPosition(getPosition().add(distance));
		} else{
			resetMotion();
		}
	}

	/// Implements Positionable

	@Override
	public Vector getVelocity() {
		// TODO implements me #PROJECT #TUTO
		// the velocity must be computed as the orientation vector
		// (getOrientation().toVector() mutiplied by
		// framesForCurrentMove
		return null;
	}

	private List<DiscreteCoordinates> getLeavingCells() {
		return getCurrentCells();
	}

	private List<DiscreteCoordinates> getEnteringCells() { // Potential improvements
		List<DiscreteCoordinates> enteringCells = new LinkedList<>();
		for (DiscreteCoordinates a : getCurrentCells()) {
			a.jump(getOrientation().toVector());
			enteringCells.add(a);
		}
		return enteringCells;

	}
}
