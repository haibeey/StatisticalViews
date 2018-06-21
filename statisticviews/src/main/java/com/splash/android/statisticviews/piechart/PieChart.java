package com.splash.android.statisticviews.piechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Random;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by haibeey on 3/18/2018.
 */

public class PieChart extends View{
    private float totalOfItems;
    private  static ArrayList<Float> Items;
    private ArrayList<String> namesOfItems;
    private boolean throwErrorForBadInputs;
    private int spaceFromMiddleToDrawText=10;
    private static  int marginLeftForText=10;
    private static float percentageRatioOfChart=3f/4f;
    private int HeightOfTextBox=50;
    private int WidthOfTextBox=50;
    private Paint paint;
    private Paint paintForText;
    private Random random;
    private RectF rectF;


    public PieChart(Context context) {
        super(context);
        init();
    }


    public void setSpaceFromMiddleToDrawText(int spaceFromMiddleToDrawText) {
        this.spaceFromMiddleToDrawText = spaceFromMiddleToDrawText;
    }

    public void setHeightOfTextBox(int heightOfTextBox) {
        HeightOfTextBox = heightOfTextBox;
        invalidate();
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        invalidate();
    }

    public void setPaintForText(Paint paintForText) {
        this.paintForText = paintForText;
        invalidate();
    }


    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private   void setItems(ArrayList<Float> items) {
        Items = items;
    }


    public  void setItemsAndNameOfItems(ArrayList<Float> items,ArrayList<String> namesOfItems){
        setItems(items);
        setNamesOfItems(namesOfItems);
        invalidate();
    }
    private void init(){
        random = new Random((int) Math.pow(2,64));
        paint=new Paint();
        paintForText=new Paint();
        paint.setColor(Color.BLUE);
        rectF=new RectF(0, 0, 0, 0);

    }



    private void setNamesOfItems(ArrayList<String> namesOfItems) {
        this.namesOfItems = namesOfItems;
    }

    public void setThrowErrorForBadInputs(boolean throwErrorForBadInputs) {
        this.throwErrorForBadInputs = throwErrorForBadInputs;
    }

    private float getSumOfItems() {
        float result = 0f;
        for (Float item : Items) {
            result = result + item;
        }
        return result;
    }

    @Override 
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float height = getHeight() * percentageRatioOfChart;
        float  width = getWidth()* percentageRatioOfChart;
        //space per spacing for text drawing
        float spaceForText=height/spaceFromMiddleToDrawText;
        //spaceForText=50;
        if (Items == null || namesOfItems==null) {
            return;
        }
        //calculating the total number of items
        totalOfItems = getSumOfItems();
        //setting the coordinates to draw text
        rectF.right=width;
        rectF.bottom=height;
        //set the paint color for text
        paintForText.setColor(Color.BLACK);
        //start angle
        float prevAngle = 0;
        //current iteration
        int indexOfText=0;

        for (Float item : Items) {
            //new angle to draw
            float sweepAngle = item / totalOfItems * 360;

            float whereToDrawText_y=(indexOfText+1)*spaceForText;
            float whereToDrawText_x=width+marginLeftForText;
            //sets random color on the paint object
            paint.setColor(random.nextInt());
            //draw box beside text
            canvas.drawRect(whereToDrawText_x,whereToDrawText_y,whereToDrawText_x+
                    WidthOfTextBox,whereToDrawText_y+HeightOfTextBox,paint);
            //draw text on the right hand side
            if(namesOfItems!=null){
                paintForText.setTextSize(calculateSizeOfText(namesOfItems.get(indexOfText),whereToDrawText_x+WidthOfTextBox));
            }
            canvas.drawText(namesOfItems==null?"":
                    namesOfItems.get(indexOfText),whereToDrawText_x+WidthOfTextBox,
                    whereToDrawText_y+(HeightOfTextBox/2+10),paintForText);

            canvas.drawArc(rectF, prevAngle, sweepAngle, true, paint);

            prevAngle=prevAngle+sweepAngle;
            indexOfText+=1;
        }
    }

    private float calculateSizeOfText(String text,float usedSpace){
        //calculate the appropriate size of the text
        int divideBy=Math.abs(text.length()-3)==0?1:Math.abs(text.length()-3);//To Eliminate divide by zero
        return (getWidth()-usedSpace)/(divideBy);
    }
}
