package com.namseox.myapplication.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.namseox.myapplication.R;
import com.namseox.myapplication.customview.BitmapUtils;

import java.util.Random;

public class CustomView extends View {
    BitmapUtils bitmapUtils;

    float imageX = 1;
    float imageY = 1;
    int ca_nho = 100;
    float x = ca_nho / 2;
    float y = ca_nho / 2;
    int spead = 10;
    float botX = ca_nho / 2;
    float botY = ca_nho / 2;


    public CustomView(Context context) {
        super(context);
        bitmapUtils = new BitmapUtils(context);
        addHandleTouch();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        Paint paint = new Paint();
        //backgroud
        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.backgroud_bien, (int) width, (int) height), 0, 0, new Paint());
        //draw shape
        // canvas.drawCircle(width / 2, height / 2, 100, paint);
        //draw image
        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish, ca_nho, ca_nho), (x - ca_nho / 2), (y - ca_nho / 2), new Paint());
        if (!(x - spead < imageX && imageX < x + spead && y - spead < imageY && imageY < y + spead)) {
            x = (float) (x + spead * (imageX - x) / Math.sqrt((imageX - x) * (imageX - x) + (imageY - y) * (imageY - y)));
            y = (float) (y + spead * (imageY - y) / Math.sqrt((imageX - x) * (imageX - x) + (imageY - y) * (imageY - y)));
            Log.d("toa_do", imageX + "...." + imageY + "------" + x + "...." + y);

//            Random random = new Random();
//            Paint paint1 = new Paint();
//            float botWith = 700;
//            float botHeight = 700;
//            canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish2, ca_nho, ca_nho), 300, 300, paint1);
//            Log.d("toado2",)
//            botX = (float) (botX + spead * (botWith - botX) / Math.sqrt((botWith - botX) * (botWith - botX) + (botHeight - botY) * (botHeight - botY)));
//            botY = (float) (botY + spead * (botHeight - botY) / Math.sqrt((botWith - botX) * (botWith - botX) + (botHeight - botY) * (botHeight - botY)));
//            if(botX-spead < botWith && botWith < botX+ spead && botY - spead < botHeight && botHeight < botY + spead ){
//                botWith = random.nextInt((int) imageX);
//                botHeight = random.nextInt((int) imageY);
//            }
        }

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
                        break;

                }
                return false;
            }
        });
    }
}
