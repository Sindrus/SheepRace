package sheeprace.derp;

import android.R.color;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.widget.TextView;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.Widget;
import sheep.gui.WidgetAction;
import sheep.input.TouchListener;

/**
 * The view about the game, containing information about how to play the game
 * and the game in general
 *
 */


public class AboutView extends State implements TouchListener{


	private TextButton backButton;
	private Font font;
	
	public AboutView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		font = new Font(88, 88, 88, 16, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
	}

	public void draw(Canvas canvas){
		
		canvas.drawColor(Color.BLACK);
		backButton.draw(canvas);
		canvas.drawText("SheepRace", canvas.getWidth()/2, 100, font);
		canvas.drawText("The player that gets from start to finish the fastest win.", canvas.getWidth()/2, 120, font);
		canvas.drawText("You start of with a full powerbar and max speed, ", canvas.getWidth()/2, 140, font);
		canvas.drawText("as you run you lose powerpoints and also speed.", canvas.getWidth()/2, 160, font);
		canvas.drawText("To fill your powerbar up you need to tap ", canvas.getWidth()/2, 180, font);
		canvas.drawText("the screen to jump and hit a questionbox. ", canvas.getWidth()/2, 200, font);
		canvas.drawText("If you answer correctly you get powerpoints ", canvas.getWidth()/2, 220, font);
		canvas.drawText("and your character runs faster.", canvas.getWidth()/2, 240, font);
		canvas.drawText("If you answer wrong you lose powerpoints", canvas.getWidth()/2, 260, font);
		canvas.drawText("and your character runs slower. ", canvas.getWidth()/2, 280, font);
		canvas.drawText("If your powerbar goes empty before you are at the ", canvas.getWidth()/2, 300, font);
		canvas.drawText("finish line you get a \"Game Over\". ", canvas.getWidth()/2, 320, font);
		canvas.drawText("When one player has reached the finish or a", canvas.getWidth()/2, 340, font);
		canvas.drawText("\"Game Over\", it is the next players turn.  ", canvas.getWidth()/2, 360, font);

	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		return true;
	}
	
	
}
