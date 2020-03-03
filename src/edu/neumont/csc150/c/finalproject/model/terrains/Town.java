package edu.neumont.csc150.c.finalproject.model.terrains;

public class Town extends Terrain {

    public Town() {
        this.setIntroString("You find yourself at the edge of a town. According to your map, there are shops here for you to buy equipment");
        this.setName("Town");
        this.setPlaceMarker('T');

        this.addEnemy("Vampire", 8, 8, 17, 4, 1, 6, 60, false);
        this.addEnemy("Stray Traveler", 1, 6, 10, 0, 1, 6, 20, true);
        this.addEnemy("Wandering hero", 5, 12, 18, 5, 2, 6, 50, true);
        this.addEnemy("Rival Adventurer", 4, 10, 16, 3, 1, 10, 30, false);
    }
}