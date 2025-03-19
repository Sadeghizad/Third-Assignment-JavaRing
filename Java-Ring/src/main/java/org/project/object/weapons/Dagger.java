package org.project.object.weapons;

import org.project.entity.Entity;

public class Dagger extends Weapon {

    public Dagger() {
        super(10, 0, 0); // Damage: 10, Mana Cost: 0 (fast weapon, lower base damage)
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("🗡️ You swiftly strike with your dagger, dealing " + getDamage() + " damage!");
    }

    public void useAbility(Entity target) {
        if (getAbilityCharge() >= 2) { // Faster ability charge than Sword
            System.out.println("💥 execute a **Critical Strike**, piercing deeply!");
            target.takeDamage((int)(getDamage() * 2.5)); // Double damage
        }
        super.useAbility(target);
    }

    @Override
    public String toString() {
        return "🗡️ Dagger - Damage: " + getDamage() + ", Ability Charge: " + getAbilityCharge();
    }
}
