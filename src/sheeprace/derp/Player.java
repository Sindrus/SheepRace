package sheeprace.derp;

import sheep.game.Sprite;

/**
 * Holds the information about a player. 
 *
 */

public class Player extends Sprite implements PlayerInterface{
	
	private PlayerGfx gfx;
	private int score;
	private String name;
	
	public Player(String name, int score){
		this.name = name;
		this.score = score;
		
	}
	
	public Player(PlayerGfx gfx){
		setPlayerGfx(gfx); //obviously not final
	}

	@Override
	public int getScore() {
		return score;
	}
	

	@Override
	public void updateScore(int time, int powerBar) {
		
	}
	
	//Pseudocode atm
	@Override
	public void setPlayerGfx(PlayerGfx p) {
		this.gfx = p;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setScore() {
		// TODO Auto-generated method stub
		
	}
	
}
