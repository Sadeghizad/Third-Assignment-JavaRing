package org.project.object.weapons;

import org.project.entity.Entity;

public class Mace extends Weapon {

    public Mace() {
        super(12, 5, 2); // Damage: 12, Mana Cost: 5 (Some magic energy required)
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("🔨 You slam your **mace**, crushing your foe for " + getDamage() + " damage!");
    }

    public void useAbility(Entity target) {
        if (getAbilityCharge() >= 3) {
            System.out.println("⚡ **Divine Smite!** strike with holy energy, dealing damage and healing self slightly!");
            target.takeDamage(getDamage() + 5); // Extra 5 holy damage
            System.out.println("✨ You regain **5 HP** from the divine energy!");
        }
        super.useAbility(target);
    }

    @Override
    public String toString() {
        return "🔨 Mace - Damage: " + getDamage() + ", Ability Charge: " + getAbilityCharge() + ", MP Cost: " + getManaCost();
    }
}
