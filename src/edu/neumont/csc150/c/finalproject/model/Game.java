package edu.neumont.csc150.c.finalproject.model;

public class Game {
    /** What the game checks */
    char[][] gameMap = new char[15][15];

    /** The map that is shown to the user, so that the terrain for each spot is not truly overwritten by the user's current location */
    char[][] userMap = new char[15][15];
}
