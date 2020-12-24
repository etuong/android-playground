package com.example.androidplayground;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AdvancedComponentActivity extends Activity {
    CheckBox chkVal;
    RadioGroup rdgVal;
    Spinner spinner;
    ProgressBar pb;
    TimePicker tpTime;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_components);

        chkVal = findViewById(R.id.chkVal);
        rdgVal = findViewById(R.id.rdgVal);
        spinner = findViewById(R.id.spnOptions);
        pb = findViewById(R.id.progressBar);
        tpTime = findViewById(R.id.tpTime);

        loadSpinner();

        Button btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(v -> {
            int selected = rdgVal.getCheckedRadioButtonId();
            RadioButton rbVal = findViewById(selected);
            String spinnerValue = spinner.getSelectedItem().toString();

            String text = chkVal.isChecked() + " | " + rbVal.getText() + " | " + spinnerValue;
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        });

        Handler hdlr = new Handler();

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(v -> {
            final int initialTimeMinute = tpTime.getMinute();
            new Thread(() -> {
                for (int i = 0; i <= 5; i++) {
                    int finalI = i;
                    final int minuteTime = initialTimeMinute + finalI > 59 ? 0 : initialTimeMinute + finalI;
                    hdlr.post(() -> {
                        pb.setProgress(20 * finalI);
                        tpTime.setMinute(minuteTime);
                    });
                    SystemClock.sleep(1000);
                }
            }).start();
        });
    }

    private void loadSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
