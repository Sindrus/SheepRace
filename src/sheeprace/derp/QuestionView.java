package sheeprace.derp;

import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.State;
import sheep.gui.TextButton;

/**
 * A view that displays the question and what answers are available.
 *
 */
public class QuestionView extends State{
	private String question;
	private MainActivity main;
	private String qString;
	private TextButton qText;
	
	public QuestionView(MainActivity main){
		this.main = main;
		magic();
		qText = new TextButton(50, 50, qString);
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.RED);
		qText.draw(canvas);
		
	}
	
	public String getQuestion(){
		return this.question;
	}
	
	public void viewQuestion(){
		System.out.println(question);
		//System.out.println(addOption("True", true));
	}
	
	
	/*
	 * method for testing
	 */
	public void magic(){
		XmlResourceParser r = main.getResources().getXml(R.xml.question);
		qString = r.getText();
	}
	
	
}
