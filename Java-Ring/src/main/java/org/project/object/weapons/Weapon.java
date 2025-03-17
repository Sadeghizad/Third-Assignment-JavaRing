package org.project.object.weapons;

import org.project.entity.Entity;
import org.project.object.Object;

// TODO: UPDATE IMPLEMENTATION
public abstract class Weapon implements Object {
    private int damage;
    private int manaCost;

    /*
    TODO: ADD OTHER REQUIRED AND BONUS ATTRIBUTES
    */

    public Weapon(int damage, int manaCost) {
        this.damage = damage;
        this.manaCost = manaCost;
    }

    @Override
    public void use(Entity target) {
        target.takeDamage(damage);
    }

    //    @Override
//    public void give(Entity target,Entity giver) {
//        target.getObject(this.clone());
//        giver.lost();
//    }
    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    /*
    TODO: ADD OTHER REQUIRED AND BONUS METHODS
    */
}
