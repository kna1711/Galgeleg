//https://github.com/kna1711/Galgeleg
package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText userNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameField = findViewById(R.id.nameTxt);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GamePage.class);
        intent.putExtra("brugerNavn", userNameField.getText().toString());
        startActivity(intent);
    }

    public void showHighScore(View view) {
        startActivity(new Intent(this, HighScorePage.class));
    }
}
