package sheeprace.derp;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

public class QuestionView extends State {
	private String question;
	private MainActivity main;
	private Font font;
	private Question q;
	private List<TextButton> possibleAnswers;
	private boolean correct;

	public QuestionView(MainActivity main) {
		q = MyGame.getGameObject().getNextQuestion();

		font = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		this.main = main;
		int fontInt = 150;
		possibleAnswers = new ArrayList<TextButton>();
		for (int i = 0; i < q.getOptions().size(); i++) {
			TextButton t = new TextButton(3*Constants.WINDOW_WIDTH / 7, fontInt,
					q.getOptions().get(i),Constants.p);
			possibleAnswers.add(t);
			fontInt += 50;
		}
		this.correct = false;

	}

	public void draw(Canvas canvas) {

		canvas.drawColor(Color.RED);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		canvas.drawText("" + q.getQuestion(), Constants.WINDOW_WIDTH / 2, 100,
				font);
		for (int i = 0; i < possibleAnswers.size(); i++) {
			possibleAnswers.get(i).draw(canvas);
		}
	}

	public String getQuestion() {
		return this.question;
	}

	public void viewQuestion() {
		System.out.println(question);
	}

	public boolean onTouchDown(MotionEvent evt) {
		for (int i = 0; i < possibleAnswers.size(); i++) {
			if (possibleAnswers.get(i).onTouchDown(evt)) {

				correct = q.getCorrectA().get(i);

				if (correct) {

					if (MyGame.getGameObject().isPlayer1sTurn())
						MyGame.getGameObject().p1IsCorrect();
					else
						MyGame.getGameObject().p2sCorrect();

				}

				MyGame.getGameObject().getPlayer().increasePowerbarPower(200);
				getGame().pushState(new Splash(main, correct));

				System.out.println("Player 1 score: "
						+ MyGame.getGameObject().getp1sCorrect());
				System.out.println("Player 2 score: "
						+ MyGame.getGameObject().getp2sCorrect());
			}

		}

		return true;
	}

	// Making a seperate class for the splash upon answering a question
	class Splash extends State {
		MainActivity main2;
		boolean correct;
		private long entryTime;

		public Splash(MainActivity main, boolean correct) {
			entryTime=System.currentTimeMillis();
			this.main2 = main;
			this.correct = correct;
		}

		public void draw(Canvas canvas) {
			if (correct){
				canvas.drawColor(Color.GREEN);
				canvas.drawText("T", Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2, new Font(255, 255, 255, 100, Typeface.SERIF, Typeface.BOLD));
			}
			else{
				canvas.drawColor(Color.RED);
				canvas.drawText("F", Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2, new Font(255, 255, 255, 100, Typeface.SERIF, Typeface.BOLD));
			}
		}
		@Override
		public void update(float dt){
			if((System.currentTimeMillis()-entryTime)>500)
				getGame().popState(2);
		}
	}
}
