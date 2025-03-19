package org.project.object.weapons;

import org.project.entity.Entity;

public class VampiricClaws extends Weapon {

    public VampiricClaws() {
        super(20, 0, 0); 
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("🦇 Drakola slashes with his vampiric claws, dealing " + getDamage() + " damage!");
    }

    @Override
    public String toString() {
        return "🩸 Vampiric Claws - Damage: " + getDamage() + ", Lifesteal 50%";
    }
}
