package edu.neumont.csc150.c.finalproject.model;

public class Enemy extends Character {
    private int gold;
    private int percentChanceToFollowPlayer;
    private boolean despicableAct;

    public Enemy() {

    }

    public Enemy(String name, int healthDice, int healthSides, int armorClass, int damageMod, int attackDice, int attackSides, int gold, boolean despicableAct) {
        this.setName(name);
        this.setTotalHealth(this.roll(healthDice, healthSides));
        this.setCurrentHealth(this.getTotalHealth());
        /** Two different enemies of the same type can have different total health
        (Some individuals will be tougher than others of the same type) */
        this.setArmorClass(armorClass);
        this.setDamageMod(damageMod);
        this.setHitBonus(healthDice - 1);
        /** Basically sets hit bonus equal to number of Hit Dice (Monster equivalent of level); Bigger Bads attack better */
        this.setAttackDice(attackDice);
        this.setAttackSides(attackSides);
        this.setGold(gold);
        this.setDespicableAct(despicableAct);
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        if (gold < 0) {
            throw new IllegalArgumentException("gold must be non-negative");
        }
        this.gold = gold;
    }

    public boolean isDespicableAct() {
        return despicableAct;
    }

    public void setDespicableAct(boolean despicableAct) {
        this.despicableAct = despicableAct;
    }
}
