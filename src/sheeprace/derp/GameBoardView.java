package sheeprace.derp;

import java.util.ArrayList;
import java.util.List;

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
	private Player player;
	private PlayerGfx playerGfx;
	private Level level;
	
	private List<QuestionBox> qb;
	private List<BlockBox> bb;
	
	private Sprite ground;
	
	private TextButton backButton, testStatus, finalStatus, questionView, gameStatusView;
	
	public GameBoardView(MainActivity main){//, int index1, int index2, Level level){
		
		this.player = MyGame.getGameObject().getPlayer();
		this.playerGfx = player.getGfx();
		
		this.level = MyGame.getGameObject().getLevel();

		this.bb = level.getBlockBoxes();
		this.qb = level.getQuestionBoxes();
		
		System.out.println("Block "+bb.get(0).getPosition());
		System.out.println("Quest "+qb.get(0).getPosition());
		
	//	System.out.println(level.getBlockBoxes());
		
	// TODO: Remove most of these buttons	
		questionView = new TextButton(Constants.WINDOW_WIDTH/2, 100, "Questions");
		gameStatusView = new TextButton(Constants.WINDOW_WIDTH/2, 150, "Status");
		backButton = new TextButton(50, 50, "Back");
//		testStatus = new TextButton(50,150,"testme");
//		finalStatus = new TextButton(50,200,"final testview");
		
		this.main = main;
		
		playerGfx.setPosition(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT*3/4 - Constants.blueSheep.getHeight()/2);
		
		ground = new Sprite();
		ground.setShape(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT/4);
		ground.setPosition(0, Constants.WINDOW_HEIGHT*3/4);
		
		ground.update(0);
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.YELLOW);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
//		testStatus.draw(canvas);
//		finalStatus.draw(canvas);
		backButton.draw(canvas);
		
		
		for (BlockBox b : bb) b.draw(canvas);
		//bb.get(0).draw(canvas);
		for (QuestionBox q : qb) q.draw(canvas);
		//qb.get(0).draw(canvas);
			
			
			
		playerGfx.draw(canvas);
		
		questionView.draw(canvas);
		gameStatusView.draw(canvas);
	}
	
	public boolean onTouchDown(MotionEvent event){
		if(backButton.onTouchDown(event)){
			getGame().popState();
		}
		else if(gameStatusView.onTouchDown(event)){
			getGame().pushState(new GameStatusView(main)); //, player1, player2));
		}
		else if(questionView.onTouchDown(event)){
			getGame().pushState(new QuestionView(main));
		}
		else {
			playerGfx.jump();
		}
		
		return true;
	}
	
	public void update(float dt) {
		
		if (playerGfx.collides(ground)) {
			
			playerGfx.setSpeed(0, 0);
			playerGfx.setAcceleration(0, 0);
			playerGfx.setPosition(playerGfx.getX(), Constants.WINDOW_HEIGHT*3/4 - Constants.blueSheep.getHeight()/2);
			playerGfx.update(dt);
		}

		playerGfx.update(dt);
		ground.update(dt);
		super.update(dt);
	}
}
