package com.example.setapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	/** The integer value to indicate a visible button. */
	private final int VISIBLE = 0;
	
	private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	settings = new Settings().instance();
    	
    	//Try to make a cool background FIXME
//    	Deck deck = new Deck();
//    	Bitmap bkgdBitmap;
//    	CardView cv = new CardView(getBaseContext());
//    	for (int i = 0; i < deck.size(); i++) {
//    		cv.setCard(deck.getTopCard());
//    		bkgdBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//    	}
    	
    	setContentView(R.layout.activity_main);
    	
    	
    	if (settings.isGameInProgress()) {
    		Button resume = (Button) findViewById(R.id.resumebutton);
    		resume.setVisibility(VISIBLE);
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    /** Switch to a new activity to start a game. 
     * 
     * @param view The current view.
     */
    public void playGame(View view) {
    	settings.gameInProgress = false;
    	Intent intent = new Intent(this, PlayGameActivity.class);
    	startActivity(intent);
    }
    
    public void resumeGame(View view) {
    	Intent intent = new Intent(this, PlayGameActivity.class);
    	startActivity(intent);
    }
   
    
    /** Switch to a new activity to change the game settings.
     * 
     * @param view The current view.
     */
    public void options(View view) {
    	Intent intent = new Intent(this, OptionsActivity.class);
    	startActivity(intent);
    }
    
    /** Switch to a new activity to read the instructions.
     * 
     * @param view The current view.
     */
    public void instructions(View view) {
    	Intent intent = new Intent(this, InstructionsActivity.class);
    	startActivity(intent);
    }
    
    // FIXME Remember to reset buttons when game is over!
    
}
