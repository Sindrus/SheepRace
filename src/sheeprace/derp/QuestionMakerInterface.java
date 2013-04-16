package sheeprace.derp;

/**
 * Proper use of this interface should make it easy to change the filetype
 * that is loaded with questions.
 * The class implementing this interface must return a question.
 *
 */

public interface QuestionMakerInterface {
	/**
	 * Get the question from some inputfile and create a questionobject from it
	 * @return a question
	 */
	public Question createQuestion(MainActivity main);

}
