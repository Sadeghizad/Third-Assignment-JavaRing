package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public class Cleric extends Player{
    public Cleric(String name, int hp, int fp, int mp, int superAbilityCooldown, int flasks, Weapon weapon, Armor armor){
        super(name, hp, fp, mp,superAbilityCooldown,flasks, weapon,armor);
    }

    @Override
    public void SuperAbility(Entity target) {
        System.out.println("\n⚡ You unleash your ultimate power!");
        this.heal((int)(this.getHp()*1.5));
        this.useStamina(10);
        this.useMana(25);
        this.setSuperAbilityCooldown(4);
    }
}
