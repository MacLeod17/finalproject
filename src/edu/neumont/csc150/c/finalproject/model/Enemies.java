package edu.neumont.csc150.c.finalproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemies {
    private List<Enemy> enemies = new ArrayList<>();
    private Random gen = new Random();

    public Enemies() {
    }

    public void addCreature(String name, int healthDice, int healthSides, int armorClass, int damageMod, int attackDice, int attackSides, int gold, boolean despicableAct) {
        enemies.add(new Enemy(name, healthDice, healthSides, armorClass, damageMod, attackDice, attackSides, gold, despicableAct));
    }

    public Enemy getCreature() {
        return this.enemies.get(gen.nextInt(enemies.size()));
    }
}
