package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    int[] pics = {R.drawable.galge, //Can return right image given number of mistakes
            R.drawable.forkert1,
            R.drawable.forkert2,
            R.drawable.forkert3,
            R.drawable.forkert4,
            R.drawable.forkert5,
            R.drawable.forkert6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String brugerNavn = getIntent().getStringExtra("brugerNavn");

        setContentView(R.layout.activity_game_page);
        //Instantiate globals to be later referenced
        hangman = findViewById(R.id.hangman);
        word = findViewById(R.id.wordView);
        letters = findViewById(R.id.lettersView);
        letterGuess = findViewById(R.id.letterInput);
        gameBtn = findViewById(R.id.userInputBtn);
        //Setup a new game
        newGame();
    }

    /**
     * Sets up a new game to be played
     * If no instance of game exists it creates a new one, else it resets it by .nulstil()
     */
    private void newGame() {
        if(game == null) {
            game = new Galgelogik();
            game.setBrugerNavn(getIntent().getStringExtra("brugerNavn"));
        }
        else game.nulstil();
        gameBtn.setText(R.string.btn_guess);
        updateView();
    }

    /**
     * Updates game for the player
     */
    private void updateView() {
        hangman.setImageResource(pics[game.getAntalForkerteBogstaver()]);
        word.setText(game.getSynligtOrd());
        letters.setText(getString(R.string.letters_guessed, game.getBrugteBogstaver().toString()));
    }

    /**
     * Reacts to player press on the button, depending on state of the game
     *
      * @param view TODO View relevant here? Have to read up on it
     */
    public void guessBtnPress(View view) {
        if (game.erSpilletSlut()) newGame(); //FixMe Game seems to be closing around here when losing, believe it was talked about at lecture
        else {
            String guess = letterGuess.getText().toString();
            if(guess.matches("[A-Za-z]{1}") && !game.getBrugteBogstaver().contains(guess)) {
                game.gætBogstav(guess);
                updateView();

                controlGameState();
            }
//            else {
//                //TODO Tell them to try new letters and only one at a time
//            }
        }
        letterGuess.setText(""); //Alternatively use letterGuess.getText().clear()
    }

    /**
     * Controls if the game is done and if so sets the information to the player
     */
    private void controlGameState() {
        if (game.erSpilletTabt()) {
            endScreen(getString(R.string.lost_game, game.getOrdet()));
        } else if (game.erSpilletVundet()) {
            String winningMsg = (game.getAntalForkerteBogstaver() == 0) ? getString(R.string.perfect_game) : getString(R.string.won_game, game.getAntalForkerteBogstaver());
            endScreen(winningMsg);
        }
    }

    /**
     * Sets the endscreen according to outcome of the game, with a given message to the player
     *
     * @param msg   Message to the player
     */
    private void endScreen(String msg) {
        gameBtn.setText(R.string.btn_play_again);
        letters.setText(msg); //TODO Consider creating and use a communication TextView
    }
}