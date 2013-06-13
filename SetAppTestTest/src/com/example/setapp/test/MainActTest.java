package com.example.setapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

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
	
	public void testPreconditions() throws NullPointerException {
		assertEquals(layout.getChildCount(), CHILD_COUNT);
	}
	
	public void testButtons() throws NullPointerException {
	
		
		testButtonHelper(mainActivity.findViewById(com.example.setapp.R.id.newgamebutton), "New Game", VISIBLE);
		testButtonHelper(mainActivity.findViewById(com.example.setapp.R.id.resumebutton), "Resume Button", INVISIBLE);
		testButtonHelper(mainActivity.findViewById(com.example.setapp.R.id.optionsButton), "Settings", VISIBLE);

		
	}
	
	private void testButtonHelper(View v, String expectedText, int visibility) {
		
		Button b = (Button) v;
		CharSequence text = b.getText();
		assertEquals("Actual text is: " + text, text, (CharSequence) "New Game");
		assertEquals(b.getVisibility(), visibility);
	}
	
	public void testCreateGame() {
		
//		Context mContext = getInstrumentation().getContext();
//		
//    	Intent intent = new Intent(mainActivity, PlayGameActivity.class);
    	
		
    	
	}
	
	
	@Override 
	public void tearDown() throws Exception {
		super.tearDown();
		
	}
}
