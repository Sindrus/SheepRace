package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.State;

/**
 * A view that displays the question and what answers are available.
 *
 */

public class QuestionView extends State{
	private String question;
	private MainActivity main;
	
	public QuestionView(MainActivity main){
		this.main = main;
		QuestionMaker q = new QuestionMaker(main);
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.RED);
	}
	
	public String getQuestion(){
		return this.question;
	}
	
	public void viewQuestion(){
		System.out.println(question);
		//System.out.println(addOption("True", true));
	}
}
