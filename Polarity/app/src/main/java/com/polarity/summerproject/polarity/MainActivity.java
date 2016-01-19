package com.polarity.summerproject.polarity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

// TODO: make "how do I play" button + matching activity with all the instructions. Consider using animations
public class MainActivity extends Activity {
    Button startButton, howToPlayButton;
    TextView highScore;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("game", 0);
        spEditor = sp.edit();
        highScore = (TextView) findViewById(R.id.high_score);

        // grab high score
        if(sp.contains("highScore")){
            highScore.setText("High Score: " + sp.getInt("highScore", 0));
        }


        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(MainActivity.this, GameActivity.class); // GAME CLASS
                startActivity(goToGame);
            }
        });

        howToPlayButton = (Button) findViewById(R.id.how_to_play_btn);
        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToInstr = new Intent(MainActivity.this, InstructionsActivity.class); // INSTRUCTIONS PAGE
                startActivity(goToInstr);
            }
        });
    }
/*
    public void updateHighScore(Bundle bundle){
        if(bundle.size() != 0){
            try{
                int i = bundle.getInt("score");
                highScore.setText("High Score: " + i);
            } catch(Exception e){
                // set to zero
                highScore.setText("High Score: 0");
            }
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(sp.contains("highScore")){
            highScore.setText("High Score: " + sp.getInt("highScore", 0));
        }
    }
}
