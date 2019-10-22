package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import logic.Galgelogik;

public class GamePage extends AppCompatActivity {
    Galgelogik game;
    ImageView hangman;
    TextView word, letters;
    EditText letterGuess;
    Button gameBtn;
    int[] pics = {R.drawable.galge,
            R.drawable.forkert1,
            R.drawable.forkert2,
            R.drawable.forkert3,
            R.drawable.forkert4,
            R.drawable.forkert5,
            R.drawable.forkert6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        instantiateGlobals();
        newGame();
    }

    private void instantiateGlobals() {
        hangman = findViewById(R.id.hangman);
        word = findViewById(R.id.wordView);
        letters = findViewById(R.id.lettersView);
        letterGuess = findViewById(R.id.letterInput);
        gameBtn = findViewById(R.id.userInputBtn);
    }

    private void newGame() {
        game = new Galgelogik(); //TODO Move to instantiate and use game.nulstil
        gameBtn.setText(R.string.btn_guess);
        updateView();
    }

    private void updateView() {
        hangman.setImageResource(pics[game.getAntalForkerteBogstaver()]);
        word.setText(game.getSynligtOrd());
        letters.setText(getString(R.string.letters_guessed, game.getBrugteBogstaver().toString()));
    }

    public void guessBtnPress(View view) {
        if (game.erSpilletSlut()) newGame();
        else {
            String guess = letterGuess.getText().toString();
            if(guess.matches("[A-Za-z]{1}") && !game.getBrugteBogstaver().contains(guess)) {
                game.gætBogstav(guess); //FixMe Game seems to be closing around here when losing, believe it was talked about at lecture
                updateView();

                controlGameState();
            }
//            else {
//                //TODO Tell them to try new letters and only one at a time
//            }
        }
        letterGuess.setText("");
    }

    private void controlGameState() {
        if (game.erSpilletTabt()) {
            endScreen(getString(R.string.lost_game, game.getOrdet()));
        } else if (game.erSpilletVundet()) {
            if(game.getAntalForkerteBogstaver() == 0) endScreen(getString(R.string.perfect_game));
            else endScreen(getString(R.string.won_game));
        }
    }

    private void endScreen(String msg) {
        gameBtn.setText(R.string.btn_play_again);
        letters.setText(msg); //TODO Consider creating and use a communication TextView
    }
}