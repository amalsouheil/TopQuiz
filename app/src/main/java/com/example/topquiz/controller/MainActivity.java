package com.example.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.Scores;
import com.example.topquiz.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static java.lang.System.out;
public class MainActivity extends AppCompatActivity {


        private TextView mGreetingText;
        private EditText mNameInput;
        private Button mPlayButton;
        private Button mHighScoresButton;
        private User mUser;
        public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
        public static final int  HIGH_SCORE_ACTIVITY_REQUEST_CODE = 11;
        private SharedPreferences mPreferences;

        private Scores mScores;
        public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
        public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
        public static final String PREF_KEY_SCORETAB = "PREF_KEY_SCORETAB";

        public static final String BUNDLE_SCORES = "BUNDLE_SCORES";


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            System.out.println("MainActivity::onCreate()");

            mUser = new User();

            mPreferences = getPreferences(MODE_PRIVATE);

            mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
            mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
            mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
            mHighScoresButton=(Button) findViewById(R.id.activity_main_score_btn);
            mPlayButton.setEnabled(false);



        // Alimente le tableau des scores avec celui récupéré
        Gson gson = new Gson();

        String json = mPreferences.getString(PREF_KEY_SCORETAB,null);
        if (json != null) {
            mScores = gson.fromJson(json,Scores.class);
        } else {
            mScores = new Scores();
            mHighScoresButton.setEnabled(false);
        }
        out.println("Tableau des scores dans les préférences");
        for (User user : mScores.getScoresTab()){
            out.println(user.getFirstName()+" "+user.getScore());
        }


            greetUser();

            mNameInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mPlayButton.setEnabled(s.toString().length() != 0);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });




            mPlayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlayButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent activityIntent;

                            out.println("Click PLAY");

                            // User clicked the button
                            activityIntent = new Intent(MainActivity.this, GameActivity.class);
                            startActivityForResult(activityIntent, GAME_ACTIVITY_REQUEST_CODE);
                        }
                    });

                    mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();

                    // User clicked the button
                    Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                    startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);


                }
            });

        // Click sur le bouton "affichage des scores"
        mHighScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent activityIntent;

                out.println("Tableau des scores avant lancement de l'activité tableua des scores");
                for (User user : mScores.getScoresTab()){
                    out.println(user.getFirstName()+" "+user.getScore());
                }

                out.println("Click SCORES");
                // User clicked the button
                activityIntent = new Intent(MainActivity.this, ScoreActivity.class);

                // Put the scores tab to ScoresActivity
                activityIntent.putExtra("Scores",mScores);
                startActivity(activityIntent);
            }
        });



        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
                //Récupérer le score de l'intent

                int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

                mUser = new User();
                mUser.setFirstName(mNameInput.getText().toString());
                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();
                mUser.setScore(score);
                mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
                // Add the new player and his score in table scores
                mScores.addScore(mUser);


                //Mettez les scores de la table dans les préférences

                 final Gson gson = new GsonBuilder()
                        .serializeNulls()
                        .disableHtmlEscaping()
                        .create();
                 String json = gson.toJson(mScores);

                  mPreferences.edit().putString(PREF_KEY_SCORETAB, json).apply();


                // Un joueur et son score sont maintenant disponible, on active le bouton
                // permettant de visualiser le tableau des scores
                   mHighScoresButton.setEnabled(true);

                // On affiche les informations sur le joueur actuel
                greetUser();

            }
        }

        private void greetUser() {
            String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);

            if (null != firstname) {
                int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

                String fulltext = "Welcome back, " + firstname
                        + "!\nYour last score was " + score
                        + ", will you do better this time?";
                mGreetingText.setText(fulltext);
                mNameInput.setText(firstname);
                mNameInput.setSelection(firstname.length());
                mPlayButton.setEnabled(true);
            }
        }

        @Override
        protected void onStart() {
            super.onStart();

            out.println("MainActivity::onStart()");
        }

        @Override
        protected void onResume() {
            super.onResume();

            out.println("MainActivity::onResume()");
        }

        @Override
        protected void onPause() {
            super.onPause();

            out.println("MainActivity::onPause()");
        }

        @Override
        protected void onStop() {
            super.onStop();

            out.println("MainActivity::onStop()");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

            out.println("MainActivity::onDestroy()");
        }
}
