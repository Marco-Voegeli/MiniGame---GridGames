package demo01;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import demo01.actor.MovingRock;

public class demo implements Game {
	private Actor A1;
	private float radius = 0.2f;
	private Window window;
	private FileSystem filesystem;
	private MovingRock MR;

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		this.filesystem = fileSystem;

		Transform viewTransform = Transform.I.scaled(1).translated(new Vector(-0.2f, 0.0f));
		window.setRelativeTransform(viewTransform);

		A1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));

		MR = new MovingRock(new Vector(0.05f, 0.05f), "I am a rock");
		return true;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo1";
	}

	@Override
	public void update(float deltaTime) {
		// Life can be given in this method
		A1.draw(window);
		MR.draw(window);
		MR.getText().draw(window);
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}

}
