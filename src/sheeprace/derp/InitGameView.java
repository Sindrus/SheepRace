package sheeprace.derp;


import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import sheep.game.State;
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
	private TextButton backButton, startGame;
//	private Image sheep,background;
	private MainActivity main;

	
	public InitGameView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		startGame = new TextButton(50, 100, "Start Game");
		players = new Player[2];
		Gfxs = new PlayerGfx[2];
		images = new ArrayList<Image>();

		
//		sheep = new Image(R.drawable.sau_big_1);
//		background = new Image(R.drawable.background);
		images.add(Constants.sheep1);
		//Need to add images in the arraylist, create a view that lets you select from these and then send this to the Gfxs for each player
		//Get the sheep from the player and create a new PlayerGfx based on it.
		Gfxs[0] = new PlayerGfx(images.get(0)); //get() from the selected image obviously
		Gfxs[1] = new PlayerGfx(images.get(0));
		
		players[0] = new Player(Gfxs[0],"P1",1);
		players[1] = new Player(Gfxs[1],"P2",0);
		Game.getGameObject().addPlayers(players[0]);

		
		Game.getGameObject().addPlayers(players[1]);

		this.main = main;
		
		this.index1 = Game.getGameObject().getPlayers().indexOf(players[0]);
		this.index2 = Game.getGameObject().getPlayers().indexOf(players[1]);
//		System.out.println("1: "+index1+", 2:" +index2);
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.CYAN);
		Constants.background.draw(canvas, 0,0);
		backButton.draw(canvas);
		startGame.draw(canvas);
		images.get(0).draw(canvas, canvas.getWidth()/2, 100); //Sheep
	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		else if(startGame.onTouchDown(event)){

			getGame().pushState(new GameBoardView(main,index1,index2));
			
		}
		return true;
	}
}
