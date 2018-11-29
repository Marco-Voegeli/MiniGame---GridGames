package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {
//@Override
//public float getCameraScaleFactor() {

	public Demo2Behavior(int i, int y, Window window, String fileName) {
		super(window, fileName);
		//TODO
	}
	
	public class Demo2Cell extends Cell {
		Demo2CellType type;

		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x, y);
			this.type = type;

		}

	}

	public enum Demo2CellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
		final int type;

		Demo2CellType(int type) {
			this.type = type;
		}

		static Demo2CellType toType(int type) {
			for (Demo2CellType a : Demo2CellType.values()) {
				if (a.type == type) {
					return a;
				}
			}
			return Demo2CellType.NULL;
		}

	}

}
