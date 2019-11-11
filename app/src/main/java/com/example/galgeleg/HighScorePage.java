package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import logic.HighScoreLogic;

public class HighScorePage extends AppCompatActivity {
    HighScoreLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_page);
        logic = HighScoreLogic.getInstance();
    }

    public void back(View view) {
        this.finish();
    }
}
