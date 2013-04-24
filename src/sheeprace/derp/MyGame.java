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
 * This is a singletonobject.
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
	private ArrayList<Player> players = new ArrayList<Player>();
	private int levelNum, p1CorrQuest, p2CorrQuest;
	private Level level;
	private boolean player1sTurn;
	
	private List<String> chosenCategory;
	
	public List<String> getChosenCategory() {
		return chosenCategory;
	}

	public void addChosenCategory(String chosen){
		chosenCategory.add(chosen);
	}

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
	 * need to have the mainactivity
	 * @param main The mainactivity for the game
	 */
	public void setMain(MainActivity main){
		this.main = main;
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
	// TODO: also reset playerlist

		levelNum=0;
		this.availableQuestions = new HashMap<String, List<Integer>>();
		this.chosenCategory = new ArrayList<String>();
		this.player1sTurn=true;
		this.p1CorrQuest=0;
		this.p2CorrQuest=0;
		
	// FIXME: Remove when categorychoice is implemented
/*		chosenCategory.add("film");
		chosenCategory.add("tv");*/
		
	}
	
	/**
	 * Call this method to create the next level
	 */
	public void createNextLevel(MainActivity main){
		this.level = LevelMaker.createLevel(main,"normal", this.levelNum);
		this.levelNum++;
	}
	/**
	 * Method that returns the level
	 * @return the level
	 */
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
	 * This method returns the player that has the current turn
	 * TODO, fix for adjusted list, aka so we can have highscores etc, or change addPLayers to insert(0,p) and insert(1,p)
	 * @return
	 */
	public Player getPlayer(){
		if(player1sTurn)
			return players.get(0);
		else
			return players.get(1);
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
		int i = idList.remove(r.nextInt(idList.size()));
		if(idList.isEmpty())
			availableQuestions.remove(randomCategory);
		return QuestionMaker.createQuestion(main, i, randomCategory);
	}

	


}
