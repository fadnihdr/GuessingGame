package com.example.fadni.guessinggame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class Main2Activity extends AppCompatActivity {

    private SeekBar minimumBar;
    private SeekBar maximumBar;
    private TextView minimumAmount;
    private TextView maximumAmount;
    private int minimumValue;
    private int maximumValue;
    private NumberFormat textFormatter =NumberFormat.getNumberInstance();
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        preferences = getSharedPreferences("limit",MODE_PRIVATE);

        minimumBar = (SeekBar) findViewById(R.id.minimumBar);
        maximumBar = (SeekBar) findViewById(R.id.maximumBar);
        minimumAmount = (TextView) findViewById(R.id.minimumAmount);
        maximumAmount = (TextView) findViewById(R.id.maximumAMount);

        minimumValue= preferences.getInt("minimum",1);
        maximumValue= preferences.getInt("maximum",10);
        if(minimumValue==1){
            minimumBar.setProgress(0);
        }
        else {
            minimumBar.setProgress(preferences.getInt("minimum",0)*11+1);
        }
        if(maximumValue==10) {
            maximumBar.setProgress(0);
        }
        else {
            maximumBar.setProgress((preferences.getInt("maximum",0)*10-100)/9);
        }
        minimumAmount.setText(textFormatter.format(minimumValue));
        maximumAmount.setText(textFormatter.format(maximumValue));

        minimumBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minimumValue= 1+ progress/12;
                minimumAmount.setText(textFormatter.format(minimumValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maximumBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maximumValue= (progress*9 +100)/10;
                maximumAmount.setText(textFormatter.format(maximumValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void setButton(View view){
        if(minimumBar.getProgress()==0){
            minimumValue=1;
        }
        if(maximumBar.getProgress()==0){
            maximumValue=10;
        }
        preferences.edit()
                .putInt("minimum",minimumValue)
                .putInt("maximum",maximumValue)
                .apply();
    }

    public void backButton(View view){
        finish();
    }
}
