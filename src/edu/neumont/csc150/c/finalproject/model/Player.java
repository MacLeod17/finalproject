package edu.neumont.csc150.c.finalproject.model;

public class Player extends Character {
    private String charClass;
    private String gender;

    private int level;
    private int daysActive;
    private int battlesWon;
    private int winsNeeded;
    private int winsToNextLevel;
    private int gold;
    private int potionsLeft;

    private Boolean regenerationRingActive;

    private final int MIN_GOLD = 0;
    private final int MIN_POTIONS = 0;

    public Player() {
        this.setName("Unknown");
        this.setCharClass("Fighter");
        this.setTotalHealth(1, 10);
        this.setCurrentHealth(this.getTotalHealth());
        this.setLevel(1);
        this.setBattlesWon(0);
        this.setGold(0);
        this.setPotionsLeft(0);
        this.setArmorClass(10);
        this.setWinsNeeded(5);
        this.setWinsToNextLevel(5);
        this.setGender("Other");
        this.setDaysActive(0);
        this.setRegenerationRingActive(false);
    }

    public Player(String name, int totalHealth, int currentHealth, int armorClass, int damageMod, int hitBonus, int attackDice, int attackSides, String charClass, String gender, int level, int battlesWon, int gold, int potionsLeft, int winsNeeded, int winsToNextLevel, int daysActive, Boolean regenerationRingActive) {
        this.setName(name);
        this.setTotalHealth(totalHealth);
        this.setCurrentHealth(currentHealth);
        this.setArmorClass(armorClass);
        this.setDamageMod(damageMod);
        this.setHitBonus(hitBonus);
        this.setAttackDice(attackDice);
        this.setAttackSides(attackSides);

        this.setCharClass(charClass);
        this.setGender(gender);
        this.setLevel(level);
        this.setBattlesWon(battlesWon);
        this.setGold(gold);
        this.setPotionsLeft(potionsLeft);
        this.setWinsNeeded(winsNeeded);
        this.setWinsToNextLevel(winsToNextLevel);
        this.setDaysActive(daysActive);
        this.setRegenerationRingActive(regenerationRingActive);
    }

    public String getCharClass() {
        return this.charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String g) {
        if (g.equals("")) {
            this.gender = "Other";
        }
        else {
            this.gender = g;
        }
    }

    public String getPronoun() {
        if (gender.toLowerCase().equals("male") || gender.toLowerCase().equals("guy") || gender.toLowerCase().equals("boy") || gender.toLowerCase().equals("man")) {
            return "He";
        }
        else if (gender.toLowerCase().equals("female") || gender.toLowerCase().equals("girl") || gender.toLowerCase().equals("woman")) {
            return "She";
        }
        else {
            return "They";
        }
    }

    public String getOtherPronoun() {
        if (gender.toLowerCase().equals("male")) {
            return "himself";
        }
        else if (gender.toLowerCase().equals("female")) {
            return "herself";
        }
        else {
            return "themselves";
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void raiseLevel(int newHealth) {
        raiseTotalHealth(newHealth);
        this.setCurrentHealth(this.getTotalHealth());
        this.level += 1;
        raiseWinsToNextLevel();
        setWinsNeeded();
    }

    public int getDaysActive() {
        return this.daysActive;
    }

    public void setDaysActive(int daysActive) {
        this.daysActive = daysActive;
    }

    public void onPassDay() {
        this.daysActive += 1;
    }

    public void onWait() {
        if(this.regenerationRingActive) {
            this.setCurrentHealth(this.getCurrentHealth() + 4);
        }
        else {
            this.setCurrentHealth(this.getCurrentHealth() + 1);
        }
        if (this.getCurrentHealth() > this.getTotalHealth()) {
            this.setCurrentHealth(this.getTotalHealth());
        }
        onPassDay();
    }

    public int getBattlesWon() {
        return this.battlesWon;
    }

    public void setBattlesWon(int wins) {
        this.battlesWon = wins;
    }

    public void battleWin() {
        this.battlesWon += 1;
    }

    public int getWinsNeeded() {
        return winsNeeded;
    }

    public void setWinsNeeded(int wins) {
        this.winsNeeded = wins;
    }

    public void setWinsNeeded() {
        setWinsNeeded(this.battlesWon + this.winsToNextLevel);
    }

    public void setWinsToNextLevel(int winsToNextLevel) {
        this.winsToNextLevel = winsToNextLevel;
    }

    public void raiseWinsToNextLevel() {
        this.winsToNextLevel += 3;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void removeGold(int gold) {
        if((this.gold - gold) < MIN_GOLD) {
            throw new IllegalArgumentException("Gold cannot go below 0");
        }
        this.gold -= gold;
    }

    public int getPotionsLeft() {
        return this.potionsLeft;
    }

    public void setPotionsLeft(int potionsLeft) {
        this.potionsLeft = potionsLeft;
    }

    public void addPotion(int cost) {
        removeGold(cost);
        this.potionsLeft += 1;
    }

    public void removePotion() {
        if (this.potionsLeft <= MIN_POTIONS) {
            throw new RuntimeException("Potion count cannot go below 0");
        }
        this.potionsLeft -= 1;
    }

    public boolean getRegenerationRingActive() {
        return this.regenerationRingActive;
    }

    public void setRegenerationRingActive(boolean regenerationRingActive) {
        this.regenerationRingActive = regenerationRingActive;
    }

    public void onBuyRegenerationRing(int cost) {
        if(regenerationRingActive) {
            throw new RuntimeException(String.format("%s can only own one Regeneration Ring", this.getName()));
        }
        removeGold(cost);
        this.regenerationRingActive = true;
    }

    public void raiseTotalHealth(int newHealth) {
        this.setTotalHealth(this.getTotalHealth() + newHealth);
    }

    public void healDamage(int heal) {
        this.removePotion();
        this.setCurrentHealth(this.getCurrentHealth() + heal);
        if(this.getCurrentHealth() > this.getTotalHealth()) {
            this.setCurrentHealth(this.getTotalHealth());
        }
    }

    public void raiseArmorClass(int cost, int armorIncrease) {
        if (this.getArmorClass() == 30) {
            throw new ArithmeticException("Armor level cannot exceed 30");
        }
        else {
            removeGold(cost);
            this.setArmorClass(this.getArmorClass() + armorIncrease);
            /* armorIncrease accounts for the fact that different classes wear different armors */
        }
        if (this.getArmorClass() > 30) {
            this.setArmorClass(30);
        }
    }
}
