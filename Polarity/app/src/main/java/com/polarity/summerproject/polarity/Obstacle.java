package com.polarity.summerproject.polarity;

import android.graphics.Color;

public class Obstacle {
    int color;
    int size, y, x, speed;

    public Obstacle(int color, int size, int x, int y, int speed) {
        this.color = color;
        this.y = y;
        this.size = size;
        this.x = x;
        this.speed = speed;
    }

    public void moveY() {
        this.y = this.y + speed;
    }

    public void decreaseLength(Integer length) {
        this.size =- 10;
    }
}
