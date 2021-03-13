package com.example.androidtictactoe.model;

/**
 * Player.java
 *
 * An enumeration representing the players playing X, O, or the case where there is not yet
 * a player assigned to an object
 *
 * @author Chris Wolf
 * @version 1.0.0 (March 13, 2021)
 */
public enum Player {
    /** Represents the player who is playing X **/
    X("X"),
    /** Represents the player who is playing O **/
    O("O"),
    /** Represents the case where there is not a player assigned to a given object **/
    NONE(" ");

    /** The string representation of the player */
    private String label;

    /**
     * Creates the player with the correct label
     * @param label a string representation of the player
     */
    Player(String label) {
        this.label = label;
    }

    /**
     * Returns the string representation of the player
     * @return the string representation of the player
     */
    public String getLabel() {
        return this.label;
    }
}
