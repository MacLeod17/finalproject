package edu.neumont.csc150.c.finalproject.model.terrains;

import edu.neumont.csc150.c.finalproject.model.Enemies;

public class Forest extends Terrain {

    public Forest() {
        this.setIntroString("You find yourself in a forest. You are in awe at the greenery as clean air fills your lungs");
        this.setName("Forest");
        this.setPlaceMarker('F');

        this.addEnemy("Goblin",2, 6, 10, 0, 1, 6, 30, false);
        this.addEnemy("Orc", 2, 8, 12, 1, 1, 8, 20, false);
        this.addEnemy("Zombie", 2, 6, 11, 1, 1, 8, 2, false);
        this.addEnemy("Skeleton", 1, 10, 14, 2, 1, 10, 0, false);
        this.addEnemy("Wolverine", 3, 8, 12, 2, 1, 6, 0, false);
        this.addEnemy("Wight", 4, 8, 16, 1, 1, 6, 25, false);
        this.addEnemy("Wolf", 2, 8, 12, 1, 1, 6, 0, false);
        this.addEnemy("Vampire", 8, 8, 17, 4, 1, 6, 60, false);
        this.addEnemy("Stray Traveler", 1, 6, 10, 0, 1, 6, 20, true);
        this.addEnemy("Wandering hero", 5, 12, 18, 5, 2, 6, 50, true);

        this.addEnemy("Rival Adventurer", 4, 10, 16, 3, 1, 10, 30, false);
        this.addEnemy("Wild Boar", 3, 8, 12, 2, 3, 4, 1, false);
        this.addEnemy("Brown Bear", 6, 8, 14, 2, 2, 6, 1, false);
        this.addEnemy("Cave Bear", 8, 8, 16, 4, 2, 8, 5, false);
        this.addEnemy("Professor Cox", 3, 6, 13, 2, 1, 8, 31, true);
        this.addEnemy("Fermin", 2, 8, 12, 0, 1, 10, 15, true);
        this.addEnemy("Elijah", 2, 8, 13, 1, 1, 8, 15, true);
        this.addEnemy("Swarm of Bats", 2, 6, 17, 2, 2, 6, 6, false);
        this.addEnemy("Giant Spider", 4, 8, 15, 5, 2, 8, 20, false);
        this.addEnemy("Red Dragon", 10, 10, 27, 10, 3, 10, 100, false);

        this.addEnemy("Gold Dragon", 11, 10, 29, 10, 3, 12, 115, false);
        this.addEnemy("Neighbor", 1, 6, 10, 1, 1, 4, 10, true);
        this.addEnemy("Cheating ex-spouse", 2, 4, 10, 2, 1, 6, 20, true);
        this.addEnemy("Giant Rat", 1, 10, 13, 3, 1, 4, 10, false);
        this.addEnemy("Nega-Cox", 9, 10, 25, 6, 4, 6, 51, false);
        this.addEnemy("Nega-Fermin", 5, 10, 20, 3, 2, 10, 35, false);
        this.addEnemy("Nega-Elijah", 4, 12, 21, 4, 2, 8, 35, false);
        this.addEnemy("Skeleton Warrior", 2, 8, 16, 3, 1, 12, 10, false);
        this.addEnemy("Pit Fiend", 13, 8, 23, 6, 1, 10, 40, false);
        this.addEnemy("Fire Elemental", 8, 8, 19, 1, 3, 8, 5, false);

        this.addEnemy("Earth Elemental", 10, 8, 21, 4, 4, 6, 15, false);
        this.addEnemy("Gelatinous Cube", 4, 8, 12, 1, 2, 4, 10, false);
        this.addEnemy("Harpy", 2, 12, 15, 2, 1, 6, 15, false);
        this.addEnemy("Giant Frog", 2, 8, 13, 1, 2, 4, 2, false);
        this.addEnemy("Poisonous Giant Frog", 2, 8, 12, 5, 2, 4, 3, false);
        this.addEnemy("Green Slime", 2, 8, 12, 2, 1, 6, 5, false);
        this.addEnemy("Mimic", 8, 8, 17, 3, 3, 4, 45, false);
        this.addEnemy("Wild Connor", 5, 8, 20, 3, 3, 6, 50, false);
        this.addEnemy("Minotaur", 7, 8, 21, 4, 1, 10, 30, false);
        this.addEnemy("Beholder", 9, 8, 23, 2, 2, 6, 15, false);
    }
}
