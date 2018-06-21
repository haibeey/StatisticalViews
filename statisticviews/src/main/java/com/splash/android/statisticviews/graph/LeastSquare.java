package com.splash.android.statisticviews.graph;

import java.util.ArrayList;

/**
 * Created by haibeey on 6/21/2018.
 */

public class LeastSquare {
    private ArrayList<Float> X;
    private ArrayList<Float> Y;
    private float slope=-1;
    private float meanX=-1;
    private float meanY=-1;
    public LeastSquare(ArrayList<Float> X,ArrayList<Float> Y){
        this.X=X;
        this.Y=Y;
    }

    private float getMean(ArrayList<Float> data){
        float result=0f;
        for(Float val:data){
            result+=val;
        }
        return result/data.size();
    }
    private float calculateNumeratorOfSlope(){
        if(meanX==-1){
            meanX=getMean(X);
        }
        if(meanY==-1){
            meanY=getMean(Y);
        }

        float result=0f;
        for(int index=0;index<X.size();index++){
            result+=(X.get(index)-meanX)*(Y.get(index)-meanY);
        }

        return result;
    }

    private float calculateDenominatorOfslope(){
        if(meanX==-1){
            meanX=getMean(X);
        }
        float result=0f;
        for(int index=0;index<X.size();index++){
            result+=(X.get(index)-meanX)*(X.get(index)-meanX);
        }

        return result;
    }

    private float getSlope(){
        slope=calculateNumeratorOfSlope()/calculateDenominatorOfslope();
        return slope;
    }

    private float getIntercept(){
        if(slope==-1)
            getSlope();
        return meanY-(meanX*slope);

    }

    public float getYValueOfLeastSquare(float x){
        float intercept=getIntercept();
        return slope*x+intercept;
    }

    public float getXValueWhenYIsZero(){
        return -(getIntercept()/slope);
    }
}
