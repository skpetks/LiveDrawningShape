package com.mydrawningapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * This is demo code to accompany the Mobiletuts+ tutorial series:
 * - Android SDK: Create a Drawing App
 * 
 * Sue Smith
 * August 2013
 *
 */
public class DrawingView extends View {

	//drawing path
	private Path drawPath;
	//drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	//initial color
	private int paintColor = 0xFF0000FF;
	//canvas
	private Canvas drawCanvas;
	//canvas bitmap
	private Bitmap canvasBitmap;
	//brush sizes
	private float brushSize, lastBrushSize;
	//erase flag
	private boolean erase=false;

	public DrawingView(Context context, AttributeSet attrs){
		super(context, attrs);
		setupDrawing();
	}

	//setup drawing
	private void setupDrawing(){

		//prepare for drawing and setup paint stroke properties
//		brushSize = getResources().getInteger(R.integer.small_size);
		brushSize=2;
		lastBrushSize = brushSize;
		drawPath = new Path();
		drawPaint = new Paint();
		drawPaint.setColor(paintColor);
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
//		drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		canvasPaint = new Paint(Paint.DITHER_FLAG);



	}


	//size assigned to view
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}

	//draw the view - will be called after touch event
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);
	}

	//register user touches as drawing action

	int start=0;
	float StartX=0f,StartY=0f;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();




		if(start==0){

			Log.d("touchX", ""+touchX);
			Log.d("touchY", ""+touchY);
		}


		//respond to down, move and up events

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

				StartX=touchX;
			    StartY=touchY;

			Log.d("ACTION_DOWNtouchX", ""+touchX);
			Log.d("ACTION_DOWNtouchY", ""+touchY);
//			drawPath.moveTo(touchX, touchY);
//			Rect rectangle = new Rect(Math.round(StartX),Math.round(StartY),Math.round(touchX),Math.round(touchY));
//			drawRectanglenew(rectangle);
			drawCircle(StartX,StartY,touchX,touchY);
//			drawPath.reset();
			break;
		case MotionEvent.ACTION_MOVE:
//			drawPath.lineTo(touchX, touchY);

//			drawRectangle( Math.round(touchX),  Math.round(touchY));

			Log.d("ACTION_MOVEtouchX", ""+touchX);
			Log.d("ACTION_MOVEtouchY", ""+touchY);
//			Rect rectangle1 = new Rect(Math.round(StartX),Math.round(StartY),Math.round(touchX),Math.round(touchY));
//			drawRectanglenew(rectangle1);
			drawCircle(StartX,StartY,touchX,touchY);
//			drawPath.reset();
			break;
		case MotionEvent.ACTION_UP:
//			drawPath.lineTo(touchX, touchY);
//			drawCanvas.drawPath(drawPath, drawPaint);
//			drawPath.reset();

			Log.d("ACTION_UPtouchX", ""+touchX);
			Log.d("ACTION_UPtouchY", ""+touchY);
			Log.d("ACTION_UPStartX", ""+StartX);
			Log.d("ACTION_UPStartY", ""+StartY);


			//Circle find midpoint
//			StartX=(StartX+touchX)/2;
//			StartY=(StartY+touchY)/2;
//
//			float radius=touchY-StartY;



			drawCircle(StartX,StartY,touchX,touchY);

			///Rectangle Working
//			Rect rectangle3 = new Rect(Math.round(StartX),Math.round(StartY),Math.round(touchX),Math.round(touchY));
//			drawRectanglenew(rectangle3);

//			drawPath.reset();
			break;
		default:
			return false;
		}
		start++;
		//redraw
		invalidate();
		return true;

	}

	public void drawRectangle(int x, int y) {





		drawPaint.setColor(Color.RED);
		int RADIUS=1;
		Rect rectangle = new Rect((int) (x - ((0.8) * RADIUS)), (int) (y - ((0.6) * RADIUS)), (int) (x + ((0.8) * RADIUS)), (int) (y + ((0.6 * RADIUS))));





		drawCanvas.drawRect(rectangle, drawPaint);
	}
	public void drawCircle(float X,float Y,float X1,float Y1) {

		float	drawX=(X+X1)/2;
		float drawY=(Y+Y1)/2;



		float radius=(drawY-Y1);
		drawPaint.setColor(Color.RED);
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
		drawCanvas.drawCircle(	drawX, drawY, radius, drawPaint);
//		invalidate();

	}

	public void drawTriangle(int x, int y, int width) {
		drawPaint.setColor(Color.GREEN);
		int halfWidth = width / 2;

		Path path = new Path();
		path.moveTo(x, y - halfWidth); // Top
		path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
		path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
		path.lineTo(x, y - halfWidth); // Back to Top
		path.close();
		drawCanvas.drawPath(path, drawPaint);
	}


	public void drawRectanglenew(Rect rectangle) {





		drawPaint.setColor(Color.RED);

		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();



		drawCanvas.drawRect(rectangle, drawPaint);

	}

	//update color
	public void setColor(String newColor){
		invalidate();
		paintColor = Color.parseColor(newColor);
		drawPaint.setColor(paintColor);
	}

	//set brush size
	public void setBrushSize(float newSize){
		float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
				newSize, getResources().getDisplayMetrics());
		brushSize=pixelAmount;
		drawPaint.setStrokeWidth(brushSize);
	}

	//get and set last brush size
	public void setLastBrushSize(float lastSize){
		lastBrushSize=lastSize;
	}
	public float getLastBrushSize(){
		return lastBrushSize;
	}

	//set erase true or false
	public void setErase(boolean isErase){
		erase=isErase;
		if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		else drawPaint.setXfermode(null);
	}

	//start new drawing
	public void startNew(){
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
	}
}
