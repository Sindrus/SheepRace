package sheeprace.derp;

import java.util.ArrayList;
import java.util.Collection;

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
	private Image background, sheep;
	private Font font, headLine;
	private ArrayList<Player> bestFivePlayers;
	
	public HighScoreView(MainActivity main){
		backButton = new TextButton(50, 50, "Back");
		font = new Font(88, 88, 88, 16, Typeface.SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
		headLine = new Font(18, 62, 110, 30, Typeface.SERIF, Typeface.BOLD);
		headLine.setTextAlign(Align.CENTER);
		background = new Image(R.drawable.background);
		sheep = new Image(R.drawable.frontsheep);
		generatePlayers();
		bestFivePlayers = new ArrayList<Player>();
		getBestFivePlayers(Game.getGameObject().getPlayers());
		}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		background.draw(canvas, 0, 0);
		backButton.draw(canvas);
		sheep.draw(canvas, 0, 100);
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
			for (int i = 1; i < stuff.size(); i++) {
				if(stuff.get(i).getScore() >= score){
					score = stuff.get(i).getScore();
					player = i;
				}
			}
			bestFivePlayers.add(stuff.get(player));
			stuff.remove(player);
		}
	}
	
	public void generatePlayers(){
		Player p1 = new Player("p1", 200);
		Player p2 = new Player("p2", 150);
		Player p3 = new Player("p3", 170);
		Player p4 = new Player("p4", 345);
		Player p5 = new Player("p5", 230);
		Player p6 = new Player("p6", 123);
		Player p7 = new Player("p7", 211);
		Game.getGameObject().getPlayers().add(p1);
		Game.getGameObject().getPlayers().add(p2);
		Game.getGameObject().getPlayers().add(p3);
		Game.getGameObject().getPlayers().add(p4);
		Game.getGameObject().getPlayers().add(p5);
		Game.getGameObject().getPlayers().add(p6);
		Game.getGameObject().getPlayers().add(p7);
	}
	
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		return true;
	}

}
