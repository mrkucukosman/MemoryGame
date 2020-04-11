package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String userName;

    int lastOpenCard = 0;
    int score = 0;
    int fail = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent mainIntent = getIntent();
        userName = mainIntent.getStringExtra("name");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(textView.getText() + " " + userName);
        fillGameTable();
    }

    private void fillGameTable() {
        GridLayout gridTableGame = (GridLayout) findViewById(R.id.gridGameTable);

        GameCard[] gameCards = new GameCard[16];
        for (int i = 1; i <= 16; i++) {
            gameCards[i-1] = new GameCard(this, i);
            gameCards[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   final GameCard card = (GameCard) v;
                    card.turnCard();

                    if (lastOpenCard > 0) {
                       final GameCard lastTurnedCard = (GameCard) findViewById(lastOpenCard);
                        if (lastTurnedCard.getFrontImageId() == card.getFrontImageId() && card.getId() != lastTurnedCard.getId()) {
                            lastTurnedCard.setCanBeTurned(false);
                            card.setCanBeTurned(false);
                            score++;
                            System.out.println("Score is " + score);
                            if (score == 8) {
                                Intent scoreIntent = new Intent(GameActivity.this, ResultActivity.class);
                                scoreIntent.putExtra("score", fail);
                                scoreIntent.putExtra("userName", userName);
                                startActivity(scoreIntent);
                            }
                        } else {

                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    lastTurnedCard.turnCard();
                                    card.turnCard();
                                }
                            }, 1000);

                            lastOpenCard = 0;
                            fail ++;
                            System.out.println("fail is : " + fail);
                        }
                    } else {
                        lastOpenCard = card.getId();
                    }
                }
            });
        }

        //Karistirma islemi
        for (int i = 0; i < 16; i++) {
            int randomCardNum = new Random().nextInt(16);
            Log.d("number", "" + randomCardNum);
            System.out.println(randomCardNum);
            GameCard k = gameCards[randomCardNum];
            gameCards[randomCardNum] = gameCards[i];
            gameCards[i] = k;
        }

        for (int i = 0; i < 16; i++) {
            gridTableGame.addView(gameCards[i]);
        }


    }
}
