package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.armors.ShadowCloak;
import org.project.object.weapons.VampiricClaws;

import java.util.List;
import java.util.Random;

public class Drakola extends Enemy {
    private static final Random random = new Random();
    private boolean firstAttackAvoided = false; 

    public Drakola() {
        super(120, 30, 100, 3, new VampiricClaws(), new ShadowCloak(), "Drakola");
    }

    @Override
    public void attack(Entity target) {
        System.out.println("\n🦇 Drakola lunges forward with his vampiric claws!");
        int damage = this.getWeapon().getDamage();
        target.takeDamage(damage);
        this.heal(damage / 2); 
        System.out.println("🩸 Drakola **absorbs " + (damage / 2) + " HP** from " + target.getName() + "!");
    }

    @Override
    public void takeDamage(int amount) {
        if (!firstAttackAvoided) {
            System.out.println("🕶️ **Drakola dodges the first attack with his Shadow Cloak!**");
            firstAttackAvoided = true;
        } else {
            super.takeDamage(amount);
        }
    }

    @Override
    public void abilityAttack(List<Entity> targets) {
        if (targets.isEmpty()) return;
        if (this.getSuperAbilityCooldown() == 0) {
            if (random.nextInt(100) < 30) { 

                System.out.println("\n🩸 **Blood Drain!** Drakola siphons life from all enemies!");
            for (Entity target : targets) {
                int damage = this.getWeapon().getDamage() + 10;
                target.takeDamage(damage);
                this.heal(damage); 
                System.out.println("🩸 Drakola drains **" + damage + " HP** from " + target.getName() + "!");
            }
            this.setSuperAbilityCooldown(3);
        }
        } else {
            int targetIndex = (int)(Math.random()*Player.getPlayerCount());
            if(targetIndex == Player.getPlayerCount()){
                targetIndex--;
            }
            Entity target = targets.get(targetIndex);

            System.out.println("\n🧠 **Drakola attempts to take control of " + target.getName() + "'s mind!**");
            if (random.nextInt(100) < 30) { 
                System.out.println("🧠 **Mind Control Successful!** " + target.getName() + " skips their turn.");
                target.stun(1);
            } else {
                System.out.println("⚡ " + target.getName() + " resists Drakola's influence!");
            }
        }

    }

    @Override
    public Enemy clone() {
        return new Drakola();
    }
}
