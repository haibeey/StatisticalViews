package com.haibeey.statisticviewsproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splash.android.statisticviews.histogram.Histogram;

import java.util.ArrayList;


public class HistogramView extends Fragment {

    public HistogramView() {
        // Required empty public constructor
    }

    public static HistogramView newInstance() {
        HistogramView fragment = new HistogramView();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_histogram, container, false);
        Histogram histogram=(Histogram) view.findViewById(R.id.histogram_id);

        ArrayList<Float> arrayList=new ArrayList<>();
        arrayList.add(100f);
        arrayList.add(90f);
        arrayList.add(97f);
        arrayList.add(240f);
        arrayList.add(89f);
        arrayList.add(300f);

        ArrayList<String> strings=new ArrayList<>();
        strings.add("2.0f");
        strings.add("2f");
        strings.add("2f");
        strings.add("3f");
        strings.add("4f");
        strings.add("5f");

        histogram.setFrequencyAndXAxis(arrayList,strings);
        return view;
    }
}
