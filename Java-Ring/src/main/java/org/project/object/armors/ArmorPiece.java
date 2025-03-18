package org.project.object.armors;

public class ArmorPiece {
    private String name;
    private int defense;
    private int durability;
    private int weight;

    private boolean isBroken;

    public ArmorPiece(String name, int defense, int durability, int weight) {
        this.name = name;
        this.defense = defense;
        this.durability = durability;
        this.weight = weight;
        this.isBroken = false;
    }

    public void reduceDurability(int amount) {
        durability -= amount;
        if (durability <= 0) {
            isBroken = true;
            durability = 0;
            defense = 0;
        }
    }

    public void repair() {
        isBroken = false;
        durability = 100; // Example: Reset durability to full
        defense = (int) (defense * 0.8); // Repair at 80% effectiveness
    }

    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isBroken() {
        return isBroken;
    }
}
