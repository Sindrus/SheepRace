package sheeprace.derp;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;

/**
 * A view that displays the question and what answers are available.
 *
 */

public class QuestionView extends State{
	private String question;
	private MainActivity main;
	private Font font;
	private Question q;
	private List<TextButton> jo;
	
	public QuestionView(MainActivity main){
	//	q = QuestionMaker.createQuestion(main,2, "film");
		q = MyGame.getGameObject().getNextQuestion();
		
		font = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		this.main = main;
		int fontInt = 150;
		jo = new ArrayList<TextButton>();
		for (int i = 0; i < q.getOptions().size(); i++) {
			TextButton t = new TextButton(Constants.WINDOW_WIDTH/2, fontInt, q.getOptions().get(i));
			jo.add(t);
			fontInt += 50;
		}
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.RED);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		canvas.drawText("" + q.getQuestion() , Constants.WINDOW_WIDTH/2, 100, font);
		for (int i = 0; i < jo.size(); i++) {
			jo.get(i).draw(canvas);
		}
		
	}
	
	public String getQuestion(){
		return this.question;
	}
	
	public void viewQuestion(){
		System.out.println(question);
	}
	
	public boolean onTouchDown(MotionEvent evt){
		for (int i = 0; i < jo.size(); i++) {
			if(jo.get(i).onTouchDown(evt) && q.getCorrectA().get(i)){
//				System.out.println("rett?"+q.getCorrectA().get(i));
				if(MyGame.getGameObject().isPlayer1sTurn())
					MyGame.getGameObject().p1IsCorrect();
				else
					MyGame.getGameObject().p2sCorrect();
				
			}
		}
		System.out.println("Player 1 score: "+MyGame.getGameObject().getp1sCorrect());
		System.out.println("Player 2 scpre: "+MyGame.getGameObject().getp2sCorrect());
		getGame().popState();
		return true;
	}
}
