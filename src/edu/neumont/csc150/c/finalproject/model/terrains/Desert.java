package edu.neumont.csc150.c.finalproject.model.terrains;

public class Desert extends Terrain {

    public Desert() {
        this.setIntroString("You find yourself in a warm, arid desert. Your mouth dries out at the thought of traversing it.");
        this.setName("Desert");
        this.setPlaceMarker('D');

        this.addEnemy("Goblin", 2, 6, 10, 0, 1, 6, 30, false);
        this.addEnemy("Orc", 2, 8, 12, 1, 1, 8, 20, false);
        this.addEnemy("Zombie", 2, 6, 11, 1, 1, 8, 2, false);
        this.addEnemy("Skeleton", 1, 10, 14, 2, 1, 10, 0, false);
    }
}
