package edu.neumont.csc150.c.finalproject.model;

import edu.neumont.csc150.c.finalproject.model.terrains.Terrain;
import edu.neumont.csc150.c.finalproject.model.terrains.Terrains;
import edu.neumont.csc150.c.finalproject.model.terrains.Town;

import java.util.Random;

public class Game {
    private Terrains terrains = new Terrains();
    private Random gen = new Random();

    private final int TOWN_COUNT = 6;
    private final int MAP_SIZE = 25;

    /** What the game checks */
    private char[][] gameMap = new char[MAP_SIZE][MAP_SIZE];

    /** The map that is shown to the user, so that the terrain for each spot is not truly overwritten by the user's current location */
    private char[][] userMap = new char[MAP_SIZE][MAP_SIZE];

    /** Used to store user's current location */
    private int currentCol = gen.nextInt(MAP_SIZE);
    private int currentRow = gen.nextInt(MAP_SIZE);

    public Game() {

    }

    public String getMap() {
        String map = "";
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {
                map += userMap[row][col] + "  ";
            }
            map += "\r\n";
        }
        return map;
    }

    public void initializeGameMap() {
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {
                gameMap[row][col] = '_';
            }
        }
        setGameMap();
        for (int i=0; i < TOWN_COUNT; i++) {
            initializeTowns();
        }
    }

    /** The if-else ensures that the correct number of towns is generated every time */
    private void initializeTowns() {
        int townRow = gen.nextInt(MAP_SIZE);
        int townCol = gen.nextInt(MAP_SIZE);
        if (gameMap[townRow][townCol] == '_') {
            gameMap[townRow][townCol] = 'T';
        }
        else {
            initializeTowns();
        }
    }

    public void setGameMap() {
        terrains.setIndexCounter();
        if (gameMap[currentRow][currentCol] == '_') {
            gameMap[currentRow][currentCol] = terrains.getTerrain().getPlaceMarker();
        }
    }

    /** userMap=gameMap doesn't save two maps, which is what we want; it just has two variables pointing to the same data.
     * saving two maps instead of one allows the user to see their current location without overwriting the terrain type */
    public void setUserMap() {
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {
                userMap[row][col] = gameMap[row][col];
            }
        }
        userMap[currentRow][currentCol] = 'X';
    }

    public void moveNorth() {
        if (currentRow == 0) {
            currentRow = MAP_SIZE - 1;
            return;
        }
        currentRow--;
    }

    public void moveSouth() {
        if (currentRow == MAP_SIZE - 1) {
            currentRow = 0;
            return;
        }
        currentRow++;
    }

    public void moveEast() {
        if (currentCol == MAP_SIZE - 1) {
            currentCol = 0;
            return;
        }
        currentCol++;
    }

    public void moveWest() {
        if (currentCol == 0) {
            currentCol = MAP_SIZE - 1;
            return;
        }
        currentCol--;
    }

    public Terrain getTerrain() {
        switch (gameMap[currentRow][currentCol]) {
            case 'F':
                return terrains.getTerrain(0);
            case 'D':
                return terrains.getTerrain(1);
            case 'H':
                return terrains.getTerrain(2);
            case 'M':
                return terrains.getTerrain(3);
            case 'L':
                return terrains.getTerrain(4);
            case 'T':
                return new Town();
            default:
                return null;
        }
    }

    public Enemy checkForEncounter() {
        int encounterValue = gen.nextInt(100) + 1;
        if (encounterValue < 50) {
            return this.getTerrain().getEnemies().getCreature();
        }
        else {
            return null;
        }
    }

    public String mapKey() {
        return "F: Forest, M: Mountains, H: Hills, T: Town, L: Lake, D: Desert, X: Current Location, _: Unexplored";
    }
}
