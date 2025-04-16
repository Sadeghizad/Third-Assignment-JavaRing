package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.armors.DragonScales;
import org.project.object.weapons.FireBreath;

import java.util.List;

public class Dragon extends Enemy {

    public Dragon() {
        super(250, 40, 100, 2, new FireBreath(), new DragonScales(), "Dragon");
    }

    @Override
    public Enemy clone() {
        return new Dragon();
    }

    @Override
    public void attack(Entity target) {
        System.out.println("\n🔥 The Dragon unleashes its fire!");
        target.takeDamage(this.getWeapon().getDamage());
        if (Math.random() < 0.3) { 
            System.out.println("🔥 " + target.getName() + " is burned and will take damage over time!");
            target.applyBurn(5, 3); 
        }
    }
    @Override
    public void abilityAttack(List<Entity> targets) {
        if (targets.isEmpty()) return;
        if (this.getSuperAbilityCooldown() == 0) {
            System.out.println("\n🌋 **Hellfire Storm!** The Dragon engulfs the battlefield in flames!");
            for (Entity target : targets) {
                System.out.println("🔥 " + target.getName() + " is caught in the storm!");
                target.takeDamage(this.getWeapon().getDamage() + 20); 
            }
            this.setSuperAbilityCooldown(2); 
        }
    }
}
