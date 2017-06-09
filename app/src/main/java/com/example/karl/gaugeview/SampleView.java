package com.example.karl.gaugeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by karl on 12/21/2016.
 */

public class SampleView extends View {

    private class Segment {
        int mStart;
        int mStop;
        float startAngle;
        float stopAngle;
        int mSegmentColor;

        Segment( int start, int stop, float s1, float s2, int color ) {
            mStart = start;
            mStop = stop;
            startAngle  = s1;
            stopAngle = s2;
            mSegmentColor = color;
        }

        public int getStart() { return mStart; };
        public int getStop() { return mStop; };
        public int getRange() { return mStop - mStart; };
        public int getColor() { return mSegmentColor; };
        public float getStartAngle() { return startAngle; };
        public float getStopAngle() { return stopAngle; };
        public float getSpan() { return stopAngle - startAngle; };
    }


    Segment[] mSegments;

    float maxAngle = 200.0f;
    float mUnitAngle = 0.0f;
    float STARTOFFSET = 135.0f;
    private int width;
    private int height;

    // CONSTRUCTOR
    public SampleView(Context context, int segments, float min, float max, float span) {
        super(context);
        setFocusable(true);

        mSegments = new Segment[segments];
        maxAngle = span;

        // Unit angle is the number of degrees associated with
        // unit of the gauge
        mUnitAngle = maxAngle / (max - min);
    }

    public SampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes( attrs, R.styleable.SampleView);

        try {
            int min = a.getInteger(R.styleable.SampleView_min, 0);
            int max = a.getInteger(R.styleable.SampleView_max, 1);
            int segments = a.getInteger(R.styleable.SampleView_segments, 0);
            maxAngle = a.getFloat(R.styleable.SampleView_span, 225.0f);
            mSegments = new Segment[segments];
            mUnitAngle = maxAngle / (max - min);
        } finally {
            a.recycle();
        }
     }

    public void insertSegment(int i, int start, int stop, int color ) {
        mSegments[i] = new Segment( start, stop, start * mUnitAngle, stop * mUnitAngle, color );
    }

    @Override
    protected void onSizeChanged( int w, int h, int oldw, int oldh ) {
        super.onSizeChanged( w, h, oldw, oldh );
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        // smooths
        p.setAntiAlias(true);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(5);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setTextSize( 60 );
        // Create a rectangle and draw the background circle for the gauge
        RectF rectF = new RectF(0,0, width, height);
        canvas.drawOval(rectF, p);

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(25);

        // Draw each segment of the Gauge as an arc around the perimeter
        // of the gauge

        int v = 0; //mValue;
        for ( int i = 0; i < mSegments.length; i++) {

            // Get the color associated with this segment of the Gauge

            p.setColor( mSegments[i].getColor());

            // If we can draw the entire segment go ahead otherwise just
            // draw a partial segment

            if ( v > mSegments[i].getStop() ) {
                canvas.drawArc(rectF, mSegments[i].getStartAngle() + STARTOFFSET, mSegments[i].getSpan(), false, p);
            }
            else {
                canvas.drawArc(rectF, mSegments[i].getStartAngle() + STARTOFFSET, (v - mSegments[i].getStart()) * mUnitAngle, false, p);
                break;
            }
        }

        Paint textPaint = new Paint();
        textPaint.setTextSize( 60 );
        textPaint.setColor( Color.GREEN );
        canvas.drawText( Integer.toString(0), 100, 100, textPaint);

    }

}