package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public abstract class Enemy implements Entity {
    Weapon weapon;
    protected String enemyType;
    private int hp;
    private int mp;
    private int fp;

    public Enemy(int hp, int mp, int fp, Weapon weapon, String enemyType) {
        this.hp = hp;
        this.mp = mp;
        this.fp = fp;
        this.weapon = weapon;
        this.enemyType = enemyType;
    }
    public abstract Enemy clone();

    @Override
    public void heal(int health) {
        hp+=health;
    }
    @Override
    public void fillMana(int mana) {
        mp+=mana;
    }
    @Override
    public void regainFP(int stamina) {
        fp+=stamina;
    }
    @Override
    public void useMana(int mana) {
        mp-=mana;
    }
    @Override
    public void useStamina(int stamina) {
        fp-=stamina;
    }

    // TODO: (BONUS) UPDATE THE FORMULA OF TAKING DAMAGE
    @Override
    public void takeDamage(int damage) {
        hp -= damage;
    }

    public String getEnemyType() {
        return enemyType;
    }
    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }
    @Override
    public int getMaxHp() {
        return hp;
    }

    public int getMaxMp() {
        return mp;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
