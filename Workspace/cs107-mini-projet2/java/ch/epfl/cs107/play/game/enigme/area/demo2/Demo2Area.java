package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;

public class Demo2Area extends Area {
	private String level;

	public Demo2Area(String s) {
		this.level = s;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 10;
	}


}
