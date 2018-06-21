package com.haibeey.statisticviewsproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splash.android.statisticviews.graph.Graph;

import java.util.ArrayList;


public class GraphView extends Fragment {

    public GraphView() {
        // Required empty public constructor
    }

    public static GraphView newInstance() {
        GraphView fragment = new GraphView();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_graph, container, false);
        Graph graph=(Graph)view.findViewById(R.id.graph_id);

        ArrayList<Float> arrayList=new ArrayList<>();
        arrayList.add(10f);
        arrayList.add(20f);
        arrayList.add(30f);
        arrayList.add(40f);
        arrayList.add(50f);
        arrayList.add(60f);


        ArrayList<Float> strings=new ArrayList<>();
        strings.add(10.0f);
        strings.add(20f);
        strings.add(30f);
        strings.add(40f);
        strings.add(50f);
        strings.add(60f);
        graph.setY_axisAndX_Axis(arrayList,strings);

        return view;
    }

}
