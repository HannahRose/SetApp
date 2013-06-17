package com.example.setapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	/** Flag indicating whether or not there is already a game in progress. */
	private boolean gameInProgress = false;
	
	/** The integer value to indicate a visible button. */
	private final int VISIBLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	
    	
    	//Try to make a cool background FIXME
//    	Deck deck = new Deck();
//    	Bitmap bkgdBitmap;
//    	CardView cv = new CardView(getBaseContext());
//    	for (int i = 0; i < deck.size(); i++) {
//    		cv.setCard(deck.getTopCard());
//    		bkgdBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//    	}
    	
    	setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	@Override
	public void onSaveInstanceState(Bundle state) {
		super.onSaveInstanceState(state);
		state.putBoolean("gameInProgress", gameInProgress);

	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedState) {
		
		gameInProgress = savedState.getBoolean("gameInProgress");
		
    	System.out.println("In progress: " + gameInProgress);
    	
    	// Change the text on the main button depending on whether
    	// or not a game is in progress already.
    	if (gameInProgress) {
    		Button resume = (Button) findViewById(R.id.resumebutton);
    		resume.setVisibility(VISIBLE);
    	}
    	
    	super.onRestoreInstanceState(savedState);
	}
    
    /** Switch to a new activity to start a game. 
     * 
     * @param view The current view.
     */
    public void playGame(View view) {
    	Intent intent = new Intent(this, PlayGameActivity.class);
    	gameInProgress = true;
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
    
    // FIXME Remember to reset buttons when game is over!
    
}
