package sheeprace.derp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.Paint;
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
     * Speed, acceleration and shit
     */
    
    public static int SPEED = 200;
    public static int ACCELERATION = 150;
    
    
    /*
     * Images
     */
    public static Image background = new Image(R.drawable.background);
    public static Bitmap background_new = null;
    public static Bitmap frontSheep_bitmap = null;
    public static Image frontSheep = new Image(R.drawable.frontsheep); 
    public static Image sheep1 = new Image(R.drawable.sau_white); 
    public static Image redSheep = new Image(R.drawable.sau_red);
    public static Image blueSheep = new Image(R.drawable.sau_blue);
    public static Image greenSheep = new Image(R.drawable.sau_green);
    public static Image redBox = new Image(R.drawable.red);
    public static Image blueBox = new Image(R.drawable.blue);
    public static Image greenBox = new Image(R.drawable.green);
    public static Image whiteBox = new Image(R.drawable.white);
    public static Image questionsBox = new Image(R.drawable.question_box);
    public static Image blockBox = new Image(R.drawable.empty_box);
    public static Image endBox = new Image(R.drawable.finished);
    //TODO, add a working list, and populate from the mainActivity or something
    
    /**
     * String is the name of the category and Integer is the number of questions in the category
     */
    public static Map<String, Integer> categories;
    
    /**
     * Integer that represent the number of levels available
     */
    public static int maxLevels;
    
    /**
     * Highscorelist
     */
    public static List<HighscorePlayer> highscore;
    
    /**
     * For setting the size of the textbuttons
     */
    public static float fontsize = 60;
    public static Paint[] p;
    public static Paint[] l;
    
}




