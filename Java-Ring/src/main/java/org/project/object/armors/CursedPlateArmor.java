package org.project.object.armors;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public class CursedPlateArmor extends Armor {

    public CursedPlateArmor() {
        super(30, 40); // High defense, but heavy
    }

    @Override
    public void use(Entity target) {
        if (target instanceof Player) {
            System.out.println("⚠️ Only Black Knight can equip this armor!");
        }
    }

    @Override
    public String toString() {
        return "🛡️ Cursed Plate Armor - High defense, but reduces speed.";
    }
}
