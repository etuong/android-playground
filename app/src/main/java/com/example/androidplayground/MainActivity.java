package com.example.androidplayground;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupChaptersListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupChaptersListView() {
        ListView listView = findViewById(R.id.mainListView);
        String[] topics = getResources().getStringArray(R.array.topics);
        TopicListAdapter listAdapter = new TopicListAdapter(this, Arrays.asList(topics));
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) (adapter, v, position, id) -> {
            String topicTitle = (String) adapter.getItemAtPosition(position);
            Class<? extends Activity> topicClass = listAdapter.getTopicClass(position);
            if (topicClass != null) {
                Toast.makeText(MainActivity.this, topicTitle, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, topicClass));
            } else {
                Toast.makeText(MainActivity.this, "Exercise Not Available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class TopicListAdapter extends ArrayAdapter {
        public TopicListAdapter(Context context, List<String> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            String topic = (String) getItem(position);
            TextView textView = new TextView(MainActivity.this);
            textView.setText(topic);
            textView.setLayoutParams(lp);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            textView.setPadding(80, 20, 20, 20);
            return textView;
        }

        public Class<? extends Activity> getTopicClass(int position) {
            String topicId = "topic" + (position + 1);
            return ActivityMapper.getExerciseClass(topicId);
        }
    }
}
