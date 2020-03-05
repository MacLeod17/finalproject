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
        this.addEnemy("Pit Fiend", 13, 8, 23, 6, 1, 10, 40, false);
        this.addEnemy("Fire Elemental", 8, 8, 19, 1, 3, 8, 5, false);
        this.addEnemy("Mimic", 8, 8, 17, 3, 3, 4, 45, false);
        this.addEnemy("Mummy", 5, 10, 19, 2, 1, 12, 42, false);
        this.addEnemy("Rival Adventurer", 4, 10, 16, 3, 1, 10, 30, false);
        this.addEnemy("Cactus Man", 6, 8, 21, 3, 4, 4, 20, false);
        this.addEnemy("Pharaoh Corey", 8, 10, 25, 4, 4, 4, 100, true);
        this.addEnemy("Noah the Golem", 9, 10, 24, 3, 3, 6, 55, false);
        this.addEnemy("Mind Flayer Garrick", 7, 10, 26, 4, 3, 10, 66, false);

    }
}
