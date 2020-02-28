package edu.neumont.csc150.c.finalproject.model;

public class Enemy {
    private String name;
    private int totalHealth;
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

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
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

    public boolean checkForDead() {
        if (this.currentHealth == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
