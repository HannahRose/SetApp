package com.example.setapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
		
		TextView setsFound = (TextView) gameActivity.findViewById(com.example.setapp.R.id.numSets);
		
		assertEquals(VISIBLE, setsFound.getVisibility());
		assertEquals((CharSequence) "0", setsFound.getText());
		
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
