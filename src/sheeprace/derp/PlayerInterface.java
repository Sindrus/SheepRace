package sheeprace.derp;

/**
 * Interface for how the player should look like
 *
 */
public interface PlayerInterface {
	/**
	 * Returns the current score of the player
	 * @return The score as an int
	 */
	public int getScore();
	
	public void setScore(int score);
	/**
	 * Calculate the score the player got for the game using the time and 
	 * powerBar parameter and update the current playerscore.
	 * @param time Time used to run through the level
	 * @param powerBar How much power was left at the end of the game
	 */
	public void updateScore(int time, int powerBar);
	
	/**
	 * Connects the playerobject to the playergraphics
	 * @param p the graphics of the player
	 */
	public void setPlayerGfx(PlayerGfx p);
	
	public String getName();
	
	public void setName(String name);
}
