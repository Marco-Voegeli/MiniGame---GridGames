package demo01;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class demo implements Game{	

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		;
		
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}	
	
	public static void main(String[] args) {
		demo demo01 = new demo();
	}
	
}
