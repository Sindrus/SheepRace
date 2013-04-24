package sheeprace.derp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.game.State;



public class ChooseCategoryView extends State{
	private TextButton backButton, startGame;
	private MainActivity main;
	private Font font;
	private ArrayList<TextButton> choices;
	private int index1, index2;
	private Level l;
	
	public ChooseCategoryView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		startGame = new TextButton(3*(Constants.WINDOW_WIDTH/4), 50, "Start Game");
		this.main = main;
		
		font = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		
		choices = new ArrayList<TextButton>();
		int leftCounter = 2;
		int rightCounter = 2;
		Set<Entry<String, Integer>> set = Constants.categories.entrySet();
		Iterator<Entry<String, Integer>> it = set.iterator();
	    while (it.hasNext()) {
	      Map.Entry entry = (Map.Entry) it.next();
	      if(leftCounter >= Constants.categories.size()/3){
	    	  TextButton bn = new TextButton(3*Constants.WINDOW_WIDTH/5, rightCounter * Constants.WINDOW_HEIGHT/(Constants.categories.size()+2), "" +  entry.getKey());
	 	      choices.add(bn);
	 	      rightCounter++;
	      }
	      else{
		      TextButton bn = new TextButton(2*Constants.WINDOW_WIDTH/5, leftCounter * Constants.WINDOW_HEIGHT/(Constants.categories.size()+2), "" +  entry.getKey());
		      choices.add(bn);
		      leftCounter++;
	      }
	    }
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		backButton.draw(canvas);
		startGame.draw(canvas);
		canvas.drawText("Choose Categories:", Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/6, font);
		for (int i = 0; i < choices.size(); i++) {
			choices.get(i).draw(canvas);
		}
	}
	
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		else if(startGame.onTouchDown(event)){
			getGame().pushState(new GameBoardView(main, index1, index2, l));
		}
		for (int i = 0; i < choices.size(); i++) {
			if(choices.get(i).onTouchDown(event)){
				MyGame.getGameObject().addChosenCategory(choices.get(i).getLabel());
			}
		}
		return true;
	}
}