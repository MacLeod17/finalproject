package edu.neumont.csc150.c.finalproject.model;

public class Enemy extends Character {
    private int gold;
    private int percentChanceToFollowPlayer;
    private boolean despicableAct;

    public Enemy() {

    }

    public Enemy(String name, int healthDice, int healthSides, int armorClass, int damageMod, int attackDice, int attackSides, int gold, int percentChanceToFollowPlayer, boolean despicableAct) {
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
        this.setPercentChanceToFollowPlayer(percentChanceToFollowPlayer);
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

    /** Used if character decides to flee from battle */
    public int getPercentChanceToFollowPlayer() {
        return percentChanceToFollowPlayer;
    }

    public void setPercentChanceToFollowPlayer(int percentChanceToFollowPlayer) {
        if (percentChanceToFollowPlayer < 0) {
            throw new IllegalArgumentException("chance to follow player must be non-negative");
        }
        this.percentChanceToFollowPlayer = percentChanceToFollowPlayer;
    }

    public boolean isDespicableAct() {
        return despicableAct;
    }

    public void setDespicableAct(boolean despicableAct) {
        this.despicableAct = despicableAct;
    }
}
