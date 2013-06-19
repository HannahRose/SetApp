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
		
		assertEquals("Sets found button is not visible.", VISIBLE, setsFound.getVisibility());
		assertEquals((CharSequence) "Sets found: \n0", setsFound.getText());
		
		int[] location = new int[2];
		
		setsFound.getLocationOnScreen(location);
		System.err.println("Location: (" + location[0] + ", " + location[1] + ")");
		System.err.println("Dimensions: " + setsFound.getHeight() + " by " + setsFound.getWidth());
		
		if (setsFound.getWidth() < 1) {
			fail("Width is zero.");
		}
		
		// Find a set and test again FIXME
		
	}
	
	/** Some limited tests to see if sets are checked properly.
	 * Checks a "normal" set, a set that is all different, and an invalid set.
	 */
	public void testSetCheck() {
		
		//Test all different
		Card a = new Card(1, Color.GREEN, Shape.DIAMOND, Fill.LINED);
		Card b = new Card(2, Color.PURPLE, Shape.OVAL, Fill.OPEN);
		Card c = new Card(3, Color.RED, Shape.SQUIGGLE, Fill.SOLID);
		
		boolean result = gameActivity.isSet(a, b, c); 
		
		assertEquals("Failed to identify a valid set.", true, result);

		Card d = new Card(2, Color.GREEN, Shape.DIAMOND, Fill.OPEN);
		
		result = gameActivity.isSet(b, c, d);
		assertEquals("Validated an incorrect set.", false, result);
		
		Card e = new Card(2, Color.GREEN, Shape.DIAMOND, Fill.LINED);
		Card f = new Card(2, Color.GREEN, Shape.DIAMOND, Fill.SOLID);
		
		result = gameActivity.isSet(d, e, f);
		assertEquals("Failed to identify a valid set.", true, result);

	}
	
	public void testRestoreGame() {
		
	}

	public void testNewGame() {
		
	}
}
