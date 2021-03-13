package com.example.androidtictactoe.model;

import com.example.androidtictactoe.model.settings.BoardSettings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    Board b;

    @Before
    public void setup() {
        b = new Board();
    }

    // getPlayerAtSquare tests

    @Test
    public void testGetPlayerAtSquareRowTooLow() {
        assertNull(b.getPlayerAtSquare(-1, BoardSettings.BOARD_SIZE - 1) );
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
}
