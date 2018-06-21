package com.haibeey.statisticviewsproject;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.splash.android.statisticviews.piechart.*;
import com.splash.android.statisticviews.piechart.PieChart;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager=(ViewPager)findViewById(R.id.holder);
        viewPager.setAdapter(new MyTabLayoutAdapter(getSupportFragmentManager()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private static  void ForPieChart(){
        ArrayList<Float> arrayList=new ArrayList<>();
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);

        ArrayList<String> strings=new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("b");
        strings.add("b");
        strings.add("b");
        strings.add("b");

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
