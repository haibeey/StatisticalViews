package com.splash.android.statisticviews.graph;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.pow;

/**
 * Created by haibeey on 6/21/2018.
 */

public class Graph extends View {
    private ArrayList<Float> X_axis;
    private ArrayList<Float> Y_axis;
    private Paint paint;
    private Random random;
    private float marginForXAxis=40;
    private float marginForYAxis=40;


    public Graph(Context context) {
        super(context);
        init();
    }

    private void setX_axis(ArrayList<Float> x_axis) {
        X_axis = x_axis;
        invalidate();

    }


    private void setY_axis(ArrayList<Float> y_axis) {
        Y_axis = y_axis;
        invalidate();
    }

    public void  setY_axisAndX_Axis(ArrayList<Float> X,ArrayList<Float> Y){
        this.X_axis=X;
        this.Y_axis=Y;
        invalidate();
    }
    public void setPaint(Paint paint) {
        this.paint = paint;
        invalidate();
    }

    public void setGetMarginForYAxis(float getMarginForYaxis) {
        this.marginForYAxis = getMarginForYaxis;
        invalidate();
    }

    public void setMarginForXAxis(float marginForXaxis) {
        this.marginForXAxis = marginForXaxis;
        invalidate();
    }

    public Graph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Graph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }




    public void init(){
        paint=new Paint();
        random=new Random((int) Math.pow(2,64));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw y axis line
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(marginForXAxis,marginForYAxis,
                marginForXAxis,getHeight()-(2.5f*marginForYAxis),paint);
        //draw x axis line
        canvas.drawLine(marginForXAxis,getHeight()-(2.5f*marginForYAxis),
                getWidth()-marginForXAxis,getHeight()-(2.5f*marginForYAxis),paint);

        assert X_axis!=null;
        float xAxisScale=(getWidth()-(2*marginForXAxis))/X_axis.size();
        assert Y_axis!=null;
        float yAxisScale=(getHeight()-(2.5f*marginForYAxis)-marginForYAxis)/Y_axis.size();

        ArrayList<Float> X=new ArrayList<>();
        ArrayList<Float> Y=new ArrayList<>();

        float scaleFactorX=findMax(X_axis)/X_axis.size();
        float scaleFactorY=findMax(Y_axis)/Y_axis.size();

        for(int index=0;index<X_axis.size();index++){
            paint.setStrokeWidth(2);
            //draw for x
            canvas.drawLine(marginForXAxis+((index+1)*xAxisScale),getHeight()-(2.5f*marginForYAxis),
                    marginForXAxis+((index+1)*xAxisScale),getHeight()-(2.5f*marginForYAxis)+20,paint);
            canvas.drawText((int)(scaleFactorX*(index+1))+"",(marginForXAxis+((index+1)*xAxisScale))-10,
                    getHeight()-(2.5f*marginForYAxis)+25,paint);
            // draw for y
            canvas.drawLine(marginForXAxis-20,marginForYAxis+((index)*yAxisScale)
                    ,marginForXAxis,marginForYAxis+((index)*yAxisScale),paint);
            canvas.drawText((int)(scaleFactorY*(index+1))+"",marginForXAxis-25,
                    marginForYAxis+((Y_axis.size()-(index+1))*yAxisScale)-2,paint);

            paint.setStrokeWidth(10);

            //draw intersection points
            canvas.drawPoint(marginForXAxis+getXPoint(index,findMax(X_axis),getWidth()-2*marginForXAxis),
                    ((getHeight()-(2.5f*marginForYAxis))-getYPoint(index,findMax(Y_axis),(getHeight()-(2.5f*marginForYAxis))-marginForYAxis)),paint);

            X.add(marginForXAxis+(getXPoint(index,findMax(X_axis),getWidth()-marginForXAxis-marginForXAxis)));
            Y.add(((getHeight()-(2.5f*marginForYAxis))-getYPoint(index,findMax(Y_axis),(getHeight()-(2.5f*marginForYAxis))-marginForYAxis)));
        }

        drawLeastSquareLine(canvas,X,Y);
    }

    private void drawLeastSquareLine(Canvas canvas,ArrayList<Float> X,ArrayList<Float> Y){
        LeastSquare leastSquare=new LeastSquare(X,Y);
        float StartY=leastSquare.getYValueOfLeastSquare(findXWithMAXY(X,Y));
        float EndY=leastSquare.getYValueOfLeastSquare(findXWithMINY(X,Y));
        paint.setStrokeWidth(5);
        canvas.drawLine(X.get(0),StartY,X.get(X.size()-1),EndY,paint);
    }

    private float calculateSum(ArrayList<Float> sumTobeCalculated){
        float result=0f;
        for(Float data : sumTobeCalculated){
            result+=data;
        }
        return result;
    }

    private float getXPoint(int index,float sum,float lineLength){
        return (X_axis.get(index)/sum)*lineLength;
    }

    private float getYPoint(int index,float sum,float lineLength){
        return (Y_axis.get(index)/sum)*lineLength;
    }

    private float findXWithMAXY(ArrayList<Float> X,ArrayList<Float> Y){
        float max=X.get(0);
        float maxY=Y.get(0);
        for( int index=0;index<X.size();index++){
            if(Y.get(index)>maxY){
                max=X.get(index);
                maxY=Y.get(index);
            }
        }
        return  max;
    }

    private float findXWithMINY(ArrayList<Float> X,ArrayList<Float> Y){
        float min= X.get(0);
        float minY=Y.get(0);
        for( int index=0;index<X.size();index++){
            if(Y.get(index)<minY){
                min=X.get(index);
                minY=Y.get(index);
            }
        }
        return  min;
    }

    private float findMax(ArrayList<Float> arrayList){
        float max= (float) ~Math.max(2,32);
        for( Float val:arrayList){
            max=Math.max(val,max);
        }
        return  max;
    }
}
