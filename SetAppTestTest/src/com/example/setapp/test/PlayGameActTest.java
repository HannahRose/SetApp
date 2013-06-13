package com.example.setapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.setapp.Card;
import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;
import com.example.setapp.PlayGameActivity;

public class PlayGameActTest extends ActivityInstrumentationTestCase2<PlayGameActivity> {
	
	private PlayGameActivity gameActivity;
	private RelativeLayout layout;
	
	/** The integer value to indicate a visible button. */
	private final int VISIBLE = 0;
	
	/** The integer value to indicate a hidden button.
	private final int GONE = 8;  */
	
	public PlayGameActTest() {
		super(PlayGameActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		gameActivity = getActivity();
		
		layout = (RelativeLayout) 
				gameActivity.findViewById(com.example.setapp.R.id.gameLayout);
	}
	
	public void testPreconditions() {
		
		int leftPad = layout.getPaddingLeft();
		System.err.println("Left padding: " + leftPad);
		
		
		
	}
	
	public void testSetsFound() {
		
		TextView setsFound = (TextView) gameActivity.findViewById(com.example.setapp.R.id.numSets);
		
		assertEquals(VISIBLE, setsFound.getVisibility());
		assertEquals((CharSequence) "0", setsFound.getText());
		
		// Find a set and test again FIXME
		
	}
	
	public void testSetCheck() {
		
		//Test all different
		Card a = new Card(1, Color.GREEN, Shape.DIAMOND, Fill.LINED);
		Card b = new Card(2, Color.PURPLE, Shape.OVAL, Fill.OPEN);
		Card c = new Card(3, Color.RED, Shape.SQUIGGLE, Fill.SOLID);
		
		boolean result = gameActivity.isSet(a, b, c); 
		
		assertEquals("Failed to identify a valid set.", true, result);
		
		result = gameActivity.isSet(c, a, b); 
		assertEquals("Failed to identify a valid set.", true, result);
		
		Card d = new Card(2, Color.GREEN, Shape.DIAMOND, Fill.OPEN);
		
		result = gameActivity.isSet(b, c, d);
		assertEquals("Validated an incorrect set.", false, result);
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
