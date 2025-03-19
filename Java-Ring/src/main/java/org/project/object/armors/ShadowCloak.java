package org.project.object.armors;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public class ShadowCloak extends Armor {

    public ShadowCloak() {
        super(10, 20); 
    }

    @Override
    public void use(Entity target) {
        if (target instanceof Player) {
            System.out.println("⚠️ Only Vampires can equip this armor!");
        }
    }

    @Override
    public String toString() {
        return "🕶️ Shadow Cloak - Avoids first attack in battle.";
    }
}
