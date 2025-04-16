package org.project.object.weapons;

import org.project.entity.Entity;

public class Staff extends Weapon {

    public Staff() {
        super(5, 10, 3); 
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("🔮 You cast a **magic bolt**, dealing " + getDamage() + " magic damage!");
    }

    public void useAbility(Entity target) {
        if (getAbilityCharge() >= 3) {
            System.out.println("✨ unleash **Arcane Blast**, striking enemy with magic energy!");
            target.takeDamage(getDamage() + 10); 
        }
        super.useAbility(target);
    }

    @Override
    public String toString() {
        return "🔮 Staff - Damage: " + getDamage() + ", Ability Charge: " + getAbilityCharge() + ", MP Cost: " + getManaCost();
    }
}
