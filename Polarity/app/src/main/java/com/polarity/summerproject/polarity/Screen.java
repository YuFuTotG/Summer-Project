package com.polarity.summerproject.polarity;

/**
 * Screen contains information about the device
 */
public class Screen {
    public static int SCREEN_HEIGHT, SCREEN_WIDTH;

    public Screen(int height, int width){
        this.SCREEN_HEIGHT = height;
        this.SCREEN_WIDTH = width;
    }

    public int getHeight(){
        return this.SCREEN_HEIGHT;
    }

    public int getWidth(){
        return this.SCREEN_WIDTH;
    }

}
