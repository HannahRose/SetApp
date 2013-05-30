package com.example.setapp;

public class Card {

	/** Possible colors for a card. */
	public enum Color {
		RED, GREEN, PURPLE
	}
	
	/** Possible shapes on a card. */
	public enum Shape {
		SQUIGGLE, OVAL, DIAMOND
	}
	
	/** Possible fills of the shapes on a card. */
	public enum Fill {
		OPEN, LINED, SOLID
	}
	
	private static int number;
	
	private static Color color;
	
	private static Shape shape;
	
	private static Fill fill;
	
	private boolean selected = false;	// Cards are not selected by default.
	
	/** Constructor for Card objects. */
	public Card(int thisNum, Color thisColor, Shape thisShape, Fill thisFill) {
		
		// The number must be between 1 and 3.
		assert((0 < thisNum) && (thisNum < 3));
		
		number = thisNum;
		color = thisColor;
		shape = thisShape;
		fill = thisFill;
	}
	
	// Occurs when a button containing a card is clicked.
	// If the card is not selected, it will become selected. 
	// Otherwise it will be unselected.
	public void select() {
		selected = !(selected);
	}
	
	/** Retrieve information about the card. */ 
	///////////////////////////////////
	
	public int getNumber() {
		return number;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public Fill getFill() {
		return fill;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public String toString() {
		return number + " " + color + " " + fill + " " + shape + "S.";
	}
	
	//////////////////////////////////////
}
