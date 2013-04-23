package sheeprace.derp;

import java.util.ArrayList;
import java.util.List;

import sheep.game.Game;


/**
 * 
 * This class holds important data about the current game. 
 * This is a singletonobject.
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
	private int levelNum, p1CorrQuest, p2CorrQuest;
	private Level level;
	private boolean player1sTurn;
	
	private List<String> chosenCategory;
	
	
	
	/**
	 * This is needed because MyGame is implemented as a singleton.
	 * @return return the MyGameobject
	 */
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
		this.chosenCategory = new ArrayList<String>();
		this.player1sTurn=true;
		this.p1CorrQuest=0;
		this.p2CorrQuest=0;
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
	public void resetGame(){
		this.levelNum=1;
		this.player1sTurn=true;
		this.p1CorrQuest=0;
		this.p2CorrQuest=0;
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
	/**
	 * Flips the player1 bit
	 */
	public void getNextPlayer(){
		player1sTurn=!player1sTurn;
	}
	
	/**
	 * If true, the turn belongs to player1 else player2
	 * @return return true if it is player1s turn
	 */
	public boolean isPlayer1sTurn(){
		return player1sTurn;
	}
	
	/**
	 * records correct answer to player 1
	 */
	public void p1IsCorrect(){
		this.p1CorrQuest++;
	}
	/**
	 * @return the number of correct answers by player 1
	 */
	public int getp1sCorrect(){
		return this.p1CorrQuest;
	}
	/**
	 * @return the number of correct answers by player 2
	 */
	public int getp2sCorrect(){
		return this.p2CorrQuest;
	}
	/**
	 * records correct answer to player 2
	 */
	public void p2sCorrect(){
		this.p2CorrQuest++;
	}


	


}
