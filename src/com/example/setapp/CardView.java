package com.example.setapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

public class CardView extends View {
	
	/** The card that this button will represent. */
	protected Card myCard;	// Make private if possible?? FIXME
	
	private Path cardDraw = new Path();

	private boolean selected = false;	// Cards are not selected by default.
		
	private Paint foreground = new Paint();
	private Paint fill = new Paint();
	private Paint edges = new Paint();
		
	private int GREEN = 0xff008000;
	private int RED = 0xffdd0033;
	private int PURPLE = 0xff900080;
	private int WHITE = 0xffffffff;
	private int GREY = 0xdddddddd;
	private int BLUE = 0xdd00bfff;
	
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
		
		setBackgroundColor(WHITE);
		foreground.setStrokeWidth(10);	//FIXME scale
		foreground.setStyle(Paint.Style.STROKE);
		fill.setStrokeWidth(8);
		fill.setStyle(Paint.Style.FILL);
		edges.setStrokeWidth(8);
		edges.setStyle(Paint.Style.STROKE);
		
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
				
		float xCenter = ((float) getWidth())/2;
		float xOffset = ((float) getWidth())/6;
		
		float[] xCenters2 = {xCenter-xOffset/2, xCenter+xOffset/2};
		float[] xCenters3 = {xCenter-xOffset, xCenter+xOffset};
		
		
		// Deal with drawing different numbers of shapes.
		if ((myCard.getNumber() % 2) == 1) {	// 1 or a 3
			setCardDraw(xCenter);
			doDrawing(canvas);
			
			if (myCard.getNumber() == 3) {
				for (int i = 0; i < 2; i++) {
					setCardDraw(xCenters3[i]);
					doDrawing(canvas);
				}
			}
		}
		else { // 2
			for (int i = 0; i < 2; i++) {
				setCardDraw(xCenters2[i]);
				doDrawing(canvas);
			}
		}
		
		
		if (selected) {
			edges.setColor(BLUE);
		}
		else {
			edges.setColor(GREY);
		}
		
		RectF cardRect = new RectF(0, 0, (float) getWidth(), (float) getHeight());
		canvas.drawRect(cardRect, edges);
		
	}
	
	private void doDrawing(Canvas canvas) {
		canvas.drawPath(cardDraw, foreground);	//draw the shape
		canvas.drawPath(cardDraw, fill);		//fill the shape (open, solid, or lined)
		cardDraw.rewind();
	}
	
	// Occurs when a button containing a card is clicked.
	// If the card is not selected, it should become selected. 
	// Otherwise it should become unselected.
	public void setSelected(boolean b) {
		selected = b;
	}

	
	private void setCardDraw(float xCenter) {
		
		float width = (float) getWidth();
		float height = (float) getHeight();
				
		float shapeWidth = width/8;
		float shapeHeight = 9*height/16;
		
		float yCenter = height/2;
		
		BitmapShader myFill;
		Bitmap fillBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		Canvas fillCanvas = new Canvas(fillBitmap);
		
		// Set the color of the card.
		if (myCard.getColor() == Color.RED) {
			foreground.setColor(RED);
			fill.setColor(RED);
		}
		else if (myCard.getColor() == Color.GREEN) {
			foreground.setColor(GREEN);
			fill.setColor(GREEN);
		}
		else {
			foreground.setColor(PURPLE);
			fill.setColor(PURPLE);
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
			fillBitmap.eraseColor(fill.getColor());	// Fill the bitmap with the specified color	
		}
		else if (myCard.getFill() == Fill.LINED) {
			
			float y = shapeHeight/6; 
			
			while (y < shapeHeight) {
				fillCanvas.drawLine(0, y, 2*shapeWidth, y, fill);
				y += shapeHeight/6;
			}			
		}
		
		// Do nothing if the fill is open
		
		myFill = new BitmapShader(fillBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
		fill.setShader(myFill);

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
		foreground.setStrokeJoin(Paint.Join.MITER);
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
		cardDraw.close();
		
		foreground.setStrokeJoin(Paint.Join.ROUND);
	}

}
