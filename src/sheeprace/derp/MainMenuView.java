package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;


/**
 * This is the main menu view. This view must hold the buttons to navigate to
 * the aboutview, initgameview and highscoreview (if it is implemented)
 *
 */

public class MainMenuView extends State implements TouchListener{
	TextButton start, about, highScore, questionView;
	Image sheep;
	private MainActivity main;
	Font font;
	
	public MainMenuView(MainActivity main){
		start = new TextButton(Constants.WINDOW_WIDTH/2, 100, "Start Game");
		about = new TextButton(Constants.WINDOW_WIDTH/2, 150, "About");
		highScore = new TextButton(Constants.WINDOW_WIDTH/2, 200, "High Score");
		questionView = new TextButton(Constants.WINDOW_WIDTH/2, 250, "Questions");
		sheep = new Image(R.drawable.frontsheep);
		font = new Font(88, 88, 88, 16, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		this.main = main;
	}

	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		start.draw(canvas);
		about.draw(canvas);
		highScore.draw(canvas);
		questionView.draw(canvas);
		sheep.draw(canvas, Constants.WINDOW_WIDTH/2, 50);
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
			getGame().pushState(new HighScoreView(main));
		}
		else if(questionView.onTouchDown(evt)){
			getGame().pushState(new QuestionView(main));
		}
		return true;
	}
}
