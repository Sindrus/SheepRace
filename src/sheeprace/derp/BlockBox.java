package sheeprace.derp;

/**
 * Object that blocks the players path in the gameworld.
 * Holds the information about a single blockbox
 *
 */

public class BlockBox extends Box{
	public BlockBox(float x, float y){
		setConvertedCoordinates(x, y);
		setView(Constants.blockBox);
		update(0);
	}
	
	@Override
	public String toString(){
		return "BlockBox @ "+getPosition().toString();
	}
}
