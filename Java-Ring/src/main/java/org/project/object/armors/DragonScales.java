package org.project.object.armors;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public class DragonScales extends Armor {

    public DragonScales() {
        super(50, 100); 
    }

    @Override
    public void use(Entity target) {
        if (target instanceof Player) {
            System.out.println("⚠️ Only Dragons can equip this armor!");
        }

    }

    @Override
    public void checkBreak() {
        if (getDurability() <= 0) {
            System.out.println("⚠️ The Dragon's scales are weakening!");
        }
    }

    @Override
    public String toString() {
        return "🐉 Dragon Scales - High durability and armor penetration resistance";
    }
}
