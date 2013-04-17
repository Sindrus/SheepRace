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
	private TextButton backButton;
	private Image background;
	
	public HighScoreView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");	
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLUE);
		backButton.draw(canvas);
	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		return true;
	}

}
