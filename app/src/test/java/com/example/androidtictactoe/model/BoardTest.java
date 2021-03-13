package com.example.androidtictactoe.model;

import com.example.androidtictactoe.model.settings.BoardSettings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * BoardTest.java
 *
 * Unit testing for Board.java
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public class BoardTest {
    Board b;

    @Before
    public void setup() {
        b = new Board();
    }

    // getPlayerAtSquare tests

    @Test
    public void testGetPlayerAtSquareRowTooLow() {
        assertNull(b.getPlayerAtSquare(-1, BoardSettings.BOARD_SIZE - 1));
    }

    @Test
    public void testGetPlayerAtSquareRowTooHigh() {
        assertNull(b.getPlayerAtSquare(BoardSettings.BOARD_SIZE, BoardSettings.BOARD_SIZE - 1));
    }

    @Test
    public void testGetPlayerAtSquareColTooLow() {
        assertNull(b.getPlayerAtSquare(BoardSettings.BOARD_SIZE - 1, -1));
    }

    @Test
    public void testGetPlayerAtSquareColTooHigh() {
        assertNull(b.getPlayerAtSquare(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE));
    }

    @Test
    public void testGetPlayerAtSquareRowAndColOutOfBounds() {
        assertNull(b.getPlayerAtSquare(-1, BoardSettings.BOARD_SIZE));
    }

    @Test
    public void testGetPlayerAtSquareShouldBeNone() {
        assertEquals(b.getPlayerAtSquare(1, 1), Player.NONE);
    }

    @Test
    public void testGetPlayerAtSquareShouldBeX() {
        assertTrue(b.placePlayerAtSquare(1, 1, Player.X));
        assertEquals(b.getPlayerAtSquare(1, 1), Player.X);
    }

    @Test
    public void testGetPlayerAtSquareShouldBeO() {
        assertTrue(b.placePlayerAtSquare(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.O));
        assertEquals(b.getPlayerAtSquare(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1), Player.O);
    }

    // placePlayerAtSquare tests

    @Test
    public void testPlacePlayerAtSquareRowTooLow() {
        assertFalse(b.placePlayerAtSquare(-1, BoardSettings.BOARD_SIZE - 1, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareRowTooHigh() {
        assertFalse(b.placePlayerAtSquare(BoardSettings.BOARD_SIZE, BoardSettings.BOARD_SIZE - 1, Player.O));
    }

    @Test
    public void testPlacePlayerAtSquareColTooLow() {
        assertFalse(b.placePlayerAtSquare(BoardSettings.BOARD_SIZE - 1, -1, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareColTooHigh() {
        assertFalse(b.placePlayerAtSquare(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE, Player.O));
    }

    @Test
    public void testPlacePlayerAtSquareRowAndColOutOfBounds() {
        assertFalse(b.placePlayerAtSquare(-1, BoardSettings.BOARD_SIZE, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareSquareAlreadyTaken() {
        assertTrue(b.placePlayerAtSquare(1, 1, Player.X));
        assertFalse(b.placePlayerAtSquare(1, 1, Player.O));
    }

    @Test
    public void testPlacePlayerAtSquareCannotPlaceNone() {
        assertFalse(b.placePlayerAtSquare(1, 1, Player.NONE));
    }

    @Test
    public void testPlacePlayerAtSquareXCorrectlyPlaced() {
        assertTrue(b.placePlayerAtSquare(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.X));
    }

    @Test
    public void testPlacePlayerAtSquareOCorrectlyPlaced() {
        assertTrue(b.placePlayerAtSquare(1, 1, Player.O));
    }

    // remainingEmptySquares Test

    @Test
    public void testRemainingEmptySquaresAllSquaresEmpty() {
        int expected = (int) Math.pow(BoardSettings.BOARD_SIZE, 2);
        assertEquals(b.remainingEmptySquares(), expected);
    }

    @Test
    public void testRemainingEmptySquaresAllButFourCornersRemaining() {
        int expected = (int) Math.pow(BoardSettings.BOARD_SIZE, 2) - 4;

        assertTrue(b.placePlayerAtSquare(1, 1, Player.X));
        assertTrue(b.placePlayerAtSquare(BoardSettings.BOARD_SIZE - 1, 1, Player.O));
        assertTrue(b.placePlayerAtSquare(1, BoardSettings.BOARD_SIZE - 1, Player.X));
        assertTrue(b.placePlayerAtSquare(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.O));

        assertEquals(b.remainingEmptySquares(), expected);
    }

    @Test
    public void testRemainingEmptySquaresNoEmptySquaresRemaining() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                assertTrue(b.placePlayerAtSquare(i, j, Player.X));
            }
        }

        assertEquals(b.remainingEmptySquares(), 0);
    }
}
