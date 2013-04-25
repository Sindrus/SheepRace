package sheeprace.derp;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
	private Player player1,player2;
	private TextButton continueButton, finishedButton;
	
	private Font font, headLine, winFont; 
	
	private boolean moreLevels, equalsNumsOfGames;
	
	private Matrix matrix, matrix2;
	
	/**
	 * This is the method to use, it does not need to take the player
	 * @param main MainActivity
	 */
	public GameStatusView(MainActivity main){
		this.main = main;
		this.player1 = MyGame.getGameObject().getPlayers().get(0);
		this.player2 = MyGame.getGameObject().getPlayers().get(1);
		
		font = new Font(88, 88, 88, 20, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		headLine = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		headLine.setTextAlign(Align.CENTER);
		
		winFont = new Font(20,190,30,40,Typeface.SERIF, Typeface.BOLD);
		winFont.setTextAlign(Align.CENTER);
		
		continueButton = new TextButton(Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/4, "Next Game");
		
		finishedButton = new TextButton((3*Constants.WINDOW_WIDTH)/4, Constants.WINDOW_HEIGHT/4, "To highscore");
		
		long timeUsed = MyGame.getGameObject().getTimeDelta(System.currentTimeMillis());
		int powerbarpower = MyGame.getGameObject().getPlayer().getPowerbarPower();
		
		int score = powerbarpower + (int)(long)(50000/timeUsed);
		
		MyGame.getGameObject().getPlayer().addScore(score);
		
		
//		player1.addScore(MyGame.getGameObject().getp1sCorrect());
//		player2.addScore(MyGame.getGameObject().getp2sCorrect());
		
		moreLevels=!(MyGame.getGameObject().numberOfLevelsLeft()==0);
		equalsNumsOfGames = MyGame.getGameObject().evenGames();
		
		MatrixOps();
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


		canvas.drawText("Your score", canvas.getWidth()/2, 100, headLine);
		canvas.drawText(player1.getName() + "  " +player1.getScore(), canvas.getWidth()/2, 150, font);
		canvas.drawText(player2.getName() + "  " +player2.getScore(), canvas.getWidth()/2, 200, font);
		
		if(moreLevels)
			continueButton.draw(canvas);
		else if(!equalsNumsOfGames)
			continueButton.draw(canvas);
		if(equalsNumsOfGames)
			finishedButton.draw(canvas);
	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(finishedButton.onTouchDown(event)){
			System.out.println("Pushed finished");
			getGame().popState(4);
			getGame().pushState(new HighScoreView(main, true));
		}else if(continueButton.onTouchDown(event)){
			if(equalsNumsOfGames)
				MyGame.getGameObject().createNextLevel();
			System.out.println("Pushed continue");
			MyGame.getGameObject().setNextPlayer();
			MyGame.getGameObject().resetTimeUsed();
			getGame().popState(2);
			getGame().pushState(new GameBoardView(main));
		}
		return true;
	}
}
