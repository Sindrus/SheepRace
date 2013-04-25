package sheeprace.derp;

import sheep.graphics.Image;

/**
 * Inherit from the Boxobject. 
 * When a player collides with this box, the view is sent to the QuestionView
 * @see BlockBox
 * @see Box
 * @see QuestionView
 *
 */
public class QuestionBox extends Box{
	
	public QuestionBox(float x, float y, Image image){
		super(image);
		
		setConvertedCoordinates(x, y);
		update(0);
	}
	
	@Override
	public String toString(){
		return "QuestionBox @ "+getPosition().toString();
	}

}
