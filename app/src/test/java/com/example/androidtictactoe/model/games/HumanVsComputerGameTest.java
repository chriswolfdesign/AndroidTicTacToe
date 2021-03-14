package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.Values;
import com.example.androidtictactoe.model.settings.BoardSettings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * HumanVsComputerGameTest.java
 *
 * Unit testing for HumanVsComputerGame.java
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 14, 2021)
 */
public class HumanVsComputerGameTest {
    // boardValue tests

    @Test
    public void testBoardValueEmptyBoardNeitherPlayerWon() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.X);
        assertEquals(g.boardValue(), Values.NEITHER_PLAYER_WON);
    }

    @Test
    public void testBoardValuesMovesMadeNeitherPlayerWon() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        assertTrue(g.makeMove(0, 0, Player.X));
        assertTrue(g.makeMove(0, BoardSettings.BOARD_SIZE - 1, Player.O));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, 0, Player.X));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.O));

        assertEquals(g.boardValue(), Values.NEITHER_PLAYER_WON);
    }

    @Test
    public void testBoardValuesXWonByRowsPlayerXWon() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(0, i, Player.X));
        }

        assertEquals(g.boardValue(), Values.PLAYER_X_WON);
    }

    @Test
    public void testBoardValuesOWonByColsPlayerOWon() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, 0, Player.O));
        }

        assertEquals(g.boardValue(), Values.PLAYER_O_WON);
    }

    // clone Tests

    @Test
    public void testCloneEmptyBoard() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.X);
        HumanVsComputerGame copy = g.copy();

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                assertEquals(g.getPlayerAtSquare(i, j), copy.getPlayerAtSquare(i, j));
            }
        }

        assertEquals(g.getCurrentPlayer(), copy.getCurrentPlayer());
    }

    @Test
    public void testCloneCornersHaveBeenPlayed() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        assertTrue(g.makeMove(0, 0, Player.X));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, 0, Player.O));
        assertTrue(g.makeMove(0, BoardSettings.BOARD_SIZE -1, Player.X));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.O));

        HumanVsComputerGame copy = g.copy();

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                assertEquals(g.getPlayerAtSquare(i, j), copy.getPlayerAtSquare(i, j));
            }
        }

        assertEquals(g.getCurrentPlayer(), copy.getCurrentPlayer());
    }

    @Test
    public void testCloneAllSquaresHaveBeenPlayed() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (Math.random() < 0.5) {
                    assertTrue(g.makeMove(i, j, Player.X));
                } else {
                    assertTrue(g.makeMove(i, j, Player.O));
                }
            }
        }

        HumanVsComputerGame copy = g.copy();

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                assertEquals(g.getPlayerAtSquare(i, j), copy.getPlayerAtSquare(i, j));
            }
        }

        assertEquals(g.getCurrentPlayer(), copy.getCurrentPlayer());
    }

    // makeComputerMove tests

    @Test
    public void testMakeComputerMoveFirstMove() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.X);

        assertEquals(g.getPlayerAtSquare(0, 0), Player.X);
    }

    @Test
    public void testMakeComputerMoveSecondMove() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        assertEquals(g.getPlayerAtSquare(0, 0), Player.NONE);
        assertTrue(g.makeMove(0, 0));

        int expected = BoardSettings.BOARD_SIZE * BoardSettings.BOARD_SIZE - 2;
        assertEquals(g.remainingEmptySquares(), expected);
    }

    @Test
    public void testMakeComputerMoveComputerShouldWinByMultipleMoves() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        for (int i = 0; i < BoardSettings.BOARD_SIZE - 1; i++) {
            assertTrue(g.makeMove(0, i, Player.X));
            assertTrue(g.makeMove(1, i, Player.O));
        }

        // Ensure that it's player O's turn
        g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.X);

        g.makeComputerMove();
        g.makeMove(1, BoardSettings.BOARD_SIZE - 1, Player.X);
        g.makeComputerMove();

        /*
          X O O
          X O
          O X O
         */
        assertTrue(g.hasPlayerWon(Player.O));
    }

    @Test
    public void testMakeComputerMoveComputerHasOneWinningMoveTakesIt() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        /*
           X X O
           X X O
           O
         */
        assertTrue(g.makeMove(0, 0, Player.X));
        assertTrue(g.makeMove(0, 2, Player.O));
        assertTrue(g.makeMove(0, 1, Player.X));
        assertTrue(g.makeMove(1, 2, Player.O));
        assertTrue(g.makeMove(1, 1, Player.X));
        assertTrue(g.makeMove(2, 0, Player.O));
        assertTrue(g.makeMove(1, 0, Player.X));

        g.makeComputerMove();
        assertTrue(g.isGameOver());
        assertTrue(g.hasPlayerWon(Player.O));
        assertEquals(g.getPlayerAtSquare(2, 2), Player.O);
        assertEquals(g.getPlayerAtSquare(2, 1), Player.NONE);
    }

    @Test
    public void testMakeComputerMOveComputerCannotWinShouldForceTie() {
        HumanVsComputerGame g = new HumanVsComputerGame(Player.O);

        /*
          X O X
          O X X
          O
         */
        assertTrue(g.makeMove(0, 0, Player.X));
        assertTrue(g.makeMove(0, 1, Player.O));
        assertTrue(g.makeMove(0, 2, Player.X));
        assertTrue(g.makeMove(1, 0, Player.O));
        assertTrue(g.makeMove(1, 1, Player.X));
        assertTrue(g.makeMove(2, 0, Player.O));
        assertTrue(g.makeMove(1, 2, Player.X));

        g.makeComputerMove();
        assertFalse(g.isGameOver());
        assertEquals(g.getPlayerAtSquare(2, 2), Player.O);
        assertEquals(g.getPlayerAtSquare(2, 1), Player.NONE);
    }
}
