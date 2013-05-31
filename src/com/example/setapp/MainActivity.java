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
	
	/** The integer value to indicate an invisible button. */
	private final int INVISIBLE = 4;
	
	/** The integer value to indicate a visible button. */
	private final int VISIBLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	// Change the text on the main button depending on whether
    	// or not a game is in progress already.
    	if (gameInProgress) {
    		Button play = (Button) findViewById(R.id.newgamebutton);
    		Button resume = (Button) findViewById(R.id.resumebutton);
    		
    		play.setVisibility(INVISIBLE);
    		resume.setVisibility(VISIBLE);
    	}
    	
    	setContentView(R.layout.activity_main);
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
    	Intent intent = new Intent(this, PlayGameActivity.class);
    	gameInProgress = true;
    	startActivity(intent);
    }
    
    // FIXME Remember to reset buttons when game is over!
    
}