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
		
	private Vector<CardView> selected = new Vector<CardView>();
	
	//private numSets = 0;	// Zero sets found so far. 
	
	private Vector<CardView> buttons = new Vector<CardView>();

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
				 
		CardView c = (CardView) view;
		
		if (c.hasCard()) { //only select or unselect a button if it has a corresponding card
			if (c.isSelected()) {
				c.setSelected(false);
				selected.remove(c);
			}
			else {
				c.setSelected(true);
				selected.add(c);
			}
		}
		else {
			c.setSelected(false);
			selected.remove(c); 
			System.out.println("Button without card was selected.");
		}
		
		c.invalidate();
		System.out.println("cards selected: " + selected.size());
		
		if (selected.size() == 3) { // If there are three cards selected
			checkIfSet();
		}
	}
	
	private void addAllButtons() {
		
		buttons.add((CardView) findViewById(R.id.buttonA1));
		buttons.add((CardView) findViewById(R.id.buttonA2)); 
		buttons.add((CardView) findViewById(R.id.buttonA3)); 
		buttons.add((CardView) findViewById(R.id.buttonA4)); 
		buttons.add((CardView) findViewById(R.id.buttonB1)); 
		buttons.add((CardView) findViewById(R.id.buttonB2)); 
		buttons.add((CardView) findViewById(R.id.buttonB3));
		buttons.add((CardView) findViewById(R.id.buttonB4)); 
		buttons.add((CardView) findViewById(R.id.buttonC1)); 
		buttons.add((CardView) findViewById(R.id.buttonC2));
		buttons.add((CardView) findViewById(R.id.buttonC3));
		buttons.add((CardView) findViewById(R.id.buttonC4)); 
	}
	
	private void dealCards() {
		
		for (CardView c : buttons) {
			
			if (!c.hasCard()) {
				c.setCard(deck.getTopCard());
			}
		}
		
		// Does it automatically draw all cards??
	}
	
	/** Checks if the selected cards form a valid set. 
	 * 
	 * @return True if the three selected cards are a set, otherwise false. 
	 */
	private void checkIfSet() {
		
		boolean result = isSet();
		System.out.println("Is set: " + result);
		
		if (result) {
			//if a set, flash and redeal, update numsetsfound
			for (CardView cv : selected) {
				cv.setCard(deck.getTopCard());
				cv.invalidate();
				//numSets++;
			}

		}
		//else display "not a set!" message(?), unselect all
		else {
			for (CardView cv : selected) {
				cv.setSelected(false);
			}
		}
		selected.removeAllElements();
	}
	
	private boolean isSet() {
		
		Card a = selected.get(0).myCard;
		Card b = selected.get(1).myCard;
		Card c = selected.get(2).myCard;
		
		boolean validNum = checkNum(a, b, c);
		boolean validCol = checkColor(a, b, c);
		boolean validShape = checkShape(a, b, c);
		boolean validFill = checkFill(a, b, c);
		
		return (validNum && validCol && validShape && validFill); 
	}
	
	private boolean checkNum(Card a, Card b, Card c) {
		
		// All same
		if ((a.getNumber() == b.getNumber()) && (b.getNumber() == c.getNumber())) {
			return true;
		}
		
		// All different
		else if ((a.getNumber() != b.getNumber()) && (a.getNumber() != c.getNumber())
							&& (b.getNumber() != c.getNumber())) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkColor(Card a, Card b, Card c) {
			
			// All same
			if ((a.getColor() == b.getColor()) && (b.getColor() == c.getColor())) {
				return true;
			}
			
			// All different
			else if ((a.getColor() != b.getColor()) && (a.getColor() != c.getColor())
								&& (b.getColor() != c.getColor())) {
				return true;
			}
			
			return false;
		}
	
	private boolean checkShape(Card a, Card b, Card c) {
		
		// All same
		if ((a.getShape() == b.getShape()) && (b.getShape() == c.getShape())) {
			return true;
		}
		
		// All different
		else if ((a.getShape() != b.getShape()) && (a.getShape() != c.getShape())
							&& (b.getShape() != c.getShape())) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkFill(Card a, Card b, Card c) {
			
			// All same
			if ((a.getFill() == b.getFill()) && (b.getFill() == c.getFill())) {
				return true;
			}
			
			// All different
			else if ((a.getFill() != b.getFill()) && (a.getFill() != c.getFill())
								&& (b.getFill() != c.getFill())) {
				return true;
			}
			
			return false;
		}

}
