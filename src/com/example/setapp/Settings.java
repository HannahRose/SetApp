package com.example.setapp;

import java.util.Vector;

import android.content.Context;

public class Settings {

//		private static final String TAG = "Global Settings";
	    static Settings mySettings = new Settings();
	    Context context;
	    
	    protected int		numCards;
	    protected Deck	deck;
	    protected Vector<CardView> dealt;
	    protected Vector<CardView> selected;
	    protected boolean gameInProgress;
	    
	    public Settings() {
	    	gameInProgress = false;
	    	numCards = 12;
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
}

