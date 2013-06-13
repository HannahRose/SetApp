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
	
	protected int number;
	
	protected Color color;
	
	protected Shape shape;
	
	protected Fill fill;
		
	/** Constructor for Card objects. */
	public Card(int thisNum, Color thisColor, Shape thisShape, Fill thisFill) {
		
		// The number must be between 1 and 3.
		assert((0 < thisNum) && (thisNum < 3));
		
		number = thisNum;
		color = thisColor;
		shape = thisShape;
		fill = thisFill;
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
	
	public String toString() {
		return number + " " + color + " " + fill + " " + shape + "S.";
	}
	
	//////////////////////////////////////
	
	public boolean equals(Card rhs) {
		
		return (this.getNumber() == rhs.getNumber()) && (this.getColor() == rhs.getColor()) 
			&& (this.getShape() == rhs.getShape()) && (this.getFill() == rhs.getFill());
	}
}
