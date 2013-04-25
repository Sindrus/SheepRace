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
	private EndBox eBox;
	
	
	public Level(){
		qBox = new ArrayList<QuestionBox>();
		bBox = new ArrayList<BlockBox>();
	}
	
	public void addQBox(QuestionBox qb){
		qBox.add(qb);
	}
	public ArrayList<QuestionBox> getQuestionBoxes(){
		return (ArrayList<QuestionBox>)this.qBox;
	}
	public void addBBox(BlockBox bb){
		bBox.add(bb);
	}
	public ArrayList<BlockBox> getBlockBoxes(){
		return (ArrayList<BlockBox>) this.bBox;
	}
	public void addEBox(EndBox eb){
		this.eBox = eb;
	}
	public EndBox getEndBox(){
		return eBox;
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
}
