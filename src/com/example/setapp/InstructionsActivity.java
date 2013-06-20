package com.example.setapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

public class InstructionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);
		// Show the Up button in the action bar.
		setupActionBar();

		setImages();
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
		getMenuInflater().inflate(R.menu.instructions, menu);
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
	
	private void setImages() {
		
		// Example of a valid set
		CardView c = (CardView) findViewById(R.id.setCardA);
		c.setCard(new Card(1, Color.RED, Shape.DIAMOND, Fill.SOLID));
		
		c = (CardView) findViewById(R.id.setCardB);
		c.setCard(new Card(2, Color.RED, Shape.DIAMOND, Fill.LINED));
		
		c = (CardView) findViewById(R.id.setCardC);
		c.setCard(new Card(3, Color.RED, Shape.DIAMOND, Fill.OPEN));
		
		// Example of an invalid set
		c = (CardView) findViewById(R.id.notsetCardA);
		c.setCard(new Card(2, Color.GREEN, Shape.DIAMOND, Fill.LINED));
		
		c = (CardView) findViewById(R.id.notsetCardB);
		c.setCard(new Card(2, Color.PURPLE, Shape.SQUIGGLE, Fill.OPEN));

		c = (CardView) findViewById(R.id.notsetCardC);
		c.setCard(new Card(2, Color.RED, Shape.DIAMOND, Fill.SOLID));

	}
	
	/* Must get button to be more visible on the screen
	public void playGame(View v) {
		Settings settings = new Settings().instance();
    	settings.gameInProgress = false;

    	int h = v.getMeasuredHeight();
    	System.err.println("Height is: " + h);
    	Intent intent = new Intent(this, PlayGameActivity.class);
    	startActivity(intent);
	}
	*/

}

