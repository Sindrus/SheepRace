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
	
	public Player(PlayerGfx gfx,String name, int score){
		setPlayerGfx(gfx); //obviously not final
		this.name = name;
		this.score = score;
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
	
	public PlayerGfx getGfx(){
		return this.gfx;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setScore(int score) {
		this.score=score;
	}
	@Override
	public String toString(){
		return ("Spillernavn: "+this.name+" with score: "+this.score);
	}
	
}
