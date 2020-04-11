package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textViewScore = (TextView)findViewById(R.id.textViewScore);
        Intent i = getIntent();
        String userName = i.getStringExtra("userName");
        int score = i.getIntExtra("fail", 0);
        textViewScore.setText("Congragulations " + userName + "\n" + score + "with fail");
    }
}
