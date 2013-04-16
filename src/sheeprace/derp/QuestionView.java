package sheeprace.derp;

import sheep.game.State;

/**
 * A view that displays the question and what answers are available.
 *
 */
public class QuestionView extends State{
	private String question;
	
	public String getQuestion(){
		return this.question;
	}
	
	public void viewQuestion(){
		System.out.println(question);
		//System.out.println(addOption("True", true));
	}
	
	
	
}
