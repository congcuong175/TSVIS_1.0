package com.example.tvsid_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class Bat_dau extends Fragment {


    public Bat_dau() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bat_dau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Animation animator= AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
        view.findViewById(R.id.logofood).startAnimation(animator);
        view.findViewById(R.id.txt_tencuahang).startAnimation(animator);
        view.findViewById(R.id.imageView).startAnimation(animator);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Navigation.findNavController(view).navigate(R.id.action_bat_dau_to_dangnhap);
            }
        },2000);
    }
}