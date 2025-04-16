package org.project.object.weapons;

import org.project.entity.Entity;
import org.project.object.Object;

import java.util.ArrayList;


public abstract class Weapon implements Object {
    private int damage;
    private int manaCost;
    private int abilityCharge;


    /*
    TODO: ADD OTHER REQUIRED AND BONUS ATTRIBUTES
    */

    public Weapon(int damage, int manaCost, int abilityCharge) {
        this.damage = damage;
        this.manaCost = manaCost;
        this.abilityCharge = 0; 

    }

    @Override
    public void use(Entity target) {
        target.takeDamage(damage);
        abilityCharge++; 
    }
    public void useAbility(Entity targets) {
        if (getAbilityCharge() >= 3) { 
            resetAbilityCharge(); 
        } else {
            System.out.println("⚠️ Not enough ability charge! Keep attacking to build it up.");
        }
    }

    public int getAbilityCharge() {
        return abilityCharge;
    }

    public void resetAbilityCharge() {
        this.abilityCharge = 0;
    }

    




    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    /*
    TODO: ADD OTHER REQUIRED AND BONUS METHODS
    */
}
