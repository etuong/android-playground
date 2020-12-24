package com.example.androidplayground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BasicComponentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_components);

        EditText editText = findViewById(R.id.bcvEditText);
        Button button = findViewById(R.id.bcvButton);
        TextView textView = findViewById(R.id.bcvTextView);

        button.setOnClickListener(v -> {
            String name = editText.getText().toString();
            textView.setText("Hello " + name);
        });

        Button javaBtn = (Button) findViewById(R.id.javaBtn);
        javaBtn.setOnClickListener(v -> secondButtonClicked(v));
    }

    public void xmlButtonClicked(View v) {
        SimpleAlertDialog.displayWithOK(this, "Button clicked via XML handler");
    }

    private void secondButtonClicked(View v) {
        SimpleAlertDialog.displayWithOK(this, "Button clicked via Java handler in onCreate");
    }
}
