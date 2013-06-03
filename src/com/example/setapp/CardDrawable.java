package com.example.setapp;

// Based on https://code.google.com/p/android-components/source/browse/
// trunk/src/org/gregoire/android/graphics/Polygon.java?r=2

import android.graphics.Path;
import android.graphics.RectF;

public class CardDrawable {
	
	private Path path = new Path();
	
	private final int diamondVert = 4;
			
	public Path Diamond(int width, int height) {
		
		int[] xpoints = {0, width/2, width, width/2};
		int[] ypoints = {height/2, 0, height/2, height};

		path.moveTo(xpoints[0], ypoints[0]);
		for (int p = 1; p < diamondVert; p++) {
			path.lineTo(xpoints[p], ypoints[p]);
		}
		path.close();
      
		return path;
	}
	
	public Path Oval(int width, int height) {
		
		RectF oval = new RectF(0, 0, (float) width, (float) height); 
		
		path.addOval(oval, Path.Direction.CW);
		
		return path;
	}
	
	public Path Squiggle(int w, int h) {
		
		float width = (float) w;
		float height = (float) h;
		
		path.moveTo(0, height/10);
		path.quadTo(width/3, 0, 5*width/6, 2*height/5);
		path.quadTo(width, 9*height/10, width/6, 3*height/5);
		path.close();
		
		return path;
	}

}