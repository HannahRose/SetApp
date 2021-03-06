package com.example.setapp;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.example.setapp.Card.Color;
import com.example.setapp.Card.Fill;
import com.example.setapp.Card.Shape;

public class CardView extends View {
	
	/** The card that this button will represent. */
	private Card myCard;
	
	private Path cardDraw = new Path();
	private RectF cardRect = new RectF();
	
	private int width;
	private int height;

	private boolean selected = false;	// Cards are not selected by default.
		
	private Paint foreground = new Paint();
	private Paint fill = new Paint();
	private Paint edges = new Paint();
	
	private Bitmap fillBitmap;
	private BitmapShader myFill;
	private Canvas fillCanvas;
		
	/** Constants for paint colors. */
	private int GREEN = 0xff008000;
	private int RED = 0xffdd0033;
	private int PURPLE = 0xff900080;
	private int WHITE = 0xffffffff;
	private int GREY = 0xdddddddd;
	private int BLUE = 0xdd00bfff;
	private int TRANSPARENT = 0x0000000;
	
	/** Constants for stroke width. */
	private int THIN = 8;
	private int THICK = 10;
	
	public CardView(Context context) {
		super(context);
			
		initialize(context);
	}

	public CardView(Context context, AttributeSet attrs) {
		super(context, attrs);
			
		initialize(context);
	}
	
	public CardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
			
		initialize(context);
	}
	
	private void initialize(Context context) {
		
		setBackgroundColor(WHITE);
		foreground.setStrokeWidth(THICK);
		foreground.setStyle(Paint.Style.STROKE);
		fill.setStrokeWidth(THIN);
		fill.setStyle(Paint.Style.FILL);
		edges.setStrokeWidth(THIN);
		edges.setColor(GREY);
		edges.setStyle(Paint.Style.STROKE);

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		//Make this depend on the number of cards shown??
	 	width = size.y/3;
		height = width/2;

	}
	
	@Override
	protected void onMeasure(int widthSpec, int heightSpec) {
		
		setMeasuredDimension(width, height);
	}
	
	/** Override the default dimensions of the card. */
	public void setDimensions(int w, int h) {
		width = w;
		height = h;
	}
	
	
	/** Sets the Card data member of a CardButton.
	 * 
	 * @param newCard The Card for the button to represent.
	 */
	public void setCard(Card newCard) {
		
		myCard = newCard;
		
		fillBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		fillCanvas = new Canvas(fillBitmap);
		
		if (myCard == null) {	// We've run out of cards
			return;
		}
		
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
		
		// The fill color should always be the same as the foreground.
		fill.setColor(foreground.getColor());	

	}
	
	/** Checks if the card data member is currently set. 
	 * @return True if the button is currently representing a card
	 *  and false if there is no card specified.
	 */
	public boolean hasCard() {
		return (myCard != null);
	}
	
	/** Returns a copy of the current card. 
	 * @return The current card, or null if no card is set. 
	 */
	public Card getCard() {
		return myCard;
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
		
		//this.setLayoutParams(layout);
				
		float xCenter = ((float) getWidth())/2;
		float xOffset = ((float) getWidth())/6;
		
		float[] xCenters2 = {xCenter-xOffset/2, xCenter+xOffset/2};
		float[] xCenters3 = {xCenter-xOffset, xCenter+xOffset};
		
		cardRect.right = (float) getWidth();
		cardRect.bottom = (float) getHeight();
		
		if (myCard == null) { // No more cards in the deck.
			foreground.setColor(TRANSPARENT);
			canvas.drawRect(cardRect, foreground);
			return;
		}
		
		// Deal with drawing different numbers of shapes.
		if ((myCard.getNumber() % 2) == 1) {	// 1 or a 3
			setShapeAndFill(xCenter);
			doDrawing(canvas);
			
			if (myCard.getNumber() == 3) {
				for (int i = 0; i < 2; i++) {
					setShapeAndFill(xCenters3[i]);
					doDrawing(canvas);
				}
			}
		}
		else { // 2
			for (int i = 0; i < 2; i++) {
				setShapeAndFill(xCenters2[i]);
				doDrawing(canvas);
			}
		}
		
		canvas.drawRect(cardRect, edges);
	}
	
	/** Draws and fills the shape, then resets the path. */
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
		
		if (selected) {
			edges.setColor(BLUE);
		}
		else {
			edges.setColor(GREY);
		}
	}

	
	private void setShapeAndFill(float xCenter) {
		
		float width = (float) getWidth();
		float height = (float) getHeight();
				
		float shapeWidth = width/8;
		float shapeHeight = 9*height/16;
		
		float yCenter = height/2;
		
		// Set the shape on the card.
		if (myCard.getShape() == Shape.DIAMOND) {
			Diamond(shapeWidth, shapeHeight, xCenter, yCenter);
			foreground.setStrokeJoin(Paint.Join.MITER);
		}
		else if (myCard.getShape() == Shape.OVAL) {
			Oval(shapeWidth, shapeHeight, xCenter, yCenter);
		}
		else {
			Squiggle(shapeWidth, shapeHeight, xCenter, yCenter);
			foreground.setStrokeJoin(Paint.Join.ROUND);
		}
		
		// Set the fill type. 
		if (myCard.getFill() == Fill.SOLID) {
			fillBitmap.eraseColor(fill.getColor());	// Fill the bitmap with the specified color	
		}
		else if (myCard.getFill() == Fill.LINED) {	// Fill the bitmap with lines
			
			float y = 0;
			foreground.setStrokeWidth(THIN);
			
			while (y < height) {
				fillCanvas.drawLine(0, y, width, y, foreground);
				y += height/11;
			}	
			foreground.setStrokeWidth(THICK);
		}
		
		// Do nothing if the fill is open
		
		myFill = new BitmapShader(fillBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
		fill.setShader(myFill);
	}
	
	public void Diamond(float width, float height, float xCenter, float yCenter) {
			
		final int diamondVert = 4; // Number of vertices of a diamond.
		
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
