package com.example.androidtictactoe.model;

import com.example.androidtictactoe.model.settings.BoardSettings;

public class Board {
    private Player[][] board;

    public Board() {
        board = new Player[BoardSettings.BOARD_SIZE][BoardSettings.BOARD_SIZE];

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                board[i][j] = Player.NONE;
            }
        }
    }

    public Player getPlayerAtSquare(int row, int col) {
        if (isSquareOutOfBounds(row, col)) {
            return null;
        }

        return board[row][col];
    }

    private boolean isSquareOutOfBounds(int row, int col) {
        if (row < 0 || row >= BoardSettings.BOARD_SIZE) {
            return true;
        }

        return col < 0 || col >= BoardSettings.BOARD_SIZE;
    }

    public boolean placePlayerAtSquare(int row, int col, Player player) {
        if (!isLegalMove(row, col, player)) {
            return false;
        }

        board[row][col] = player;

        return true;
    }

    private boolean isLegalMove(int row, int col, Player player) {
        return !(isSquareOutOfBounds(row, col) || board[row][col] != Player.NONE || player == Player.NONE);
    }
}
