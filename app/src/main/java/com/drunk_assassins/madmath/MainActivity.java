package com.drunk_assassins.madmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

        final int mPSoundId = mSoundPool.load(this, R.raw.play_btn, 1);
        final int mESoundId = mSoundPool.load(this, R.raw.exit_btn, 1);
        final int mHSoundId = mSoundPool.load(this, R.raw.high_score_btn, 1);

        findViewById(R.id.playBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.playBtn).setBackgroundResource(R.drawable.play_btn_press);
                findViewById(R.id.loading_image).setVisibility(View.VISIBLE);
                mSoundPool.play(mPSoundId, 1.0f, 1.0f, 0, 0, 1.0f);
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
                finish();
            }
        });

        findViewById(R.id.exitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.exitBtn).setBackgroundResource(R.drawable.exit_btn_press);
                mSoundPool.play(mESoundId, 1.0f, 1.0f, 0, 0, 1.0f);
                finish();
            }
        });

        findViewById(R.id.highscoreBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.highscoreBtn).setBackgroundResource(R.drawable.high_score_btn_press);
                mSoundPool.play(mHSoundId, 1.0f, 1.0f, 0, 0, 1.0f);
                startActivity(new Intent(MainActivity.this, HighScoreActivity.class));
                finish();
            }
        });
        

    }

}
