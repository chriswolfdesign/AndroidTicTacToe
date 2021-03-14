package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;

/**
 * TwoHumanGame.java
 *
 * An instance of the game meant to manage two human players playing the game
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public class TwoHumanGame extends Game {
    /**
     * Attempts to make the move the player has requested
     * @param row the row of the square the player is trying to make a move on
     * @param col the column of the square the player is trying to make a move on
     * @return false if the move is illegal, otherwise it returns true and makes the move
     */
    @Override
    public boolean makeMove(int row, int col) {
        return this.makeMove(row, col, this.getCurrentPlayer());
    }
}
