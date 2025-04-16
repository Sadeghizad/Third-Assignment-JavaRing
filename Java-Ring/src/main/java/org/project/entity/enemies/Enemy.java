package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

import java.util.List;

public abstract class Enemy extends Entity {
    String enemyType;
    public Enemy(int hp, int mp, int fp,int superAbilityCooldown, Weapon weapon, Armor armor, String enemyType) {
        super(hp, mp, fp,superAbilityCooldown, weapon,armor);
        this.enemyType = enemyType;
        name=enemyType;
    }
    public abstract Enemy clone();

    public abstract void abilityAttack(List<Entity> target);

    public String getEnemyType() {
        return enemyType;
    }

}
