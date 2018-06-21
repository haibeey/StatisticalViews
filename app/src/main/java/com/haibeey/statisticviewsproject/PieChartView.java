package com.haibeey.statisticviewsproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splash.android.statisticviews.piechart.PieChart;

import java.util.ArrayList;


public class PieChartView extends Fragment {


    public PieChartView() {
        // Required empty public constructor
    }

    public static PieChartView newInstance() {
        PieChartView fragment = new PieChartView();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie_chart_view, container, false);
        PieChart pieChart = (PieChart) view.findViewById(R.id.pie);


        ArrayList<Float> arrayList = new ArrayList<>();
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);
        arrayList.add(60f);


        ArrayList<String> strings = new ArrayList<>();
        strings.add("abahsjh");
        strings.add("dddknskjnsb");
        strings.add("bdkmskms");
        strings.add("bjlslsj");
        strings.add("bdlsls");
        strings.add("bls,;ls,sd");

        pieChart.setItemsAndNameOfItems(arrayList, strings);
        return view;
    }

}
