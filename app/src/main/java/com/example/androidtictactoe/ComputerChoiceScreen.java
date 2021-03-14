package com.example.androidtictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ComputerChoiceScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_choice_screen);
        this.getSupportActionBar().hide();

        Button playerXButton = (Button) findViewById(R.id.playerXButton);
        playerXButton.setOnClickListener(this::playerXButtonClicked);

        Button playerOButton = (Button) findViewById(R.id.playerOButton);
        playerOButton.setOnClickListener(this::playerOButtonClicked);
    }

    public void playerXButtonClicked(View v) {
        Toast.makeText(this, "You chose for the computer to play Player X", Toast.LENGTH_LONG).show();
    }

    public void playerOButtonClicked(View v) {
        Toast.makeText(this, "You chose for the computer to play Player O", Toast.LENGTH_LONG).show();
    }
}