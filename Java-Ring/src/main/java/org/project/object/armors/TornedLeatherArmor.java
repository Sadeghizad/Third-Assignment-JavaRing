package org.project.object.armors;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public class TornedLeatherArmor extends Armor {

    public TornedLeatherArmor() {
        super(10, 40); // Defense: 40, Durability: 200
    }

    @Override
    public void use(Entity target) {
        if (target instanceof Player) {
            ((Player) target).equipArmor(this);
            System.out.println("🛡️ You have equipped Torned Leather Armor.");
        } else {
            System.out.println("⚠️ Only players can equip armor!");
        }
    }

    @Override
    public String toString() {
        return "🛡️ Knight Armor - Defense: " + getDefense() + ", Durability: " + getDurability();
    }
}
