package sheeprace.derp;

import sheep.game.Sprite;

/**
 * Holds the information about a player. 
 *
 */

public class Player extends Sprite implements PlayerInterface{

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public void updateScore(int time, int powerBar) {
		
	}

	@Override
	public void setPlayerGfx(PlayerGfx p) {
		
	}
	
}
