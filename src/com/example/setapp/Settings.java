package com.example.setapp;

import java.util.Vector;
import android.content.Context;


public class Settings {

//		private static final String TAG = "Global Settings";
	    static Settings mySettings = new Settings();
	    Context context;
	    
	    protected int		numCards;
	    
		/** Keeps track of the number of sets found by the player. */
	    protected int 		numSetsFound;
	    
	    protected boolean 	gameInProgress;
	    protected Deck		deck;
	    protected Vector<CardView> dealt;
	    protected Vector<CardView> selected;
	    
	    /* Constructor sets some defaults. */
	    public Settings() {	
	    	gameInProgress = false;
	    	numCards = 12;
	    	numSetsFound = 0;
	    }

	    public Settings instance() {
	    	return mySettings;
	    }
	    
	    public boolean isGameInProgress() {
	    	return gameInProgress;
	    }

	    public Deck getDeck() {
	    	return deck;
	    }

	    public int getNumCards() {
	    	return numCards;
	    }
	    
	    public Vector<CardView> getDealt() {
	    	return dealt;
	    }
	    
	    public Vector<CardView> getSelected() {
	    	return selected;
	    }
	    
	    public void setDeck(Deck newDeck) {
	    	deck = newDeck;
	    }
	    
	    public void setDealt(Vector<CardView> cards) {
	    	dealt = cards;
	    }
	    
	    public void setSelected(Vector<CardView> select) {
	    	selected = select;
	    }
}

