package com.example.setapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.*;

import com.example.setapp.MainActivity;

public class MainActTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
//	private final String ACTION_MAIN = "android.intent.action.MAIN";
	private MainActivity mainActivity;
	private RelativeLayout layout;
	
	private final int CHILD_COUNT = 3;
	
	/** The integer value to indicate a visible button. */
	private final int VISIBLE = 0;
	
	/** The integer value to indicate an invisible button. */
	private final int INVISIBLE = 4;
	
	/** The integer value to indicate a hidden button.
	private final int GONE = 8;  */
	
	public MainActTest() {
		super(MainActivity.class);
	}
	
	@Override
	public void setUp() throws Exception {
		
		super.setUp();
		mainActivity = getActivity();
		
		layout = (RelativeLayout) 
					mainActivity.findViewById(com.example.setapp.R.id.mainLayout);
		
	}
	
	/** Basic preconditions required for other tests. */
	public void testPreconditions() throws NullPointerException {
		assertEquals("Wrong number of children in main layout.", layout.getChildCount(), CHILD_COUNT);
	}
	
	/** Check the text and visibility of all the buttons in the main activity. */
	public void testButtons() {
	
		testButtonHelper(mainActivity.findViewById(com.example.setapp.R.id.newgamebutton), "New Game", VISIBLE);
		testButtonHelper(mainActivity.findViewById(com.example.setapp.R.id.resumebutton), "Resume Game", INVISIBLE);
		testButtonHelper(mainActivity.findViewById(com.example.setapp.R.id.optionsButton), "Settings", VISIBLE);
	}
	
	/** A helper function to test the text and visibility of a button. */
	private void testButtonHelper(View v, String expectedText, int visibility) {
		
		Button b = (Button) v;
		CharSequence text = b.getText();
		assertEquals("Text does not match.", (CharSequence) expectedText, text);
		assertEquals("Visibility is wrong.", b.getVisibility(), visibility);
	}
	
	/** Test stopping and starting the main activity to see if the resume game button appears. */
	public void testRestartActivity() {
		mainActivity.finish();
		
		//
	}
	
}
