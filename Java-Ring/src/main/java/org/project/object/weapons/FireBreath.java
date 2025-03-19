package org.project.object.weapons;

import org.project.entity.Entity;

public class FireBreath extends Weapon {

    public FireBreath() {
        super(30, 15, 0); // Damage: 30, FP Cost: 15
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("🔥 The Dragon breathes fire, dealing " + getDamage() + " damage!");
    }

    @Override
    public String toString() {
        return "🔥 Fire Breath - Damage: " + getDamage() + ", FP Cost: " + getFpCost();
    }
}
