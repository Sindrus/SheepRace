package sheeprace.derp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.hardware.input.InputManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

@SuppressLint("NewApi")
public class InitGameView extends State implements KeyboardListener{

	private int index1, index2;
	private MainActivity main;
	private TextButton backButton, startGame, savePlayer1, savePlayer2;
	private Font font;
	private String player1Name = "";
	private String player2Name = "";
	private Level l;
	private Player sheepPlayer1, sheepPlayer2;
	private boolean player1Ready, player2Ready;
	private InputMethodManager imm;
	private EditText et;
	
	
	
	
	@SuppressLint("NewApi")
	public InitGameView(MainActivity main){
		
		imm = (InputMethodManager) main.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
		imm.showSoftInput(MyGame.getGameObject().getAndroidGame(), InputMethodManager.RESULT_SHOWN);
		
		
		
/*		et = new EditText(main);
		et.setHeight(30);
		et.setText("lol");
		et.setX(0);
		et.setY(0);*/
		
//		et = (EditText)MyGame.getGameObject().getAndroidGame().findViewById(R.id.editText1);
		
		
		
		
		backButton = new TextButton(50, 50, "Back");
		startGame = new TextButton(3*(Constants.WINDOW_WIDTH/4), 50, "Start Game");
		savePlayer1 = new TextButton(Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/2, "Save Player");
		player1Ready = false;
		savePlayer2 = new TextButton(3 * Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/2, "Save Player");
		player2Ready = false;
	
		font = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		
		sheepPlayer1 = new Player(new PlayerGfx(Constants.sheep1), player1Name, 0);
		sheepPlayer1.getGfx().setPosition(Constants.WINDOW_WIDTH/3, Constants.WINDOW_HEIGHT/3);
		sheepPlayer2 = new Player(new PlayerGfx(Constants.sheep1), player2Name, 0);
		sheepPlayer2.getGfx().setPosition(5*Constants.WINDOW_WIDTH/6, Constants.WINDOW_HEIGHT/3);

		this.main = main;
//		this.index1 = Game.getGameObject().getPlayers().indexOf(players[0]);
//		this.index2 = Game.getGameObject().getPlayers().indexOf(players[1]);
		
	}
	
	@Override
	public boolean onKeyDown(KeyEvent e){
		System.out.println("knapp trykket "+(char)e.getUnicodeChar());
		if(!player1Ready)
			player1Name+=Character.toString((char)e.getUnicodeChar());
		else
			player2Name+=Character.toString((char)e.getUnicodeChar());
		
		return true;
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
//		et.draw(canvas);
		backButton.draw(canvas);
		startGame.draw(canvas);
		sheepPlayer1.getGfx().draw(canvas);
		sheepPlayer2.getGfx().draw(canvas);
		
		Constants.redBox.draw(canvas, Constants.WINDOW_WIDTH/2 - 15, Constants.WINDOW_HEIGHT/6);
		Constants.whiteBox.draw(canvas, Constants.WINDOW_WIDTH/2 - 45, Constants.WINDOW_HEIGHT/6);
		Constants.greenBox.draw(canvas, Constants.WINDOW_WIDTH/2 + 15, Constants.WINDOW_HEIGHT/6);
		Constants.blueBox.draw(canvas, Constants.WINDOW_WIDTH/2 + 45, Constants.WINDOW_HEIGHT/6);

		savePlayer1.draw(canvas);
		savePlayer2.draw(canvas);

		canvas.drawText("Player Name:", 3*Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/6, font);
		canvas.drawText("Player Name:", Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/6, font);
		canvas.drawText(player2Name, 3*Constants.WINDOW_WIDTH/4, 2*Constants.WINDOW_HEIGHT/6, font);
		canvas.drawText(player1Name, Constants.WINDOW_WIDTH/4, 2*Constants.WINDOW_HEIGHT/6, font);
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			
			imm.showSoftInput(MyGame.getGameObject().getAndroidGame(), InputMethodManager.RESULT_HIDDEN);
			
//			imm.hideSoftInputFromWindow(main.getCurrentFocus().getWindowToken(), 0);
			getGame().popState();
			}
		else if(startGame.onTouchDown(event) && player1Ready && player2Ready ){
			if(player1Ready && player2Ready){
				if(!player1Name.isEmpty() || !player2Name.isEmpty()){
					MyGame.getGameObject().getPlayers().add(sheepPlayer1);
					MyGame.getGameObject().getPlayers().add(sheepPlayer2);
					}
				else{
					player1Name = "P1";
					player2Name = "P2";
					MyGame.getGameObject().getPlayers().add(sheepPlayer1);
					MyGame.getGameObject().getPlayers().add(sheepPlayer2);
					}
				imm.showSoftInput(MyGame.getGameObject().getAndroidGame(), InputMethodManager.RESULT_HIDDEN);
				getGame().pushState(new GameBoardView(main,index1,index2, l));
				}
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2-15 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.redBox.getWidth()-15 
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/5+Constants.redBox.getHeight()){
				if(player1Ready){
					sheepPlayer2.getGfx().setView(Constants.redSheep);
					}
				else{
					sheepPlayer1.getGfx().setView(Constants.redSheep);
					}
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2-45 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.whiteBox.getWidth()-45
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/6+Constants.whiteBox.getHeight()){
				if(player1Ready){
					sheepPlayer2.getGfx().setView(Constants.sheep1);
					}
				else{
					sheepPlayer1.getGfx().setView(Constants.sheep1);
					}
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2+15 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.greenBox.getWidth()+15
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/6+Constants.greenBox.getHeight()){
				if(player1Ready){
					sheepPlayer2.getGfx().setView(Constants.greenSheep);
					}
				else{
					sheepPlayer1.getGfx().setView(Constants.greenSheep);
					}
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2+45 && event.getX()<Constants.WINDOW_WIDTH/2+Constants.blueBox.getWidth()+45
				&& event.getY()>Constants.WINDOW_HEIGHT/6 && event.getY()<Constants.WINDOW_HEIGHT/6+Constants.blueBox.getHeight()){
				if(player1Ready){
					sheepPlayer2.getGfx().setView(Constants.blueSheep);
					}
				else{
					sheepPlayer1.getGfx().setView(Constants.blueSheep);
					}
			}
		if(savePlayer1.onTouchDown(event)){
			player1Ready = true;
			savePlayer1.setLabel("Player ready!");
		}
		if(savePlayer2.onTouchDown(event) && player1Ready){
			player2Ready = true;
			savePlayer2.setLabel("Player ready!");
		}
		return true;
	}
	
	public void update(float dt){
		sheepPlayer1.getGfx().update(dt);
		sheepPlayer2.getGfx().update(dt);
	}
}
