package com.example.setapp;

import java.util.Vector;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PlayGameActivity extends Activity {
	
	private Deck deck;
	
	private int selected = 0;	// The game always begins with zero cards selected.
	
	//private int totalCards = 12;
	
	//private numSets = 0;	// Zero sets found so far. 
	
	private Vector<CardButton> buttons = new Vector<CardButton>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		// Show the Up button in the action bar.
		setupActionBar();	
		
		deck = new Deck();
		addAllButtons();
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/** Selects or unselects a card if the button is pressed. */
	public void selectCard(View view) {
		 
		if (view instanceof CardButton) {
			CardButton c = (CardButton) view;
			
			if (c.hasCard()) { //only select a button if it has a corresponding card
				if (c.isSelected()) {
					c.setSelected(false);
					selected--;
				}
				else {
					c.setSelected(true);
					selected++;
				}
			}
			
			if (selected == 3) {
				//check if there is a set
				//if a set, flash and redeal, update numsetsfound
				//else display "not a set!" message, unselect all 
			}
		}
		else {
			System.out.println("View was not a CardButton!");
		}
	}
	
	private void addAllButtons() {
		
		buttons.add((CardButton) findViewById(R.id.buttonA1));
		buttons.add((CardButton) findViewById(R.id.buttonA2)); 
		buttons.add((CardButton) findViewById(R.id.buttonA3)); 
		buttons.add((CardButton) findViewById(R.id.buttonB1)); 
		buttons.add((CardButton) findViewById(R.id.buttonB2)); 
		buttons.add((CardButton) findViewById(R.id.buttonB3)); 
		buttons.add((CardButton) findViewById(R.id.buttonC1)); 
		buttons.add((CardButton) findViewById(R.id.buttonC2));
		buttons.add((CardButton) findViewById(R.id.buttonC3));
	}
	
	// Assumes that all elements in the Table Layout are CardButtons.
	private void dealCards() {
		
		for (CardButton c : buttons) {
			
			if (!c.hasCard()) {
				c.setCard(deck.getTopCard());
			}
		}
		
		// Does it automatically draw all cards??
	}

}
