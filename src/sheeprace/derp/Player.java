package sheeprace.derp;

import sheep.game.Sprite;

/**
 * Holds the information about a player. 
 *
 */

public class Player extends Sprite implements PlayerInterface{
	
	private PlayerGfx gfx;
	
	public Player(PlayerGfx gfx){
		setPlayerGfx(gfx); //obviously not final
	}

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public void updateScore(int time, int powerBar) {
		
	}
	
	//Pseudocode atm
	@Override
	public void setPlayerGfx(PlayerGfx p) {
		this.gfx = p;
	}
	
}
