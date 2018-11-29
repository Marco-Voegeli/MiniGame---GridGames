package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {
	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	private final int width, height;
	/// We will convert the image into an array of cells
	private final Cell[][] cells;

	public AreaBehavior(Window window, String fileName) {

		this.behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		this.width = behaviorMap.getWidth();
		this.height = behaviorMap.getHeight();
		this.cells = new Cell[width][height];
	}

	public int getWidth() {
		return width;
	}
	public Cell[][] getCells(){
		return cells;
	}
	public void setCells(Cell[][] cells){
		if(this.cells.length == cells.length) {
			for (int i = 0; i < this.cells.length; i++) {
				for (int j = 0; j < this.cells[i].length; j++) {
					this.cells[i][j] = cells[i][j];
				}
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	public Image getbehaviorMap() {
		return behaviorMap;
	}

	public abstract class Cell {
		DiscreteCoordinates cellCoordinates;
		public Cell(int x, int y) {
			cellCoordinates = new DiscreteCoordinates (x,y);
		}
	}

	/**
	 * Default AreaBehavior Constructor
	 * 
	 * @param window   (Window): graphic context, not null
	 * @param fileName (String): name of the file containing the behavior image, not
	 *                 null
	 */

	// TODO implements me #PROJECT #TUTO

}
