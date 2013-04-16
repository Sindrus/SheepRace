package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.gui.TextButton;

/**
 * Keep a list of the high score of previous games.
 * Not critical for the game to work, should only be implemented if there is 
 * enough time before the deadline.
 *
 */

public class HighScoreView extends State {
	
	public HighScoreView(MainActivity main){
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLUE);
	}
	

}
