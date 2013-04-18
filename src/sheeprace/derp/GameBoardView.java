package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.gui.TextButton;

/**
 * This class is responsible for drawing the gameboard on the screen. It is
 * also responsible for updating the screen as the player moves around on the
 * board.
 * 
 */

public class GameBoardView extends State {
	private MainActivity main;
	private Player dude = Game.getGameObject().getPlayers().get(0);
	private PlayerGfx duden = dude.getGfx();
	
	private TextButton backButton;
	
	public GameBoardView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		this.main = main;
		duden.setPosition(100, 100);
		duden.update(0);
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.YELLOW);
		backButton.draw(canvas);
		duden.draw(canvas);
	}
	
	public boolean onTouchDown(MotionEvent event){
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		return true;
	}
}
