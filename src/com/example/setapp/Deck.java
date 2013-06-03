package com.example.setapp;

import java.util.Stack;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

/** A class that holds a stack of cards. */
public class Deck {

	/** The total (maximum) number of cards that will be in the deck. */
	private final int NUMCARDS = 81;
	
	private Stack<Card> deck = new Stack<Card>();
	
	/** Creates a new deck of cards and shuffles them. */
	public Deck() {
		
		
		// Create all the cards in the deck.
		for (int num = 0; num < 3; num++) { // Yuck, icky nesting.
			for (Color color : Color.values()) {
				for (Shape shape : Shape.values()) {
					for (Fill fill : Fill.values()) {
						
						deck.push(new Card(num+1, color, shape, fill));
					}
				}
			}
		}

		
		// Shuffle the deck using the Fisher-Yates algorithm
		for (int i = 0; i < NUMCARDS; i++) {
			
			Card here = deck.get(i);

			// Generate a random position for the card.
			double rand = i*Math.random();
			int pos = (int) rand;

			Card copy = deck.get(pos); // The card currently at the given position.

			// Swap the positions of the two cards.
			deck.setElementAt(here, pos);
			deck.setElementAt(copy, i);	
		}
		
	}
		
	/** Removes and returns the top Card in the deck. 
	 * Precondition: there is at least one card left in the deck.
	 * @return The card that was on top of the deck.
	 */
	public Card getTopCard() {
		
		return deck.pop();
	}
	
	/** Gives the size of the deck. 
	 * 
	 * @return The number of cards currently in the stack representing the deck.
	 */
	public int size() {
		return deck.size();
	}
}
