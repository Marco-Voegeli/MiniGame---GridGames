package demo01;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import demo01.actor.MovingRock;

public class demo implements Game {
	private Actor A1;
	private float radius = 0.1f;
	private Window window;
	private FileSystem fileSystem;
	private MovingRock MR;
	private float thickness = 0.005f;

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		this.fileSystem = fileSystem;

//		Transform viewTransform = Transform.I.scaled(1).translated(new Vector(0.0f, 0.0f));
//		window.setRelativeTransform(viewTransform);

		A1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, thickness));
		MR = new MovingRock(new Vector(0.2f, 0.3f), "I am a rock");

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
		Keyboard keyboard = window.getKeyboard();
		Button[] Arrows = { keyboard.get(Keyboard.UP), keyboard.get(Keyboard.DOWN), keyboard.get(Keyboard.LEFT),
				keyboard.get(Keyboard.RIGHT) };
		TextGraphics crash = new TextGraphics("BOUM!!!!", 0.08f, Color.RED);

		A1.draw(window);
		MR.draw(window);

		MR.getText().draw(window);

		if (Arrows[0].isDown()) {
			MR.up();

		} else if (Arrows[1].isDown()) {
			MR.down();
		} else if (Arrows[2].isDown()) {
			MR.left();
		} else if (Arrows[3].isDown()) {
			MR.right();
		}
		if (crash(MR)) {
			crash.draw(window);
		}
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}

	public boolean crash(MovingRock MR) {

		float distAvg = (float) Math
				.sqrt((MR.getPosition().x + MR.getDimx() / 2) * (MR.getPosition().x + MR.getDimx() / 2)
						+ (MR.getPosition().y + MR.getDimy() / 2) * (MR.getPosition().y + MR.getDimy() / 2));
		if (distAvg < radius + MR.getDimx() / 2 + thickness) {

			return true;
		}

		return false;

	}
}
