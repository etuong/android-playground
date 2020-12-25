package com.example.androidplayground;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class PersistenceActivity extends Activity {
    SharedPreferences prefs;
    TextView txtPersist;
    CheckBox chkPersist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistence);

        txtPersist = findViewById(R.id.txtPersistText);
        chkPersist = findViewById(R.id.chkPersistState);
        prefs = getSharedPreferences("view", 0);
        restoreValues();
    }

    public void restoreValues() {
        String persistedText = prefs.getString("txtVal", "");
        boolean isChecked = prefs.getBoolean("chkState", false);
        txtPersist.setText(persistedText);
        chkPersist.setChecked(isChecked);
    }

    public void persistValues(View v) {
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString("txtVal", txtPersist.getText().toString());
        edits.putBoolean("chkState", chkPersist.isChecked());
        edits.commit();

        Toast.makeText(this, "Data Are Persisted!", Toast.LENGTH_SHORT).show();
    }
}
