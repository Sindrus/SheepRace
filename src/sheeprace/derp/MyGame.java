package sheeprace.derp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import sheep.game.Game;


/**
 * 
 * This class holds important data about the current game. 
 * This is a singleton object.
 * Including players currently in the game and the level object of the current 
 * level so that GameBoardView knows what level to draw.
 * Rename because of namecrash
 * @see Level
 * @see GameBoardView
 */

public class MyGame implements GameInterface{
	
	private static MyGame game;
	private MainActivity main;
	private Game androidGame;
	private ArrayList<Player> players;
	private int levelNum, p1CorrQuest, p2CorrQuest, roundsPlayed;
	private long timeUsed;
	private Level level;
	private boolean player1sTurn;
	
	private List<String> chosenCategory;
	private Map<String, List<Integer>> availableQuestions;
	
	
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
		resetGame();
	}
	
	/**
	 * Because this is a singletonobject we need some way to reset the data
	 * that is kept in this class every time we reset the game.
	 */
	public void resetGame(){
		this.timeUsed=0;
		this.levelNum=0;
		this.roundsPlayed=0;
		this.availableQuestions = null;
		this.availableQuestions = new HashMap<String, List<Integer>>();
		this.chosenCategory = null;
		this.chosenCategory = new ArrayList<String>();
		this.players = null;
		this.players = new ArrayList<Player>();
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
	 * need to have the mainactivity
	 * @param main The mainactivity for the game
	 */
	public void setMain(MainActivity main){
		this.main = main;
	}
	
	public List<String> getChosenCategory() {
		return chosenCategory;
	}

	public void addChosenCategory(String chosen){
		chosenCategory.add(chosen);
	}
	
	/**
	 * Call this method to create the next level
	 */
	public void createNextLevel(){
		this.levelNum++;
	}
	/**
	 * Method that returns the level
	 * @return the level
	 */
	public Level getLevel(){
		roundsPlayed++;
		this.level = LevelMaker.createLevel(main, "normal", this.levelNum);
		return this.level;
	}
	
	/**
	 * Calculates how many more levels are available
	 * @return the number of levels not yet played
	 */
	public int numberOfLevelsLeft(){
		System.out.println("Number of levels left: "+(Constants.maxLevels-levelNum-1));
		return (Constants.maxLevels-levelNum-1);
	}
	
	@Override
	public ArrayList<Player> getPlayers() {
		System.out.println("spiller lengde: " + players.size());
		return players;
	}
	
	/**
	 * This method returns the player that has the current turn
	 * TODO, fix for adjusted list, aka so we can have highscores etc, or change addPLayers to insert(0,p) and insert(1,p)
	 * @return
	 */
	public Player getPlayer(){
		if(player1sTurn){
			return players.get(0);
		}
		else{
			return players.get(1);
		}
	}

	@Override
	public void addPlayers(Player p) {
		players.add(p);	
	}
	/**
	 * Keep track of which players turn it is
	 * Now it's next playres turn.
	 */
	public void setNextPlayer(){
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
	 * Calculates if all players had the same number of games
	 * @return if the games played are evenly distributed among the players
	 */
	public boolean evenGames(){
		return ((roundsPlayed % players.size())==0);
		
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
	 * records correct answer to player 2
	 */
	public void p2sCorrect(){
		this.p2CorrQuest++;
	}
	/**
	 * @return the number of correct answers by player 2
	 */
	public int getp2sCorrect(){
		return this.p2CorrQuest;
	}
	
	/**
	 * reset the time used to prepare for the next player
	 */
	public void resetTimeUsed(){
		this.timeUsed=0;
	}
	
	/**
	 * Keep the starttime
	 * @param initialTime the starttime of the player
	 */
	public void setStartTime(long initialTime){
		this.timeUsed=initialTime;
	}
	
	/**
	 * Calculate the time used by the player
	 * @param finalTime the time the player finished the race
	 * @return the timedelta from beginning to the end.
	 */
	public long getTimeDelta(long finalTime){
		return (finalTime-this.timeUsed);
	}
	
	/**
	 * Used to make a hashmap for available categories and what questions are
	 * still unused in that category.
	 */
	private void populateAvailableQuestions(){
		for(String s : chosenCategory){
			List<Integer> l = new ArrayList<Integer>();
			int numOfQuestions = Constants.categories.get(s);
			for(int i=0;i<numOfQuestions;i++)
				l.add(i);
			Collections.shuffle(l);
			availableQuestions.put(s, l);
		}
	}
	
	/**
	 * This method look for available questions in the hashmap and pick one
	 * at random
	 * @return a question from the list of available questions
	 */
	public Question getNextQuestion(){
		if(availableQuestions.isEmpty())
			populateAvailableQuestions();
		Random r = new Random();
		List<String> keyList = new ArrayList<String>();
		keyList.addAll(availableQuestions.keySet());
		String randomCategory = keyList.get(r.nextInt(keyList.size()));
		List<Integer> idList = availableQuestions.get(randomCategory);
		System.out.println("For kategori: "+randomCategory+" med spørsmålsid: "+idList);
		int i = idList.remove(r.nextInt(idList.size()));
		if(idList.isEmpty())
			availableQuestions.remove(randomCategory);
		return QuestionMaker.createQuestion(main, i, randomCategory);
	}

	


}
