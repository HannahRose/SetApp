package com.example.setapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;



public class PlayGameActivity extends Activity {
	
	private Deck deck;
	
	//private int selected = 0;	// The game always begins with zero cards selected.
	
	//private int totalCards = 12;
	
	//private numSets = 0;	// Zero sets found so far. 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		// Show the Up button in the action bar.
		setupActionBar();	
		
		deck = new Deck();
		dealCards();
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	public void selectCard(View view) {
		
	} */
	// Assumes that all elements in the Table Layout are CardButtons.
	private void dealCards() {
		
		TableLayout myLayout = (TableLayout) findViewById(R.id.tableLayout);
		
		for (int i = 0; i < myLayout.getChildCount(); i++) {
			CardButton c = (CardButton) myLayout.getChildAt(i);
			
			if (!c.hasCard()) {
				c.setCard(deck.getTopCard());
			}
		}
		
		// Does it automatically draw all cards??
	}

}
