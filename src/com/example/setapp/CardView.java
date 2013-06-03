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
//		foreground.setStrokeJoin(Paint.Join.ROUND);
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
		
		float width = (float) getWidth();
		float height = (float) getHeight();
				
		float shapeWidth = width/8;
		float shapeHeight = 3*height/4;
		
		float xCenter = width/2;
		float yCenter = height/2;
		

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
			Diamond(shapeWidth, shapeHeight, xCenter, yCenter);
		}
		else if (myCard.getShape() == Shape.OVAL) {
			Oval(shapeWidth, shapeHeight, xCenter, yCenter);
		}
		else {
			Squiggle(shapeWidth, shapeHeight, xCenter, yCenter);
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
	
	public void Diamond(float width, float height, float xCenter, float yCenter) {
			
		final int diamondVert = 4; // Number of verticies of a diamond.
		
		float[] xpoints = {xCenter-width/2, xCenter, xCenter+width/2, xCenter};
		float[] ypoints = {yCenter, yCenter-height/2, yCenter, yCenter+height/2};

		cardDraw.moveTo(xpoints[0], ypoints[0]);
		for (int p = 1; p < diamondVert; p++) {
			cardDraw.lineTo(xpoints[p], ypoints[p]);
		}
		
		cardDraw.close();
	}
	
	public void Oval(float width, float height, float xCenter, float yCenter) {
		
		RectF oval = new RectF(xCenter-width/2, yCenter-height/2, xCenter+width/2, yCenter+height/2);
		
		cardDraw.addOval(oval, Path.Direction.CW);
	}
	
	public void Squiggle(float width, float height, float xCenter, float yCenter) {
		
		cardDraw.moveTo(xCenter-width/2, yCenter-height/4);
		cardDraw.quadTo(xCenter-width/4, yCenter-height/2, xCenter+width/4, yCenter-height/4);
		cardDraw.cubicTo(xCenter+3*width/8, yCenter-height/8, xCenter+width/6, yCenter+height/8, xCenter+width/2, yCenter+height/4);
		cardDraw.quadTo(xCenter+width/4, yCenter+height/2, xCenter-width/4, yCenter+height/4);
		cardDraw.cubicTo(xCenter-3*width/8, yCenter+height/8, xCenter-width/6, yCenter+height/8, xCenter-width/2, yCenter-height/4);
		
		
	}

}
