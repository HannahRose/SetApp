package com.example.setapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageButton;
//import android.graphics.RectF;

/** This class extends the ImageButton class to create rounded rectangular buttons
 *  showing a card, which can be selected. 
 *  
 * @author Hannah Rose
 *
 */
public class CardButton extends ImageButton {
	
	/** The card that this button will represent. */
	protected Card myCard;	// Make private if possible?? FIXME
	
	/** The Paint with which to draw the background of the card. */
	private Paint background;
	
	/** The rectangle representing the CardButton. */
	private Rect cardRect;
	
	private boolean selected = false;	// Cards are not selected by default.
	
	public CardButton(Context context) {
		super(context);
		
		initialize();
	}
	
	public CardButton(Context context, AttributeSet attrib) {
		super(context, attrib);
		
		initialize();
		
	}
	
	/** Constructor that initializes the Card data member.
	 * 
	 * @param context The given context in which the button should be created.
	 * @param attrib The AttributeSet with which to create the button.
	 * @param card The Card that the button represents.
	 */
	public CardButton(Context context, AttributeSet attrib, Card card) {
		super(context, attrib);
		
		myCard = card;
		initialize();
		
	}
	
	/** Helper method for constructors to avoid redundancy.
	 */
	private void initialize() {
		background = new Paint(-1); 	// Create the white background paint. 
		cardRect = new Rect(0, getHeight(), 0, getWidth());
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
	
	/** Draws the button on the screen (FIXME we hope?).
	 * 
	 * @param canvas The Canvas on which to draw the button.
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawRect(cardRect, background);
		myCard.draw();
		
		if (selected) {
			//do something special
		}
	}
	
	// Occurs when a button containing a card is clicked.
	// If the card is not selected, it should become selected. 
	// Otherwise it should become unselected.
	public void setSelected(boolean b) {
		selected = b;
	}

}
