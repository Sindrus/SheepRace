package sheeprace.derp;

import java.util.ArrayList;

import sheep.game.State;

/**
 * 
 * This class holds important data about the current game
 * Including players currently in the game and the level object of the current 
 * level so that GameBoardView knows what level to draw.
 * @see Level
 * @see GameBoardView
 */

public class Game extends State implements GameInterface{
	
	private static Game game;
	private ArrayList<Player> players = new ArrayList<Player>();

	public static synchronized Game getGameObject(){
		if(game == null){
			game = new Game();
		}
		return game;
	}
	
	@Override
	public ArrayList<Player> getPlayers() {
		// TODO Auto-generated method stub
		return players;
	}

	@Override
	public void addPlayers(Player p) {
		// TODO Auto-generated method stub
		players.add(p);
		
	}



	


}
