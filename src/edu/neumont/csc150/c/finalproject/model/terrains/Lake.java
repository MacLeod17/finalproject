package edu.neumont.csc150.c.finalproject.model.terrains;

public class Lake extends Terrain {

    public Lake() {
        this.setIntroString("You find yourself on the edge of a beautiful lake. It seems like a great place to rest.");
        this.setName("Lake");
        this.setPlaceMarker('L');

        this.addEnemy("Giant Frog", 2, 8, 13, 1, 2, 4, 2, false);
        this.addEnemy("Poisonous Giant Frog", 2, 8, 12, 5, 2, 4, 3, false);
        this.addEnemy("Mimic", 8, 8, 17, 3, 3, 4, 45, false);
        this.addEnemy("Siren", 2, 8, 16, 2, 2, 4, 25, false);
        this.addEnemy("Swarm of Mosquitos", 2, 6, 17, 5, 3, 4, 15, false);
        this.addEnemy("Piranha", 2, 6, 14, 3, 2, 8,17 , false);
        this.addEnemy("Water elemental", 9, 8, 22, 3, 5, 4, 46, false);
        this.addEnemy("Wind elemental", 8, 8, 24, 2, 3, 6, 31, false);
        this.addEnemy("Goblin",2, 6, 10, 0, 1, 6, 30, false);
        this.addEnemy("Wandering hero", 5, 12, 18, 5, 2, 6, 50, true);
        this.addEnemy("Rival Adventurer", 4, 10, 16, 3, 1, 10, 30, false);
    }
}
