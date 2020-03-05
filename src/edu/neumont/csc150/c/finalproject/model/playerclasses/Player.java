package edu.neumont.csc150.c.finalproject.model.playerclasses;

import edu.neumont.csc150.c.finalproject.model.Character;

public abstract class Player extends Character {
    private String gender;

    private int level;
    private int hoursActive;
    private int daysActive;
    private int currentExp;
    private int expNeeded;
    private int expToNextLevel;
    private int gold;
    private int potionsLeft;

    private Boolean regenerationRingActive;

    private final int MIN_GOLD = 0;
    private final int MIN_POTIONS = 0;

    public Player() {

    }

    /** This Constructor is used for creating a brand new character */
    public Player(String name, int totalHealth, int armorClass, int damageMod, int hitBonus, int attackDice, int attackSides, String gender) {
        this.setName(name);
        this.setTotalHealth(totalHealth);
        this.setCurrentHealth(this.getTotalHealth());
        this.setArmorClass(armorClass);
        this.setDamageMod(damageMod);
        this.setHitBonus(hitBonus);
        this.setAttackDice(attackDice);
        this.setAttackSides(attackSides);

        this.setGender(gender);
        this.setLevel(1);
        this.setCurrentExp(0);
        this.setGold(0);
        this.setPotionsLeft(0);
        this.setExpNeeded(1000);
        this.setExpToNextLevel(1000);
        this.setHoursActive(0);
        this.setDaysActive(0);
        this.setRegenerationRingActive(false);
    }

    public String getGender() {
        return this.gender;
    }

    /**
     * No validation of g to ensure that all gender identities are included
     * */
    public void setGender(String g) {
        if (g.equals("")) {
            this.gender = "Other";
        }
        else {
            this.gender = g;
        }
    }

    /**
     * getPronoun() and getOtherPronoun() ensure that correct grammar is used whenever a pronoun is used
     * */
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

    /** Used only for reloading character that has already been made */
    public void setLevel(int level) {
        this.level = level;
    }

    public abstract void raiseLevel();

    public void raiseLevel(int newHealth) {
        raiseTotalHealth(newHealth);
        this.setCurrentHealth(this.getTotalHealth());
        this.level += 1;
        raiseExpToNextLevel();
        setExpNeeded();
    }

    public int getHoursActive() {
        return this.hoursActive;
    }

    /** Used only for reloading character that has already been made */
    public void setHoursActive(int hours) {
        this.hoursActive = hours;
    }

    public void onPassHour() {
        if (this.hoursActive == 24) {
            onPassDay();
            return;
        }
        this.hoursActive += 1;
    }

    public int getDaysActive() {
        return this.daysActive;
    }

    /** Used only for reloading character that has already been made */
    public void setDaysActive(int daysActive) {
        this.daysActive = daysActive;
    }

    public void onPassDay() {
        this.setHoursActive(0);
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

    public int getCurrentExp() {
        return this.currentExp;
    }

    /** Used only for reloading character that has already been made */
    public void setCurrentExp(int exp) {
        this.currentExp = exp;
    }

    public void battleWin(int expValue) {
        this.currentExp += expValue;
    }

    public int getExpNeeded() {
        return this.expNeeded;
    }

    /** Used only for reloading character that has already been made */
    public void setExpNeeded(int expTotal) {
        this.expNeeded = expTotal;
    }

    public void setExpNeeded() {
        setExpNeeded(this.expNeeded + this.expToNextLevel);
    }

    public int getExpToNextLevel() {
        return this.expToNextLevel;
    }

    /** Used only for reloading character that has already been made */
    public void setExpToNextLevel(int exp) {
        this.expToNextLevel = exp;
    }

    public void raiseExpToNextLevel() {
        this.expToNextLevel += 1000;
    }

    public int getGold() {
        return this.gold;
    }

    /** Used only for reloading character that has already been made */
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

    /** Used only for reloading character that has already been made */
    public void setPotionsLeft(int potionsLeft) {
        this.potionsLeft = potionsLeft;
    }

    public void addPotion(int cost) {
        removeGold(cost);
        this.potionsLeft += 1;
    }

    public void removePotion() {
        if (this.potionsLeft <= MIN_POTIONS) {
            throw new IllegalStateException("Potion count cannot go below 0");
        }
        this.potionsLeft -= 1;
    }

    public boolean isRegenerationRingActive() {
        return this.regenerationRingActive;
    }

    /** Used only for reloading character that has already been made */
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

    public void raiseHitBonus() {
        this.setHitBonus(this.getHitBonus() + 1);
    }

    public void raiseTotalHealth(int newHealth) {
        this.setTotalHealth(this.getTotalHealth() + newHealth);
    }

    public void potionHeal(int heal) {
        this.removePotion();
        this.setCurrentHealth(this.getCurrentHealth() + heal);
        if(this.getCurrentHealth() > this.getTotalHealth()) {
            this.setCurrentHealth(this.getTotalHealth());
        }
    }

    public abstract void raiseArmorClass();

    /** armorIncrease accounts for the fact that different classes wear different armors */
    public void raiseArmorClass(int cost, int armorIncrease) {
        if (this.getArmorClass() == 30) {
            throw new ArithmeticException("Armor level cannot exceed 30");
        }
        else {
            removeGold(cost);
            this.setArmorClass(this.getArmorClass() + armorIncrease);
        }
        if (this.getArmorClass() > 30) {
            this.setArmorClass(30);
        }
    }

    public String serialize() {
        return String.format("%s|%d|%d|%d|%d|%d|%d|%d|%s|%s|%d|%d|%d|%d|%d|%d|%d|%d|%b",
                this.getName(), this.getTotalHealth(), this.getCurrentHealth(), this.getArmorClass(), this.getDamageMod(), this.getHitBonus(),
                this.getAttackDice(), this.getAttackSides(), this.getClass().getSimpleName(), this.getGender(), this.getLevel(), this.getCurrentExp(),
                this.getGold(), this.getPotionsLeft(), this.getExpNeeded(), this.getExpToNextLevel(), this.getHoursActive(), this.getDaysActive(),
                this.isRegenerationRingActive());
    }

    public void deserialize(String line) {
        String[] pieces = line.split("\\|");
        this.setName(pieces[0].trim());
        this.setTotalHealth(Integer.parseInt(pieces[1].trim()));
        this.setCurrentHealth(Integer.parseInt(pieces[2].trim()));
        this.setArmorClass(Integer.parseInt(pieces[3].trim()));
        this.setDamageMod(Integer.parseInt(pieces[4].trim()));
        this.setHitBonus(Integer.parseInt(pieces[5].trim()));
        this.setAttackDice(Integer.parseInt(pieces[6].trim()));
        this.setAttackSides(Integer.parseInt(pieces[7].trim()));
        this.setGender(pieces[9].trim());
        this.setLevel(Integer.parseInt(pieces[10].trim()));
        this.setCurrentExp(Integer.parseInt(pieces[11].trim()));
        this.setGold(Integer.parseInt(pieces[12].trim()));
        this.setPotionsLeft(Integer.parseInt(pieces[13].trim()));
        this.setExpNeeded(Integer.parseInt(pieces[14].trim()));
        this.setExpToNextLevel(Integer.parseInt(pieces[15].trim()));
        this.setHoursActive(Integer.parseInt(pieces[16].trim()));
        this.setDaysActive(Integer.parseInt(pieces[17].trim()));
        this.setRegenerationRingActive(Boolean.parseBoolean(pieces[18].trim()));
    }

    public String toSearchString() {
        return String.format("Name: %s, Class: %s, Gender: %s Level: %d", this.getName(), this.getClass().getSimpleName(), this.getGender(), this.getLevel());
    }

    public String toBattleString() {
        return String.format(
                "Name: %s" +
                "\r\nClass: %s" +
                "\r\nLevel: %d" +
                "\r\nHealth: %d/%d" +
                "\r\nArmor Class: %d" +
                "\r\nPotions Left: %d" +
                "\r\nAttack Damage: %dd%d + %d" +
                "\r\nExperience: %d/%d",
                this.getName(), this.getClass().getSimpleName(), this.getLevel(), this.getCurrentHealth(), this.getTotalHealth(), this.getArmorClass(),
                this.getPotionsLeft(), this.getAttackDice(), this.getAttackSides(), this.getDamageMod(), this.getCurrentExp(), this.getExpNeeded());
    }

    @Override
    public String toString() {
        return String.format(
                        "Name: %s" +
                        "\r\nClass: %s" +
                        "\r\nGender: %s" +
                        "\r\nLevel: %d" +
                        "\r\nHealth: %d/%d" +
                        "\r\nArmor Class: %d" +
                        "\r\nAttack Damage: %dd%d + %d" +
                        "\r\nHit Bonus: %d" +
                        "\r\nGold: %d" +
                        "\r\nPotions Left: %d" +
                        "\r\nHas Regeneration Ring: %b" +
                        "\r\nExperience: %d/%d" +
                        "\r\nHour of Day: %d" +
                        "\r\nDays Active: %d",
                this.getName(), this.getClass().getSimpleName(), this.getGender(), this.getLevel(), this.getCurrentHealth(), this.getTotalHealth(),
                this.getArmorClass(), this.getAttackDice(), this.getAttackSides(), this.getDamageMod(), this.getHitBonus(), this.getGold(), this.getPotionsLeft(),
                this.isRegenerationRingActive(), this.getCurrentExp(), this.getExpNeeded(), this.getHoursActive(), this.getDaysActive());
    }
}
