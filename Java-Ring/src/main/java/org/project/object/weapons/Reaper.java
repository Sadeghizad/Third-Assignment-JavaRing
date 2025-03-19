package org.project.object.weapons;

import org.project.entity.Entity;

public class Reaper extends Weapon {

    public Reaper() {
        super(30, 0, 0); 
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("⚔️ The Reaper cuts deep, reducing " + target.getName() + "'s max HP!");
        target.reduceMaxHP(3);
    }

    @Override
    public String toString() {
        return "⚔️ Reaper - Damage: " + getDamage() + ", Reduces enemy max HP.";
    }
}
