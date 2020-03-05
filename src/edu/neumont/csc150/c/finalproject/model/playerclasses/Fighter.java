package edu.neumont.csc150.c.finalproject.model.playerclasses;

public class Fighter extends Player {

    public Fighter() {
    }

    public Fighter(String name, String gender) {
        super(name, 12, 13, 2, 1, 2, 6, gender);
    }

    public void raiseLevel() {
        super.raiseLevel(roll(1, 12));
        super.raiseHitBonus();
    }

    @Override
    public void raiseArmorClass() {
        super.raiseArmorClass(150, 4);
    }
}
