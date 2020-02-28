package edu.neumont.csc150.c.finalproject.model;

public class Player {
    private String name;
    private String charClass;
    private String gender;

    private int totalHealth;
    private int currentHealth;
    private int level;
    private int battlesWon;
    private int gold;
    private int potionsLeft;
    private int armorClass;
    private int winsNeeded;
    private int winsToNextLevel;
    private int daysActive;

    private Boolean ringActive;

    private final int MIN_GOLD = 0;
    private final int MIN_POTIONS = 0;
    private final int MIN_NAME_SIZE = 2;
    private final int MAX_NAME_SIZE = 20;
    private final int MAX_CLASS_SIZE = 20;
    private final int MIN_CURRENT_HEALTH = 0;

    public Player() {
        name = "";
        charClass = "Fighter";
        totalHealth = 100;
        currentHealth = 100;
        level = 1;
        battlesWon = 0;
        gold = 0;
        potionsLeft = 0;
        armorClass = 0;
        winsNeeded = 5;
        winsToNextLevel = 5;
        gender = "Other";
        daysActive = 0;
        ringActive = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException("Name must be 2-20 characters");
        }
        this.name = name;
    }

    public void resetName() {
        this.name = "";
    }

    public String getCharClass() {
        return this.charClass;
    }

    public void setCharClass(String charClass) {
        if(charClass.length() > MAX_CLASS_SIZE) {
            throw new IllegalArgumentException("Character class cannot exceed 20 characters");
        }
        this.charClass = charClass;
        if(charClass.length() == 0 || charClass.isEmpty()) {
            this.charClass = "Fighter";
        }
    }

    public void resetCharClass() {
        this.charClass = "Fighter";
    }

    public String getGender() {
        return this.gender;
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
        if (gender.toLowerCase().equals("male") || gender.toLowerCase().equals("guy") || gender.toLowerCase().equals("boy") || gender.toLowerCase().equals("man")) {
            return "himself";
        }
        else if (gender.toLowerCase().equals("female") || gender.toLowerCase().equals("girl") || gender.toLowerCase().equals("woman")) {
            return "herself";
        }
        else {
            return "themselves";
        }
    }

    public void setGender(String g) {
        if (g.equals("")) {
            this.gender = "Other";
        }
        else {
            this.gender = g;
        }
    }

    public void resetGender() {
        this.gender = "Other";
    }

    public int getTotalHealth() {
        return this.totalHealth;
    }

    public void raiseTotalHealth() {
        this.totalHealth += 10;
    }

    public void resetTotalHealth() {
        this.totalHealth = 100;
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public void takeDamage(int damage) {
        if(damage < 1) {
            this.currentHealth -= 1;
        }
        else {
            this.currentHealth -= damage;
        }
        if(this.currentHealth < MIN_CURRENT_HEALTH) {
            this.currentHealth = 0;
        }
    }

    public void healDamage(int heal) {
        this.removePotion();
        this.currentHealth += heal;
        if(this.currentHealth > totalHealth) {
            this.currentHealth = totalHealth;
        }
    }

    public void resetCurrentHealth() {
        this.currentHealth = 100;
    }

    public int getLevel() {
        return this.level;
    }

    public void raiseLevel() {
        raiseTotalHealth();
        this.currentHealth = this.totalHealth;
        this.level += 1;
        raiseWinsToNextLevel();
        setWinsNeeded();
    }

    public void resetLevel() {
        this.level = 0;
    }

    public int getBattlesWon() {
        return this.battlesWon;
    }

    public void battleWin() {
        this.battlesWon += 1;
    }

    public void resetBattlesWon() {
        this.battlesWon = 0;
    }

    public int getWinsNeeded() {
        return winsNeeded;
    }

    public void setWinsNeeded() {
        this.winsNeeded = (battlesWon + winsToNextLevel);
    }

    public void resetWinsNeeded() {
        this.winsNeeded = 5;
    }

    public void raiseWinsToNextLevel() {
        this.winsToNextLevel += 3;
    }

    public void resetWinsToNextLevel() {
        this.winsToNextLevel = 5;
    }

    public int getGold() {
        return this.gold;
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

    public void resetGold() {
        this.gold = 0;
    }

    public int getPotionsLeft() {
        return this.potionsLeft;
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

    public void resetPotion() {
        this.potionsLeft = 0;
    }

    public int getArmorClass() {
        return this.armorClass;
    }

    public void raiseArmorClass(int cost) {
        if(armorClass == 0) {
            removeGold(cost);
            this.armorClass += 10;
        }
        else if (armorClass == 30) {
            throw new ArithmeticException("Armor level cannot exceed 30");
        }
        else {
            removeGold(cost);
            this.armorClass += 5;
        }
    }

    public void resetArmorClass() {
        this.armorClass = 0;
    }

    public int getDaysActive() {
        return this.daysActive;
    }

    public void onPassDay() {
        this.daysActive += 1;
    }

    public void onWait() {
        if(ringActive) {
            if(currentHealth < 40) {
                this.currentHealth += 4;
            }
        }
        else {
            if(currentHealth < 25) {
                this.currentHealth += 1;
            }
        }
        onPassDay();
    }

    public void resetDaysActive() {
        this.daysActive = 0;
    }

    public void onBuyRing(int cost) {
        if(ringActive) {
            throw new RuntimeException("Ring can only be active once");
        }
        removeGold(cost);
        this.ringActive = true;
    }

    public void resetRingActive() {
        this.ringActive = false;
    }

    public void resetAll() {
        resetArmorClass();
        resetBattlesWon();
        resetCharClass();
        resetCurrentHealth();
        resetGold();
        resetLevel();
        resetName();
        resetPotion();
        resetTotalHealth();
        resetWinsNeeded();
        resetWinsToNextLevel();
        resetGender();
        resetDaysActive();
        resetRingActive();
    }

    public Boolean checkForLose() {
        if(this.getCurrentHealth() <= 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
