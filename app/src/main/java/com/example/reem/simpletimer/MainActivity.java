package com.example.reem.simpletimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button startButton;
    Boolean timerIsActive = false;
    CountDownTimer CDT;

    int seekBarMin = 30;

    public void resetTimer(){

        timerTextView.setText("00:30");
        timerSeekBar.setEnabled(true);
        timerSeekBar.setProgress(seekBarMin);
        startButton.setText("Count Down");
        CDT.cancel();
        timerIsActive = false;

    }

    public void startCountingDown(View view) {

        if (timerIsActive) {

            resetTimer();

        } else {
            timerIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("Stop");

            CDT = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    MediaPlayer alarm = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    alarm.start();
                    resetTimer();
                }
            }.start();

        }
    }

    public void updateTimer(int secondLeft) {
        int minutes = secondLeft / 60;
        int seconds = secondLeft - (minutes * 60);

        String secondsString = Integer.toString(seconds);
        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }
        String minutesString = Integer.toString(minutes);

        if (minutes <= 9) {
            minutesString = "0" + minutesString;
        }
        timerTextView.setText(minutesString + ":" + secondsString);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);

        timerSeekBar.setProgress(seekBarMin);

        int max = 600;  // max =10 min
        timerSeekBar.setMax(max);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

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
