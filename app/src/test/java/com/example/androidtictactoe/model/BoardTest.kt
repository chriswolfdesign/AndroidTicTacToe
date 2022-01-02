package com.example.androidtictactoe.model

import com.example.androidtictactoe.settings.BoardSettings
import junit.framework.Assert.assertEquals
import org.junit.Test

class BoardTest {
    @Test
    fun testAllBoardTilesStartAsPlayerNone() {
        val board = Board()
        for (row in 0 until BoardSettings.BOARD_SIZE) {
            for (col in 0 until BoardSettings.BOARD_SIZE) {
                assertEquals(Player.NONE, board.getPlayerAt(row, col))
            }
        }
    }

    @Test
    fun testChangeBoardTileToPlayerX() {
        val board = Board()
        assertEquals(Player.NONE, board.getPlayerAt(0, 0))
        board.setPlayerAt(0, 0, Player.X)
        assertEquals(Player.X, board.getPlayerAt(0, 0))
    }

    @Test
    fun testChangeBoardTileToPlayerO() {
        val board = Board()
        assertEquals(Player.NONE, board.getPlayerAt(1, 1))
        board.setPlayerAt(1, 1, Player.O)
        assertEquals(Player.O, board.getPlayerAt(1, 1))
    }
}