package edu.neumont.csc150.c.finalproject.model.playerclasses;

public class Cleric extends Player {

    public Cleric() {
    }

    public Cleric(String name, String gender) {
        super(name, 10, 12, 0, 1, 1, 10, gender);

    }

    public void raiseLevel() {
        this.raiseLevel(roll(1, 10));
        if (this.getLevel() % 4 == 1) {
            this.raiseHitBonus();
        }
    }
}
