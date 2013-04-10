package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.State;
import sheep.gui.TextButton;

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
