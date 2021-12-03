package com.example.quiz;

import android.app.Activity;
//import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

public class FragmentStart extends Fragment {

//    public interface onSomeEventListener{
//        public void someEvent(String s);
//    }

    onSomeEventListener someEventListener;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            someEventListener = (onSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragmentstart, null);

        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);

        Button btnStart = (Button) v.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener.someEvent("Quiz");
            }
        });
        Button btnLeader = (Button) v.findViewById(R.id.btnLeader);
        btnLeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener.someEvent("Leader");
            }
        });
        return v;
    }
}
