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
import android.widget.ToggleButton;

public class OptionsActivity extends Activity {
	
	Vector<ToggleButton> numCardButtons = new Vector<ToggleButton>();
		
	private Settings settings;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		// Show the Up button in the action bar.
		setupActionBar();
		
		settings = new Settings().instance();
		
		numCardButtons.add( (ToggleButton) findViewById(R.id.nineCards));
		numCardButtons.add( (ToggleButton) findViewById(R.id.twelveCards));
		numCardButtons.add( (ToggleButton) findViewById(R.id.fifteenCards));
		
		// Calculate which button should be selected
		int select = (settings.numCards - 9) / 3;
		numCardButtons.elementAt(select).setChecked(true);
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
		getMenuInflater().inflate(R.menu.options, menu);
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
	
	public void setCardNum(View view) {
		
		if (view instanceof ToggleButton) {
			ToggleButton t = (ToggleButton) view;
			String num = (String) t.getText();
			settings.numCards = Integer.parseInt(num);
			
			// Ensures that the only checked button is the one corresponding
			// to the current number of cards (the last button clicked).
			//FIXME Make this more elegant.
			for (ToggleButton b : numCardButtons) {
				b.setChecked(false);
			}
			
			t.setChecked(true);

		}
		
		 
		
	}

}
