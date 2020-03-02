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
        this.setTotalHealth(12);
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

    /** This Constructor is used for creating a brand new character */
    public Player(String name, int totalHealth, int armorClass, int damageMod, int hitBonus, int attackDice, int attackSides, String charClass, String gender) {
        this.setName(name);
        this.setTotalHealth(totalHealth);
        this.setCurrentHealth(this.getTotalHealth());
        this.setArmorClass(armorClass);
        this.setDamageMod(damageMod);
        this.setHitBonus(hitBonus);
        this.setAttackDice(attackDice);
        this.setAttackSides(attackSides);

        this.setCharClass(charClass);
        this.setGender(gender);
        this.setLevel(1);
        this.setBattlesWon(0);
        this.setGold(0);
        this.setPotionsLeft(0);
        this.setWinsNeeded(5);
        this.setWinsToNextLevel(5);
        this.setDaysActive(0);
        this.setRegenerationRingActive(false);
    }

    public String getCharClass() {
        return this.charClass;
    }

    /** Used only for initial creation */
    public void setCharClass(String charClass) {
        this.charClass = charClass;
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

    /** Used only for reloading character that has already been made */
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

    /** Used only for reloading character that has already been made */
    public void setBattlesWon(int wins) {
        this.battlesWon = wins;
    }

    public void battleWin() {
        this.battlesWon += 1;
    }

    public int getWinsNeeded() {
        return winsNeeded;
    }

    /** Used only for reloading character that has already been made */
    public void setWinsNeeded(int wins) {
        this.winsNeeded = wins;
    }

    public void setWinsNeeded() {
        setWinsNeeded(this.battlesWon + this.winsToNextLevel);
    }

    public int getWinsToNextLevel() {
        return this.winsToNextLevel;
    }

    /** Used only for reloading character that has already been made */
    public void setWinsToNextLevel(int winsToNextLevel) {
        this.winsToNextLevel = winsToNextLevel;
    }

    public void raiseWinsToNextLevel() {
        this.winsToNextLevel += 3;
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
            throw new RuntimeException("Potion count cannot go below 0");
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
        return String.format("%s|%d|%d|%d|%d|%d|%d|%d|%s|%s|%d|%d|%d|%d|%d|%d|%d|%b",
                this.getName(), this.getTotalHealth(), this.getCurrentHealth(), this.getArmorClass(), this.getDamageMod(), this.getHitBonus(),
                this.getAttackDice(), this.getAttackSides(), this.getCharClass(), this.getGender(), this.getLevel(), this.getBattlesWon(),
                this.getGold(), this.getPotionsLeft(), this.getWinsNeeded(), this.getWinsToNextLevel(), this.getDaysActive(),
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
        this.setCharClass(pieces[8].trim());
        this.setGender(pieces[9].trim());
        this.setLevel(Integer.parseInt(pieces[10].trim()));
        this.setBattlesWon(Integer.parseInt(pieces[11].trim()));
        this.setGold(Integer.parseInt(pieces[12].trim()));
        this.setPotionsLeft(Integer.parseInt(pieces[13].trim()));
        this.setWinsNeeded(Integer.parseInt(pieces[14].trim()));
        this.setWinsToNextLevel(Integer.parseInt(pieces[15].trim()));
        this.setDaysActive(Integer.parseInt(pieces[16].trim()));
        this.setRegenerationRingActive(Boolean.parseBoolean(pieces[17].trim()));
    }

    public String toSearchString() {
        return String.format("Name: %s, Class: %s, Gender: %s Level: %d", this.getName(), this.getCharClass(), this.getGender(), this.getLevel());
    }

    @Override
    public String toString() {
        return String.format(
                        "Name: %s" +
                        "\r\nClass: %s" +
                        "\r\nGender: %s" +
                        "\r\nLevel: %d" +
                        "\r\nCurrent Health: %d" +
                        "\r\nTotal Health: %d" +
                        "\r\nArmor Class: %d" +
                        "\r\nDamage Mod: %d" +
                        "\r\nHit Bonus: %d" +
                        "\r\nGold: %d" +
                        "\r\nPotions Left: %d" +
                        "\r\nHas Regeneration Ring: %b" +
                        "\r\nBattles Won: %d" +
                        "\r\nDays Active: %d",
                this.getName(), this.getCharClass(), this.getGender(), this.getLevel(), this.getCurrentHealth(), this.getTotalHealth(),
                this.getArmorClass(), this.getDamageMod(), this.getHitBonus(), this.getGold(), this.getPotionsLeft(),
                this.isRegenerationRingActive(), this.getBattlesWon(), this.getDaysActive());
    }
}
