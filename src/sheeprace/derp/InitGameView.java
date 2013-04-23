package sheeprace.derp;


import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;

/**
 * This is the configview before the game starts. Each player should be able
 * to customize his or her character and choose from what category the
 * questions should come from.
 *
 */

public class InitGameView extends State{

	private int index1, index2;
	private MainActivity main;
	private TextButton backButton, startGame, savePlayer1, savePlayer2;
	private List<TextButton> letters;
	private List<Character> alph;
	private Font font;
	private String player1Name = "";
	private String player2Name = "";
	private Level l;
	private Player sheepPlayer1, sheepPlayer2;
	private boolean player1Ready, player2Ready;
	
	public InitGameView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		startGame = new TextButton(3*(Constants.WINDOW_WIDTH/4), 50, "Start Game");
		savePlayer1 = new TextButton(Constants.WINDOW_WIDTH/4, 5*Constants.WINDOW_HEIGHT/6, "Save Player");
		player1Ready = false;
		savePlayer2 = new TextButton(3 * Constants.WINDOW_WIDTH/4, 5*Constants.WINDOW_HEIGHT/6, "Save Player");
		player2Ready = false;
	
		font = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		
		sheepPlayer1 = new Player(new PlayerGfx(Constants.sheep1), player1Name, 0);
		sheepPlayer1.getGfx().setPosition(Constants.WINDOW_WIDTH/3, 4*Constants.WINDOW_HEIGHT/6);
		sheepPlayer2 = new Player(new PlayerGfx(Constants.sheep1), player2Name, 0);
		sheepPlayer2.getGfx().setPosition(5*Constants.WINDOW_WIDTH/6, 4*Constants.WINDOW_HEIGHT/6);

		this.main = main;
//		this.index1 = Game.getGameObject().getPlayers().indexOf(players[0]);
//		this.index2 = Game.getGameObject().getPlayers().indexOf(players[1]);		
		letters = new ArrayList<TextButton>();
		makeAlphabet();
		int fX= -45;
		int fY= 2;
		for (int i = 0; i < alph.size(); i++) {
			if(i == 0 || i == 4 || i == 8 || i == 12 || i == 16 || i == 20){
				fY +=1;
				fX = -45;
				TextButton tb = new TextButton(Constants.WINDOW_WIDTH/2 + fX, fY*Constants.WINDOW_HEIGHT/11, "" + alph.get(i));
				letters.add(tb);
			}
			else{
				fX += 30;
				TextButton tb = new TextButton(Constants.WINDOW_WIDTH/2 + fX, fY*Constants.WINDOW_HEIGHT/11, "" + alph.get(i));
				letters.add(tb);
			}
		}
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
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
		for (int i = 0; i < letters.size(); i++) {
			letters.get(i).draw(canvas);
		}
		canvas.drawText("Player Name:", 3*Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/6, font);
		canvas.drawText("Player Name:", Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGHT/6, font);
		canvas.drawText(player2Name, 3*Constants.WINDOW_WIDTH/4, 2*Constants.WINDOW_HEIGHT/6, font);
		canvas.drawText(player1Name, Constants.WINDOW_WIDTH/4, 2*Constants.WINDOW_HEIGHT/6, font);
	}
	
	public void makeAlphabet(){
		alph = new ArrayList<Character>();
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++){
			 alph.add(alphabet);
			 }
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
			}
		else if(startGame.onTouchDown(event) && player1Ready && player2Ready ){
			if(player1Ready && player2Ready){
				if(!player1Name.isEmpty() || !player2Name.isEmpty()){
					Game.getGameObject().getPlayers().add(sheepPlayer1);
					Game.getGameObject().getPlayers().add(sheepPlayer2);
					getGame().pushState(new GameBoardView(main,index1,index2, l));
					}
				else{
					player1Name = "P1";
					player2Name = "P2";
					Game.getGameObject().getPlayers().add(sheepPlayer1);
					Game.getGameObject().getPlayers().add(sheepPlayer2);
					getGame().pushState(new GameBoardView(main,index1,index2, l));
					}
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
		for (int i = 0; i < letters.size(); i++) {
			if(letters.get(i).onTouchDown(event)){
				if(player1Ready){
					player2Name += alph.get(i);
				}
				else{
					player1Name += alph.get(i);
				}
			}
		}
		return true;
	}
	
	public void update(float dt){
		sheepPlayer1.getGfx().update(dt);
		sheepPlayer2.getGfx().update(dt);
	}
}
