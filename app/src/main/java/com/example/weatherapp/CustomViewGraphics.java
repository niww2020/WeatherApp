package com.example.weatherapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Random;

public class CustomViewGraphics extends View {

    public static final int DEF_RADIUS = 0;
    public static final int DEF_COLOR = 0;
    private int mPoint;
    Paint paint;
    Paint paintArc;
    Paint paintText;
    Paint paintTextWeather;

    float radius;
    int textSize;
    Color color;
    String mText;
    Calendar calendar;


    // used in view creation programmatically
    public CustomViewGraphics(Context context) {
        super(context);
        initPaint(100);
//        initPaint(100, 32423);
    }

    // used in XML layout file
    public CustomViewGraphics(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//
//        final TypedArray typedArray = context.
//                obtainStyledAttributes(attrs, R.styleable.CustomTextView, 0, 0);
//
//
//        int mRadius = typedArray.getInt(R.styleable.CustomTextView_ctv_size, DEF_RADIUS);
//        int mColor = typedArray.getColor(R.styleable.CustomTextView_ctv_color, DEF_COLOR);
//
//
//
//        initPaint(mRadius, mColor);
////        mPoint = typedArray.getInt(R.styleable.CircleView_points, mPoint);
//        typedArray.recycle();

    }

    public CustomViewGraphics(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //todo add hour but its max value 12
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Random random = new Random();


        RectF mRectF = new RectF(20, 20, 400, 400);
        Path path = new Path();
        path.addArc(mRectF, 180, (180 * hour) / 24);
//        mRectF.centerX(g)
//        canvas.drawCircle(radius,radius,radius,paint);
//        canvas.drawArc(mRectF,180,180,false,paintArc);
//        canvas.drawArc(mRectF,180,(180*hour)/24,false,paint);
//        canvas.drawText("-10 C",200,200,paintTextWeather);
//        canvas.drawTextOnPath("Hour "+ hour,path,0,10,paintText);
        for (int i = 0; i < 10; i++) {


            int randomInt = random.nextInt(100);
            canvas.drawText("Monday", 90 * i, 90, paintText);
            canvas.drawLine(100 * i, 100, 100 * i, 100 + randomInt, paint);
            canvas.drawLine(105 * i, 100, 105 * i, 100 + (randomInt * i), paint);
            canvas.drawLine(110 * i, 100, 110 * i, 100 + randomInt, paint);

        }


    }

    private void initPaint(int radius) {

        this.radius = radius;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);//todo what it is?
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(radius);
        paint.setStrokeWidth(5);

        paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(30);

    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
