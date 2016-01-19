package com.polarity.summerproject.polarity;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity{
    Game game;
    Screen screen;
    protected GameSurfaceView gameView;
    final MediaPlayer mp = new MediaPlayer();
    AssetFileDescriptor afd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screen = new Screen(size.y, size.x); // SCREEN

        game = new Game(screen); // GAME DECLARATION
        game.setContext(this);
        game.obsHolderInit();

        gameView = new GameSurfaceView(this, game);
        setContentView(gameView);
        // music player
        try{
            mp.reset();
            afd = getAssets().openFd("game_song.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        }   catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Get Touch Input
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {
                // DO THINGS WITH TOUCH EVENTS
                return game.onTouch(me);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        // resume music
        try{
            mp.reset();
            afd = getAssets().openFd("game_song.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        }   catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
        mp.pause();
    }
    @Override
    public void onStop(){
        super.onStop();
        mp.stop();
    }
}