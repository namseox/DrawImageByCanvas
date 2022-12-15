package com.namseox.myapplication.customview;

public class Player extends GameObject {
    public int point = 0;
    public int time = 0;
    public int lives = 3;
    public Player() {
    }

    public Player(int x, int y, int width, int heigh, int spead, int type) {
        super(x, y, width, heigh, spead, type);
    }
}
