package org.project.object.consumables;

import org.project.entity.Entity;

public class HPFlask extends Consumable {
    private final int healAmount;

    public HPFlask() {
        this.healAmount = 20; // Example: Each flask heals 20 HP
    }

    @Override
    public void use(Entity target) {
        if (target.getHp() > 0) {
            if (target.getHp() <= target.getMaxHP()) {
                System.out.println("❌ You cannot use an HP Flask while you are at full health.");
            }else {
                target.heal(healAmount);
                System.out.println("🩹 You drank an HP Flask! Restored " + healAmount + " HP.");
            }
        } else {
            System.out.println("❌ You cannot use an HP Flask while dead.");
        }
    }

    @Override
    public String toString() {
        return "HP Flask - Restores " + healAmount + " HP.";
    }
}

// extras:
// MPflask
// Blastingjar
// throwingknife
// wonderflask