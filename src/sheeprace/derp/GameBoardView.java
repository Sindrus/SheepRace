package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.State;

/**
 * This class is responsible for drawing the gameboard on the screen. It is
 * also responsible for updating the screen as the player moves around on the
 * board.
 * 
 */

public class GameBoardView extends State {
	private MainActivity main;
	
	public GameBoardView(MainActivity main){
		this.main = main;
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.YELLOW);
	}
}
