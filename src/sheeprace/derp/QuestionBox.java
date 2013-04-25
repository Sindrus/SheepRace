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
		super(x, y, image);
	}
	
	@Override
	public String toString(){
		return "QuestionBox @ "+getPosition().toString();
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
