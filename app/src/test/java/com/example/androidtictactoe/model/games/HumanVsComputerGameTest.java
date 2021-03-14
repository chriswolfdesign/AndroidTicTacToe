package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.Values;
import com.example.androidtictactoe.model.settings.BoardSettings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanVsComputerGameTest {

    // boardValue tests

    @Test
    public void testBoardValueEmptyBoardNeitherPlayerWon() {
        HumanVsComputer g = new HumanVsComputer(Player.X);
        assertEquals(g.boardValue(), Values.NEITHER_PLAYER_WON);
    }

    @Test
    public void testBoardValuesMovesMadeNeitherPlayerWon() {
        HumanVsComputer g = new HumanVsComputer(Player.X);

        assertTrue(g.makeMove(0, 0, Player.X));
        assertTrue(g.makeMove(0, BoardSettings.BOARD_SIZE - 1, Player.O));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, 0, Player.X));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.O));

        assertEquals(g.boardValue(), Values.NEITHER_PLAYER_WON);
    }

    @Test
    public void testBoardValuesXWonByRowsPlayerXWon() {
        HumanVsComputer g = new HumanVsComputer(Player.X);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(0, i, Player.X));
        }

        assertEquals(g.boardValue(), Values.PLAYER_X_WON);
    }

    @Test
    public void testBoardValuesOWonByColsPlayerOWon() {
        HumanVsComputer g = new HumanVsComputer(Player.O);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, 0, Player.O));
        }

        assertEquals(g.boardValue(), Values.PLAYER_O_WON);
    }

    // clone Tests

    @Test
    public void testCloneEmptyBoard() {
        HumanVsComputer g = new HumanVsComputer(Player.X);
        HumanVsComputer copy = g.clone();

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                assertEquals(g.getPlayerAtSquare(i, j), copy.getPlayerAtSquare(i, j));
            }
        }

        assertEquals(g.getCurrentPlayer(), copy.getCurrentPlayer());
    }

    @Test
    public void testCloneCornersHaveBeenPlayed() {
        HumanVsComputer g = new HumanVsComputer(Player.X);

        assertTrue(g.makeMove(0, 0, Player.X));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, 0, Player.O));
        assertTrue(g.makeMove(0, BoardSettings.BOARD_SIZE -1, Player.X));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.O));

        HumanVsComputer copy = g.clone();

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                assertEquals(g.getPlayerAtSquare(i, j), copy.getPlayerAtSquare(i, j));
            }
        }

        assertEquals(g.getCurrentPlayer(), copy.getCurrentPlayer());
    }

    @Test
    public void testCloneAllSquaresHaveBeenPlayed() {
        HumanVsComputer g = new HumanVsComputer(Player.X);

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (Math.random() < 0.5) {
                    assertTrue(g.makeMove(i, j, Player.X));
                } else {
                    assertTrue(g.makeMove(i, j, Player.O));
                }
            }
        }

        HumanVsComputer copy = g.clone();

        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                assertEquals(g.getPlayerAtSquare(i, j), copy.getPlayerAtSquare(i, j));
            }
        }

        assertEquals(g.getCurrentPlayer(), copy.getCurrentPlayer());
    }
}
