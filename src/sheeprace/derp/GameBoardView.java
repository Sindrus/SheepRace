package sheeprace.derp;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	private EndBox eb;
	
	private Sprite ground;
	
	private TextButton backButton;
	
	private float mapSpeed;
	
	private boolean isBlocked;
	
	public GameBoardView(MainActivity main){
		this.main = main;
		
		this.player = MyGame.getGameObject().getPlayer();
		this.player.resetPowerbarPower();
		this.playerGfx = player.getGfx();
		
		this.mapSpeed = 60;
		
		this.isBlocked = false;
		
		this.level = MyGame.getGameObject().getLevel();
		this.bb = level.getBlockBoxes();
		this.qb = level.getQuestionBoxes();
		this.eb = level.getEndBox();
		
		for (BlockBox b : bb){
			b.setSpeed(-mapSpeed, 0);
			b.update(0);
		}
		for (QuestionBox q : qb){
			q.setSpeed(-mapSpeed, 0);
			q.update(0);
		}
		eb.setSpeed(-mapSpeed, 0);
		eb.update(0);
		
	// TODO: Remove most of these buttons
//		backButton = new TextButton(50, 50, "Back");
		
		playerGfx.setPosition(Constants.WINDOW_WIDTH/2-20, Constants.WINDOW_HEIGHT*3/4 - Constants.blueSheep.getHeight()/2);
		
		ground = new Sprite();
		ground.setShape(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT/4);
		ground.setPosition(0, Constants.WINDOW_HEIGHT*3/4);
		
		ground.update(0);
		
		MyGame.getGameObject().setStartTime(System.currentTimeMillis());
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(Color.YELLOW);
		canvas.drawBitmap(Constants.background_new, 0, 0, null);
//		backButton.draw(canvas);
		
		int powerbarvalue = (player.getPowerbarPower()/10);
		
		Paint p = new Paint();
	// FIXME: At some point the color cuts into some other color, this is a !feature.	
		if(powerbarvalue>100)
			p.setColor(Color.GREEN);
		else if(powerbarvalue>50)
			p.setColor(Color.rgb((255-(powerbarvalue*5)), 255, 0));
		else if(powerbarvalue>0)
			p.setColor(Color.rgb(255, (5+(powerbarvalue*5)), 0));
		else
			p.setColor(Color.BLACK);
		if(powerbarvalue>0)
			canvas.drawRect(50, 50, powerbarvalue*5+50, 60, p);
		else
			canvas.drawRect(50, 50, 55, 60, p);
		
		eb.draw(canvas);
		for (BlockBox b : bb) b.draw(canvas);
		for (QuestionBox q : qb) q.draw(canvas);
		
		playerGfx.draw(canvas);
		
	//	questionView.draw(canvas);
	}
	
	public boolean onTouchDown(MotionEvent event){
//		if(backButton.onTouchDown(event)){
//			getGame().popState();
//		}
		{
			playerGfx.jump();
		}
		
		return true;
	}
	
	public void update(float dt) {
		
		player.decreasePowerbarPower();
		mapSpeed=40+(player.getPowerbarPower()/10);
		
		if(playerGfx.collides(eb))
			getGame().pushState(new GameStatusView(main));
		
		if (playerGfx.collides(ground))
			playerGfx.stop();
	// Updating blockbox	
		for(BlockBox bBox : bb){
			if(bBox.collides(playerGfx)){
				if(playerGfx.getY()<bBox.getY()){
					isBlocked=false;
					playerGfx.stop();
				}else if(playerGfx.getX()<bBox.getX()){
					isBlocked=true;
				}else if(playerGfx.getY()>bBox.getY()){
					playerGfx.topBlocked();
					playerGfx.setPosition(playerGfx.getX(), playerGfx.getY()+2);
					isBlocked=false;
				}
			}
			
			if(isBlocked)
				bBox.setSpeed(0, 0);
			else
				bBox.setSpeed(-mapSpeed, 0);
			
			bBox.update(dt);
		}
	// Updating Questionbox	
		for(QuestionBox qBox : qb){
			if(qBox.collides(playerGfx)){
				qBox.setPosition(-200, -200);
				qBox.update(dt);
				System.out.println("before question" +playerGfx.getPosition() );
				getGame().pushState(new QuestionView(main));
			}
			if(isBlocked)
				qBox.setSpeed(0, 0);
			else
				qBox.setSpeed(-mapSpeed, 0);
			
			qBox.update(dt);
		}
	// Updating endbox
		if(isBlocked)
			eb.setSpeed(0, 0);
		else
			eb.setSpeed(-mapSpeed, 0);
		
		player.update(dt);
		eb.update(dt);
		playerGfx.update(dt);
		ground.update(dt);
		super.update(dt);
	}
}
