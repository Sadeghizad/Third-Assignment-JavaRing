package org.project.entity;

import org.project.object.Object;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public abstract class Entity {
    Weapon weapon;
    Armor armor;
    protected String enemyType;
    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;
    private int fp;
    private int maxFP;
    private int superAbilityCooldown;
    public Entity(int hp, int mp, int fp,int superAbilityCooldown, Weapon weapon,Armor armor){
        this.hp = hp;
        this.maxHP = hp;
        this.mp = mp;
        this.maxMP = mp;
        this.fp = fp;
        this.maxFP = fp;
        this.weapon = weapon;
        this.armor = armor;
        this.superAbilityCooldown=superAbilityCooldown;
    }


    public void heal(int health) {
        hp+=health;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }
    public void fillMana(int mana) {
        mp+=mana;
        if (mp > maxMP) {
            mp = maxMP;
        }
    }
    public void regainFP(int stamina) {
        fp+=stamina;
        if (fp > maxFP) {
            fp = maxFP;
        }
    }

    public void useMana(int mana) {
        mp-=mana;
    }
    public void useStamina(int stamina) {
        fp-=stamina;
    }

    public void takeDamage(int damage) {
        if(armor != null && !armor.isBroke()){
        hp -= (int)(0.3*damage);
        armor.reduceDurability((int)(0.7*damage));
        }else{
            hp -= damage;
        }
    }

    public int getMaxHp() {
        return maxHP;
    }

    public int getMaxMp() {
        return maxMP;
    }

    public int getMaxFp() {
        return maxFP;
    }

    public int getSuperAbilityCooldown() {
        return superAbilityCooldown;
    }

    public void setSuperAbilityCooldown(int superAbilityCooldown) {
        this.superAbilityCooldown = superAbilityCooldown;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getFp() {
        return fp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void attack(Entity target) {
        target.takeDamage(weapon.getDamage());
    }

    public abstract void defend();

    public void reduceSuperCooldown() {
        this.superAbilityCooldown --;
    }
//    Object giveObject();
//    Object lost();
}
