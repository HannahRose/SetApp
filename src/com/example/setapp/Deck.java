package com.example.setapp;

import java.util.Stack;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

public class Deck {

	private final int NUMCARDS = 81;
	
	private Stack<Card> deck;
	
	public Deck() {

		deck = new Stack<Card>();
		
		// Create all the cards in the deck.
		for (int num = 0; num < 3; num++) { // Yuck, icky nesting.
			for (Color color : Color.values()) {
				for (Shape shape : Shape.values()) {
					for (Fill fill : Fill.values()) {
						
						deck.push(new Card(num, color, shape, fill));
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
		
	
	public Card getTopCard() {
			return deck.pop();
	}
	
	public int size() {
		return deck.size();
	}
}
