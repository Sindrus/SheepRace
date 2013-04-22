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
	private int levelNum;
	private Level level;

	public static synchronized Game getGameObject(){
		if(game == null){
			game = new Game();
		}
		return game;
	}
	
	/**
	 * Constructor
	 * Private constructor, because singleton
	 */
	private Game(){
		levelNum=1;
	}
	
	/**
	 * Because this is a singletonobject we need some way to reset the data
	 * that is kept in this class every time we reset the game.
	 */
	public void resetLevel(){
		this.levelNum=1;
	}
	
	/**
	 * Call this method to get the next level
	 */
	public void nextLevel(MainActivity main){
		this.level = LevelMaker.createLevel(main,"normal", this.levelNum);
		this.levelNum++;
	}
	
	public Level getNextLevel(MainActivity main){
		nextLevel(main);
		return getLevel();
	}
	
	public Level getLevel(){
		return this.level;
	}
	
	@Override
	public ArrayList<Player> getPlayers() {
		return players;
	}

	@Override
	public void addPlayers(Player p) {
		players.add(p);
		
	}



	


}
