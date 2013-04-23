package sheeprace.derp;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
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
	
	private Font font, headLine, winFont; 
	
	private boolean finalScreen;
	
	private Matrix matrix, matrix2;
	
	/** 
	 * I think this should be deprecated since we don't have singleplayer mode
	 * -Sindre
	 * @deprecated
	 */
	public GameStatusView(MainActivity main, Player player){
		this.main = main;
		this.player = player; //Don't know if this is the right way to do it just now
		
		font = new Font(88, 88, 88, 20, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		headLine = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		headLine.setTextAlign(Align.CENTER);
		
		backButton = new TextButton(50, 50, "Back");
		
		MatrixOps();
		
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
		
		winFont = new Font(20,190,30,40,Typeface.SERIF, Typeface.BOLD);
		winFont.setTextAlign(Align.CENTER);
		
		backButton = new TextButton(50, 50, "Back");

		player1.setScore(MyGame.getGameObject().getp1sCorrect());
		player2.setScore(MyGame.getGameObject().getp2sCorrect());
		
		
		MatrixOps();
		
		this.finalScreen= true;
	}
	
	public void MatrixOps(){
		matrix = new Matrix();
		matrix.reset();
//		RectF drawableRect = new RectF(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
//		RectF viewRect = new RectF(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
//		matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
		matrix.postRotate(45);
		matrix.postTranslate(Constants.WINDOW_WIDTH/28, 20);
		
		
		matrix2 = new Matrix();
		matrix2.reset();
		
		matrix2.postRotate(-45);
		matrix2.postTranslate((Constants.WINDOW_WIDTH/7)*6, 165);
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
//		Constants.frontSheep.draw(canvas,0,100);
		
		canvas.drawBitmap(Constants.frontSheep_bitmap, matrix ,null);
		canvas.drawBitmap(Constants.frontSheep_bitmap, matrix2 ,null);
		
		if(!finalScreen){
			canvas.drawText("Your score", canvas.getWidth()/2, 100, headLine);
			canvas.drawText(player.getName() + "  " +player.getScore(), canvas.getWidth()/2, 150, font);
			backButton.draw(canvas); // TODO for now, change to "continue" later on
		}
		else if(finalScreen){
			canvas.drawText("Your scores", canvas.getWidth()/2, 100, headLine);
			System.out.println(player1.toString());
			System.out.println(player2.toString());
			canvas.drawText(player1.getName() + "  " +player1.getScore(), canvas.getWidth()/2, 150, font);
			canvas.drawText(player2.getName() + "  " +player2.getScore(), canvas.getWidth()/2, 200, font);
			if(player1.getScore()>player2.getScore())
				canvas.drawText(player1.getName() + " wins!",canvas.getWidth()/2,canvas.getHeight()/2,winFont);
			else if(player1.getScore()<player2.getScore())
				canvas.drawText(player2.getName() + " wins!",canvas.getWidth()/2,canvas.getHeight()/2,winFont);
			else
				canvas.drawText("Draw! Looks like you need to play again!",canvas.getWidth()/2,canvas.getHeight()/2,winFont);
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
