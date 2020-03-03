package edu.neumont.csc150.c.finalproject.model.playerclasses;

public class Fighter extends Player {

    public Fighter() {
    }

    public Fighter(String name, String gender) {
        super(name, 12, 13, 2, 1, 2, 6, gender);
    }

    public void raiseLevel() {
        this.raiseLevel(roll(1, 12));
        this.raiseHitBonus();
    }
}
