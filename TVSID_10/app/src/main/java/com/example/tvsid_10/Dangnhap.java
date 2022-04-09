package com.example.tvsid_10;

import static com.example.tvsid_10.MainActivity.down;
import static com.example.tvsid_10.MainActivity.up;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Dangnhap extends Fragment {



    public Dangnhap() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dangnhap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Animation animator= AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
        view.findViewById(R.id.logofood).startAnimation(animator);
        view.findViewById(R.id.imageView).startAnimation(animator);
        view.findViewById(R.id.btn_login_login).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.findViewById(R.id.btn_login_login).startAnimation(up);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        view.findViewById(R.id.btn_login_login).startAnimation(down);
                        startActivity(new Intent(getActivity(),Home.class));
                    }
                }
                return true;
            }
        });
    }
}