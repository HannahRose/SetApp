package com.example.setapp;

import java.util.Vector;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/** An activity to play the game! */
public class PlayGameActivity extends Activity {
	
	/** A shuffled stack of the cards. */
	private Deck deck;
		
	/** Keeps track of which cards are selected (max 3). */
	private Vector<CardView> selected = new Vector<CardView>();
	
	/** Keeps track of the number of sets found by the player. */
	private int numSets = 0;	// Zero sets found so far.
	
	/** Keeps track of all the visible cards (CardViews). */
	private Vector<CardView> buttons = new Vector<CardView>();
	
	/** A view to display the number of sets found so far. FIXME */
	private TextView displayNumSets;
	
	private FrameLayout notASet;
	
	private Settings settings;
	
	/** The integer value to indicate a visible button. */
	private int VISIBLE = 0;
	
	/** The integer value to indicate a hidden button. */
	private int GONE = 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
//		Debug.startMethodTracing("game");
		
		super.onCreate(savedInstanceState);
		
		settings = new Settings().instance();
		
		setContentView(R.layout.activity_play_game);
		// Show the Up button in the action bar.
		setupActionBar();	
		
		// Lock the orientation to landscape.
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		setVisibleRows();
		
		/* If we are restoring a saved game. */
		if (settings.isGameInProgress()) {
			onRestart();
			return;
		}
		
		settings.gameInProgress = true;
		
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/* Save state of the game */
	@Override
	protected void onStop() {
		super.onStop();
		
		settings.setDeck(deck);
		settings.setDealt(buttons);
		settings.setSelected(selected);	
	}
	
	/* Restore saved state */
	@Override
	protected void onRestart() {
		super.onRestart();
		
		deck = settings.getDeck();
		selected = settings.getSelected();
		numSets = settings.numSetsFound;
		
		Vector<CardView> dealt = settings.getDealt();
		
		//reset all the cards!
		for (int i = 0; i < dealt.size(); i++) {
			buttons.elementAt(i).setCard(dealt.elementAt(i).getCard());
		}
		
		dealCards();	//if more cards have been added
	}
	
	
	/** Selects or unselects a card if the button is pressed. 
	 * If the card is selected, it will become unselected, and visa versa. 
	 */
	public void selectCard(View view) {
				 
		CardView c = (CardView) view;
		
		if (c.isSelected()) {
			c.setSelected(false);
			selected.remove(c);
		}
		
		else if (c.hasCard()) { // Not selected and has card
			if (selected.size() > 2) { // A max of three cards can be selected at a time.
				for (CardView cv : selected) {	// Unselect all the other cards
					cv.setSelected(false);
					cv.invalidate();
				}
				selected.removeAllElements();
			}
			
			// Select the original card
			c.setSelected(true);
			selected.add(c);
		}
		c.invalidate();	// Redraw the card
	
		if (selected.size() == 3) { // If there are three cards selected
			checkIfSet();
		}
	}
	
	/** Sets the rows that should be visible or gone for the game and 
	 * adds all buttons that are currently visible to the relevant vector. */
	private void setVisibleRows() {
		
		int rows = settings.getNumCards()/3;	// Three cards per row
		
		TableLayout cardTable = (TableLayout) findViewById(R.id.cardTable);
		TableRow t;
		
		for (int i = 0; i < rows; i++) {
			t = (TableRow) cardTable.getChildAt(i);
			t.setVisibility(VISIBLE);
			
			for (int button = 0; button < t.getChildCount(); button++) {
				CardView c = (CardView) t.getChildAt(button);
				buttons.add(c);
			}
		}
		
		displayNumSets = (TextView) findViewById(R.id.numSets);
		notASet = (FrameLayout) findViewById(R.id.notASet);
		
		setDisplayNumSets();
	}
	
	
	/** Add a card to each CardView in the list of visible buttons. */
	private void dealCards() {
		
		for (CardView c : buttons) {
			if (!c.hasCard()) {
					c.setCard(deck.getTopCard());
			}
		}
	}
	
	/** Checks if the selected cards form a valid set. 
	 * 
	 * @return True if the three selected cards are a set, otherwise false. 
	 */
	private void checkIfSet() {
		
		Card a = selected.get(0).getCard();
		Card b = selected.get(1).getCard();
		Card c = selected.get(2).getCard();
		
		boolean result = isSet(a, b, c);
		
		//if a set, flash and redeal, update numsetsfound
		if (result) {
			
			for(CardView cv : selected) { 	
				cv.setCard(deck.getTopCard());
				cv.setSelected(false);
				cv.invalidate();	// invalidate the selected cards so they will be redrawn.
			}
			
			numSets++;
			setDisplayNumSets();
			selected.removeAllElements();
			
//			Debug.stopMethodTracing();
		}
		
		//else display "not a set!" message
		else {
			notASet.setVisibility(VISIBLE);
		}
	}
	
	public void dismissNotSet(View v) {
		notASet.setVisibility(GONE);
		
		for(CardView cv : selected) {
			cv.setSelected(false);
			cv.invalidate();	// invalidate the selected cards so they will be redrawn.
		}

		selected.removeAllElements();
	}
	
	private void setDisplayNumSets() {
		String thisMany = "Sets found: \n" + numSets;
		displayNumSets.setText((CharSequence) thisMany);
		
		/* // This isn't doing anything....
		int padding = 10;
		displayNumSets.setPadding(padding, padding, padding, padding);
		displayNumSets.setBackgroundColor(0xfff8ff);
		 */
	}
	
	public boolean isSet(Card a, Card b, Card c) {
		
//		Debug.startMethodTracing("is_set");
		
		boolean validNum = checkNum(a, b, c);
		boolean validCol = checkColor(a, b, c);
		boolean validShape = checkShape(a, b, c);
		boolean validFill = checkFill(a, b, c);
		
//		Debug.stopMethodTracing();
		
		//Implement more specific feedback for invalid sets? FIXME
		return (validNum && validCol && validShape && validFill); 
	}
	
	private boolean checkNum(Card a, Card b, Card c) {
		
		// All same
		if ((a.number == b.number) && (b.number == c.number)) {
			return true;
		}
		
		// All different
		else if ((a.number != b.number) && (a.number != c.number)
							&& (b.number != c.number)) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkColor(Card a, Card b, Card c) {
			
			// All same
			if ((a.color == b.color) && (b.color == c.color)) {
				return true;
			}
			
			// All different
			else if ((a.color != b.color) && (a.color != c.color)
								&& (b.color != c.color)) {
				return true;
			}
			
			return false;
		}
	
	private boolean checkShape(Card a, Card b, Card c) {
		
		// All same
		if ((a.shape == b.shape) && (b.shape == c.shape)) {
			return true;
		}
		
		// All different
		else if ((a.shape != b.shape) && (a.shape != c.shape)
							&& (b.shape != c.shape)) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkFill(Card a, Card b, Card c) {
			
			// All same
			if ((a.fill == b.fill) && (b.fill == c.fill)) {
				return true;
			}
			
			// All different
			else if ((a.fill != b.fill) && (a.fill != c.fill)
								&& (b.fill != c.fill)) {
				return true;
			}
			
			return false;
		}

}
