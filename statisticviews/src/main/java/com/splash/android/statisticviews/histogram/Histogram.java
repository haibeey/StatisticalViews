package com.splash.android.statisticviews.histogram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by haibeey on 3/18/2018.
 */

public class Histogram extends View {
    private ArrayList<String> x_axis;
    private Paint paint=new Paint();
    private int marginTopOfY_axis=20;
    private int marginTopOfX_axis=20;
    private float sumOfXAxis=-1;
    private ArrayList<Float> frequency;
    //random class for random colors
    private Random random;

    public Histogram(Context context) {
        super(context);
        init();
    }

    public Histogram(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Histogram(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setMarginTopOfX_axis(int marginTopOfX_axis) {
        this.marginTopOfX_axis = marginTopOfX_axis;
        invalidate();
    }

    public void setMarginTopOfY_axis(int marginTopOfY_axis) {
        this.marginTopOfY_axis = marginTopOfY_axis;
        invalidate();
    }

    private void setX_axis(ArrayList<String> x_axis) {
        this.x_axis = x_axis;
        requestLayout();
        invalidate();
    }

    private void setFrequency(ArrayList<Float> frequency){
        this.frequency = frequency;
        requestLayout();
        invalidate();
    }

    public void setFrequencyAndXAxis(ArrayList<Float> frequency,ArrayList<String> XAxis){
        this.frequency = frequency;
        this.x_axis = XAxis;
        invalidate();

    }
    private void init(){
        random=new Random((int)Math.pow(2,32));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw y axis
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(marginTopOfX_axis,marginTopOfY_axis,
                marginTopOfX_axis,getHeight()-(2.5f*marginTopOfY_axis),paint);

        //draw x axis
        canvas.drawLine(marginTopOfX_axis,getHeight()-(2.5f*marginTopOfY_axis),
                getWidth()-marginTopOfX_axis,getHeight()-(2.5f*marginTopOfY_axis),paint);
        float widthOfRectangle=x_axis!=null?(getWidth()-(2*marginTopOfX_axis))/x_axis.size():50;
        for(int index=0;index<frequency.size();index++){
            drawRectangle(canvas,index,widthOfRectangle);
        }

    }
    /*
        method to draw a rectangle
        parameter :
            canvas the canvas to be draw on
            index in the y axis array list
     */
    @SuppressLint("Assert")
    private void drawRectangle(Canvas canvas, int index,float widthOfRectangle){
        assert index<frequency.size();
        float left=((index)*widthOfRectangle)+marginTopOfX_axis;
        float top=getHeight()-scaleFrequency(frequency.get(index))-marginTopOfY_axis;

        paint.setColor(random.nextInt());
        canvas.drawRect(left+10,top,left+widthOfRectangle,getHeight()-(2.5f*marginTopOfY_axis),paint);
        String text=x_axis!=null?x_axis.get(index):"";
        paint.setTextSize(widthOfRectangle/text.length());
        canvas.drawText(text,left+10,getHeight()-marginTopOfY_axis,paint);
    }


    private float sumOfFrequency(){
        if(frequency==null)return 0f;
        float sum=0f;

        for(float f:frequency) sum+=f;
        return sum;
    }

    private float scaleFrequency(float frequency){
        if(sumOfXAxis==-1){
            sumOfXAxis=sumOfFrequency();
        }
        return (frequency/sumOfXAxis)*(getHeight());
    }
}
