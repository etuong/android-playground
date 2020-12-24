package com.example.androidplayground;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageActivity extends Activity {
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_download);
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        String imageAddress = "https://media-exp1.licdn.com/dms/image/C5603AQGpsUsYDUu0jA/profile-displayphoto-shrink_400_400/0/1580958205996?e=1614211200&v=beta&t=mWxy1ra0gl1B4IYxJtAL2gYPWYSq8t7-lQJpW-8hiWk";
        downloadImageFromUri(imageAddress);
    }

    private void downloadImageFromUri(String address) {
        try {
            URL url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            if (bitmap != null) {
                ImageView img = findViewById(R.id.ivBasicImage);
                img.setImageBitmap(bitmap);
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
