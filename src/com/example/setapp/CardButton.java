package com.example.setapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
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
	
	private RectF cardRect;
	
	public CardButton(Context context) {
		super(context);
		
		background = new Paint(-1); 	// Create the white background paint. 
		cardRect = new RectF();
		
	}
		
	public CardButton(Context context, Card card) {
		super(context);
		
		myCard = card;
		background = new Paint(-1); 	// Create the white background paint. 
		cardRect = new RectF();
		
	}
	
	public void setCard(Card newCard) {
		myCard = newCard;
	}
	
	protected void onDraw(Canvas canvas) {
		
		canvas.drawRoundRect(cardRect, 5f, 5f, background);
		myCard.draw();
	}

}
