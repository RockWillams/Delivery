package com.wine.delivery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wine.delivery.R;


/**
 * @ClassName: TestFragment
 * @Description 测试界面
 * @author 柏江龙
 * @date 2015-4-11
 */
public class TestFragment extends DeliveryFragment {


    public TestFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_test_layout, container, false);
        ((TextView)view.findViewById(R.id.message)).setText("fsda");
        return view;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public String getTitle() {
        return "Test";
    }
}
