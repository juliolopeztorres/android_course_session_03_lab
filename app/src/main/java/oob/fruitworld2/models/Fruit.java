package oob.fruitworld2.models;

import oob.fruitworld2.Utils.Utils;

public class Fruit {
    private String name;
    private String description;
    private int background;
    private int icon;
    private int amount;

    public Fruit(String name, String description, int background, int icon, int amount) {
        this.name = name;
        this.description = description;
        this.background = background;
        this.icon = icon;
        this.amount = amount;
    }

    public void resetAmount() {
        this.setAmount(Utils.MINIMUM_AMOUNT);
    }

    public void addAmount() {
        this.setAmount(this.getAmount() + 1);
    }

    // ---------------- Getters & Setters ----------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getAmount() {
        return amount;
    }

    private boolean setAmount(int amount) {
        if (amount >= Utils.MINIMUM_AMOUNT && amount <= Utils.MAXIMUM_AMOUNT) {
            this.amount = amount;
            return true;
        }
        return false;
    }
}
