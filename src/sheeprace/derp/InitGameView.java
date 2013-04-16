package sheeprace.derp;


import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;


import sheep.game.State;
import sheep.graphics.Image;

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
	
	public InitGameView(MainActivity main){
		players = new Player[2];
		Gfxs = new PlayerGfx[2];
		images = new ArrayList<Image>();
		
		//Need to add images in the arraylist, create a view that lets you select from these and then send this to the Gfxs for each player
		//Get the sheep from the player and create a new PlayerGfx based on it.
//		Gfxs[0] = new PlayerGfx(images.get(0)); //get() from the selected image obviously
//		Gfxs[1] = new PlayerGfx(images.get(1));
//		Gfxs[0] = new PlayerGfx(null); //WILL NOT WORK ATM
//		Gfxs[1] = new PlayerGfx(null); //WILL NOT WORK ATM
//		
//		players[0] = new Player(Gfxs[0]);
//		players[1] = new Player(Gfxs[1]);
	}

}
