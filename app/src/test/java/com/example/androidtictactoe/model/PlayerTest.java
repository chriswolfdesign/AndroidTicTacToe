package com.example.androidtictactoe.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * PlayerTest.java
 *
 * Unit testing for Player.java
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public class PlayerTest {
    @Test
    public void testPlayerXConstructor() {
        Player p = Player.X;
        assertEquals(p.getLabel(), "X");
    }

    @Test
    public void testPlayerOConstructor() {
        Player p = Player.O;
        assertEquals(p.getLabel(), "O");
    }

    @Test
    public void testNoPlayerConstructor() {
        Player p = Player.NONE;
        assertEquals(p.getLabel(), " ");
    }
}
