package sheeprace.derp;


import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import sheep.graphics.Image;

/**
 * Constants of the game
 * 
 * All constants should be set in this file
 *
 */

public class Constants {
	/*
     * WINDOW SIZE
     */
	
	// Set in MainActivity
    public static int WINDOW_WIDTH = 0;
    public static int WINDOW_HEIGHT = 0;
    
    /*
     * Images
     */
    
    public static Image background = new Image(R.drawable.background);
    public static Bitmap background_new = null;
    public static Bitmap frontSheep_bitmap = null;
    public static Image frontSheep = new Image(R.drawable.frontsheep); 
    public static Image sheep1 = new Image(R.drawable.sau_big_1); 
      
    //TODO, add a working list, and populate from the mainActivity or something
    
    /*
     * All available categories
     */
    public static List<Category> categories = new ArrayList<Category>();
}
