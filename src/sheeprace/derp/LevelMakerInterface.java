package sheeprace.derp;

/**
 * Proper use of this interface should make it easy to change the filetype
 * that defines the level layout.
 * The class implementing this interface must return a Levelobject
 *
 */

public interface LevelMakerInterface {
	
	/**
	 * The returns a new level to the game
	 */
	public Level makeLevel();
}
