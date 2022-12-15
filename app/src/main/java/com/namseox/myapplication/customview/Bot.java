package com.namseox.myapplication.customview;

public class Bot extends GameObject{
    //vi tri ma con bot muon toi
    public int toaDoX, toadoY = -1;

    public Bot(int x, int y, int width, int heigh, int spead, int type) {
        super(x, y, width, heigh, spead, type);
    }


    public Bot() {
    }
}
