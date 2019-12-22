package com.drunk_assassins.madmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    Button plusBtn, minusBtn, multBtn, startBtn;
    String questionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        questionType = "+";         // Default Question Type

        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        final int keyTap = mSoundPool.load(this, R.raw.menu_btn_select, 1);
        final int newGame = mSoundPool.load(this, R.raw.start_game, 1);

        plusBtn = findViewById(R.id.plus_btn);
        minusBtn = findViewById(R.id.minus_btn);
        multBtn = findViewById(R.id.mult_btn);
        startBtn = findViewById(R.id.start_btn);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectBtns();
                plusBtn.setBackgroundResource(R.drawable.plus_btn_active);
                mSoundPool.play(keyTap, 1.0f, 1.0f, 0, 0, 1.0f);
                questionType = "+";
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectBtns();
                minusBtn.setBackgroundResource(R.drawable.minus_btn_active);
                mSoundPool.play(keyTap, 1.0f, 1.0f, 0, 0, 1.0f);
                questionType = "-";
            }
        });

        multBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectBtns();
                multBtn.setBackgroundResource(R.drawable.mult_btn_active);
                mSoundPool.play(keyTap, 1.0f, 1.0f, 0, 0, 1.0f);
                questionType = "*";
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBtn.setBackgroundResource(R.drawable.loading_menu);
                mSoundPool.play(keyTap, 1.0f, 1.0f, 0, 0, 1.0f);
                Intent gameActivity = new Intent(MenuActivity.this, GameActivity.class);
                gameActivity.putExtra("QUESTION_TYPE", questionType);
                startActivity(gameActivity);
                mSoundPool.play(newGame, 1.0f, 1.0f, 0, 0, 1.0f);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void unSelectBtns() {
        plusBtn.setBackgroundResource(R.drawable.plus_btn);
        minusBtn.setBackgroundResource(R.drawable.minus_btn);
        multBtn.setBackgroundResource(R.drawable.mult_btn);
    }
}
