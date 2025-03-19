package org.project.object.weapons;

import org.project.entity.Entity;

public class Sword extends Weapon {

    public Sword() {
        super(15, 0,0); 
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("⚔️ You swing your sword, dealing " + getDamage() + " damage!");
    }

    public void useAbility(Entity target) {
        if (getAbilityCharge() >= 3) {
            System.out.println("⚡ unleash a Sword Flurry, heavy striking enemy!");
            target.takeDamage(getDamage() * 2); 
        }
        super.useAbility(target);
    }

    @Override
        public String toString() {
            return "⚔️ Sword - Damage: " + getDamage() + ", Ability Charge: " + getAbilityCharge();
        }
}













