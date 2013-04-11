package sheeprace.derp;

/**
 * Interface for controlling the questionobject
 *
 */

public interface QuestionInterface {
	/**
	 * Sets the question.
	 * @param question to be asked
	 */
	public void setQuestion(String question);
	
	/**
	 * Add options to the question
	 * @param opiton The text for the option
	 * @param correct is this option a correct answer.
	 */
	public void addOption(String opiton, boolean correct);
}
