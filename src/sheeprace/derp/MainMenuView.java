package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.gui.TextButton;
import sheep.input.TouchListener;


/**
 * This is the main menu view. This view must hold the buttons to navigate to
 * the aboutview, initgameview and highscoreview (if it is implemented)
 *
 */

public class MainMenuView extends State implements TouchListener{
	TextButton start, about, highScore, questionView;
	private MainActivity main;
	
	
	public MainMenuView(MainActivity main){
		start = new TextButton(50, 50, "Start Game");
		about = new TextButton(50, 100, "About");
		highScore = new TextButton(50, 150, "High Score");
		questionView = new TextButton(50, 250, "Questions");
		this.main = main;
	}

	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		start.draw(canvas);
		about.draw(canvas);
	}
	
	public boolean onTouchDown(MotionEvent evt){
		if(start.onTouchDown(evt)){
			getGame().pushState(new InitGameView(main));
		}
		else if(about.onTouchDown(evt)){
			getGame().pushState(new AboutView(main));
		}
		return true;
	}
}
