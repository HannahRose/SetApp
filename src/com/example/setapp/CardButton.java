package com.example.setapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
//import android.graphics.RectF;
import android.widget.ImageButton;

/** This class extends the ImageButton class to create rounded rectangular buttons
 *  showing a card, which can be selected. 
 *  
 * @author Hannah Rose
 *
 */
public class CardButton extends ImageButton {
	
	private Card myCard;
	
	private Paint background;
	
	private Rect cardRect;
	
	public CardButton(Context context) {
		super(context);
		
		initialize();
		
	}
		
	public CardButton(Context context, Card card) {
		super(context);
		
		myCard = card;
		initialize();
		
	}
	
	/** Helper method for constructors to avoid redundancy.
	 */
	private void initialize() {
		background = new Paint(-1); 	// Create the white background paint. 
		cardRect = new Rect(0, getHeight(), 0, getWidth());
	}
	
	public void setCard(Card newCard) {
		myCard = newCard;
	}
	
	public boolean hasCard() {
		return (myCard != null);
	}
	
	protected void onDraw(Canvas canvas) {
		
		canvas.drawRect(cardRect, background);
		myCard.draw();
	}

}
