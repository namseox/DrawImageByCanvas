package com.namseox.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView helloText;
    TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindow();

        setContentView(R.layout.activity_main);
//        bindingView();
        addHandleTouch();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }




    void setupWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    void addHandleTouch() {
        int i = 0;
        View root = this.findViewById(android.R.id.content).getRootView();
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                count.setText(java.time.LocalTime.now().toString());
                switch (motionEvent.getAction()) {
                    case  MotionEvent.ACTION_UP:
//                        helloText.setText("TouchUP - x: " + motionEvent.getX() +", y: "+   motionEvent.getY());
                        openGameDraw();
                        break;
//                    case MotionEvent.ACTION_DOWN:
//                        helloText.setText("TouchDOWN - x: " + motionEvent.getX() +", y: "+   motionEvent.getY());
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        helloText.setText("TouchMove - x: " + motionEvent.getX() +", y: "+   motionEvent.getY());
//                        break;
                }
                return false;
            }
        });
    }

    void openGameDraw() {
        Intent intent = new Intent(this, GameDraw.class);
        startActivity(intent);
    }

}