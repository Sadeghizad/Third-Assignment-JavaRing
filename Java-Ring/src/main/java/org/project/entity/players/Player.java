package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public abstract class Player extends Entity{
    protected String name;

    public Player(String name, int hp, int fp, int mp,int superAbilityCooldown, Weapon weapon, Armor armor) {
        super(hp, mp, fp,superAbilityCooldown, weapon,armor);
        this.name = name;
    }

    @Override
    public void defend() {
        // TODO: (BONUS) IMPLEMENT A DEFENSE METHOD FOR SHIELDS
    }

    public String getName() {
        return name;
    }

}
