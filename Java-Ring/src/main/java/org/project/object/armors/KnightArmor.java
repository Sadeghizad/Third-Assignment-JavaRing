package org.project.object.armors;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public class KnightArmor extends Armor {

    public KnightArmor() {
        super(40, 200); // Defense: 40, Durability: 200
    }

    @Override
    public void use(Entity target) {
        if (target instanceof Player) {
            ((Player) target).equipArmor(this);
            System.out.println("🛡️ You have equipped Knight Armor.");
        } else {
            System.out.println("⚠️ Only players can equip armor!");
        }
    }

    @Override
    public String toString() {
        return "🛡️ Knight Armor - Defense: " + getDefense() + ", Durability: " + getDurability();
    }
}



// peices
// other:
// noArmor
// other armor