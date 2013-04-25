package sheeprace.derp;

import sheep.collision.Rectangle;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Holds the graphical information about a player and render the player
 * to the screen.
 *
 */

public class PlayerGfx extends Sprite{
	
	public PlayerGfx(Image imageName){
		super(imageName);
	}
	
	public void jump() {
		if (canJump()) {
			this.setSpeed(0, -Constants.SPEED);
			this.setAcceleration(0, Constants.ACCELERATION);
		}
	}
	
	public void update(float dt) {
		
		if (this.getY() <= 0) 
			this.setSpeed(0, Constants.SPEED);
		
		super.update(dt);
	}
	
	public boolean canJump() {
		if (getSpeed().getY() == 0 && getAcceleration().getY() == 0)
			return true;
		return false;
	}
	
	/**
	 * Stops the player from moving
	 */
	public void stop(){
		setSpeed(0, 0);
		setAcceleration(0, 0);
		setPosition(getX(), getY()-3);
		//setPosition(getX(), Constants.WINDOW_HEIGHT*3/4 - Constants.sheep1.getHeight()/2);
	}
	
	/**
	 * Reverses the speed of the playerGfx
	 */
	public void reverseSpeed(){
		setSpeed(-getSpeed().getX(), -getSpeed().getY());
	}
	
	/**
	 * If the abovearea of the sheep is blocked, set the speed to 0
	 * and make gravity take care of the rest.
	 */
	public void topBlocked(){
		setSpeed(0,0);
	}
	
/*	/**
	 * Reset the startposition of the player. 
	 *
	public void resetStartPosition(){
		
	}*/
}
