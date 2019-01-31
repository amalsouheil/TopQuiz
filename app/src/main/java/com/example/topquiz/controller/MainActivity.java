package com.example.topquiz.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {


    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser=new User();
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);//Elle permet de désactiver le bouton de l'interface
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

                      String firstname = mNameInput.getText().toString();
                      mUser.setFirstName(mNameInput.getText().toString());//pour memoriser le prenom du joueur lorsqu'on clique sur le bouton
                      //User clicked the Button 
                      Intent GameActivityIntent = new Intent(MainActivity.this,GameActivity.class);
                      startActivity(GameActivityIntent);
                     
                  }
              });

    }


}
