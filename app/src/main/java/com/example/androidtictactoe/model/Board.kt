package com.example.androidtictactoe.model

import com.example.androidtictactoe.settings.BoardSettings

class Board {
    private val tiles = Array(BoardSettings.BOARD_SIZE) {Array(BoardSettings.BOARD_SIZE){Player.NONE}}

    fun setPlayerAt(row: Int, col: Int, player: Player) {
        this.tiles[row][col] = player
    }

    fun getPlayerAt(row: Int, col: Int): Player {
        return this.tiles[row][col]
    }
}