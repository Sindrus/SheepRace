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
	private int powerbarpower;
	
	public Player(PlayerGfx gfx,String name){
		setPlayerGfx(gfx); //obviously not final
		this.name = name;
		this.score = 0;
	}
	
	@Override
	public int getScore() {
		return score;
	}
	
	@Override
	public void updateScore(int time, int powerBar) {
		//TODO, DO THIS! need some calculation based on the powerbar
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
		return name;
	}
	
	@Override
	public void setName(String name) {
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
	
	/**
	 * decrease the power of the powerbarpower
	 */
	public void decreasePowerbarPower(){
		this.powerbarpower--;
	}
	
	/**
	 * return the power of the powerbar
	 * @return the powerbar
	 */
	public int getPowerbarPower(){
		return this.powerbarpower;
	}
	
	/**
	 * Increase the power of the powerbarpower by power
	 * @param power
	 */
	public void increasePowerbarPower(int power){
		this.powerbarpower++;
	}
	
	/**
	 * reset the powerbarpower to some defaultvalue, between games.
	 */
	public void resetPowerbarPower(){
		this.powerbarpower=1000;
	}
}
