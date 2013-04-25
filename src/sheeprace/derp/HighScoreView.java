package sheeprace.derp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;

/**
 * Keep a list of the high score of previous games.
 *
 */

public class HighScoreView extends State {
	private TextButton backButton;
//	private Image sheep, background;
	private Font font, headLine;
	private ArrayList<Player> bestFivePlayers;
	
	public HighScoreView(MainActivity main, boolean fromGame){
		backButton = new TextButton(50, 50, "Back",Constants.p);
		font = new Font(88, 88, 88, 16, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		headLine = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		headLine.setTextAlign(Align.CENTER);
		bestFivePlayers = new ArrayList<Player>();
		
		
	// New code	
		
		if(Constants.highscore==null){
			System.out.println("creating new highscorelist");
			Constants.highscore = new ArrayList<HighscorePlayer>();
		}
		System.out.println(Constants.highscore);
		if(fromGame)
			populateHighscore();
		System.out.println(Constants.highscore);
		
		Collections.sort(Constants.highscore);
		System.out.println(Constants.highscore);
	}
	
	private void populateHighscore(){
		ArrayList<Player> players = MyGame.getGameObject().getPlayers();
		for(Player p : players){
			System.out.println("Adding player "+p);
			HighscorePlayer hp = new HighscorePlayer(p.getName(), p.getScore());
			System.out.println("Highscore obj "+hp);
			for(int i=0;i<Constants.highscore.size();i++){
				if(hp.getScore() >= Constants.highscore.get(i).getScore()){
					Constants.highscore.add(i,hp);
					if(Constants.highscore.size()>5)
						Constants.highscore.remove(Constants.highscore.size()-1);
					break;
				}	
			}
			if(Constants.highscore.size()<=5 && !Constants.highscore.contains(hp))
				Constants.highscore.add(hp);
		}
	}
	
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		backButton.draw(canvas);
		Constants.frontSheep.draw(canvas, 0, 100);
		int screenCord = 150;
		canvas.drawText("Highscore", canvas.getWidth()/2, 100, headLine);
		for (int i = 0; i < Constants.highscore.size(); i++) {
			canvas.drawText(Constants.highscore.get(i).getName() + "  " + Constants.highscore.get(i).getScore(), Constants.WINDOW_WIDTH/2, screenCord, font);
			screenCord += 50;
		}
	}
	

	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		return true;
	}

}
