package com.example.androidtictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * GameChoiceScreen.java
 *
 * The opening screen of the application.
 * Allows a user to decide whether they want to play another human or a computer.
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 14, 2021)
 */
public class GameChoiceScreen extends AppCompatActivity {

    /**
     * Behavior to be completed when the screen is created
     * @param savedInstanceState data meant to be passed between screens
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_choice_screen);

        initializeButtonClickListeners();
    }

    /**
     * Initializes the behavior that should be completed when the buttons are clicked
     */
    private void initializeButtonClickListeners() {
        Button humanVsHumanButton = (Button) findViewById(R.id.humanVsHumanButton);
        humanVsHumanButton.setOnClickListener(this::humanVsHumanButtonClicked);

        Button humanVsComputerButton = (Button) findViewById(R.id.humanVsComputerButton);
        humanVsComputerButton.setOnClickListener(this::humanVsComputerButtonClicked);
    }

    /**
     * Behavior that should be completed when the human vs. human button is clicked
     * @param view the View that has been clicked
     */
    public void humanVsHumanButtonClicked(View view) {
        Toast.makeText(this, "You clicked the Human Vs. Human Button!", Toast.LENGTH_LONG).show();
    }

    /**
     * Behavior that should be completed when the human vs. computer button is clicked
     * @param view the view that has been clicked
     */
    public void humanVsComputerButtonClicked(View view) {
        Intent computerChoiceScreen = new Intent(this, ComputerChoiceScreen.class);
        startActivity(computerChoiceScreen);
    }
}