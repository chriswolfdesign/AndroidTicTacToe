package com.example.androidtictactoe.model;

import com.example.androidtictactoe.model.settings.BoardSettings;

/**
 * Board.java
 *
 * An object representing the board we will be playing Tic-Tac-Toe on
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public class Board {
    /** the 2-D square array that will hold our board */
    private Player[][] board;

    /**
     * Initializes the board and sets all squares to be Player.NONE
     */
    public Board() {
        board = new Player[BoardSettings.BOARD_SIZE][BoardSettings.BOARD_SIZE];

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                board[i][j] = Player.NONE;
            }
        }
    }

    /**
     * Finds what player is currently on a given square
     * @param row the row the square we are searching for is on
     * @param col the column the square we are searching for is on
     * @return X, O, or NONE, depending on what player is on the given square
     */
    public Player getPlayerAtSquare(int row, int col) {
        if (isSquareOutOfBounds(row, col)) {
            return null;
        }

        return board[row][col];
    }

    /**
     * Determines if a given square is out of bounds (based on BoardSettings.BOARD_SIZE)
     * @param row the row the square we are searching for is on
     * @param col the col the square we are searching for is on
     * @return true if the square is out of bounds, false otherwise
     */
    private boolean isSquareOutOfBounds(int row, int col) {
        if (row < 0 || row >= BoardSettings.BOARD_SIZE) {
            return true;
        }

        return col < 0 || col >= BoardSettings.BOARD_SIZE;
    }

    /**
     * Places the given player at the given square, if possible
     * NOTE: Player.NONE cannot be placed
     * @param row the row of the square we are attempting to put a player on
     * @param col the column of the square we are attempting to put a player on
     * @param player the player we are attempting to place on the given square
     * @return false if the given move is illegal, true and places player otherwise
     */
    public boolean placePlayerAtSquare(int row, int col, Player player) {
        if (!isLegalMove(row, col, player)) {
            return false;
        }

        board[row][col] = player;

        return true;
    }

    /**
     * Determines if the given move is legal
     * @param row the row of the square are attempting to see will allow us to make a move
     * @param col the column of the square we are attempting to see will allow us to make a move
     * @param player the player who is attempting to make this move
     * @return true if the given move is legal, false otherwise
     */
    private boolean isLegalMove(int row, int col, Player player) {
        return !(isSquareOutOfBounds(row, col) || board[row][col] != Player.NONE || player == Player.NONE);
    }

    public int remainingEmptySquares() {
        int remainingEmptySquares = 0;

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (board[i][j] == Player.NONE) {
                    remainingEmptySquares++;
                }
            }
        }

        return remainingEmptySquares;
    }
}
