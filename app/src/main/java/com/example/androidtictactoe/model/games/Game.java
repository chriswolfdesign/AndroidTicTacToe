package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Board;
import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.settings.BoardSettings;

/**
 * Game.java
 *
 * An abstract class meant to provide functionality and requirements for all Game instances
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public abstract class Game {
    /** The board the game will be played on */
    protected Board board;
    /** The player who's turn it currently is */
    private Player currentPlayer;

    /**
     * Initializes the game with a "blank" board
     */
    public Game() {
        this.board = new Board();
        this.currentPlayer = Player.X;
    }

    /**
     * Getter for currentPlayer
     * @return currentPlayer
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Switch the current player from X -> O or vice-versa
     */
    public void updateCurrentPlayer() {
        if (currentPlayer == Player.X) {
            currentPlayer = Player.O;
        } else {
            currentPlayer = Player.X;
        }
    }

    /**
     * Makes a move for the current player
     * @param row the row of the square the current player is trying to make a move on
     * @param col the column of the square the current player is trying to make a move on
     * @return false is the move is illegal, otherwise returns true and makes the move
     */
    public boolean makeMove(int row, int col) {
        return this.makeMove(row, col, this.currentPlayer);
    }

    /**
     * Makes a move for the player onto the board
     * @param row the row of the square the player is trying to make a move on
     * @param col the column of the square the player is trying to make a move on
     * @param player the player who is trying to make the move
     * @return false if the move is illegal, otherwise returns true and makes the move
     */
    public abstract boolean makeMove(int row, int col, Player player);

    /**
     * Determines whether or not the game is over
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return this.board.remainingEmptySquares() == 0 || this.hasPlayerWon(Player.X) || this.hasPlayerWon(Player.O);
    }

    /**
     * Determines whether or not a player has won the game
     * @param player the player we are checking to see if they've won
     * @return true if the player has won the game, false otherwise
     */
    public boolean hasPlayerWon(Player player) {
        return this.hasPlayerWonByRows(player) || this.hasPlayerWonByCols(player) || this.hasPlayerWonByDiagonals(player);
    }

    /**
     * Determines whether or not the player has won by occupying every square in a row
     * @param player the player we are determining if they've won by occupying every square in a row
     * @return true if the player has won by occupying every square in a row, false otherwise
     */
    private boolean hasPlayerWonByRows(Player player) {
        boolean hasPlayerWon;

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            hasPlayerWon = true;

            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (this.board.getPlayerAtSquare(i, j) != player) {
                    hasPlayerWon = false;
                }
            }

            if (hasPlayerWon) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines whether or not the player has won by occupying every square in a column
     * @param player the player we are checking whether or not they won by occupying every square in a column
     * @return true if the given player has won by occupying every square in a column, false otherwise
     */
    private boolean hasPlayerWonByCols(Player player) {
        boolean hasPlayerWon;

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            hasPlayerWon = true;

            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (this.board.getPlayerAtSquare(j, i) != player) {
                    hasPlayerWon = false;
                }
            }

            if (hasPlayerWon) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines whether or not the given player has won by occupying every square in a single diagonal
     * @param player the player we are checking to see if they've won by occupying ever square in a single diagonal
     * @return true if the player occupies every square in a diagonal, false otherwise
     */
    private boolean hasPlayerWonByDiagonals(Player player) {
        return this.hasPlayerWonByTopLeftBottomRightDiagonal(player) || this.hasPlayerWonByTopRightBottomLeftDiagonal(player);
    }

    /**
     * Determining whether or not the given player occupies all of the squares in the top-left to bottom-right diagonal
     * @param player the player we are checking to see if they occupy all of the squares in the top-left to bottom-right diagonal
     * @return true if the given player occupies every square in the top-left to bottom-right diagonal, false otherwise
     */
    private boolean hasPlayerWonByTopLeftBottomRightDiagonal(Player player) {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            if (this.board.getPlayerAtSquare(i, i) != player) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determines whether or not the given player occupies every square in the top-right to bottom-left diagonal
     * @param player the player we are checking to see if they occupy every square in the top-right to bottom-left diagonal
     * @return true if the given player occupies every square in the top-right to bottom-left diagonal, false otherwise
     */
    private boolean hasPlayerWonByTopRightBottomLeftDiagonal(Player player) {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            if (this.board.getPlayerAtSquare(i, BoardSettings.BOARD_SIZE - 1 - i) != player) {
                return false;
            }
        }

        return true;
    }
}
