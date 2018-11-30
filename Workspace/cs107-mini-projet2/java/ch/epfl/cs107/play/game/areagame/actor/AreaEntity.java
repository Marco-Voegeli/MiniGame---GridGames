package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity {
	/// an AreaEntity knows its own Area
	private Area ownerArea;
	/// Orientation of the AreaEntity in the Area
	private Orientation orientation;
	/// Coordinate of the main Cell linked to the entity
	private DiscreteCoordinates currentMainCellCoordinates;
	// TODO implements me #PROJECT #TUTO

	/**
	 * Default AreaEntity constructor
	 * 
	 * @param area        (Area): Owner area. Not null
	 * @param orientation (Orientation): Initial orientation of the entity in the
	 *                    Area. Not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity in
	 *                    the Area. Not null
	 */
	public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {

		super(position.toVector());
		if(area == null || orientation == null || position == null) {
			throw new NullPointerException("One of the parameters is null");
		}
		else {
			this.orientation = orientation;
			this.ownerArea = area;
			this.currentMainCellCoordinates = position;
			
		}
		// TODO implements me #PROJECT #TUTO
	}

	/**
	 * Getter for the coordinates of the main cell occupied by the AreaEntity
	 * 
	 * @return (DiscreteCoordinates)
	 */
	protected DiscreteCoordinates getCurrentMainCellCoordinates() {

		return null;
	}

}
