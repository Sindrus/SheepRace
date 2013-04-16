package sheeprace.derp;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the question. It should be created by a class implementing the
 * QuestionMakerInterface 
 * @see QuestionMaker
 * @see QuestionMakerInterface
 *
 */

public class Question implements QuestionInterface{
	
	private String question;
	private List<String> options = new ArrayList<String>();
	private List<Boolean> correctA = new ArrayList<Boolean>();
	
	@Override
	public void setQuestion(String question) {
		this.question = "Er Trondheim verdens beste studentby?";
		
	}

	public String getQuestion(){
		return question;
	}
	
	@Override
	public void addOption(String option, boolean correct) {
		options.add(option);
		correctA.add(correct);
	}

}
