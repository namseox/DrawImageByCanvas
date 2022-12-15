package com.namseox.myapplication.customview;

public class GameObject {
    //x,y là toạ độ ảnh
    //width,heigh là bề ngang, chiều cao ảnh
    public int x,y,width,heigh,spead,type;
    public boolean isLive = true;

    public GameObject(int x, int y, int width, int heigh, int spead, int type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigh = heigh;
        this.spead = spead;
        this.type = type;
    }

    public GameObject() {
    }
}
