package com.example.androidplayground;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BroadcastExampleActivity extends Activity {
    private AirplaneModeChangeReceiver amcReceiver = new AirplaneModeChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_example);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(amcReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(amcReceiver);
    }
}
