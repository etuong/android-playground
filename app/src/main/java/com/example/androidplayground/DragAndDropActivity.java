package com.example.androidplayground;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class DragAndDropActivity extends Activity implements View.OnTouchListener, View.OnDragListener {
    Button btnYes, btnNo;
    ImageView imgDestination;
    TextView yesCount;
    TextView noCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        imgDestination = findViewById(R.id.imgDestination);
        yesCount = findViewById(R.id.yesCount);
        noCount = findViewById(R.id.noCount);

        btnYes.setOnTouchListener(this);
        btnNo.setOnTouchListener(this);
        imgDestination.setOnDragListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        View.DragShadowBuilder mShadow = new View.DragShadowBuilder(v);
        ClipData.Item item = new ClipData.Item(v.getTag().toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);

        switch (v.getId()) {
            case R.id.btnYes:
            case R.id.btnNo:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, mShadow, null, 0);
                } else {
                    v.startDrag(data, mShadow, null, 0);
                }
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                ((ImageView) v).setColorFilter(Color.YELLOW);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:
                String clipData = event.getClipDescription().getLabel().toString();
                switch (clipData) {
                    case "YES":
                        ((ImageView) v).setColorFilter(ContextCompat.getColor(DragAndDropActivity.this, R.color.blue), android.graphics.PorterDuff.Mode.MULTIPLY);
                        break;
                    case "NO":
                        ((ImageView) v).setColorFilter(ContextCompat.getColor(DragAndDropActivity.this, R.color.purple_200), android.graphics.PorterDuff.Mode.MULTIPLY);
                        break;
                }
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                ((ImageView) v).clearColorFilter();
                ((ImageView) v).setColorFilter(Color.YELLOW);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                clipData = event.getClipDescription().getLabel().toString();
                switch (clipData) {
                    case "YES":
                        if (yesCount.getText().toString().isEmpty()) {
                            yesCount.setText("1");
                        } else {
                            yesCount.setText(String.valueOf(Integer.parseInt(yesCount.getText().toString()) + 1));
                        }
                        break;
                    case "NO":
                        if (noCount.getText().toString().isEmpty()) {
                            noCount.setText("1");
                        } else {
                            noCount.setText(String.valueOf(Integer.parseInt(noCount.getText().toString()) + 1));
                        }
                        break;
                }
                Toast.makeText(getApplicationContext(), clipData, Toast.LENGTH_SHORT).show();
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                ((ImageView) v).clearColorFilter();
                if (!event.getResult()) {
                    Toast.makeText(DragAndDropActivity.this, "Aw Snap! Try dropping it again", Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                return false;
        }
    }
}