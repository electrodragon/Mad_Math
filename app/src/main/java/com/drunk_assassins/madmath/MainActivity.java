package com.drunk_assassins.madmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playBtnClicked(View view) {
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }

    public void highScoreBtnClicked(View view) {
        Intent hScoreIntent = new Intent(this, HighScoreActivity.class);
        startActivity(hScoreIntent);
    }

    public void exitBtnClicked(View view) {
        finish();
    }
}
