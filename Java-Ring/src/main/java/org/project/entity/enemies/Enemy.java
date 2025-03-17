package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public abstract class Enemy extends Entity {

    public Enemy(int hp, int mp, int fp,int superAbilityCooldown, Weapon weapon, Armor armor, String enemyType) {
        super(hp, mp, fp,superAbilityCooldown, weapon,armor);
        this.enemyType = enemyType;
    }
    public abstract Enemy clone();



    public String getEnemyType() {
        return enemyType;
    }

}
