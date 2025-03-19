package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public class Wizard extends Player{
    public Wizard(String name, int hp, int fp, int mp, int superAbilityCooldown, int flasks, Weapon weapon, Armor armor){
        super(name, hp, fp, mp,superAbilityCooldown,flasks, weapon,armor);
    }

    @Override
    public void SuperAbility(Entity target) {
        System.out.println("\n⚡ You cast a special spell!");
        this.heal((int)(this.getHp()*0.5));
        target.takeDamage((int)(this.getWeapon().getDamage() * 1.5));
        this.useStamina(10);
        this.useMana(30);
        this.setSuperAbilityCooldown(3);
    }
}