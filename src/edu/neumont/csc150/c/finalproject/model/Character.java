package edu.neumont.csc150.c.finalproject.model;

import java.util.Random;

public abstract class Character {
    private String hitType;
    private String name;

    private int totalHealth;
    private int currentHealth;
    private int armorClass;
    private int damageMod;
    private int hitBonus;
    private int attackDice;
    private int attackSides;
    private final int MIN_CURRENT_HEALTH = 0;
    private final int MIN_NAME_LENGTH = 2;

    private Random gen = new Random();

    public Character() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("Name must be at least %d characters long", MIN_NAME_LENGTH));
        }
        this.name = name;
    }

    public int getTotalHealth() {
        return this.totalHealth;
    }

    /** Used for reloading a character for a new session */
    public void setTotalHealth(int hp) {
        this.totalHealth = hp;
    }

    /** Used for rolling health the first time (Ex. a creature with 5d8 hp) */
    public void setTotalHealth(int dice, int sides) {
        this.totalHealth = roll(dice, sides);
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    /** Used *ONLY* for reloading characters, possibly special attack types */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if(this.currentHealth < MIN_CURRENT_HEALTH) {
            this.currentHealth = MIN_CURRENT_HEALTH;
        }
    }

    public int getArmorClass() {
        return this.armorClass;
    }

    public void setArmorClass(int armorClass) {
        if (armorClass < 10 || armorClass > 30) {
            throw new IllegalArgumentException("armorClass must be between 10 and 30");
        }
        this.armorClass = armorClass;
    }

    public String getHitType() {
        return this.hitType;
    }

    public void setHitType(String hitType) {
        this.hitType = hitType;
    }

    public int getDamageMod() {
        return this.damageMod;
    }

    public void setDamageMod(int damageMod) {
        if (damageMod < 0) {
            throw new IllegalArgumentException("damageMod must be non-negative");
        }
        this.damageMod = damageMod;
    }

    public int getHitBonus() {
        return this.hitBonus;
    }

    public void setHitBonus(int hitBonus) {
        if (hitBonus < 0) {
            throw new IllegalArgumentException("hitBonus must be non-negative");
        }
        this.hitBonus = hitBonus;
    }

    public int getAttackDice() {
        return this.attackDice;
    }

    public void setAttackDice(int attackDice) {
        if (attackDice < 1) {
            throw new IllegalArgumentException();
        }
        this.attackDice = attackDice;
    }

    public int getAttackSides() {
        return this.attackSides;
    }

    public void setAttackSides(int attackSides) {
        if (attackSides < 1) {
            throw new IllegalArgumentException();
        }
        this.attackSides = attackSides;
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

    /** Used for normal attacks */
    public int attack(int enemyArmorClass) {
        return attack(enemyArmorClass, 0, 1);
    }

    /** Damage Multiplier is for special attack types like Thief's sneak attack (if special attacks are ever implemented) */
    public int attack(int enemyArmorClass, int specialHitBonus, int damageMultiplier) {
        int rawRoll = roll(1, 20); /** Simulates the d20 roll without any bonuses */
        int roll = rawRoll + this.hitBonus + specialHitBonus; /** Simulates d20 after character's bonus to hit
        * Minimum 1 in 20 chance of hitting or missing */
        if (rawRoll == 1) {
            setHitType("Natch 1! Critical Miss!");
            return 0;
        }
        else if (rawRoll == 20) {
            setHitType("Natch 20! Critical Hit!");
            return (this.attackDice * this.attackSides) * damageMultiplier;
        }
        else if (roll >= enemyArmorClass) {
            setHitType(String.format("%d, Hit!", roll));
            return roll(this.attackDice, this.attackSides) * damageMultiplier;
        }
        else {
            setHitType(String.format("%d, Miss!", roll));
            return 0;
        }
    }

    public Boolean checkForDeath() {
        if(this.currentHealth <= 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
