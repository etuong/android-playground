package com.example.androidplayground;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

public class NotificationActivity extends Activity {
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        b1 = findViewById(R.id.button);
        b1.setOnClickListener(v -> addNotification());
    }

    private void addNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "CHANNEL_ID";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "PRACTICE", NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_happy)
                .setContentTitle("Ethan's Notifications")
                .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, DragAndDropActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        manager.notify(0, builder.build());
    }
}
