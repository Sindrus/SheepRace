package sheeprace.derp;

import sheep.collision.CollisionListener;
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
		this.setShape(new Rectangle(60, 60));
	}
	
	public void jump() {
		// getSpeed().getY() == 0 && getAcceleration().getY() == 0)
	/*	System.out.println("Before: -----------");
		System.out.println("Pos Y: "+ this.getY());
		System.out.println("Pos X:" + this.getX());
		
		System.out.println("Speed Y: "+ this.getSpeed().getY());
		System.out.println("Speed X:" + this.getSpeed().getX());
		
		System.out.println("Acc Y: "+ this.getAcceleration().getY());
		System.out.println("Acc X:" + this.getAcceleration().getX());
	*/	
		if (canJump()) {
			this.setSpeed(0, -Constants.SPEED);
			this.setAcceleration(0, Constants.ACCELERATION);
		}
	/*	
		System.out.println("After: -----------");
		System.out.println("Pos Y: "+ this.getY());
		System.out.println("Pos X:" + this.getX());
		
		System.out.println("Speed Y: "+ this.getSpeed().getY());
		System.out.println("Speed X:" + this.getSpeed().getX());
		
		System.out.println("Acc Y: "+ this.getAcceleration().getY());
		System.out.println("Acc X:" + this.getAcceleration().getX());
	*/
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
}
