package edu.neumont.csc150.c.finalproject.model.playerclasses;

public class Thief extends Player {

    public Thief() {
    }

    public Thief(String name, String gender) {
        super(name, 8, 11, 1, 0, 1, 8, gender);
    }

    public void raiseLevel() {
        this.raiseLevel(roll(1, 8));
        if (!(this.getLevel() % 3 == 1)) {
            this.raiseHitBonus();
        }
    }
}
