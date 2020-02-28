package edu.neumont.csc150.c.finalproject.model;

public class Enemy {
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int damage;
    private int gold;
    private boolean despicableAct;
    private final int MIN_CURRENT_HEALTH = 0;

    public Enemy() {

    }

    public Enemy(String name, int damage, int gold, boolean despicableAct) {
        this.setName(name);
        this.setDamage(damage);
        this.setGold(gold);
        this.setDespicableAct(despicableAct);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
        this.currentHealth = maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isDespicableAct() {
        return despicableAct;
    }

    public void setDespicableAct(boolean despicableAct) {
        this.despicableAct = despicableAct;
    }
}
