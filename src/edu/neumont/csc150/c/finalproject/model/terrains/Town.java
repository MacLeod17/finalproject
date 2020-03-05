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
        this.addEnemy("Professor Cox", 3, 6, 13, 2, 1, 8, 31, true);
        this.addEnemy("Fermin", 2, 8, 12, 0, 1, 10, 15, true);
        this.addEnemy("Elijah", 2, 8, 13, 1, 1, 8, 15, true);
        this.addEnemy("Giant Spider", 4, 8, 15, 5, 2, 8, 20, false);
        this.addEnemy("Neighbor", 1, 6, 10, 1, 1, 4, 10, true);
        this.addEnemy("Cheating ex-spouse", 2, 4, 10, 2, 1, 6, 20, true);
        this.addEnemy("Giant Rat", 1, 10, 13, 3, 1, 4, 10, false);
        this.addEnemy("Nega-Cox", 9, 10, 25, 6, 4, 6, 51, false);
        this.addEnemy("Nega-Fermin", 5, 10, 20, 3, 2, 10, 35, false);
        this.addEnemy("Nega-Elijah", 4, 12, 21, 4, 2, 8, 35, false);
    }
}