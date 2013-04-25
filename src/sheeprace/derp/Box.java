package sheeprace.derp;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * This is the general boxobject. This object inherits from the sheep.sprite
 * object.
 * Known classes that inherit from this class is BlockBox and QuestionBox
 * @see BlockBox
 * @see QuestionBox
 *
 */
public class Box extends Sprite{
	
	public Box(Image image){
		super(image);
	}
	
	public void setConvertedCoordinates(float x,float y){
		
		// How far away from the start the box is
		// divide by more to decrease distance between them
		x=(x*((float)Constants.WINDOW_HEIGHT/(float)(7.5)));
		// How high in the air the box is
		// divide by more to increase the hight
		y=(y*((float)Constants.WINDOW_WIDTH/(float)(13.5)));
		
		setPosition(x,y);
	}
}
