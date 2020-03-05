package edu.neumont.csc150.c.finalproject.model;

import java.util.Random;

public class Enemy extends Character {
    private int gold;
    private int expValue;
    private int healthDice;
    private int healthSides;
    private boolean despicableAct;
    private Random gen = new Random();

    public Enemy() {

    }

    public Enemy(String name, int healthDice, int healthSides, int armorClass, int damageMod, int attackDice, int attackSides, int gold, boolean despicableAct) {
        this.setName(name);
        /** Two different enemies of the same type can have different total health
         (Some individuals will be tougher than others of the same type) */
        this.setHealthDice(healthDice);
        this.setHealthSides(healthSides);
        this.setTotalHealth(healthDice, healthSides);
        this.setCurrentHealth(this.getTotalHealth());
        this.setArmorClass(armorClass);
        this.setDamageMod(damageMod);
        this.setHitBonus(healthDice - 3);
        /** Bigger Bads attack better */
        this.setAttackDice(attackDice);
        this.setAttackSides(attackSides);
        this.setGold(gold);
        this.setDespicableAct(despicableAct);
        this.setExpValue();
    }

    public int getExpValue() {
        return this.expValue;
    }

    /** If one goblin rolls low on hit points, he will be worth less experience than one who rolls high because
     * totalHealth can severely affect difficulty of the battle */
    private void setExpValue() {
        this.expValue = this.getTotalHealth() * 20;
    }

    public void resetEnemy() {
        this.setTotalHealth(this.healthDice, this.healthSides);
        this.setCurrentHealth(this.getTotalHealth());
        this.setExpValue();
    }

    private void setHealthDice(int healthDice) {
        this.healthDice = healthDice;
    }

    private void setHealthSides(int healthSides) {
        this.healthSides = healthSides;
    }

    public int getGold() {
        return gen.nextInt(this.gold + 1);
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

    /** The string returned is intended to hide specific details from the user (The user shouldn't know the enemy's damage range,
     * hit bonus, damage bonus, exp value, how much gold it has, etc) */
    public String toBattleString() {
        return String.format(
                "\r\nName: %s" +
                "\r\nHealth: %d/%d" +
                "\r\nDespicable Act: %b",
                this.getName(), this.getCurrentHealth(), this.getTotalHealth(), this.isDespicableAct());
    }
}
