package edu.neumont.csc150.c.finalproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Terrain> terrains = new ArrayList<>();
    private Random gen = new Random();

    /** What the game checks */
    private char[][] gameMap = new char[15][15];

    /** The map that is shown to the user, so that the terrain for each spot is not truly overwritten by the user's current location */
    private char[][] userMap = new char[15][15];

    /** Used to store user's current location */
    private int currentCol;
    private int currentRow;

    public Game() {

    }

    public void moveNorth() {

    }

    public void moveSouth() {

    }

    public void moveEast() {

    }

    public void moveWest() {

    }
}
