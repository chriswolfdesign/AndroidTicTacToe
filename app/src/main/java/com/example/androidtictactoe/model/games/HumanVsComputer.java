package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.Values;

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
}
