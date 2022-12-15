package com.namseox.myapplication.customview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.namseox.myapplication.R;
import com.namseox.myapplication.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {
    BitmapUtils bitmapUtils;
    //    int a, b = 100;
    //   int isLive = 1;
    boolean fishToright = true;
    float imageX = 1;
    float imageY = 1;
    int ca_nho = 100;
    float x = ca_nho / 2;
    float y = ca_nho / 2;
    int spead = 15;
    //    float botX = ca_nho / 2;
//    float botY = ca_nho / 2;
//    private int xRandom1 = 0, yRandom1 = 30, random1Speed = 15;
//    private int xRandom2 = -200, yRandom2 = 200, random2Speed = 15;
    List<Bot> botListSmall = new ArrayList<>();
    List<Bot> botListMedium = new ArrayList<>();
    List<Bot> botListBig = new ArrayList<>();
    List<Bot> botList = new ArrayList<>();
    Player player = new Player();
    Bot bot = new Bot();


    public CustomView(Context context) {
        super(context);
        bitmapUtils = new BitmapUtils(context);
        addHandleTouch();
    }

//    public void randomFish(Canvas canvas) {
//        xRandom1 = xRandom1 + random1Speed;
//        xRandom2 = xRandom2 + random2Speed;
//        if (xRandom1 > canvas.getWidth()) {
//            xRandom1 = 0;
//            yRandom1 = (int) Math.floor(Math.random() * canvas.getHeight());
//        }
//        if (xRandom2 > canvas.getWidth()) {
//            xRandom2 = 0;
//            yRandom2 = (int) Math.floor(Math.random() * canvas.getHeight());
//        }
//        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish2, ca_nho, ca_nho), (xRandom1), (yRandom1), new Paint());
//        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish2, ca_nho, ca_nho), (xRandom2), (yRandom2), new Paint());
//    }


    public void listBot(float width, float heigh) {
        if (player.isLive) {
            if (player.point < Const.level1) {
                if (botListSmall.size() < 7) {
                    botListSmall.add(creatBot(width, heigh, Const.typeSmall));
                }
                if (botListMedium.size() < 5) {
                    botListMedium.add(creatBot(width, heigh, Const.typeMedium));
                }
                botListBig.clear();
            }
            if (player.point < Const.level2 && player.point > Const.level1) {
                if (botListSmall.size() < 5) {
                    botListSmall.add(creatBot(width, heigh, Const.typeSmall));
                }
                if (botListMedium.size() < 3) {
                    botListMedium.add(creatBot(width, heigh, Const.typeMedium));
                }
                if (botListBig.size() < 5) {
                    botListBig.add(creatBot(width, heigh, Const.typeBig));
                }
            }
            if (player.point > Const.level2) {
                if (botListSmall.size() < 3) {
                    botListSmall.add(creatBot(width, heigh, Const.typeSmall));
                }
                if (botListMedium.size() < 4) {
                    botListMedium.add(creatBot(width, heigh, Const.typeMedium));
                }
                if (botListBig.size() < 5) {
                    botListBig.add(creatBot(width, heigh, Const.typeBig));
                }
            }
            if (botList.size() < 15) {
                botList.addAll(botListSmall);
                botList.addAll(botListMedium);
                botList.addAll(botListBig);
            }
        }
    }

    public Bot creatBot(float width, float heigh, int type) {
        Bot bot;
        int randomX = (int) Math.floor(Math.random() * 1000);
        int randomY = (int) Math.floor(Math.random() * 1000);
        switch (type) {
            case Const.typeSmall:
                bot = new Bot(-randomX, -randomY, Const.ca_nho, Const.ca_nho, Const.spead_nho, Const.typeSmall);
                break;
            case Const.typeMedium:
                bot = new Bot(-randomX, -randomY, Const.ca_tb, Const.ca_tb, Const.spead_tb, Const.typeMedium);
                break;
            case Const.typeBig:
                bot = new Bot(-randomX, -randomY, Const.ca_to, Const.ca_to, Const.spead_to, Const.typeBig);
                break;
            case Const.typeMax:
                bot = new Bot(-randomX, -randomY, Const.ca_Max, Const.ca_Max, Const.spead_max, Const.typeMax);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return bot;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        Paint paint = new Paint();
        //backgroud
        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.bg, (int) width, (int) height), 0, 0, paint);
        //draw image

        canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish, ca_nho, ca_nho), (x - ca_nho / 2), (y - ca_nho / 2), new Paint());
        if (!(x - spead < imageX && imageX < x + spead && y - spead < imageY && imageY < y + spead)) {
            x = (float) (x + spead * (imageX - x) / Math.sqrt((imageX - x) * (imageX - x) + (imageY - y) * (imageY - y)));
            y = (float) (y + spead * (imageY - y) / Math.sqrt((imageX - x) * (imageX - x) + (imageY - y) * (imageY - y)));
        }
        //        randomFish(canvas);
        listBot(width, height);
List<Bot> removeBot = new ArrayList<>();
        botList.forEach((bot) -> {
            if (bot.toaDoX > width || bot.toaDoX < 0 || bot.toadoY > height || bot.toadoY < 0 || Math.abs(bot.x - bot.toaDoX) < 50) {
                bot.toaDoX = (int) Math.floor(Math.random() * 1000);
                bot.toadoY = (int) Math.floor(Math.random() * 1000);
            }
            Log.d("listbot", bot.type + "    " + bot.width + "     " + bot.heigh + botListSmall.size() + "   " + botListMedium.size());
            //          xet va cham


            canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish2, bot.width, bot.heigh), bot.x, bot.y, paint);
            bot.x = (int) (bot.x + bot.spead * (bot.toaDoX - bot.x) / Math.sqrt((bot.toaDoX - bot.x) * (bot.toaDoX - bot.x) + (bot.toadoY - bot.y) * (bot.toadoY - bot.y)));
            bot.y = (int) (bot.y + bot.spead * (bot.toadoY - bot.y) / Math.sqrt((bot.toaDoX - bot.x) * (bot.toaDoX - bot.x) + (bot.toadoY - bot.y) * (bot.toadoY - bot.y)));
            if (new Rect((int) x - ca_nho / 2, (int) y - ca_nho / 2, (int) x + ca_nho / 2, (int) y + ca_nho / 2).intersect(new Rect(bot.x + 20, bot.y + 20, bot.x + ca_nho - 20, bot.y + ca_nho - 20))) {
                removeBot.add(bot);
            }
        });
        botList.removeAll(removeBot);
//
//        if (isLive > 0) {
//            a += spead / 3;
//            b += spead / 3;
//            canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish2, ca_nho, ca_nho), a, b, new Paint());
//
//        }
//
////        quay dau ca
//        if (fishToright) {
//            canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.img_1, ca_nho, ca_nho), (x - ca_nho / 2), (y - ca_nho / 2), new Paint());
//        } else {
//            canvas.drawBitmap(bitmapUtils.loadBitmap(R.drawable.fish, ca_nho, ca_nho), (x - ca_nho / 2), (y - ca_nho / 2), new Paint());
//        }
//

//

        invalidate();
    }

    void addHandleTouch() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        if (motionEvent.getX() > x) {
//                            fishToright = false;
//                        } else {
//                            fishToright = true;
//                        }
                        imageX = motionEvent.getX();
                        imageY = motionEvent.getY();
                        break;

                }
                return false;
            }
        });
    }
}
