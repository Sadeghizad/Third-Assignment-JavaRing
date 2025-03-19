package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.ColossalHammer;
import java.util.List;

public class Golem extends Enemy {

    public Golem() {
        super(300, 20, 10, 4, new ColossalHammer(), null, "Golem");
    }

    @Override
    public Enemy clone() {
        return new Golem();
    }

    @Override
    public void attack(Entity target) {
        System.out.println("\n🪨 The Golem swings its colossal hammer in slow motion...");
        target.takeDamage(this.getWeapon().getDamage());
    }

    @Override
    public void abilityAttack(List<Entity> targets) {
        if (targets.isEmpty()) return;

        if (this.getSuperAbilityCooldown() == 0) {
            System.out.println("\n💥 **Seismic Slam!** The Golem smashes the ground, shaking the battlefield!");
            for (Entity target : targets) {
                System.out.println("🌪️ " + target.getName() + " is **stunned** and takes massive damage!");
                target.takeDamage(this.getWeapon().getDamage() + 10);
                target.stun(1); // Stuns for 1 turn
            }
            this.setSuperAbilityCooldown(4);
        }
    }

    @Override
    public void takeDamage(int amount) {
        int reducedDamage = Math.max(1, amount - 10); // Passive rock body absorbs some damage
        super.takeDamage(reducedDamage);
        System.out.println("🛡️ The Golem's rock body absorbs some of the damage!");
    }
}
