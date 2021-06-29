package com.mydrawningapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * This is demo code to accompany the  tutorial series:
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
	private Canvas drawCanvas,drawCanvas2;
	//canvas bitmap
	private Bitmap canvasBitmap;
	//brush sizes
	private float brushSize, lastBrushSize;
	//erase flag
	private boolean erase=false;

	public int Shapesdrawing=1;
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

//		drawPath.moveTo(34, 259);
//		drawPath.cubicTo(68, 151, 286, 350, 336, 252);
//		drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));



		canvasPaint = new Paint(Paint.DITHER_FLAG);




	}


	//size assigned to view
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);

		drawCanvas2= new Canvas(canvasBitmap);
	}

	//draw the view - will be called after touch event
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);


		//drawCanvas.drawPath(drawPath, drawPaint);
	}



	public void setShapes(int Shape) {
		Shapesdrawing=	Shape;
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

//			StartX=(StartX+touchX)/2;
//			StartY=(StartY+touchY)/2;
//			drawCanvas = new Canvas(canvasBitmap);
//			Rect rectangle = new Rect(Math.round(StartX),Math.round(StartY),Math.round(touchX),Math.round(touchY));
//			drawRectanglenew(rectangle);

			if(Shapesdrawing==1) {
				drawCircle(StartX,StartY,touchX,touchY);
				drawCanvas = new Canvas(canvasBitmap);
			}
			else if (Shapesdrawing==2){
				Rect rectangle3 = new Rect(Math.round(StartX),Math.round(StartY),Math.round(touchX),Math.round(touchY));
				drawRectanglenew(rectangle3);
			}
			else if (Shapesdrawing==3){
//				drawPath.lineTo(touchX, touchY);
//				drawPath.rLineTo();

				drawAngel(StartX,StartY,touchX,touchY);
			}
			else  if(Shapesdrawing==4){
				drawBeizerCurve(StartX,StartY,touchX,touchY);
			}
			else {

				drawTriangle((int)touchX,(int)touchY);
			}


//			drawPath.reset();
			break;
		case MotionEvent.ACTION_MOVE:
//			drawPath.lineTo(touchX, touchY);

//			drawRectangle( Math.round(touchX),  Math.round(touchY));

			Log.d("ACTION_MOVEtouchX", ""+touchX);
			Log.d("ACTION_MOVEtouchY", ""+touchY);
//			Rect rectangle1 = new Rect(Math.round(StartX),Math.round(StartY),Math.round(touchX),Math.round(touchY));
//			drawRectanglenew(rectangle1);

//			StartX=(StartX+touchX)/2;
//			StartY=(StartY+touchY)/2;




			if(Shapesdrawing==1) {
				drawCircle(StartX,StartY,touchX,touchY);
			}
			else if (Shapesdrawing==2){
				Rect rectangle3 = new Rect(Math.round(StartX),Math.round(StartX),Math.round(touchX),Math.round(touchY));
				drawRectanglenew(rectangle3);
			}
			else if (Shapesdrawing==3){
//				drawPath.reset();
//				drawPath.rLineTo(StartX, StartX);
//
//				drawPath.rLineTo(touchX, touchY);
//
//
//				drawPath.set();

				drawAngel(StartX,StartY,touchX,touchY);

			}
			else  if(Shapesdrawing==4){
				drawBeizerCurve(StartX,StartY,touchX,touchY);
			}
			else {
				drawTriangle((int)touchX,(int)touchY);
			}


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


//			Circle find midpoint
//			StartX=(StartX+touchX)/2;
//			StartY=(StartY+touchY)/2;
//
//			float radius=touchY-StartY;







			if(Shapesdrawing==1) {
				drawCircle(StartX,StartY,touchX,touchY);


			}
			else if (Shapesdrawing==2){
			Rect rectangle3 = new Rect(Math.round(StartX),Math.round(StartY),Math.round(touchX),Math.round(touchY));
			drawRectanglenew(rectangle3);
			}
			else if (Shapesdrawing==3){
//				drawPath.lineTo(touchX, touchY);
				drawAngel(StartX,StartY,touchX,touchY);
//				drawCanvas.drawLine(50, 550, 770, 0, drawPaint);
			}
			else if (Shapesdrawing==4){
//				drawPath.lineTo(touchX, touchY);
//				drawPath.moveTo(touchX, touchY);

				drawBeizerCurve(StartX,StartY,touchX,touchY);
			}
			else {
				drawTriangle((int)touchX,(int)touchY);
			}



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


		Log.d("drawX", ""+drawX);
		Log.d("drawY", ""+drawY);


		float radius=(drawY-Y1);

		if(radius<=0){
			radius=radius*-1;
		}
		Log.d("radius", ""+radius);
		drawPaint.setColor(Color.RED);
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
		drawCanvas.drawCircle(	drawX, drawY, radius, drawPaint);



		invalidate();

	}


	public  void drawAngel(float X1,float Y1,float X3,float Y3){


		float X2=X1;
		float Y2=Y1-400;
		float f = 0.1F;
		double d = f;


	double angle=	CalculateAngle0To180( (double) X2,(double)  Y2,(double) X1,(double)  Y1,(double)  X3,(double)  Y3);
		Log.d("angle", ""+angle);

		Log.d("X1", ""+X2);
		Log.d("X3", ""+X3);
		if(X1>X3){
			double findnewangle=360-angle;
			Log.d("Balance Angle", ""+findnewangle);

			angle=360-angle;

			Log.d("Optain Angle", ""+angle);
		}
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		drawPath.reset();

		int startAngle = (int) (180 / Math.PI * Math.atan2(Y2 - Y3, X2 - X3));

		float radius = 20;
		final RectF oval = new RectF();
		oval.set(X2 - radius, Y2 - radius, X3 + radius, Y3+ radius);
		Path myPath = new Path();

		drawPath.arcTo(oval, startAngle, -(float) startAngle+80, true);


		Paint paint2=new Paint();
		paint2.setTextSize(35);
		paint2.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		drawCanvas.drawText(String.format("%,.0f",angle)+(char) 0x00B0, X2+20, Y2+50,paint2);
		drawCanvas.drawLine(X1, Y1, X2, Y2, drawPaint);
		drawCanvas.drawLine(X2, Y2, X3, Y3, drawPaint);
		invalidate();





	}

	public void drawBeizerCurve(float X1,float Y1,float X2,float Y2) {




		drawPath.reset(); // reset the Path

		float midx=(X1+X2)/2;
		float midy=(Y1+Y2)/2;
		float radiusx=0f;
		float radiusy=0f;
		if(X2>X1){
			float findXlength=X2-X1;
			 radiusy=findXlength/2;
		}
		else if(X1>X2){
			float findXlength=X1-X2;
			 radiusy=findXlength/2;
		}

		if(radiusy<100){

			if(Y2>Y1){
				float findYlength=Y2-Y1;
				radiusx=findYlength/2;
			}
			else if(Y1>Y2){
				float findYlength=Y1-Y2;
				radiusx=findYlength/2;
			}
			Log.d("radiusx", ""+radiusy);
			float ControlPointX1=midx-radiusx;
			float ControlPointX2=midx+radiusx;
			drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
			drawPath.moveTo(X1, Y1);
			drawPath.cubicTo(ControlPointX1, midy, ControlPointX2, midy, X2, Y2);
			invalidate();

		}
		else{



			Log.d("radiusy", ""+radiusy);
			float ControlPointY1=midy-radiusy;
			float ControlPointY2=midy+radiusy;


			drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);


			drawPath.moveTo(X1, Y1);
			drawPath.cubicTo(midx, ControlPointY1, midx, ControlPointY2, X2, Y2);
			invalidate();


		}






	}

	public void drawTriangle(int x, int y) {
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		  int RADIUS =70;

		int width =70*5;
		drawPaint.setColor(Color.GREEN);
		int halfWidth = width / 2;

//		Path path = new Path();
//		path.moveTo(x, y - halfWidth); // Top
//		path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
//		path.lineTo(x - halfWidth, y + halfWidth); // Bottom right
////		path.lineTo(x, y - halfWidth); // Back to Top
//		path.close();

		drawPath.lineTo(x, y - halfWidth);
		drawPath.lineTo(x - halfWidth, y + halfWidth);
		drawPath.lineTo(x - halfWidth, y + halfWidth);

//		drawCanvas.drawPath(path, drawPaint);
	}


	public void drawRectanglenew(Rect rectangle) {





		drawPaint.setColor(Color.RED);

		drawCanvas2.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();



		drawCanvas2.drawRect(rectangle, drawPaint);

	}


	public  void CurveLine(){
		int w=drawCanvas.getWidth();
		int h=drawCanvas.getHeight();
		int w_2= (w / 2);
		int h_2= (h / 2);
		PointF mPoint1 = new PointF(0, 0); //starts at canvas left top
		PointF mPoint2 = new PointF(w_2, h_2);//mid of the canvas
		Path drawPath1 =drawCurve(mPoint1, mPoint2);
		drawCanvas.drawPath(drawPath1, drawPaint);
	}


	private Path drawCurve(PointF mPointa, PointF mPointb) {
		Path myPath = new Path();
		myPath.moveTo(mPointa.x, mPointa.y);
		final float x2 = (mPointb.x + mPointa.x) / 3;
		final float y2 = (mPointb.y + mPointa.y) / 3;
		myPath.quadTo(x2, y2, mPointb.x, mPointb.y);
		return myPath;
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


	public static double CalculateAngleFromHorizontal(double startX, double startY, double endX, double endY)
	{

		double atan = Math.atan2(endY - startY, endX - startX); // Angle in radians
		double angleDegrees = atan * (180 / Math.PI);  // Angle in degrees (can be +/-)
		if (angleDegrees < 0.0)
		{
			angleDegrees = 360.0 + angleDegrees;
		}
		return angleDegrees;
	}

	// Angle from point2 to point 3 counter clockwise
	public static double CalculateAngle0To360(double centerX, double centerY, double x2, double y2, double x3, double y3)
	{
		double angle2 = CalculateAngleFromHorizontal(centerX, centerY, x2, y2);
		double angle3 = CalculateAngleFromHorizontal(centerX, centerY, x3, y3);
		return (360.0 + angle3 - angle2)%360;
	}

	// Smaller angle from point2 to point 3
	public static double CalculateAngle0To180(double centerX, double centerY, double x2, double y2, double x3, double y3)
	{
		double angle = CalculateAngle0To360(centerX, centerY, x2, y2, x3, y3);
		if (angle > 180.0)
		{
			angle = 360 - angle;
		}
		return angle;
	}
}
