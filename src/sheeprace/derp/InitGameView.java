package sheeprace.derp;


import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;

/**
 * This is the configview before the game starts. Each player should be able
 * to customize his or her character and choose from what category the
 * questions should come from.
 *
 */

public class InitGameView extends State{

	private Player[] players;
	private PlayerGfx[] Gfxs;
	private List<Image> images;
	
	private int index1, index2;
	private TextButton backButton, startGame, savePlayer1, savePlayer2;
	private MainActivity main;
	private List<TextButton> letters;
	private List<Character> alph;
	private Font font;
	private String test = "";
	private Image red, white, blue, green;
	private Level l;
	
	public InitGameView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		startGame = new TextButton(3*(Constants.WINDOW_WIDTH/4), 50, "Start Game");
		savePlayer1 = new TextButton(Constants.WINDOW_WIDTH/4, 5*Constants.WINDOW_HEIGHT/6, "Save Player");
		savePlayer2 = new TextButton(3 * Constants.WINDOW_WIDTH/4, 5*Constants.WINDOW_HEIGHT/6, "Save Player");
		red = new Image(R.drawable.red);
		white = new Image(R.drawable.white);
		blue = new Image(R.drawable.blue);
		green = new Image(R.drawable.green);
	//	Denne linjen krasjer fordi ingen har lagt til bildet 'red'
		red = new Image(R.drawable.sau_red);
		
		players = new Player[2];
		Gfxs = new PlayerGfx[2];
		images = new ArrayList<Image>();
		
//		Creating levelobject
		Level l = LevelMaker.createLevel(main,"normal", 1);
		this.l = LevelMaker.createLevel(main,"normal", 1);

		images.add(Constants.sheep1);
		//Need to add images in the arraylist, create a view that lets you select from these and then send this to the Gfxs for each player
		//Get the sheep from the player and create a new PlayerGfx based on it.
		Gfxs[0] = new PlayerGfx(images.get(0)); //get() from the selected image obviously
		Gfxs[1] = new PlayerGfx(images.get(0));
		
		players[0] = new Player(Gfxs[0],"P1",0);
		players[1] = new Player(Gfxs[1],"P2",1);
		Game.getGameObject().addPlayers(players[0]);		
		Game.getGameObject().addPlayers(players[1]);

		this.main = main;
		
		this.index1 = Game.getGameObject().getPlayers().indexOf(players[0]);
		this.index2 = Game.getGameObject().getPlayers().indexOf(players[1]);
		
	
//		System.out.println("1: "+index1+", 2:" +index2);
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
		canvas.drawColor(Color.CYAN);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		backButton.draw(canvas);
		startGame.draw(canvas);
		
		red.draw(canvas, Constants.WINDOW_WIDTH/2 - 15, Constants.WINDOW_HEIGHT/6);
		white.draw(canvas, Constants.WINDOW_WIDTH/2 - 45, Constants.WINDOW_HEIGHT/6);
		green.draw(canvas, Constants.WINDOW_WIDTH/2 + 15, Constants.WINDOW_HEIGHT/6);
		blue.draw(canvas, Constants.WINDOW_WIDTH/2 + 45, Constants.WINDOW_HEIGHT/6);
		
		images.get(0).draw(canvas, canvas.getWidth()/4, 3*Constants.WINDOW_HEIGHT/5); //Sheep
		images.get(0).draw(canvas, 3*(canvas.getWidth()/4), 3*Constants.WINDOW_HEIGHT/5);
		savePlayer1.draw(canvas);
		savePlayer2.draw(canvas);
		
		for (int i = 0; i < letters.size(); i++) {
			letters.get(i).draw(canvas);
		}
	}
	
	public void makeAlphabet(){
		alph = new ArrayList<Character>();
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++){
			 alph.add(alphabet);
			 }
	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
			}
		else if(startGame.onTouchDown(event)){
			getGame().pushState(new GameBoardView(main,index1,index2, l));
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2-15 && event.getX()<Constants.WINDOW_WIDTH/2+red.getWidth() 
				&& event.getY()>Constants.WINDOW_HEIGHT/5 && event.getY()<Constants.WINDOW_HEIGHT/5+red.getHeight()){
				getGame().popState();
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2-45 && event.getX()<Constants.WINDOW_WIDTH/2+white.getWidth()
				&& event.getY()>Constants.WINDOW_HEIGHT/5 && event.getY()<Constants.WINDOW_HEIGHT/5+white.getHeight()){

			getGame().pushState(new GameBoardView(main,index1,index2, l));
			
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2+15 && event.getX()<Constants.WINDOW_WIDTH/2+blue.getWidth()
				&& event.getY()>Constants.WINDOW_HEIGHT/5 && event.getY()<Constants.WINDOW_HEIGHT/5+blue.getHeight()){
			}
		else if(event.getX()>Constants.WINDOW_WIDTH/2+45 && event.getX()<Constants.WINDOW_WIDTH/2+blue.getWidth()
				&& event.getY()>Constants.WINDOW_HEIGHT/5 && event.getY()<Constants.WINDOW_HEIGHT/5+blue.getHeight()){
			}
		for (int i = 0; i < letters.size(); i++) {
			if(letters.get(i).onTouchDown(event)){
				test += alph.get(i);
				
			}
		}
		return true;
	}
}
