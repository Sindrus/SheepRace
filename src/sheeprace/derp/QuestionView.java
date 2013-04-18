package sheeprace.derp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import sheep.game.State;
import sheep.graphics.Font;

/**
 * A view that displays the question and what answers are available.
 *
 */

public class QuestionView extends State{
	private String question;
	private MainActivity main;
	private Font font;
	
	public QuestionView(MainActivity main){
		this.main = main;
		Question q = QuestionMaker.createQuestion(main,2, "test1");
		font = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.RED);
		canvas.drawText("" + question.length(), 100, 100, font);
	}
	
	public String getQuestion(){
		return this.question;
	}
	
	public void viewQuestion(){
		System.out.println(question);
		//System.out.println(addOption("True", true));
	}
}
