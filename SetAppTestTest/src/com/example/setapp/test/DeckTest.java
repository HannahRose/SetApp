/**
 * 
 */
package com.example.setapp.test;

import java.util.EmptyStackException;

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

	public DeckTest() {
		super();
	}
	
	public void testGetTopCard() {
		
		int size = myDeck.size();
		
		Card c;
		// Pop all the cards off the deck.
		while(size > 0) {
			
//			assertEquals("Deck size is not correct.", size, myDeck.size());
			c = myDeck.getTopCard();
			
			assertNotNull("Non-empty deck returned a null card.", c);
			size--;
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

	public void testReturnCard() {
		
//		Debug.startMethodTracing("deck_test");
		
		myDeck = new Deck();
		int size = myDeck.size();
		
		Card c = myDeck.getTopCard();
		
		// Test that the card is really not in the deck and that the deck is smaller.
		assertFalse(myDeck.contains(c));
		assertEquals("The deck size did not decrease after removing a card!", size-1, myDeck.size());
		
		// Shuffle the card back into the deck. 
		myDeck.returnCard(c);
		
		// Check that the card is in the deck again
		assertTrue(myDeck.contains(c));
		
		// Check that the deck size is correct (the new card didn't replace an old one).
		assertEquals("The deck size is different from the original.", size, myDeck.size());
		
//		Debug.stopMethodTracing();
	}

}