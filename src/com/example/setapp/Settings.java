package com.example.setapp;

import java.util.Vector;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class Settings implements OnSharedPreferenceChangeListener {

//		private static final String TAG = "Global Settings";
	    static Settings mySettings = new Settings();
	    protected SharedPreferences prefs;
	    Context context;
	    
	    public int		numCards;
	    protected Deck	deck;
	    protected Vector<CardView> dealt;
	    protected Vector<CardView> selected;
	    
	    public Settings() {
	    	
	    }

	    public Settings instance() {
	    	return mySettings;
	    }
	    
	    public void initModel(Context app)
	    {
	           context = app;
	           
	           prefs = PreferenceManager.getDefaultSharedPreferences(app);
	           prefs.registerOnSharedPreferenceChangeListener(this);
	    }
	    
	    
		/* Default preferences. */
		public void initPrefs() {
			//create editor
		}
		
		
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String key) {

		}
	    
}
