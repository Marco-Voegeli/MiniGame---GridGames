package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors
 */
public abstract class Area implements Playable {
	private Window window;
	private FileSystem fileSystem;
	private List<Actor> actors;
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;
	// Camera Parameter
	// actor on which the view is centered
	private Actor viewCandidate;
	// effective center of the view
	private Vector viewCenter;
	private AreaBehavior areaBehavior;

	protected final void setBehavior(AreaBehavior ab) {
		this.areaBehavior = ab;

	}

	/**
	 * @return (float) : camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	{

	}

	public boolean vetoFromGrid() { // Checks if the grid is not full or not available for that type
		// TODO
		return false;
	}

	public boolean agreeToAdd(Actor a) {
		// TODO
		return false;
	}

	/**
	 * Add an actor to the actors list
	 * 
	 * @param a      (Actor): the actor to add, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {
		boolean errorOccured = !agreeToAdd(a) || vetoFromGrid();

		if (errorOccured /* error */ && !forced /* not forced */) { // If there was a problem with adding
			System.out.println("Actor " + a + " cannot be" + "completely added, so remove it from where it");
			removeActor(a, true);
		} else if (!errorOccured || forced) { // add no matter what
			actors.add(a);
		}

	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a      (Actor): the actor to remove, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced) {
		// TODO implements me #PROJECT #TUTO
		if (forced) {
			actors.remove(a);
		}

	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a) {
		registeredActors.add(a);
		return false;
	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a) {
		unregisteredActors.add(a);
		return false;
	}

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth() {
		return areaBehavior.getWidth();
	}

	/**
	 *
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight() {
		// TODO implements me #PROJECT #TUTO
		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard() {
		// TODO implements me #PROJECT #TUTO
		return null;
	}

	/// Area implements Playable

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {

		this.window = window;
		this.fileSystem = fileSystem;

		actors = new LinkedList<>();
		viewCenter = Vector.ZERO;
		setViewCandidate(null);
		return true;
	}

	/**
	 * Resume method: Can be overridden and is supposed to take the game back from
	 * the paused state
	 * 
	 * @param window     (Window): display context, not null
	 * @param fileSystem (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem) {
		return true;
	}

	@Override
	public void update(float deltaTime) {
		purgeRegistration();
		updateCamera();
		for (Actor a : actors) {
			a.update(deltaTime);
			a.draw(window);
		}
		// maybe under purgeRegistration()
		// TODO implements me #PROJECT #TUTO
	}

	public void purgeRegistration() {
		for (Actor a : unregisteredActors) {
			actors.remove(a);
		}
		actors.addAll(registeredActors);
		registeredActors.clear();
		unregisteredActors.clear();
	}

	private void updateCamera() {
		if (getViewCandidate() != null) {
			viewCenter = getViewCandidate().getPosition();
		}

		Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
		window.setRelativeTransform(viewTransform);
	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public final void suspend() {
		purgeRegistration();
// Do nothing by default
	}

	@Override
	public void end() {

		// TODO save the AreaState somewhere
	}

	public Actor getViewCandidate() {
		return viewCandidate;
	}

	public void setViewCandidate(Actor viewCandidate) {
		this.viewCandidate = viewCandidate;
	}

	public boolean addressed() {
		// TODO
		return false;
	}

}
