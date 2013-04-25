package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.input.TouchListener;


/**
 * This is the main menu view. This view must hold the buttons to navigate to
 * the AboutView, InitGameView and HighScoreView
 *
 */

public class MainMenuView extends State implements TouchListener{
	private TextButton start, about, highScore;
	private MainActivity main;
	private Font font;
	
	public MainMenuView(MainActivity main){
		start = new TextButton(Constants.WINDOW_WIDTH/3, 150, "Start Game",Constants.p);
		about = new TextButton(Constants.WINDOW_WIDTH/3, 200, "About",Constants.p);
		highScore = new TextButton(Constants.WINDOW_WIDTH/3, 250, "Highscore",Constants.p);
//		questionView = new TextButton(Constants.WINDOW_WIDTH/3, 300, "Questions");
		font = new Font(18, 62, 110, 50, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		this.main = main;
	}

	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		start.draw(canvas);
		about.draw(canvas);
		highScore.draw(canvas);
		Constants.frontSheep.draw(canvas, Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/5);
		canvas.drawText("SheepRace", canvas.getWidth()/2, 100, font);
	}
	
	public boolean onTouchDown(MotionEvent evt){
		if(start.onTouchDown(evt)){
			getGame().pushState(new InitGameView(main));
		}
		else if(about.onTouchDown(evt)){
			getGame().pushState(new AboutView(main));
		}
		else if(highScore.onTouchDown(evt)){
			getGame().pushState(new HighScoreView(main, false));
		}
		return true;
	}
}
