package sheeprace.derp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import sheep.game.Game;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	//remove titlebar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	//remove statusbar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Game game = new Game(this, null);
		
	// Need to get the q-categories	
		QuestionMaker.getCategories(this);
		
		for(Category s : Constants.categories)
			System.out.println(s.toString());
		
		
		game.pushState(new MainMenuView(this));
		setContentView(game);
		

		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Constants.WINDOW_HEIGHT = dm.heightPixels;
        Constants.WINDOW_WIDTH = dm.widthPixels;
        
        Constants.background_new = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.background), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, true);
        Constants.frontSheep_bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.frontsheep),200,200,true);
	}
	
}
