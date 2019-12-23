package com.drunk_assassins.madmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.drunk_assassins.madmath.custom_utils.MyMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    TextView currentScore, currentLevel, questionValue1, questionValue2, questionOperator;
    Button answerChoice1, answerChoice2, answerChoice3;

    private SoundPool mSoundPool;
    int wrongSoundID, scoreSoundID, levelSoundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

        wrongSoundID = mSoundPool.load(this, R.raw.wrong_answer, 1);
        scoreSoundID = mSoundPool.load(this, R.raw.score_up, 1);
        levelSoundID = mSoundPool.load(this, R.raw.level_up, 1);

        currentScore = findViewById(R.id.currentScore);
        currentLevel = findViewById(R.id.currentLevel);

        questionValue1 = findViewById(R.id.questionValue1);
        questionValue2 = findViewById(R.id.questionValue2);

        questionOperator = findViewById(R.id.questionOperator);

        answerChoice1 = findViewById(R.id.answerChoice1);
        answerChoice2 = findViewById(R.id.answerChoice2);
        answerChoice3 = findViewById(R.id.answerChoice3);

        questionOperator.setText(getIntent().getStringExtra("QUESTION_TYPE"));
        createQuestion();

        answerChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerChoice1.setBackgroundResource(R.color.maroon);
                manageAnswer(answerChoice1.getText().toString());

            }
        });

        answerChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerChoice2.setBackgroundResource(R.color.maroon);
                manageAnswer(answerChoice2.getText().toString());
            }
        });

        answerChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerChoice3.setBackgroundResource(R.color.maroon);
                manageAnswer(answerChoice3.getText().toString());
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void createQuestion() {
        int lv = Integer.parseInt(currentLevel.getText().toString());
        int maxRand = (lv > 1) ? 10*lv : 10;
        int val1 = MyMath.randInt(1, maxRand);
        int val2 = MyMath.randInt(1, maxRand);

        questionValue1.setText(String.valueOf(val1));
        questionValue2.setText(String.valueOf(val2));

        List<Integer> answers = new ArrayList();
        answers.add(MyMath.calculate(val1, val2, questionOperator.getText().toString()));
        answers.add(MyMath.calculate(val1-1, val2, questionOperator.getText().toString()));
        answers.add(MyMath.calculate(val1+1, val2, questionOperator.getText().toString()));
        Collections.shuffle(answers);

        answerChoice1.setText(String.valueOf(answers.get(0)));
        answerChoice2.setText(String.valueOf(answers.get(1)));
        answerChoice3.setText(String.valueOf(answers.get(2)));

    }

    private void manageAnswer(String selectedBtnText) {

        int qV1 = Integer.parseInt(questionValue1.getText().toString());
        int qV2 = Integer.parseInt(questionValue2.getText().toString());
        String op = questionOperator.getText().toString();

        if (Integer.parseInt(selectedBtnText) == MyMath.calculate(qV1,qV2,op)) {
            mSoundPool.play(scoreSoundID, 1.0f, 1.0f, 0, 0, 1.0f);
            currentScore.setText(String.valueOf(Integer.parseInt(currentScore.getText().toString())+1));
            createQuestion();
            if (Integer.parseInt(currentScore.getText().toString()) % 10 == 0) {
                mSoundPool.play(levelSoundID, 1.0f, 1.0f, 0, 0, 1.0f);
                currentLevel.setText(String.valueOf((Integer.parseInt(currentScore.getText().toString())/10)+1));
            }
            unSelectBtns();
        } else {
            mSoundPool.play(wrongSoundID, 1.0f, 1.0f, 0, 0, 1.0f);
            currentScore.setText(R.string.defaultGameScore);
            currentLevel.setText(R.string.defaultGameLevel);
        }

    }

    public void unSelectBtns() {
        answerChoice1.setBackgroundResource(R.color.tomato);
        answerChoice2.setBackgroundResource(R.color.tomato);
        answerChoice3.setBackgroundResource(R.color.tomato);
    }

}
