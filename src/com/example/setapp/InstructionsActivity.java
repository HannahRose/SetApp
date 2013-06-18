package com.example.setapp;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class InstructionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);
		// Show the Up button in the action bar.
		setupActionBar();
		
		setText();
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

	private void setText() {
		TextView text = (TextView) findViewById(R.id.instructA);
		
		String instruct = "A set is a group of three cards which are either all the same or all " +
				"different in each individual characteristic. The four characteristics are number " +
				"(one, two, or three), color (red, green, or purple), shape (diamond, squiggle, or " +
				"oval), and fill (solid, lined, or open). In order to form a set, three cards must " +
				"all have the same number, or have three different numbers, all the same color, or " +
				"three different colors, etc.";
		
		text.setText((CharSequence) instruct);
	}
	
	private void setImages() {
		
		CardView a = (CardView) findViewById(R.id.setCardA);
		CardView b = (CardView) findViewById(R.id.setCardB);
		CardView c = (CardView) findViewById(R.id.setCardC);
		
		a.setCard(new Card(1, Color.RED, Shape.DIAMOND, Fill.SOLID));
		b.setCard(new Card(2, Color.RED, Shape.DIAMOND, Fill.LINED));
		c.setCard(new Card(3, Color.RED, Shape.DIAMOND, Fill.OPEN));
	}
}
