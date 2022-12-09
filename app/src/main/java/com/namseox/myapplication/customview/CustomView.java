package com.namseox.myapplication.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.namseox.myapplication.R;
import com.namseox.myapplication.customview.BitmapUtils;

public class CustomView extends View {
    BitmapUtils bitmapUtils;

    float imageX = 0;
    float imageY = 0;
    float x=0;
    float y=0;
    int spead = 10;

    public CustomView(Context context) {
        super(context);
        bitmapUtils = new BitmapUtils(context);
        addHandleTouch();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        Bitmap bitmapBackGround = bitmapUtils.loadBitmap(R.drawable.backgroud_bien);
        bitmapBackGround = Bitmap.createScaledBitmap(bitmapBackGround,(int) width,(int) height,true);
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.BLUE);
        //backgroud
        canvas.drawBitmap(bitmapBackGround,0,0,new Paint());
        //
        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.pot), x, y, new Paint());
        //draw shape
        canvas.drawCircle(width / 2, height / 2, 100, paint);
        canvas.drawText("width: " + width + ", height: " + height, 100, 100, paint);

        //draw image
        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.pot), x, y, new Paint());

        invalidate();
    }


    void addHandleTouch() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imageX = motionEvent.getX();
                        imageY = motionEvent.getY();
                        x = (float) (x+ spead*(imageX - x)/Math.sqrt((imageX-x)*(imageX-x)+(imageY-y)*(imageY-y)));
                        y = (float) (y+ spead*(imageY - y)/Math.sqrt((imageX-x)*(imageX-x)+(imageY-y)*(imageY-y)));
                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        imageX = motionEvent.getX();
//                        imageY = motionEvent.getY();
//                        x = (float) (x+ spead*(imageX - x)/Math.sqrt((imageX-x)*(imageX-x)+(imageY-y)*(imageY-y)));
//                        y = (float) (y+ spead*(imageY - y)/Math.sqrt((imageX-x)*(imageX-x)+(imageY-y)*(imageY-y)));
//
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        imageX = motionEvent.getX();
//                        imageY = motionEvent.getY();
//                        x = (float) (x+ spead*(imageX - x)/Math.sqrt((imageX-x)*(imageX-x)+(imageY-y)*(imageY-y)));
//                        y = (float) (y+ spead*(imageY - y)/Math.sqrt((imageX-x)*(imageX-x)+(imageY-y)*(imageY-y)));
//
//                        break;
                }
                return false;
            }
        });
    }
}
