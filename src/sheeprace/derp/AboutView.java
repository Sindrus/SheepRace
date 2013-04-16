package sheeprace.derp;

import android.R.color;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * The view about the game, containing information about how to play the game
 * and the game in general
 *
 */

public class AboutView extends State implements TouchListener{

	private TextButton back;
	private Font font;
	
	public AboutView(MainActivity main){
		back = new TextButton(50, 50, "Back");
		font = new Font(88, 88, 88, 50, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		
	}

	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		back.draw(canvas);
		canvas.drawText("Sheeprace er et morsomt spill/n og ingen har det \n og alle vil ha det", canvas.getWidth()/2, 100, font);
	}
	
}
