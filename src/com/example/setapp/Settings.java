package com.example.setapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class Settings implements OnSharedPreferenceChangeListener {

	    protected SharedPreferences prefs;

	    public Settings() {
	    	
	    }
	    
	    public void initModel(Context context)
	    {
	            prefs = PreferenceManager.getDefaultSharedPreferences(context);
	            prefs.registerOnSharedPreferenceChangeListener(this);
	    }
	    
	    
		/* Default preferences. */
		public void initPrefs() {
			//create editor
		}
		
		
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String key) {
			prefs = sharedPrefs;
		}
	    
}
