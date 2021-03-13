package com.example.androidtictactoe.model.games;

import com.example.androidtictactoe.model.Player;
import com.example.androidtictactoe.model.settings.BoardSettings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * GameTest.java
 *
 * Unit testing for Game.java
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public class GameTest {
    Game g;

    @Before
    public void setup() {
        g = new MockGame();
    }

    // isGameOver tests

    @Test
    public void testIsGameOverNoMovesMadeShouldBeFalse() {
        assertFalse(g.isGameOver());
    }

    @Test
    public void testIsGameOverSomeMovesMadeShouldBeFalse() {
        assertTrue(g.makeMove(0, 0, Player.X));
        assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, BoardSettings.BOARD_SIZE - 1, Player.O));

        assertFalse(g.isGameOver());
    }

    @Test
    public void testIsGameOverRandomMovesMadeOnAllSquaresShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardSettings.BOARD_SIZE; j++) {
                if (Math.random() < 0.5) {
                    assertTrue(g.makeMove(i, j, Player.X));
                } else {
                    assertTrue(g.makeMove(i, j, Player.O));
                }
            }
         }

        assertTrue(g.isGameOver());
    }

    @Test
    public void testIsGameOverPlayerXWonThreeInARowShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(0, i, Player.X));
        }

        assertTrue(g.isGameOver());
    }

    @Test
    public void testIsGameOverPlayerOHasWonThreeInAColumnShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, 0, Player.O));
        }

        assertTrue(g.isGameOver());
    }

    @Test
    public void testIsGameOverPlayerXHasWonByTopLeftBottomRightDiagonalShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, i, Player.X));
        }

        assertTrue(g.isGameOver());
    }

    @Test
    public void testIsGameOverPlayerOHasWonByTopRightBottomLeftDiagonalShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, BoardSettings.BOARD_SIZE - 1 - i, Player.O));
        }

        assertTrue(g.isGameOver());
    }

    // hasPlayerWon tests

    @Test
    public void testHasPlayerWonNoMovesMadeShouldBeFalse() {
        assertFalse(g.hasPlayerWon(Player.X));
        assertFalse(g.hasPlayerWon(Player.O));
    }

    @Test
    public void testHasPlayerWonPlayerXWonByFirstRowShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(0, i, Player.X));
        }

        assertTrue(g.hasPlayerWon(Player.X));
        assertFalse(g.hasPlayerWon(Player.O));
    }

    @Test
    public void testHasPlayerWonPlayerOWonByLastRowShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(BoardSettings.BOARD_SIZE - 1, i, Player.O));
        }

        assertTrue(g.hasPlayerWon(Player.O));
        assertFalse(g.hasPlayerWon(Player.X));
    }

    @Test
    public void testHasPlayerWonPlayerXWonByFirstColumnShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, 0, Player.X));
        }

        assertTrue(g.hasPlayerWon(Player.X));
        assertFalse(g.hasPlayerWon(Player.O));
    }

    @Test
    public void testHasPlayerWonPlayerOWonByLastColumnShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, BoardSettings.BOARD_SIZE - 1, Player.O));
        }

        assertTrue(g.hasPlayerWon(Player.O));
        assertFalse(g.hasPlayerWon(Player.X));
    }

    @Test
    public void testHasPlayerWonPlayerXWonByTopLeftBottomRightDiagonalShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, i, Player.X));
        }

        assertTrue(g.hasPlayerWon(Player.X));
        assertFalse(g.hasPlayerWon(Player.O));
    }

    @Test
    public void testHasPlayerWonPlayerOWonByTopRightBottomLeftDiagonalShouldBeTrue() {
        for (int i = 0; i < BoardSettings.BOARD_SIZE; i++) {
            assertTrue(g.makeMove(i, BoardSettings.BOARD_SIZE - 1 - i, Player.O));
        }

        assertTrue(g.hasPlayerWon(Player.O));
        assertFalse(g.hasPlayerWon(Player.X));
    }
}

/**
 * A mock implementation of the Game class for means of testing
 */
class MockGame extends Game {
    public MockGame() {
        super();
    }

    @Override
    public boolean makeMove(int row, int col, Player player) {
        return this.board.placePlayerAtSquare(row, col, player);
    }
}
