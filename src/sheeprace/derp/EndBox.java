package sheeprace.derp;

import sheep.graphics.Image;

/**
 * 	The purpose of this class is to create a box at the end of the game world which ends the current play session when the player  
 * 
 * 	Contains information about the endbox
 * 
 */

public class EndBox extends Box{

	public EndBox(float x, float y, Image image) {
		super(x,y,image);
	}
	
	@Override
	public String toString(){
		return ("End: X: "+getX()+" Y: "+getY());
	}

	@Override
	public void setConvertedCoordinates(float x, float y) {
		x=x*((float)Constants.WINDOW_HEIGHT/(float)(7.5));
		setPosition(x, Constants.WINDOW_HEIGHT/2);
	}

}
