package demo01.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;

public class MovingRock extends GraphicsEntity {
	private final TextGraphics text;
	private final static float dimx = 0.1f;
	private final static float dimy = 0.1f;

	public float getDimx() {
		return dimx;
	}

	public float getDimy() {
		return dimy;
	}

	public MovingRock(Vector position, String text) {
		super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"), dimx, dimy, null, Vector.ZERO, 1.0f,
				-Float.MAX_VALUE));

		this.text = new TextGraphics(text, 0.04f, Color.BLUE);
		this.text.setParent(this);
		this.text.setAnchor(new Vector(-0.3f, 0.1f));
	}

	public TextGraphics getText() {
		return text;
	}

	public void up() {
		setCurrentPosition(getPosition().add(0f, 0.005f));
	}

	public void down() {
		setCurrentPosition(getPosition().sub(0f, 0.005f));
	}

	public void left() {
		setCurrentPosition(getPosition().sub(0.005f, 0f));
	}

	public void right() {
		setCurrentPosition(getPosition().add(0.005f, 0f));
	}
	
}
