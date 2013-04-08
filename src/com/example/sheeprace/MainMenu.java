package com.example.sheeprace;

import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.State;
import sheep.gui.TextButton;

public class MainMenu extends State{
	TextButton start;
	
	public MainMenu(MainActivity main){
		start = new TextButton(50, 50, "Start Game!");
	}

	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		start.draw(canvas);
	}
	
}
