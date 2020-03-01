package edu.neumont.csc150.c.finalproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemies {
    private List<Enemy> enemies = new ArrayList<>();
    private Random gen = new Random();
    private int indexCounter;

    Enemies() { // 4 Despicables, 36 Battles
        /** These show name, gold, what damage would'v been, and if killing them was despicable **/
//        enemies.add(new Enemy("Neighbor", 10, 10, true));
//        enemies.add(new Enemy("Cheating Ex-Spouse", 10, 20, true));
//        enemies.add(new Enemy("School Teacher", 15, 10, true));
//        enemies.add(new Enemy("Professor Cox", 15, 15, true));
//        enemies.add(new Enemy("Scorpion", 20, 0, false));
//        enemies.add(new Enemy("Swarm of Rats", 20, 5, false));
//        enemies.add(new Enemy("Thief", 20, 20, false));
//        enemies.add(new Enemy("Bogeyman", 25, 10, false));
//        enemies.add(new Enemy("Giant Rat", 25, 5, false));
//        enemies.add(new Enemy("Gremlin", 25, 15, false));
//
//        enemies.add(new Enemy("Savage", 25, 5, false));
//        enemies.add(new Enemy("Goblin", 30, 15, false));
//        enemies.add(new Enemy("Wild Connor", 30, 25, false));
//        enemies.add(new Enemy("Pack of Wolves", 35, 5, false));
//        enemies.add(new Enemy("Orcs", 35, 15, false));
//        enemies.add(new Enemy("Alien", 35, 25, false));
//        enemies.add(new Enemy("Assassin", 35, 30, false));
//        enemies.add(new Enemy("Barbarian", 40, 10, false));
//        enemies.add(new Enemy("Zombies", 40, 15, false));
//        enemies.add(new Enemy("Skeletons", 40, 20, false));
//
//        enemies.add(new Enemy("Ghost", 40, 20, false));
//        enemies.add(new Enemy("Harpy", 40, 25, false));
//        enemies.add(new Enemy("Witch", 40, 30, false));
//        enemies.add(new Enemy("Warlock", 40, 30, false));
//        enemies.add(new Enemy("Giant Spider", 45, 10, false));
//        enemies.add(new Enemy("Giant Scorpion", 45, 10, false));
//        enemies.add(new Enemy("Werewolf", 45, 20, false));
//        enemies.add(new Enemy("Vampire", 45, 20, false));
//        enemies.add(new Enemy("Rival Adventurer", 45, 25, false));
//        enemies.add(new Enemy("Minotaur", 50, 20, false));
//
//        enemies.add(new Enemy("Ogre", 50, 25, false));
//        enemies.add(new Enemy("Troll", 50, 25, false));
//        enemies.add(new Enemy("Cyclops", 50, 20, false));
//        enemies.add(new Enemy("Demon", 50, 30, false));
//        enemies.add(new Enemy("Manticore", 55, 15, false));
//        enemies.add(new Enemy("Evil Wizard", 55, 35, false));
//        enemies.add(new Enemy("Giant", 55, 25, false));
//        enemies.add(new Enemy("Dragon", 55, 30, false));
//        enemies.add(new Enemy("Killer Rabbit", 60, 20, false));
//        enemies.add(new Enemy("Kraken", 65, 40, false));
    }

    public void addCreature(String name, int healthDice, int healthSides, int armorClass, int damageMod, int attackDice, int attackSides, int gold, int percentChanceToFollowPlayer, boolean despicableAct) {
        enemies.add(new Enemy(name, healthDice, healthSides, armorClass, damageMod, attackDice, attackSides, gold, percentChanceToFollowPlayer, despicableAct));
    }

    public Enemy getCreature(int index) {
        return this.enemies.get(index);
    }

    public int getIndexCounter() {
        return this.indexCounter;
    }

    public void setIndex() {
        indexCounter = gen.nextInt(enemies.size());
    }
}
