package sheeprace.derp;

/**
 * Inherit from the Boxobject. 
 * When a player collides with this box, the view is sent to the QuestionView
 * @see BlockBox
 * @see Box
 * @see QuestionView
 *
 */
public class QuestionBox extends Box{
	public QuestionBox(float x, float y){
		setPosition(x, y);
	}
	
	@Override
	public String toString(){
		return "QuestionBox";
	}

}
