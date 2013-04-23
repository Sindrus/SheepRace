package sheeprace.derp;

import java.util.ArrayList;

import sheep.game.Game;


/**
 * 
 * This class holds important data about the current game
 * Including players currently in the game and the level object of the current 
 * level so that GameBoardView knows what level to draw.
 * Rename because of namecrash
 * @see Level
 * @see GameBoardView
 */

public class MyGame implements GameInterface{
	
	private static MyGame game;
	private Game androidGame;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int levelNum;
	private Level level;

	public static synchronized MyGame getGameObject(){
		if(game == null){
			game = new MyGame();
		}
		return game;
	}
	
	/**
	 * Constructor
	 * Private constructor, because singleton
	 */
	private MyGame(){
		levelNum=1;
	}
	
	/**
	 * 
	 * @return the sheepgameobject
	 */
	public Game getAndroidGame(){
		return androidGame;
	}
	
	public void setAndroidGame(Game game){
		this.androidGame=game;
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
