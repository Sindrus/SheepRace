package sheeprace.derp;

import sheep.graphics.Image;

public class EndBox extends Box{

	public EndBox(float x, float y, Image image) {
		super(image);
		setConvertedCoordinates(x, y);
		setPosition(getX(), Constants.WINDOW_HEIGHT/2);
		update(0);
	}
	
	@Override
	public String toString(){
		return ("End: X: "+getX()+" Y: "+getY());
	}

}
