package edu.neumont.csc150.c.finalproject.model.terrains;

import edu.neumont.csc150.c.finalproject.model.Enemies;

public abstract class Terrain {
    private String introString;
    private String name;
    private Enemies enemies = new Enemies();
    private char placeMarker;

    public Terrain() {

    }

    public String getIntroString() {
        return introString;
    }

    public void setIntroString(String introString) {
        validateNotNull(introString, "introString");
        this.introString = introString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateNotNull(name, "name");
        this.name = name;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public void addEnemy(String name, int healthDice, int healthSides, int armorClass, int damageMod, int attackDice, int attackSides, int gold, boolean despicableAct) {
        this.enemies.addCreature(name, healthDice, healthSides, armorClass, damageMod, attackDice, attackSides, gold, despicableAct);
    }

    public char getPlaceMarker() {
        return placeMarker;
    }

    public void setPlaceMarker(char placeMarker) {
        validateNotNull(placeMarker, "placeMarker");
        this.placeMarker = placeMarker;
    }

    private void validateNotNull(Object parm, String name) {
        if(parm == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", name));
        }
    }
}
