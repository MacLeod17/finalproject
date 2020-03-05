package edu.neumont.csc150.c.finalproject.model.terrains;

public class Hills extends Terrain {

    public Hills() {
        this.setIntroString("You find yourself overlooking rolling green hills, lush with flora and fauna.");
        this.setName("Hills");
        this.setPlaceMarker('H');

        this.addEnemy("Goblin",2, 6, 10, 0, 1, 6, 30, false);
        this.addEnemy("Orc", 2, 8, 12, 1, 1, 8, 20, false);
        this.addEnemy("Zombie", 2, 6, 11, 1, 1, 8, 2, false);
        this.addEnemy("Skeleton", 1, 10, 14, 2, 1, 10, 0, false);
        this.addEnemy("Wolverine", 3, 8, 12, 2, 1, 6, 5, false);
        this.addEnemy("Stray Traveler", 1, 6, 10, 0, 1, 6, 20, true);
        this.addEnemy("Wolf", 2, 8, 12, 1, 1, 6, 0, false);
        this.addEnemy("Wandering hero", 5, 12, 18, 5, 2, 6, 50, true);
        this.addEnemy("Wild Boar", 3, 8, 12, 2, 3, 4, 1, false);
        this.addEnemy("Brown Bear", 6, 8, 14, 2, 2, 6, 1, false);
        this.addEnemy("Harpy", 2, 12, 15, 2, 1, 6, 15, false);
        this.addEnemy("Mimic", 8, 8, 17, 3, 3, 4, 45, false);
        this.addEnemy("Wolf", 2, 8, 12, 1, 1, 6, 0, false);
        this.addEnemy("Rival Adventurer", 4, 10, 16, 3, 1, 10, 30, false);
        this.addEnemy("Giant Rat", 1, 10, 13, 3, 1, 4, 10, false);

    }
}
