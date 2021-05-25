package com.yash10019coder.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.yash10019coder.eggtimerapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int timer;
    private ActivityMainBinding binding;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        timer = 0;
//        binding.seekBar.setProgress(1);
        binding.seekBar.setProgress(1);
        binding.seekBar.setMax(300);
        binding.textView.setText("0 : 0 : 0");
        mediaPlayer=MediaPlayer.create(this,R.raw.alarm);
        mediaPlayer.setLooping(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textView.setText(secondToReadabelTime(progress));
//                binding.textView.setText(String.valueOf(progress));
                timer = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "onClick: ");
            new CountDownTimer(timer*1000,1000)
            {

                @Override
                public void onTick(long millisUntilFinished) {
                    binding.textView.setText(secondToReadabelTime((int)millisUntilFinished/1000));
                    Log.i("TAG", "onTick: timer");
                }

                @Override
                public void onFinish() {
                mediaPlayer.start();
                    Log.i("TAG", "onFinish: ");
                }
            }.start();
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }

    protected String secondToReadabelTime(int t) {
        String s;
        int rem = 0;
        int div = 0;
        div = t / 60;
        rem = t % 60;
        s = String.valueOf(rem);

        t = div;
        div = t / 60;
        rem = t % 60;
        s = String.valueOf(rem) + " : " + s;

        t = div;
        div = t / 60;
        rem = t % 60;
        s = String.valueOf(rem) + " : " + s;

        return s;
    }
}