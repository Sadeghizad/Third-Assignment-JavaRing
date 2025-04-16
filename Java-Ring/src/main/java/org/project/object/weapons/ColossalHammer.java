package org.project.object.weapons;

import org.project.entity.Entity;

public class ColossalHammer extends Weapon {

    public ColossalHammer() {
        super(40, 20, 0); 
    }

    @Override
    public void use(Entity target) {
        super.use(target);
        System.out.println("💀 The hammer lands with a **thunderous impact**, dealing " + getDamage() + " crushing damage!");
    }

    @Override
    public String toString() {
        return "🔨 Colossal Hammer - Damage: " + getDamage() + ", MP Cost: " + getManaCost();
    }
}
