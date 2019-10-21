package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import logic.Galgelogik;

public class GamePage extends AppCompatActivity {
    Galgelogik game;
    ImageView hangman;
    TextView word, letters;
    EditText letterGuess;
//    String currLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        instanciateGlobals();
        newGame();
    }

    private void instanciateGlobals() {
        hangman = findViewById(R.id.hangman);
        word = findViewById(R.id.wordView);
        letters = findViewById(R.id.lettersView);
        letterGuess = findViewById(R.id.letterInput);
    }

    private void newGame() {
        hangman.setImageResource(R.drawable.galge);
        game = new Galgelogik();

//        word.setText(game.getSynligtOrd());
//        currLetters = "";
        updateView();
    }

    private void updateView() {
//        letters.setText("You've guessed on:\n" + currLetters);
        word.setText(game.getSynligtOrd());
        letters.setText("You've guessed on:\n" + game.getBrugteBogstaver());
    }

    public void guessBtnPress(View view) {
        String guess = letterGuess.getText().toString();
        if(guess.matches("[A-Za-z]{1}") && !game.getBrugteBogstaver().contains(guess)) {
//            currLetters += guess + " ";
//            updateView();
            game.gætBogstav(guess);
            updateView();
        } else {
            //TODO Tell them to try new letters and only one at a time
        }

    }
}
