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
	private Player player1,player2;
	private PlayerGfx gfx1,gfx2;
//	private Player dude = Game.getGameObject().getPlayers().get(0); //TODO, fix for the correct player based on whos turn it is
//	private PlayerGfx duden = dude.getGfx();
	
	private TextButton backButton;
	
	public GameBoardView(MainActivity main, int index1, int index2){
		this.player1 = Game.getGameObject().getPlayers().get(index1);
		this.gfx1 = player1.getGfx();
		this.player2 = Game.getGameObject().getPlayers().get(index2);
		this.gfx2 = player2.getGfx();
		
		backButton = new TextButton(50, 50, "Back");
		this.main = main;
		
		gfx1.setPosition(50,100);
		gfx1.update(0);
		gfx2.setPosition(100,100);
		gfx2.update(0);
//		duden.setPosition(100, 100);
//		duden.update(0);
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.YELLOW);
		Constants.background.draw(canvas,0,0);
		backButton.draw(canvas);
		gfx1.draw(canvas);
		gfx2.draw(canvas);
//		duden.draw(canvas);
	}
	
	public boolean onTouchDown(MotionEvent event){
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		return true;
	}
}
