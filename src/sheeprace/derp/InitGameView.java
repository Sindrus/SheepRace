package sheeprace.derp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.input.KeyboardListener;

/**
 * This is the configview before the game starts. Each player should be able
 * to customize his or her character and choose from what category the
 * questions should come from.
 *
 */

public class InitGameView extends State implements KeyboardListener{

	private MainActivity main;
	private TextButton backButton, startGame, savePlayer, player1ReadyButton, player2ReadyButton;
	private Font font;
	private String playerName = "";
	private Player sheepPlayer1;
	private boolean player1Ready, player2Ready;
	private InputMethodManager imm;
	
	public InitGameView(MainActivity main){
		MyGame.getGameObject().resetGame();
	// Show the keyboard
		imm = (InputMethodManager) main.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
	// Instantiate 	buttons
		backButton = new TextButton(50, 50, "Back");
		startGame = new TextButton(3*(Constants.WINDOW_WIDTH/4), 50, "Start Game");
		savePlayer = new TextButton(Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/2, "Save Player");
		player1ReadyButton = new TextButton(Constants.WINDOW_WIDTH/2 - 45, 2*Constants.WINDOW_HEIGHT/6, " ");
		player2ReadyButton = new TextButton(Constants.WINDOW_WIDTH/2 - 45, 3*Constants.WINDOW_HEIGHT/6, " ");
		player1Ready = false;
		player2Ready = false;
		player1ReadyButton = new TextButton(Constants.WINDOW_WIDTH/2, 2*Constants.WINDOW_HEIGHT/6, " ");
	// Set flag what player is ready
		player1Ready = false;
		player2Ready = false;
		
	// Create the first level
		MyGame.getGameObject().createNextLevel(main);
>>>>>>> 51b5b06e2ef1a3b3e11cb9bb46d89eaa15a855e8
	
		font = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		
		sheepPlayer1 = new Player(new PlayerGfx(Constants.sheep1), playerName, 0);
		sheepPlayer1.getGfx().setPosition(Constants.WINDOW_WIDTH/8, 4*Constants.WINDOW_HEIGHT/10);
		this.main = main;
	}
	
	@Override
	public boolean onKeyDown(KeyEvent e){
		if(!player1Ready && !player2Ready){
			System.out.println("knapp trykket "+(char)e.getUnicodeChar());
			playerName+=Character.toString((char)e.getUnicodeChar());
		}
		return true;
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		backButton.draw(canvas);
		startGame.draw(canvas);
		sheepPlayer1.getGfx().draw(canvas);
		
		Constants.redBox.draw(canvas, Constants.WINDOW_WIDTH/2 - 15, Constants.WINDOW_HEIGHT/6);
		Constants.whiteBox.draw(canvas, Constants.WINDOW_WIDTH/2 - 45, Constants.WINDOW_HEIGHT/6);
		Constants.greenBox.draw(canvas, Constants.WINDOW_WIDTH/2 + 15, Constants.WINDOW_HEIGHT/6);
		Constants.blueBox.draw(canvas, Constants.WINDOW_WIDTH/2 + 45, Constants.WINDOW_HEIGHT/6);

		savePlayer.draw(canvas);
		player1ReadyButton.draw(canvas);
		player2ReadyButton.draw(canvas);

		canvas.drawText("Player Name:", Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/6, font);
		canvas.drawText(playerName, Constants.WINDOW_WIDTH/4, 2*Constants.WINDOW_HEIGHT/6, font);
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			// Hide the keyboard when going to another state
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
			getGame().popState();
			}
		else if(startGame.onTouchDown(event) && player1Ready && player2Ready ){
				// Hide the keyboard when going to another state
				imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
				getGame().pushState(new ChooseCategoryView(main));
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2-15 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.redBox.getWidth()-15 
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/5+Constants.redBox.getHeight()){
				sheepPlayer1.getGfx().setView(Constants.redSheep);
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2-45 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.whiteBox.getWidth()-45
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/6+Constants.whiteBox.getHeight()){
					sheepPlayer1.getGfx().setView(Constants.sheep1);
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2+15 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.greenBox.getWidth()+15
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/6+Constants.greenBox.getHeight()){
				sheepPlayer1.getGfx().setView(Constants.greenSheep);
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2+45 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.blueBox.getWidth()+45
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/6+Constants.blueBox.getHeight()){
				sheepPlayer1.getGfx().setView(Constants.blueSheep);
			}
		if(savePlayer.onTouchDown(event)){
			if(player1Ready){
				if(playerName.isEmpty()){
					playerName = "P2";
				}
				player2Ready = true;
				player2ReadyButton.setLabel("Player 2 ready!");
				savePlayer.setLabel(" ");
				savePlayer(sheepPlayer1);
			}
			else{
				if(playerName.isEmpty()){
					playerName = "P1";
				}
				player1Ready = true;
				player1ReadyButton.setLabel("Player 1 ready!");
				savePlayer(sheepPlayer1);
				playerName = "";
				sheepPlayer1 = new Player(new PlayerGfx(Constants.sheep1), playerName, 0);
				sheepPlayer1.getGfx().setPosition(Constants.WINDOW_WIDTH/8, 4*Constants.WINDOW_HEIGHT/10);
			}
		}
		return true;
	}
	
	public void savePlayer(Player sheep){
		Player player = new Player(sheep.getGfx(), sheep.getName(), sheep.getScore());
		MyGame.getGameObject().getPlayers().add(player);
		
	}
	
	public void update(float dt){
		sheepPlayer1.getGfx().update(dt);
	}
}
