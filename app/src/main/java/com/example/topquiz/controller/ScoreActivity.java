package com.example.topquiz.controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.Scores;
import com.example.topquiz.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

public class ScoreActivity extends AppCompatActivity {


    private TextView mTitleTextView;
    private TextView mFirstScoreTextView;
    private TextView mSecondScoreTextView;
    private TextView mThirdScoreTextView;
    private TextView mFourthScoreTextView;
    private TextView mScoreTitleTextView;
    private TextView mNameTitleTextView;
    private TextView mFifthScoreTextView;
    private Button mSortNameButton;
    private Button mSortScoreButton;

    private Scores mScores;

    private List<TextView> textViewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        mTitleTextView = (TextView) findViewById(R.id.activity_scores_title_txt);
        mFirstScoreTextView = (TextView) findViewById(R.id.activity_scores_first_txt);
        mSecondScoreTextView = (TextView) findViewById(R.id.activity_scores_second_txt);
        mThirdScoreTextView = (TextView) findViewById(R.id.activity_scores_third_txt);
        mFourthScoreTextView = (TextView) findViewById(R.id.activity_scores_fourth_txt);
        mFifthScoreTextView = (TextView) findViewById(R.id.activity_scores_fifth_txt);
        mSortNameButton = (Button) findViewById(R.id.activity_scores_sort_nameplayers_btn);
        mSortScoreButton = (Button) findViewById(R.id.activity_scores_sort_highscores_btn);
        mScoreTitleTextView  = (TextView) findViewById(R.id.activity_scores_title_score_txt);
        mNameTitleTextView = (TextView) findViewById(R.id.activity_scores_title_name_txt);


        // Stock les lignes de score dans un tableau
        textViewList = new ArrayList<TextView>();
        textViewList.add(mFirstScoreTextView);
        textViewList.add(mSecondScoreTextView);
        textViewList.add(mThirdScoreTextView);
        textViewList.add(mFourthScoreTextView);
        textViewList.add(mFifthScoreTextView);


        // Récupération de la table des Scores
        Intent i = getIntent();
        mScores = (Scores) i.getSerializableExtra("Scores");

        // Triez les scores du plus grand au plus petit
        // Création du tableau des 5 meilleurs scores
        ArrayList<User> scoresFive = createFiveTab();
        Collections.sort(scoresFive,new User());
        Collections.reverse(scoresFive);

        //Met à jour l'affichage du tableau des scores
        if (mScores != null) updateScoresTab(mScores.getScoresTab());



        // Bouton permettant le tri par rapport au score
        mSortScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mScores != null) {

                    // Création du tableau des 5 meilleurs scores
                    ArrayList<User> scoresFive;
                    scoresFive = createFiveTab();

                    // Triez les scores du plus grand au plus petit
                    Collections.sort(scoresFive,new User());
                    Collections.reverse(scoresFive);

                    // Affichage du tableau de score
                    updateScoresTab(scoresFive);
                }
            }
        });

        // Bouton permettant le tri par rapport au nom
        mSortNameButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                if (mScores != null) {

                    // Creation of the table of 5 better scores
                    ArrayList<User> scoresFive;
                    scoresFive = createFiveTab();

                    // Sort out the scores of the biggest in the smallest
                    Collections.sort(scoresFive);



                    // Display the scores tab
                    updateScoresTab(scoresFive);
                }
            }
        });
    }




    //methode pour créer le tableau de 5 meilleurs scores
    private ArrayList<User> createFiveTab() {

        ArrayList<User> scoresFive = new ArrayList<User>();


        //Triez les scores du plus grand au plus petit
        Collections.sort(mScores.getScoresTab(),new User());
        Collections.reverse(mScores.getScoresTab());

        // On conserve uniquement les 5 premiers postes du tableau de score
        for (int i = 0;i<mScores.getScoresTab().size();i++){
            out.println("index = "+i);
            scoresFive.add(mScores.getScoresTab().get(i));
        }
        return scoresFive;
    }

    // Methode qui permet de  mettre à jour l'affichage du tableau de scores

    private void updateScoresTab(List<User> userList) {

        String name;
        int score, index = 0;

        for (TextView textView : textViewList) {
            if (index < userList.size() ) {
                if (userList.get(index) != null) {
                    name = userList.get(index).getFirstName();
                    score = userList.get(index).getScore();
                    textView.setText(   (index + 1)
                            + title(index+1)
                            + "        "
                            + score
                            + "          "
                            + name);
                }
            } else {
                textView.setText(   (index + 1)
                        + title(index+1)
                        + "        "
                        + "-"
                        + "          "
                        + "unknown");
                textView.setTextColor(Color.GRAY);
                textView.setTypeface(Typeface.DEFAULT,Typeface.ITALIC);
            }
            index++;
        }
    }

    // Méthode permettant de retourner le bon titre

    private String title(int rang) {
        String position;
        switch (rang) {
            case 1: position = ")";
                break;
            case 2: position = ")";
                break;
            case 3: position = ")";
                break;
            default: position = ")";
                break;
        }
        return position;
    }

    }


