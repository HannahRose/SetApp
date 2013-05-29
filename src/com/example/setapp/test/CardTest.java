/**
 * 
 */
package com.example.setapp.test;

import junit.framework.TestCase;

import com.example.setapp.Card;
import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

/**
 * @author Hannah Rose
 *
 */
public class CardTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public CardTest() {
		super();
	}
	
	//Test creating a valid card.
	public void testValidCard() {
		
		try {
			Card myCard = new Card(3, Color.RED, Shape.OVAL, Fill.SOLID);
			
			assertNotNull("The Card object is null.", myCard);
			assertEquals("The card has the wrong number.", 3, myCard.getNumber());
			assertEquals("The card is the wrong color.", Color.RED, myCard.getColor());
			assertEquals("The card has the wrong shapes.", Shape.OVAL, myCard.getShape());
			assertEquals("The card has the wrong fill.", Fill.SOLID, myCard.getFill());	
		}
		catch (Exception e) {
			fail("Constructor threw an exception.");
		}
	}
	
	public void testToString() {
		
		Card myCard = new Card(2, Color.GREEN, Shape.DIAMOND, Fill.LINED);
		
		assertEquals("The toString method does not work as expected.",
					"2 GREEN LINED DIAMONDS.",
					myCard.toString());
	}

}
