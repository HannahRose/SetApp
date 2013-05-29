/**
 * 
 */
package com.example.setapp.test;

import com.example.setapp.Deck;

import junit.framework.TestCase;

/**
 * @author Hannah Rose
 *
 */
public class DeckTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public DeckTest() { // Why is this needed?
		super();
	}
	
	public void testDeck() {
		
		try {
			Deck myDeck = new Deck();
			
			assertNotNull("The Deck object is null.", myDeck);
			assertEquals("The deck has an incorrect number of" +
					"		cards.", 81, myDeck.size());
		}
		catch (Exception e) {
			fail("Constructor threw an exception.");
		}
	}

}
