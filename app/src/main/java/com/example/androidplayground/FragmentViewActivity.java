package com.example.androidplayground;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class FragmentViewActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}
