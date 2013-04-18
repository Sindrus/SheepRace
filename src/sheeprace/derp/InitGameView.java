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
	
	private TextButton backButton, startGame;
	private Image sheep, background;
	private MainActivity main;
	
	public InitGameView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		startGame = new TextButton(100, 50, "Start Game");
		players = new Player[2];
		Gfxs = new PlayerGfx[2];
		images = new ArrayList<Image>();
		sheep = new Image(R.drawable.sau_1);
		background = new Image(R.drawable.background);
		images.add(sheep);
		//Need to add images in the arraylist, create a view that lets you select from these and then send this to the Gfxs for each player
		//Get the sheep from the player and create a new PlayerGfx based on it.
		Gfxs[0] = new PlayerGfx(images.get(0)); //get() from the selected image obviously
//		Gfxs[1] = new PlayerGfx(images.get(1));
//		Gfxs[0] = new PlayerGfx(null); //WILL NOT WORK ATM
//		Gfxs[1] = new PlayerGfx(null); //WILL NOT WORK ATM
//		
		players[0] = new Player(Gfxs[0]);
//		players[1] = new Player(Gfxs[1]);
		Game.getGameObject().addPlayers(players[0]);
		this.main = main;
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.CYAN);
		background.draw(canvas, 0,0);
		backButton.draw(canvas);
		startGame.draw(canvas);
		sheep.draw(canvas, canvas.getWidth()/2, 100);
	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		else if(startGame.onTouchDown(event)){
			getGame().pushState(new GameBoardView(main));
			
		}
		return true;
	}
}
