package edu.neumont.csc150.c.finalproject.model.playerclasses;

public class Wizard extends Player {

    public Wizard() {
    }

    public Wizard(String name, String gender) {
        super(name, 6, 10, 0, 0, 1, 8, gender);
    }

    public void raiseLevel() {
        super.raiseLevel(roll(1, 6));
        if (this.getLevel() % 2 == 0) {
            super.raiseHitBonus();
        }
    }

    @Override
    public void raiseArmorClass() {
        super.raiseArmorClass(150, 2);
    }
}
