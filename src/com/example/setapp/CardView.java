package com.example.setapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

public class CardView extends View {
	
	/** The card that this button will represent. */
	protected Card myCard;	// Make private if possible?? FIXME
	
	private Path cardDraw = new Path();
	
	private final int offsetRatio = 10;

	private boolean selected = false;	// Cards are not selected by default.
		
	private Paint foreground = new Paint();
	
	private int GREEN = 0xff008000;
	private int RED = 0xffdd0033;
	private int PURPLE = 0xff900080;
	
	public CardView(Context context) {
		super(context);
			
		initialize();
	}

	public CardView(Context context, AttributeSet attrs) {
		super(context, attrs);
			
		initialize();
	}
	
	public CardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
			
		initialize();
	}
	
	private void initialize() {
		
		setBackgroundColor(0xdddddddd);

		foreground.setStrokeWidth(10);	//FIXME scale
		foreground.setStrokeJoin(Paint.Join.ROUND);
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
	
	@Override
	public void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		System.out.println(myCard.toString());

		setCardDraw();
		canvas.drawPath(cardDraw, foreground);
		//canvas.drawLine(0, 0, (float) getWidth(), (float) getHeight(), foreground);
		
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
	
	private void setCardDraw() {
				
		float shapeWidth = ((float) getWidth())/6;
		float shapeHeight = ((float) 3*getHeight())/4;
		
		float wOffset = ((float) getWidth())/offsetRatio;
		float hOffset = ((float) getHeight())/offsetRatio;
		
		// Set the color of the card.
		if (myCard.getColor() == Color.RED) {
			foreground.setColor(RED);
		}
		else if (myCard.getColor() == Color.GREEN) {
			foreground.setColor(GREEN);
		}
		else {
			foreground.setColor(PURPLE);
		}
		
		// Set the shape on the card.
		if (myCard.getShape() == Shape.DIAMOND) {
			Diamond(shapeWidth, shapeHeight, wOffset, hOffset);
		}
		else if (myCard.getShape() == Shape.OVAL) {
			Oval(shapeWidth, shapeHeight, wOffset, hOffset);
		}
		else {
			Squiggle(shapeWidth, shapeHeight, wOffset, hOffset);
		}
		
		// Set the fill type. 
		if (myCard.getFill() == Fill.SOLID) {
			foreground.setStyle(Paint.Style.FILL);
		}
		else if (myCard.getFill() == Fill.OPEN) {
			foreground.setStyle(Paint.Style.STROKE);
		}
		else {
			foreground.setStyle(Paint.Style.FILL_AND_STROKE);
		}
	}
	
	public void Diamond(float width, float height, float wOffset, float hOffset) {
			
		final int diamondVert = 4; // Number of verticies of a diamond.
		
		float[] xpoints = {wOffset, (width-wOffset)/2, width-wOffset, (width-wOffset)/2};
		float[] ypoints = {(height-hOffset)/2, hOffset, (height-hOffset)/2, height-hOffset};

		cardDraw.moveTo(xpoints[0], ypoints[0]);
		for (int p = 1; p < diamondVert; p++) {
			cardDraw.lineTo(xpoints[p], ypoints[p]);
		}
		
		cardDraw.close();
	}
	
	public void Oval(float width, float height, float wOffset, float hOffset) {
		
		RectF oval = new RectF(0, 0, 5*width/6, 5*height/6);
		
		cardDraw.addOval(oval, Path.Direction.CW);
	}
	
	public void Squiggle(float width, float height, float wOffset, float hOffset) {
		
		cardDraw.moveTo(0, height/10);
		cardDraw.quadTo(width/3, 0, 5*width/6, 2*height/5);
		cardDraw.quadTo(width, 9*height/10, width/6, 3*height/5);
		cardDraw.close();
	}

}
