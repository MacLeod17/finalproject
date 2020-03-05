package edu.neumont.csc150.c.finalproject.model.terrains;

public class Mountains extends Terrain {

    public Mountains() {
        this.setIntroString("You find yourself walking a narrow path through the mountains");
        this.setName("Mountains");
        this.setPlaceMarker('M');

        this.addEnemy("Goblin",2, 6, 10, 0, 1, 6, 30, false);
        this.addEnemy("Orc", 2, 8, 12, 1, 1, 8, 20, false);
        this.addEnemy("Zombie", 2, 6, 11, 1, 1, 8, 2, false);
        this.addEnemy("Skeleton", 1, 10, 14, 2, 1, 10, 0, false);
        this.addEnemy("Harpy", 2, 12, 15, 2, 1, 6, 15, false);
        this.addEnemy("Wolverine", 3, 8, 12, 2, 1, 6, 5, false);
        this.addEnemy("Wolf", 2, 8, 12, 1, 1, 6, 0, false);
        this.addEnemy("Stray Traveler", 1, 6, 10, 0, 1, 6, 20, true);
        this.addEnemy("Wandering hero", 5, 12, 18, 5, 2, 6, 50, true);
        this.addEnemy("Rival Adventurer", 4, 10, 16, 3, 1, 10, 30, false);
        this.addEnemy("Cave Bear", 8, 8, 16, 4, 2, 8, 5, false);
        this.addEnemy("Swarm of Bats", 2, 6, 17, 2, 2, 6, 6, false);
        this.addEnemy("Giant Spider", 4, 8, 15, 5, 2, 8, 20, false);
        this.addEnemy("Red Dragon", 10, 10, 27, 10, 3, 10, 100, false);
        this.addEnemy("Gold Dragon", 11, 10, 29, 10, 3, 12, 115, false);
        this.addEnemy("Earth Elemental", 10, 8, 21, 4, 4, 6, 15, false);
        this.addEnemy("Wild Connor", 5, 8, 20, 3, 3, 6, 50, false);
        this.addEnemy("Minotaur", 7, 8, 21, 4, 1, 10, 30, false);

    }
}
