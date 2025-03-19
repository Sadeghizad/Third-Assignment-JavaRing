package org.project.object.weapons;

import org.project.entity.Entity;

public class BrokenSword extends Weapon {

    public BrokenSword() {
        super(6, 0,0); 
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("⚔️ You swing your sword, dealing " + getDamage() + " damage!");
    }

    public void useAbility(Entity target) {
        if (getAbilityCharge() >= 3) {
            System.out.println("Malfunction of weapon!");
        }
        super.useAbility(target);
    }

    @Override
    public String toString() {
        return "⚔️ Sword - Damage: " + getDamage() + ", Ability Charge: " + getAbilityCharge();
    }
}