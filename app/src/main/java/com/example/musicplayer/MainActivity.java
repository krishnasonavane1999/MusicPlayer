package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.new_song);

        //get context from audio service

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //set context to seekbar

        SeekBar volumeController = findViewById(R.id.seekBar);
        volumeController.setMax(maxVolume);
        volumeController.setProgress(currentVolume);

        //set listener on volume controller

        volumeController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                audioManager.setStreamVolume(audioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //time line part

        final SeekBar timeline;

        timeline = findViewById(R.id.timeline);

        timeline.setMax(mediaPlayer.getDuration());



        //set on change listener on timeline


        timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //custmize timeline seekbar

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                timeline.setProgress(mediaPlayer.getCurrentPosition());

            }
        }, 0, 10000);

    }

    public void playMe(View view){

        mediaPlayer.start();

    }

    public void pauseMe(View view){

        mediaPlayer.pause();
    }
}
