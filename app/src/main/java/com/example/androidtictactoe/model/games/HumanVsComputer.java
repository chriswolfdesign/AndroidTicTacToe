package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;

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
        return false;
    }

    public void makeComputerMove() {
        // TODO: Implement functionality for the minimax algorithm
    }
}
