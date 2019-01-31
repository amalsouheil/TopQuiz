package com.example.topquiz.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mQuestionText;
    private Button manswer1Btn;
    private Button manswer2Btn;
    private Button manswer3Btn;
    private Button manswer4Btn;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mScore;
    private int mNumberOfQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Wire widgets
        mQuestionBank = this.generateQuestions();
        mScore=0;
        mNumberOfQuestions=4;

        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        manswer1Btn = (Button) findViewById(R.id.activity_game_answer1_btn);
        manswer2Btn = (Button) findViewById(R.id.activity_game_answer2_btn);
        manswer3Btn = (Button) findViewById(R.id.activity_game_answer3_btn);
        manswer3Btn = (Button) findViewById(R.id.activity_game_answer3_btn);
        manswer4Btn = (Button) findViewById(R.id.activity_game_answer4_btn);


        // Use the tag property to 'name' the buttons
        manswer1Btn.setTag(0);
        manswer2Btn.setTag(1);
        manswer3Btn.setTag(2);
        manswer4Btn.setTag(3);

        manswer1Btn.setOnClickListener(this);
        manswer2Btn.setOnClickListener(this);
        manswer3Btn.setOnClickListener(this);
        manswer4Btn.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);


    }

    private void displayQuestion(final Question question) {
        mQuestionText.setText(question.getQuestion());
        manswer1Btn.setText(question.getChoiceList().get(0));
        manswer2Btn.setText(question.getChoiceList().get(1));
        manswer3Btn.setText(question.getChoiceList().get(2));
        manswer4Btn.setText(question.getChoiceList().get(3));
    }


    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();

        }

        if (--mNumberOfQuestions == 0) {
            // No question left, end the game
            endGame();
        } else {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }
}