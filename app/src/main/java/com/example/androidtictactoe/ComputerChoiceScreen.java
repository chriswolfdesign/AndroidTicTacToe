package com.example.androidtictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * ComputerChoiceScreen.java
 *
 * Screen that allows the user to decide which player the computer will be controlling.
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 14, 2021)
 */
public class ComputerChoiceScreen extends AppCompatActivity {

    /**
     * Behavior to be completed when the screen is created
     * @param savedInstanceState data to be passed between screens
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_choice_screen);
        this.getSupportActionBar().hide();

        initializeButtonClickListeners();
    }

    /**
     * Initializes the behavior to be completed when each button is clicked.
     */
    private void initializeButtonClickListeners() {
        Button playerXButton = (Button) findViewById(R.id.playerXButton);
        playerXButton.setOnClickListener(this::playerXButtonClicked);

        Button playerOButton = (Button) findViewById(R.id.playerOButton);
        playerOButton.setOnClickListener(this::playerOButtonClicked);
    }

    /**
     * Behavior to be completed when the player X button is clicked
     * @param view the view that was clicked
     */
    public void playerXButtonClicked(View view) {
        Toast.makeText(this, "You chose for the computer to play Player X", Toast.LENGTH_LONG).show();
    }

    /**
     * Behavior to be completed when the player O button is clicked
     * @param view the view that was clicked
     */
    public void playerOButtonClicked(View view) {
        Toast.makeText(this, "You chose for the computer to play Player O", Toast.LENGTH_LONG).show();
    }
}