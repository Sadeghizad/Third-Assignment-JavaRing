package org.project.object.weapons;

import org.project.entity.Entity;

public class Fist extends Weapon {

    public Fist() {
        super(6, 0,0); // Damage: 15, Mana Cost: 0 (basic sword has no mana cost)
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("⚔️ You punch with your fist, dealing " + getDamage() + " damage!");
    }

    public void useAbility(Entity target) {
        if (getAbilityCharge() >= 3) {
            System.out.println("no weapon!");
        }
        super.useAbility(target);
    }

    @Override
    public String toString() {
        return "👊️ Fist - Damage: " + getDamage() + ", Ability Charge: No Ability";
    }
}