package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.State;
import sheep.gui.TextButton;

/**
 * This is the main menu view. This view must hold the buttons to navigate to
 * the aboutview, initgameview and highscoreview (if it is implemented)
 *
 */

public class MainMenuView extends State{
	TextButton start;
	
	public MainMenuView(MainActivity main){
		start = new TextButton(50, 50, "Herro");
	}

	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		start.draw(canvas);
	}
	
}
