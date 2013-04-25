package sheeprace.derp;

import sheep.graphics.Image;

/**
 * Object that blocks the players path in the gameworld.
 * Holds the information about a single blockbox
 *
 */

public class BlockBox extends Box{
	
	public BlockBox(float x, float y, Image image){
		super(x,y,image);
		
	}
	
	@Override
	public String toString(){
		return "BlockBox @ "+getPosition().toString();
	}

	@Override
	public void setConvertedCoordinates(float x, float y) {
		// How far away from the start the box is
		// divide by more to decrease distance between them
		x=(x*((float)Constants.WINDOW_HEIGHT/(float)(7.5)));
		// How high in the air the box is
		// divide by more to increase the hight
		y=(y*((float)Constants.WINDOW_WIDTH/(float)(13.5)));
		setPosition(x, y);
	}
}
