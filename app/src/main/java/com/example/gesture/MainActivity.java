package com.example.gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;

import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnGesturePerformedListener{

    private GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureLibrary=GestureLibraries.fromRawResource(this,R.raw.gesture);
        if(!gestureLibrary.load())
        {
            finish();
        }
        GestureOverlayView gestureOverlayView=(GestureOverlayView)findViewById(R.id.widgetGesture);
        gestureOverlayView.addOnGesturePerformedListener(this);

    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

        ArrayList<Prediction> arrayList=gestureLibrary.recognize(gesture);
        if(arrayList.size() >0 && arrayList.get(0).score>1){
        String name= arrayList.get(0).name;
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
        }
    }
}