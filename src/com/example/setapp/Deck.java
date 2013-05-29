package com.example.setapp;

import java.util.Stack;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

public class Deck {
	
	// private final int numCards = 81;

	private Stack<Card> deck;
	
	public Deck() {

		deck = new Stack<Card>();
		
		// Create all the cards in the deck.
		int cardNum = 0;
		for (int num = 0; num < 3; num++) { // Yuck, icky nesting.
			for (Color color : Color.values()) {
				for (Shape shape : Shape.values()) {
					for (Fill fill : Fill.values()) {
						
						// Place cards in deck using an inside-out shuffle.
						double rand = cardNum*Math.random();
						int pos = (int) rand;
						
						// If needed, move element at pos, then insert a new card.
						Card here = deck.get(pos); //out of bounds?? FIXME
						if (here != null) {	
							// Move to position of the nth current) card
							deck.insertElementAt(here, cardNum);	
							
							// Delete the copy so we can add our new card.
							deck.removeElementAt(pos);
						}
						
						deck.insertElementAt(new Card(num, color, shape, fill), pos);
						cardNum++;
					}
				}
			}
		}
	}
	
	public Card getTopCard() {
			return deck.pop();
	}
}
