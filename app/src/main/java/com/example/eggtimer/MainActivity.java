package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;
    MediaPlayer airHorn;
    boolean counterActive;
    Button goButton;
    CountDownTimer countDown;

    public void resetTimer(){

        countDown.cancel();
        goButton.setText("GO!");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        counterActive = false;
    }

    public void updateTime(int timeLeft)
    {

        int minute = timeLeft/60;
        int second = timeLeft- (minute*60);
        String secondTime = Integer.toString(second);
        if(second <=9)
        {
            secondTime = "0" + Integer.toString(second);
        }

        timerTextView.setText(Integer.toString(minute) + ":" + secondTime);
    }

    public void buttonClicked(View view)
    {
        if(counterActive)
        {
            resetTimer();
        }
        else {
            counterActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP");
            countDown = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                public void onTick(long l) {
                    updateTime((int) l / 1000);
                }

                public void onFinish() {
                    resetTimer();
                }
            }.start();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            timerSeekBar = findViewById(R.id.timerSeekBar);
            timerTextView = findViewById(R.id.timerTextView);
            timerSeekBar.setMax(600);
            timerSeekBar.setProgress(30);
            goButton = findViewById(R.id.goButton);

            timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {

                    updateTime(i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });





    }
}
