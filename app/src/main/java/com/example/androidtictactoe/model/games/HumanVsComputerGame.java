package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.Values;
import com.example.androidtictactoe.model.settings.BoardSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * HumanVsComputer.java
 *
 * An object to supervise the playing of a game of Tic-Tac-Toe between a human and a computer
 * (Computer uses the mini-max algorithm)
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 14, 2021)
 */
public class HumanVsComputerGame extends Game {
    /** The player who is being controlled by the computer */
    private Player computerPlayer;

    /** Arbitrarily large number to emulate positive infinity */
    private static final int MAX_START = 500;
    /** Arbitrarily small number to emulate negative infinity */
    private static final int MIN_START = -500;

    /**
     * Initializes the human vs computer game
     * @param computerPlayer the player who will be controlled by the computer
     */
    public HumanVsComputerGame(Player computerPlayer) {
        super();
        this.computerPlayer = computerPlayer;

        if (computerPlayer == Player.X) {
            makeComputerMove();
        }
    }

    /**
     * Makes the proposed move for the human if able, then makes a move for the computer
     * @param row the row of the square the current player is trying to make a move on
     * @param col the column of the square the current player is trying to make a move on
     * @return false is move was illegal, returns true and makes move for human and computer if legal
     */
    @Override
    public boolean makeMove(int row, int col) {
        boolean result = this.makeMove(row, col, this.getCurrentPlayer());

        if (result && !this.isGameOver()) {
            this.makeComputerMove();
        }

        return result;
    }

    /**
     * Calculates the best possible move for the computer and makes said move
     */
    public void makeComputerMove() {
        Move nextMove;

        if (this.getCurrentPlayer() == Player.X) {
            nextMove = this.maxValue(this, MIN_START, MAX_START);
        } else {
            nextMove = this.minValue(this, MIN_START, MAX_START);
        }

        this.makeMove(nextMove.row, nextMove.col, this.getCurrentPlayer());
    }

    /**
     * Determines the move that will lead to best value for player X
     * @param game the game that is being evaluated
     * @param alpha the alpha value needed to complete alpha-beta pruning
     * @param beta the beta value needed to complete alpha-beta pruning
     * @return the move that holds the best potential for player X
     */
    private Move maxValue(HumanVsComputerGame game, int alpha, int beta) {
        // if the game is over, we only care about the game's board value
        if (game.isGameOver()) {
            return new Move(MAX_START, MAX_START, game.boardValue());
        }

        Move bestMove = null;
        int highestValue = MIN_START;

        // for every possible move, see if that move is the best one to make
        for (Move move: this.getEmptySquares()) {
            HumanVsComputerGame newGame = this.copy();
            newGame.makeMove(move.row, move.col, newGame.getCurrentPlayer());
            int moveValue = newGame.minValue(newGame, alpha, beta).value;

            if (moveValue > highestValue) {
                bestMove = move;
                bestMove.value = moveValue;
                highestValue = moveValue;

                if (highestValue > alpha) {
                    alpha = highestValue;

                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }

        return bestMove;
    }

    /**
     * Determines the move that will lead to best value for player O
     * @param game the game that is being evaluated
     * @param alpha the alpha value needed to complete alpha-beta pruning
     * @param beta the beta value needed to complete alpha-beta pruning
     * @return the move that holds the best potential for player O
     */
    private Move minValue(HumanVsComputerGame game, int alpha, int beta) {
        // if the game is over, we only care about the game's board value
        if (game.isGameOver()) {
            return new Move(MAX_START, MAX_START, game.boardValue());
        }

        Move bestMove = null;
        int lowestValue = MAX_START;

        for (Move move: this.getEmptySquares()) {
            HumanVsComputerGame newGame = this.copy();
            newGame.makeMove(move.row, move.col, newGame.getCurrentPlayer());
            int moveValue = newGame.maxValue(newGame, alpha, beta).value;

            if (moveValue < lowestValue) {
                bestMove = move;
                bestMove.value = moveValue;
                lowestValue = moveValue;

                if (lowestValue < beta) {
                    beta = lowestValue;

                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }

        return bestMove;
    }

    /**
     * Determines the value of the board for mini-max calculations
     * @return 1 if X has won, -1 if O has won, 0 otherwise
     */
    public int boardValue() {
        if (this.hasPlayerWon(Player.X)) {
            return Values.PLAYER_X_WON;
        } else if (this.hasPlayerWon(Player.O)) {
            return Values.PLAYER_O_WON;
        }

        return Values.NEITHER_PLAYER_WON;
    }

    /**
     * Creates a clone of the current game
     * @return a clone of the current game
     */
    public HumanVsComputerGame copy() {
        HumanVsComputerGame other = new HumanVsComputerGame(Player.O);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (this.board.getPlayerAtSquare(i, j) != Player.NONE) {
                    other.makeMove(i, j, this.board.getPlayerAtSquare(i, j));
                }
            }
        }

        other.computerPlayer = this.computerPlayer;
        return other;
    }

    /**
     * Creates a move for each of the remaining squares without player values
     * @return a list of each of the possible moves left to be made
     */
    private List<Move> getEmptySquares() {
        List<Move> emptySquares = new ArrayList<>();

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (this.getPlayerAtSquare(i, j) == Player.NONE) {
                    emptySquares.add(new Move(i, j, 0));
                }
            }
        }

        return emptySquares;
    }
}

/**
 * Move.java
 *
 * A helper class to assist the mini-max algorithm in finding potential moves to make
 */
class Move {
    /** the row this move represents */
    protected int row;
    /** the column this move represents */
    protected int col;
    /** the value that could be created for the player by making this move */
    protected int value;

    /**
     * Generates a move
     * @param row the row this move represents
     * @param col the column this move represents
     * @param value the value that could be created for the player by making this move
     */
    protected Move(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
}
