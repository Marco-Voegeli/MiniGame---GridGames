package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
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


	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {

		return true;
	}

	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {

		return true;
	}

	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells[i].length; j++) {
				if (cells[i][j].getCurrentCells().equals(coordinates)) {
					cells[i][j].leave(entity);
				}
			}
		}
	}

	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells[i].length; j++) {
				if (cells[i][j].getCurrentCells().equals(coordinates)) {
					cells[i][j].enter(entity);
				}
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		if (this.cells.length == cells.length) {
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

	public abstract class Cell implements Interactable {
		List<DiscreteCoordinates> cell;
		DiscreteCoordinates cellCoordinates;
		Set<Interactable> cellSet = new HashSet<>();

		public Cell(int x, int y) {
			cellCoordinates = new DiscreteCoordinates(x, y);
			cellSet = new HashSet<>();
		}

		@Override
		public List<DiscreteCoordinates> getCurrentCells() {
			cell = new LinkedList<DiscreteCoordinates>();
			cell.add(cellCoordinates);
			return cell;
		}

		public final boolean takeCellSpace() {
//			if(cellCoordinates.filled()) {
//				return false;
//			}
			return true;
		} // if true not traversable

		public final boolean isViewInteractable() {
			return false;
		}

		public final boolean isCellInteractable() {
			return false;
		}

		private void enter(Interactable i) {
			cellSet.add(i);
		}

		private void leave(Interactable i) {
			cellSet.remove(i);
		}

		protected abstract boolean canEnter(Interactable entity);

		protected abstract boolean canLeave(Interactable entity);
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
