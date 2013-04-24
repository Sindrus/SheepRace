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
	
	public void setConvertedCoordinates(float x,float y){
		
	//	System.out.println("Height: "+Constants.WINDOW_HEIGHT + " Width: "+Constants.WINDOW_WIDTH);
		
		float a, b;
		a=(x*(Constants.WINDOW_HEIGHT/5));
	//	System.out.println("X= "+a);
		b=(y*(Constants.WINDOW_WIDTH/14));
	//	System.out.println("Y= "+b);
		
		setPosition(a,b);
	}
}
