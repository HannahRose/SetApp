/**
 * 
 */
package com.example.setapp.test;

import java.util.*;
import junit.framework.TestCase;

import com.example.setapp.Card;
import com.example.setapp.Deck;

/**
 * @author Hannah Rose
 *
 */
public class DeckTest extends TestCase {
	
	Deck myDeck;

	protected void setUp() throws Exception {
		super.setUp();
		
		try {
			myDeck = new Deck();

			assertNotNull("The Deck object is null.", myDeck);
			assertEquals("The deck has an incorrect number of" +
					"		cards.", myDeck.size(), 81);
		}
		catch (Exception e) {
			fail("Constructor threw an exception: " + e.getMessage());
		}
	}

	public DeckTest() { // Why is this needed?
		super();
	}

	public void testPreconditions() {

	}
	
	
	public void testGetTopCard() {
		
		myDeck = new Deck(); 
		int size = myDeck.size();
		assertEquals("The deck has an incorrect number of cards.", size, 81);
		
		Card c;
		// Pop all the cards off the deck.
		while(size > 0) {
			c = myDeck.getTopCard();
			size--;
			assertNotNull("Non-empty deck returned a null card.", c);
			assertEquals("Deck size is not correct.", size, myDeck.size());
		}
		
		assertEquals("Deck is not empty!", 0, myDeck.size());
		
		try {
			c = myDeck.getTopCard();
			
			assertNull("Empty deck did not return a null card. " + "Deck size: " + myDeck.size(), c);
		}
		catch (EmptyStackException e) {
			fail("Empty deck threw an exception: " + e.getMessage());
		}
	}

}