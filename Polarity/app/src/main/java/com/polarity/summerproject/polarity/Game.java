package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import com.polarity.summerproject.polarity.Dir;

public class Game{
    //int testX, testY, sizeX, sizeY;
    Paint paint;
    Paint bg;
    Screen screen;
    ObstacleHolder obsHolder;
    Context context;
    static int score;
    static int SCORE = 50;
    static int SPEED_INTERVAL = 7; // increase speed by this much
    static int MAX_SPEED = 75;
    int speedUpTime = 8000; // 10 seconds
    private int tapThreshold = 12; // must be within 5 px to be a TAP


    long prevTime;

    public Game(Screen screen) {
        paint = new Paint();
        bg = new Paint();
        bg.setColor(Color.BLACK);
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(100);

        prevTime = System.currentTimeMillis();
        Game.score = 0;

        this.screen = screen;
        obsHolder = new ObstacleHolder(this.screen, 200, 8);   // ENTER OBSTACLE SIZE AND SPEED HERE.
    }
    // TODO: make speed-ups score-based after awhile
    public boolean update(){
        // increase speed
        if(System.currentTimeMillis() - prevTime >= speedUpTime){
            obsHolder.speedUp();
            prevTime = System.currentTimeMillis();
        }

        // basic move and check
        obsHolder.moveObj();
        if (obsHolder.colCheck() == true){ // true terminates game
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas){    // DRAW OBSTACLE IN ITS OWN FUNCTION? probably

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), bg);       // draw bg
        obsHolder.draw(canvas);

        // score center alignment
        Rect bounds = new Rect();
        String text = String.valueOf(score);
        paint.getTextBounds(text, 0, text.length(), bounds);
        int textX = (canvas.getWidth()/2) - (bounds.width()/2);
        System.out.println(text);
        canvas.drawText(text, screen.getWidth()/2 , 100, paint);
    }

    public void onSwipeRelease(float oldX, float oldY, float newX, float newY){
        float deltaX = oldX - newX;
        float deltaY = oldY - newY;
        Dir direction = Dir.STAY;
        if(Math.abs(deltaX) <= tapThreshold && Math.abs(deltaY) <= tapThreshold){ // detect tap
            direction = Dir.UP;
        }else if(Math.abs(deltaX) > Math.abs(deltaY)) { // if LEFT/RIGHT motion stronger
            if(deltaX < 0) {// RIGHT
                direction = Dir.RIGHT;
            }else{
                direction = Dir.LEFT;
            }
        }else{  // UP/DOWN motion stronger.
            if(deltaY < 0){
                direction = Dir.DOWN;
            }else{
                direction = Dir.UP;
            }
        }
        obsHolder.playerMove(direction);
        Game.score+=Game.SCORE;
        //score+=50;
    }

    // TODO: switch to swipe-based movement

    private int btnPointerId = -1, swipePointerId = -1;
    private float oldX, oldY, newX, newY;

    public boolean onTouch(MotionEvent me){

        int action = MotionEventCompat.getActionMasked(me);

        switch(action) {
            case(MotionEvent.ACTION_DOWN) :
                System.out.println("ACTION DOWN");
                if(me.getY() > obsHolder.btn.y && me.getX() < obsHolder.btn.width){
                    btnPointerId = me.getPointerId(0);
                    //System.out.println(btnPointerId);
                    obsHolder.playerChange(true); // change to white
                }else{ // else swipe event
                    swipePointerId = me.getPointerId(0);
                    oldX = me.getX();
                    oldY = me.getY();
                }
                return true;
            case(MotionEvent.ACTION_UP) :
                System.out.println("ACTION UP");
                if(me.getPointerId(0) == btnPointerId) { // button released TODO: ??? check over this part
                    obsHolder.playerChange(false); // change to black
                    btnPointerId = -1;
                }else{ // swipe released
                    newX = me.getX();
                    newY = me.getY();
                    // calculate swipe direction
                    onSwipeRelease(oldX, oldY, newX, newY);
                    swipePointerId = -1;
                }
                return true;
            case(MotionEvent.ACTION_POINTER_DOWN) : // deal with multi touch
                System.out.println("ACTION POINTER DOWN");
                // only two cases can exist: btn already pushed, swipe pushed next
                // swipe in progress, btn pushed next
                //if(MotionEventCompat.getActionIndex(me) == btnPointerIndex){
                if(btnPointerId == -1) { // button has not yet been pressed,
                    btnPointerId = me.getPointerId(MotionEventCompat.getActionIndex(me));
                    obsHolder.playerChange(true); // change to white
                }else{
                    swipePointerId = me.getPointerId(MotionEventCompat.getActionIndex(me));
                    oldX = me.getX(MotionEventCompat.getActionIndex(me));
                    oldY = me.getY(MotionEventCompat.getActionIndex(me));
                }

                return true;
            case(MotionEvent.ACTION_POINTER_UP) :
                System.out.println("ACTION POINTER UP");
                if(btnPointerId == me.getPointerId(MotionEventCompat.getActionIndex(me))){
                    // button released
                    obsHolder.playerChange(false); // change to black
                    btnPointerId = -1;
                }else{
                    // swipe released
                    newX = me.getX(MotionEventCompat.getActionIndex(me));
                    newY = me.getY(MotionEventCompat.getActionIndex(me));
                    // calculate swipe direction
                    onSwipeRelease(oldX, oldY, newX, newY);
                    swipePointerId = -1;
                }
                return true;
            default :
                return true;
        }
    }

    public void obsHolderInit(){
        this.obsHolder.playerInit(this.context);
    }

    public void setContext(Context context){
        this.context = context;
    }
}
