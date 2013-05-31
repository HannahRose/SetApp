package com.example.setapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CardView extends View {
	
	/** The card that this button will represent. */
	protected Card myCard;	// Make private if possible?? FIXME

	private boolean selected = false;	// Cards are not selected by default.
		
	private Paint foreground = new Paint();;
	

	public CardView(Context context, AttributeSet attrs) {
		super(context, attrs);
			
		setBackgroundColor(0xff0000ff);
		
		foreground.setColor(0xff00ff00);
		foreground.setStrokeWidth(6f);	//FIXME scale
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
		
		canvas.drawLine(0, 0, width, height, foreground);
		
	}
	
	// Occurs when a button containing a card is clicked.
	// If the card is not selected, it should become selected. 
	// Otherwise it should become unselected.
	public void setSelected(boolean b) {
		// Call super.setSelected(b);??
		selected = b;
	}

}
