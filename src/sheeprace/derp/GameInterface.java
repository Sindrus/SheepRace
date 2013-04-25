package sheeprace.derp;

import java.util.ArrayList;

/**
 * Interface for use in the singleton class
 *
 */

public interface GameInterface {

	public ArrayList<Player> getPlayers();
	
	public void addPlayers(Player p);
}
