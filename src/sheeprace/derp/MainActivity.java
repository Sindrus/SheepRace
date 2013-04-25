package sheeprace.derp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import sheep.game.Game;
import sheep.graphics.Font;
//import sheep.graphics.Font;
// Linje 2500 for min del, \o/
/**
 * This is the class which initializes everything
 * 
 *
 */

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	//remove titlebar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	//remove statusbar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Game game = new Game(this, null);
		
	// Initializing font
		Constants.p = new Paint[]{ new Font(255, 255, 255, 40.0f, 
				Typeface.SANS_SERIF, Typeface.NORMAL),  new Font(255, 255, 255, 40.0f, 
						Typeface.SANS_SERIF, Typeface.NORMAL)};
		
	// Need to get the q-categories	
		QuestionMaker.getCategories(this);
		
		game.pushState(new MainMenuView(this));
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(game);
		
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Constants.WINDOW_HEIGHT = dm.heightPixels;
        Constants.WINDOW_WIDTH = dm.widthPixels;
        
        Constants.background_new = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.background), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, true);
        Constants.frontSheep_bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.frontsheep),200,200,true);
        
    // Creating the gameobject and tell it about this game
        MyGame mg = MyGame.getGameObject();
        mg.setMain(this);
	}
	
}
