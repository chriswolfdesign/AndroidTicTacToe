package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.settings.BoardSettings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TwoHumanGameTest.java
 *
 * Unit testing for TwoHumanGame.java
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public class TwoHumanGameTest {
    Game g;

    @Before
    public void setup() {
        g = new TwoHumanGame();
    }

    @Test
    public void testPlacePlayerAtSquareRowTooLow() {
        assertFalse(g.makeMove(-1, BoardSettings.BOARD_SIZE - 1, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareRowTooHigh() {
        assertFalse(g.makeMove(BoardSettings.BOARD_SIZE, BoardSettings.BOARD_SIZE - 1, Player.O));
    }

    @Test
    public void testPlacePlayerAtSquareColTooLow() {
        assertFalse(g.makeMove(BoardSettings.BOARD_SIZE - 1, -1, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareColTooHigh() {
        assertFalse(g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE, Player.O));
    }

    @Test
    public void testPlacePlayerAtSquareRowAndColOutOfBounds() {
        assertFalse(g.makeMove(-1, BoardSettings.BOARD_SIZE, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareSquareAlreadyTaken() {
        assertTrue(g.makeMove(1, 1, Player.X));
        assertFalse(g.makeMove(1, 1, Player.O));
    }

    @Test
    public void testPlacePlayerAtSquareCannotPlaceNone() {
        assertFalse(g.makeMove(1, 1, Player.NONE));
    }

    @Test
    public void testPlacePlayerAtSquareXCorrectlyPlaced() {
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareOCorrectlyPlaced() {
        assertTrue(g.makeMove(1, 1, Player.O));
    }
}
