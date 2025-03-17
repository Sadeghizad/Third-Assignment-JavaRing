package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;

// TODO: UPDATE IMPLEMENTATION
public class Skeleton extends Enemy{
    private boolean resurrected = false;
    public Skeleton() {
        super(50, 0,30 , new Sword(),"Skeleton"); // Set type name
    }

    @Override
    public void attack(Entity target) {
        weapon.use(target);
    }

    @Override
    public void defend()
    {
        //
    }

    @Override
    public Enemy clone() {
        return new Skeleton();
    }
    // TODO: DESIGN ENEMY'S WEAPON AND ARMOR AND IMPLEMENT THE CONSTRUCTOR
}
// extras:
// zombie
// golem
// goblin
// drakola
// dragon
// blackknight
