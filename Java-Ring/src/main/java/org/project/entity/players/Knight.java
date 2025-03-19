package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public class Knight extends Player{
    public Knight(String name, int hp, int fp, int mp,int superAbilityCooldown,int flasks, Weapon weapon, Armor armor){
        super(name, hp, fp, mp,superAbilityCooldown,flasks, weapon,armor);
    }

    @Override
    public void SuperAbility(Entity target) {
        System.out.println("\n⚡ You unleash your ultimate power! \n     --- STRONG KICK! --- \n");
        target.takeDamage(this.getWeapon().getDamage() * 3);
        this.useStamina(25);
        this.useMana(20);
        this.setSuperAbilityCooldown(3);
    }
}
