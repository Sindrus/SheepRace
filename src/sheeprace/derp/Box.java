package sheeprace.derp;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * This is the general boxobject. This object inherits from the sheep.sprite
 * object.
 * Known classes that inherit from this class is BlockBoxConstants.WINDOW_HEIGHT/2Constants.WINDOW_HEIGHT/2 and QuestionBox
 * @see BlockBox
 * @see QuestionBox
 *
 */
public abstract class Box extends Sprite{
	
	public Box(float x, float y, Image image){
		super(image);
		setConvertedCoordinates(x, y);
		update(0);
	}
	
	public abstract void setConvertedCoordinates(float x, float y);
}
