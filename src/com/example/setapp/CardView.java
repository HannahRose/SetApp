package com.example.setapp;

import com.example.setapp.Card.Color;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CardView extends View {
	
	/** The card that this button will represent. */
	protected Card myCard;	// Make private if possible?? FIXME

	private boolean selected = false;	// Cards are not selected by default.
		
	private Paint foreground = new Paint();
	
	private int GREEN = 0xff008000;
	private int RED = 0xffdd0033;
	private int PURPLE = 0xff900080;
	

	public CardView(Context context, AttributeSet attrs) {
		super(context, attrs);
			
		setBackgroundColor(0xdddddddd);
		
		foreground.setStrokeWidth(8f);	//FIXME scale
	}
	
	
	/** Sets the Card data member of a CardButton.
	 * 
	 * @param newCard The Card for the button to represent.
	 */
	public void setCard(Card newCard) {
		myCard = newCard;
	}
	
	/** Checks if the card data member is currently set. 
	 * @return True if the button is currently representing a card
	 *  and false if there is no card specified.
	 */
	public boolean hasCard() {
		return (myCard != null);
	}
	
	/** Returns information about whether or not the button is selected.
	 * 
	 * @return True if the card is selected, otherwise false.
	 */
	public boolean isSelected() {
		return selected;
	}
	
	
	public void onDraw(Canvas canvas) {
				
		int width = getWidth(); 
		int height = getHeight();
		
		if (myCard.getColor() == Color.RED) {
			foreground.setColor(RED);
		}
		else if (myCard.getColor() == Color.GREEN) {
			foreground.setColor(GREEN);
		}
		else {
			foreground.setColor(PURPLE);
		}
		
		System.out.println(myCard.toString());

		canvas.drawLine(0, 0, width, height, foreground);
		
		if (selected) {
			//do something cool
		}
		
	}
	
	// Occurs when a button containing a card is clicked.
	// If the card is not selected, it should become selected. 
	// Otherwise it should become unselected.
	public void setSelected(boolean b) {
		// Call super.setSelected(b);??
		selected = b;
	}

}
