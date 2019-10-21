package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import logic.Galgelogik;

public class GamePage extends AppCompatActivity {
    ImageView hangman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        hangman = (ImageView) findViewById(R.id.hangman);

        newGame();
    }

    private void newGame() {
        hangman.setImageResource(R.drawable.galge);
        Galgelogik game = new Galgelogik();
    }
}
