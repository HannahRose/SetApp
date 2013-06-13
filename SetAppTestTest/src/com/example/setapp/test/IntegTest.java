package com.example.setapp.test;

//import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class IntegTest extends UiAutomatorTestCase {

	public IntegTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testInitialization() {
		
		// Simulate a press on the HOME button
		getUiDevice().pressHome();
		
		// Navigate to the apps screen
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
