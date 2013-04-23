package sheeprace.derp;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.gui.TextButton;

/**
 * This class is responsible for drawing the gameboard on the screen. It is
 * also responsible for updating the screen as the player moves around on the
 * board.
 * 
 */

public class GameBoardView extends State {
	private MainActivity main;
	private Player player1,player2;
	private PlayerGfx gfx1,gfx2;
//	private Player dude = Game.getGameObject().getPlayers().get(0); //TODO, fix for the correct player based on whos turn it is
//	private PlayerGfx duden = dude.getGfx();
	private Level level;
	
	private Sprite ground;
	
	private TextButton backButton, testStatus, finalStatus, questionView, gameStatusView;
	
	public GameBoardView(MainActivity main, int index1, int index2, Level level){
		this.player1 = MyGame.getGameObject().getPlayers().get(index1);
		this.gfx1 = player1.getGfx();
		this.player2 = MyGame.getGameObject().getPlayers().get(index2);
		this.gfx2 = player2.getGfx();
		this.level = MyGame.getGameObject().getNextLevel(main);
		questionView = new TextButton(Constants.WINDOW_WIDTH/2, 100, "Questions");
		gameStatusView = new TextButton(Constants.WINDOW_WIDTH/2, 150, "Status");
		backButton = new TextButton(50, 50, "Back");
		testStatus = new TextButton(50,150,"testme");
		finalStatus = new TextButton(50,200,"final testview");
		this.main = main;
		
		gfx1.setPosition(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT*3/4);
		gfx1.update(0);
		gfx2.setPosition(100,100);
		gfx2.update(0);
//		duden.setPosition(100, 100);
//		duden.update(0);
		ground = new Sprite();
		ground.setShape(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT/4);
		ground.setPosition(0, Constants.WINDOW_HEIGHT*3/4);
		
		ground.update(0);
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.YELLOW);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
		testStatus.draw(canvas);
		finalStatus.draw(canvas);
		backButton.draw(canvas);
		ArrayList<BlockBox> bb = level.getBoxes();
//		for (BlockBox b : bb)
//			b.draw(canvas);
		gfx1.draw(canvas);
		//gfx2.draw(canvas);
//		duden.draw(canvas);
		
		questionView.draw(canvas);
		gameStatusView.draw(canvas);
	}
	
	public boolean onTouchDown(MotionEvent event){
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		else if(gameStatusView.onTouchDown(event)){
			getGame().pushState(new GameStatusView(main, player1, player2));
		}
		else if(questionView.onTouchDown(event)){
			getGame().pushState(new QuestionView(main));
		}
		else if(testStatus.onTouchDown(event)){
			getGame().pushState(new GameStatusView(main,player1));
		}
		else if(finalStatus.onTouchDown(event)){
			getGame().pushState(new GameStatusView(main,player1,player2));
		}
		else {
			gfx1.jump();
		}
		
		return true;
	}
	
	public void update(float dt) {
		if (gfx1.collides(ground)) {
			System.out.println("hva faen");
			gfx1.setSpeed(0, 0);
			gfx1.setAcceleration(0, 0);
		}
		
		gfx1.update(dt);
		ground.update(dt);
		super.update(dt);
	}
}
