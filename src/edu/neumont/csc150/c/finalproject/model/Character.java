package edu.neumont.csc150.c.finalproject.model;

import java.util.Random;

public abstract class Character {
    private int totalHealth;
    private int currentHealth;
    private int roll;
    private String hitType;
    private int damageMod;
    private int hitBonus;
    private int dice;
    private int sides;
    private final int MIN_CURRENT_HEALTH = 0;


    private Random gen = new Random();

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int hp) {
        this.totalHealth = hp;
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if(this.currentHealth < MIN_CURRENT_HEALTH) {
            this.currentHealth = MIN_CURRENT_HEALTH;
        }
    }

    public void resetCurrentHealth() {
        this.currentHealth = totalHealth;
    }

    protected int getRoll() {
        return roll;
    }

    protected void setRoll(int roll) {
        this.roll = roll;
    }

    protected String getHitType() {
        return hitType;
    }

    protected void setHitType(String hitType) {
        this.hitType = hitType;
    }

    public int getDamageMod() {
        return damageMod;
    }

    public void setDamageMod(int damageMod) {
        if (damageMod < 0) {
            throw new IllegalArgumentException("damageMod must be non-negative");
        }
        this.damageMod = damageMod;
    }

    public int getHitBonus() {
        return hitBonus;
    }

    public void setHitBonus(int hitBonus) {
        if (hitBonus < 1) {
            throw new IllegalArgumentException();
        }
        this.hitBonus = hitBonus;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        if (dice < 1) {
            throw new IllegalArgumentException();
        }
        this.dice = dice;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException();
        }
        this.sides = sides;
    }

    public int roll(int dice, int sides) {
        int roll = 0;
        while (dice > 0) {
            int die = gen.nextInt(sides) + 1;
            roll += die;
            dice --;
        }
        return roll;
    }

    public int attack(int enemyArmorClass, int dice, int sides, int hitBonus) {
        int rawRoll = roll(1, 20) + 1; //Simulates the d20 without any bonuses
        int roll = rawRoll + hitBonus; //Simulates d20 after character's bonus to hit
        if (rawRoll == 20) {
            setHitType("Natch 20!");
            return dice * sides;
        }
        else if (roll >= enemyArmorClass) {
            setHitType("Hit!");
            return roll(dice, sides);
        }
        else {
            setHitType("Miss!");
            return 0;
        }
    }
}
