package sheeprace.derp;

import java.util.ArrayList;
import java.util.List;

/**
 * Holding the structure of the level and feeding it to the gameboardview when
 * it needs to draw the map.
 * Level is defined in xmlfile, where each element has a startcoordinat.
 * @see GameBoardView
 */

public class Level {
	
	private List<QuestionBox> qBox;
	private List<BlockBox> bBox;
	
	
	public Level(){
		qBox = new ArrayList<QuestionBox>();
		bBox = new ArrayList<BlockBox>();
	}
	
	public void addQBox(QuestionBox qb){
		qBox.add(qb);
	}
	public void addBBox(BlockBox bb){
		bBox.add(bb);
	}
	
	@Override
	public String toString(){
		String s="";
		for(QuestionBox q : qBox)
			s+=q.toString()+'\n';
		for(BlockBox b : bBox)
			s+=b.toString()+'\n';
		return s;
	}
	
	public ArrayList<BlockBox> getBlockBoxes(){
		return (ArrayList<BlockBox>) this.bBox;
	}
	
	public ArrayList<QuestionBox> getQuestionBoxes(){
		return (ArrayList<QuestionBox>)this.qBox;
	}
}
