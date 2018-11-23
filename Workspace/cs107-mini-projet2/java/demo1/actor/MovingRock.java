package demo1.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;

public class MovingRock extends GraphicsEntity {
	protected final TextGraphics text;


	public MovingRock(Vector position, String text) {
		super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"), 0.1f, 0.1f, null, Vector.ZERO,1.0f,-Float.MAX_VALUE));
		this.text = new TextGraphics(text,0.04f,Color.BLUE);
		this.text.setParent(this);
		this.text.setAnchor(new Vector(-0.3f,0.1f));
	}
	public TextGraphics getText() {
		return text;
	}
public void up() {
		setCurrentPosition(getPosition().add(0f, 0.05f));
		
	}
public void down() {
	setCurrentPosition(getPosition().sub(0f, 0.05f));

	}
public void left() {
	setCurrentPosition(getPosition().add(0.05f, 0f));
	
}
public void right() {
	setCurrentPosition(getPosition().sub(0.05f, 0f));
	
}

	
@Override
public void update(float deltaTime) {
	
	setCurrentPosition(getPosition().sub(0.05f, 0.05f*deltaTime));
}

}
