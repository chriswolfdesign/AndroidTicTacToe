package com.example.androidtictactoe.model

import junit.framework.Assert.assertEquals
import org.junit.Test

class PlayerTest {
    @Test
    fun testPlayerXCorrectRepresentation() {
        assertEquals("X", Player.X.representation)
    }

    @Test
    fun testPlayerOCorrectRepresentation() {
        assertEquals("O", Player.O.representation)
    }

    @Test
    fun testPlayerNoneCorrectRepresentation() {
        assertEquals(" ", Player.NONE.representation)
    }
}