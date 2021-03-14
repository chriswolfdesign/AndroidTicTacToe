package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.Values;
import com.example.androidtictactoe.model.settings.BoardSettings;

public class HumanVsComputer extends Game {
    private Player computerPlayer;

    public HumanVsComputer(Player computerPlayer) {
        super();
        this.computerPlayer = computerPlayer;

        if (computerPlayer == Player.X) {
            makeComputerMove();
        }
    }

    @Override
    public boolean makeMove(int row, int col) {
        boolean result = this.makeMove(row, col, this.getCurrentPlayer());

        if (result && !this.isGameOver()) {
            this.makeComputerMove();
        }

        return result;
    }

    public void makeComputerMove() {
        // TODO: Implement functionality for the mini-max algorithm
    }

    public int boardValue() {
        if (this.hasPlayerWon(Player.X)) {
            return Values.PLAYER_X_WON;
        } else if (this.hasPlayerWon(Player.O)) {
            return Values.PLAYER_O_WON;
        }

        return Values.NEITHER_PLAYER_WON;
    }

    @Override
    public HumanVsComputer clone() {
        HumanVsComputer copy = new HumanVsComputer(this.computerPlayer);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (this.board.getPlayerAtSquare(i, j) != Player.NONE) {
                    copy.makeMove(i, j, this.board.getPlayerAtSquare(i, j));
                }
            }
        }

        return copy;
    }
}
