package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {
//@Override
//public float getCameraScaleFactor() {

	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		Cell[][] tab = getCells();
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				Demo2CellType cellType = Demo2CellType.toType(getbehaviorMap().getRGB(getHeight() - 1 - j, i));
				tab[i][j] = new Demo2Cell(i, j, cellType);
			}
		}
		this.setCells(tab);
	}

	public class Demo2Cell extends Cell {
		Demo2CellType cellType;

		private Demo2Cell(int x, int y, Demo2CellType cellType) {
			super(x, y);
			this.cellType = cellType;
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
