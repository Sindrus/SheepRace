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
 * Not critical for the game to work, should only be implemented if there is 
 * enough time before the deadline.
 *
 */

public class HighScoreView extends State {
	private TextButton backButton;
//	private Image sheep, background;
	private Font font, headLine;
	private ArrayList<Player> bestFivePlayers;
	
	public HighScoreView(MainActivity main, boolean fromGame){
		backButton = new TextButton(50, 50, "Back");
		font = new Font(88, 88, 88, 16, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		headLine = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		headLine.setTextAlign(Align.CENTER);
//		background = new Image(R.drawable.background);
//		sheep = new Image(R.drawable.frontsheep);
//		generatePlayers(); //Polutes the players created in the game itself
		bestFivePlayers = new ArrayList<Player>();
		getBestFivePlayers(MyGame.getGameObject().getPlayers());
		
		
		
	// New code	
		
		if(Constants.highscore==null)
			Constants.highscore = new ArrayList<HighscorePlayer>();
		
		if(fromGame)
			populateHighscore();
		
		Collections.sort(Constants.highscore);
		System.out.println(Constants.highscore);
	}
	
	private void populateHighscore(){
		ArrayList<Player> players = MyGame.getGameObject().getPlayers();
		for(Player p : players){
			HighscorePlayer hp = new HighscorePlayer(p.getName(), p.getScore());
			for(int i=0;i<Constants.highscore.size();i++){
				if(hp.getScore() >= Constants.highscore.get(i).getScore()){
					Constants.highscore.add(i,hp);
					if(Constants.highscore.size()>5)
						Constants.highscore.remove(Constants.highscore.size()-1);
				}	
			}
		}
	}
	
	
	
	
	
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		backButton.draw(canvas);
		Constants.frontSheep.draw(canvas, 0, 100);
		int screenCord = 150;
		canvas.drawText("Highscore", canvas.getWidth()/2, 100, headLine);
		for (int i = 0; i < bestFivePlayers.size(); i++) {
			canvas.drawText(bestFivePlayers.get(i).getName() + "  " + bestFivePlayers.get(i).getScore(), Constants.WINDOW_WIDTH/2, screenCord, font);
			screenCord += 50;
		}
	}
	
	public void getBestFivePlayers(ArrayList<Player> players){
		ArrayList<Player> stuff = new ArrayList<Player>();
		for (int i = 0; i < players.size(); i++) {
			stuff.add(players.get(i));
		}
		int score = 0;
		int player = -1;
		int size = 0;
		if(players.size()>5){
			size = 5;
		}
		else{
			size = players.size();
		}
		for(int j = 0; j < size; j++){
			score = 0;
			for (int i = 0; i < stuff.size(); i++) {
				if(stuff.get(i).getScore() >= score){
					score = stuff.get(i).getScore();
					player = i;
				}
			}
			bestFivePlayers.add(stuff.get(player));
			stuff.remove(player);
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
