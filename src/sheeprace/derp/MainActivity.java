package sheeprace.derp;

import android.os.Bundle;
import android.app.Activity;
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
		
		game.pushState(new MainMenuView(this));
		setContentView(game);
	}
}
