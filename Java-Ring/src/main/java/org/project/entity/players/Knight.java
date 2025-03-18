package org.project.entity.players;

import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public class Knight extends Player{
    public Knight(String name, int hp, int fp, int mp,int superAbilityCooldown,int flasks, Weapon weapon, Armor armor){
        super(name, hp, fp, mp,superAbilityCooldown,flasks, weapon,armor);
    }
    // TODO: DESIGN KNIGHT'S WEAPON AND ARMOR AND IMPLEMENT THE CONSTRUCTOR
}
// extras:
// wizard
// assassin
// cleric