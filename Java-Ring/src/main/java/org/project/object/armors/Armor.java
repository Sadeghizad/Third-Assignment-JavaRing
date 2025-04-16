package org.project.object.armors;
import org.project.object.Object;

public abstract class Armor implements Object {
    private int defense;
    private int maxDefense;
    private int durability;
    private int maxDurability;
    private int weight;

    private boolean isBroke;

    public Armor(int defense, int durability) {
        this.defense = defense;
        this.durability = durability;
    }

    public void checkBreak() {
        if (durability <= 0) {
            isBroke = true;
            defense = 0;
        }
    }

    public void repair() {
        isBroke = false;
        defense = (int)(0.8*maxDefense);
        durability = (int)(0.8*maxDurability);
    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public void reduceDurability(int durabilityCost) {
        this.durability-=durabilityCost;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isBroke() {
        return isBroke;
    }
}
