package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public abstract class Player extends Entity{
    private int flasks;
    private boolean isAlive;
    public Player(String name, int hp, int fp, int mp,int superAbilityCooldown, int flasks, Weapon weapon, Armor armor) {
        super(hp, mp, fp,superAbilityCooldown, weapon,armor);
        this.name = name;
        isAlive = true;
        this.flasks = flasks;
    }


    public void equipArmor(Armor newArmor) {
        this.armor = newArmor;
        System.out.println("🛡️ You have equipped " + newArmor);
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }
    public void die() {
        isAlive = false;
    }
    public boolean hasFlask(){
        return flasks > 0;
    }

    public int getFlasks() {
        return flasks;
    }

    public void useFlask(){
        flasks--;
    }
}
