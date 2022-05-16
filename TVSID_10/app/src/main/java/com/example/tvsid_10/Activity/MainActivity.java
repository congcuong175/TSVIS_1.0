package com.example.tvsid_10.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.tvsid_10.R;

public class MainActivity extends AppCompatActivity {
    public static Animation up;
    public static Animation down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        up = AnimationUtils.loadAnimation(this, R.anim.up);
        down = AnimationUtils.loadAnimation(this, R.anim.down);
    }
}