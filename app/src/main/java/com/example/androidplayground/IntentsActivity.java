package com.example.androidplayground;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntentsActivity extends Activity {
    private TextView urlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
        urlTextView = findViewById(R.id.txtUrlAddress);
    }

    public void visitUrlAddress(View v) {
        String urlAddress = urlTextView.getText().toString();
        if (urlAddress != null) {
            if (!urlAddress.startsWith("http://") || !urlAddress.startsWith("https://"))
                urlAddress = "http://" + urlAddress;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(urlAddress));
            startActivity(i);
        }
    }
}