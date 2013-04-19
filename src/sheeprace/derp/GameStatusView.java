package sheeprace.derp;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;

/**
 * This class must show the status of each player between each game. 
 * The status includes the score of each player and how many correct questions
 * the player has answered correct and wrong.
 * Also list the player that is in the lead.
 *
 */

public class GameStatusView extends State{
	
	private MainActivity main;
	private Player player,player1,player2;
	private TextButton backButton;
	
	private Font font, headLine; 
	
	private boolean finalScreen;
	
	public GameStatusView(MainActivity main, Player player){
		this.main = main;
		this.player = player; //Don't know if this is the right way to do it just now
		
		font = new Font(88, 88, 88, 20, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		headLine = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		headLine.setTextAlign(Align.CENTER);
		
		backButton = new TextButton(50, 50, "Back");
		
		this.finalScreen= false; 
	}
	
	public GameStatusView(MainActivity main, Player player1, Player player2){
		this.main = main;
		this.player1 = player1;
		this.player2 = player2;
		
		font = new Font(88, 88, 88, 20, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		headLine = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		headLine.setTextAlign(Align.CENTER);
		
		backButton = new TextButton(50, 50, "Back");
		
		this.finalScreen= true;
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.CYAN);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		Constants.frontSheep.draw(canvas,0,100);
		
		if(!finalScreen){
			canvas.drawText("Your score", canvas.getWidth()/2, 100, headLine);
			canvas.drawText(player.getName() + "  " +player.getScore(), canvas.getWidth()/2, 150, font);
			backButton.draw(canvas); // TODO for now, change to "continue" later on
		}
		if(finalScreen){
			canvas.drawText("Your scores", canvas.getWidth()/2, 100, headLine);
			canvas.drawText(player1.getName() + "  " +player1.getScore(), canvas.getWidth()/2, 150, font);
			canvas.drawText(player2.getName() + "  " +player2.getScore(), canvas.getWidth()/2, 200, font);
			if(player1.getScore()>player2.getScore())
				canvas.drawText(player1.getName() + " wins!",canvas.getWidth()/2,300,headLine);
			else if(player1.getScore()<player2.getScore())
				canvas.drawText(player2.getName() + " wins!",canvas.getWidth()/2,300,headLine);
			else
				canvas.drawText("Draw! Looks like you need to play again!",canvas.getWidth()/2,300,headLine);
			backButton.draw(canvas);
		}
	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
//		else if(startGame.onTouchDown(event)){
//
//			getGame().pushState(new GameBoardView(main,index1,index2));
//			
//		}
		return true;
	}
}
