package com.example.topquiz.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiz.R;

public class GameActivity extends AppCompatActivity {


    private TextView mQuestionText ;
    private Button manswer1Btn;
    private Button manswer2Btn;
    private Button manswer3Btn;
    private Button manswer4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        manswer1Btn = (Button) findViewById(R.id.activity_game_answer1_btn);
        manswer2Btn = (Button) findViewById(R.id.activity_game_answer2_btn);
        manswer3Btn = (Button) findViewById(R.id.activity_game_answer3_btn);
        manswer3Btn = (Button) findViewById(R.id.activity_game_answer3_btn);
        manswer4Btn = (Button) findViewById(R.id.activity_game_answer4_btn);
    }
}
