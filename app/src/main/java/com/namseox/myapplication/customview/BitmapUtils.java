package com.namseox.myapplication.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.namseox.myapplication.R;


public class BitmapUtils {
    Context context;
    public BitmapUtils(Context context) {
        this.context = context;
    }
    public Bitmap loadBitmap(int id,float width, float heigh) {
        Bitmap  bm = BitmapFactory.decodeResource(context.getResources(), id);
        bm = Bitmap.createScaledBitmap(bm,(int) width,(int) heigh,true);
        if (bm == null) {
            System.out.println("load bitmap failed");
        }
         return bm;
    }

    Bitmap cropBitmap(Bitmap bitmap, int x, int y, int width, int height) {
       return Bitmap.createBitmap(bitmap, x, y ,width,height);
    }

    Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
    }

}
