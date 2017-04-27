package com.example.fadni.guessinggame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView statusText;
    private EditText guessField;
    private SharedPreferences preferences;
    private int secretNumber;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("limit",MODE_PRIVATE);
        preferences.edit().clear().apply();
        Random random = new Random();
        secretNumber = 1+ random.nextInt(10);

        statusText = (TextView) findViewById(R.id.statusText);
        guessField = (EditText) findViewById(R.id.guessField);

        guessField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int value = Integer.parseInt(s.toString());

                    if(value ==  secretNumber){
                        statusText.setText("You win");
                    }
                    if (value < secretNumber){
                        statusText.setText("Try Bigger");
                    }
                    if (value > secretNumber){
                        statusText.setText("Try Smaller");
                    }
                } catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Random random = new Random();
        int minNumber = preferences.getInt("minimum",1);
        int maxNumber = preferences.getInt("maximum",10);
        secretNumber = minNumber + random.nextInt(maxNumber-minNumber+1);
    }

    public void setNumber(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
