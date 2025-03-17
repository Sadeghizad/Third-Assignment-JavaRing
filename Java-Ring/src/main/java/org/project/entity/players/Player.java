package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public abstract class Player implements Entity{
    protected String name;
    Weapon weapon;
    Armor armor;
    private int hp;
    private int maxHP;
    private int fp;
    private int maxFP;
    private int mp;
    private int maxMP;
    private int superAbilityCooldown;

    public Player(String name, int hp, int mp, Weapon weapon, Armor armor) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.fp = fp;

        this.weapon = weapon;
        this.armor = armor;
    }

    @Override
    public void attack(Entity target) {
        target.takeDamage(weapon.getDamage());
    }

    @Override
    public void defend() {
        // TODO: (BONUS) IMPLEMENT A DEFENSE METHOD FOR SHIELDS
    }

    // TODO: (BONUS) UPDATE THE FORMULA OF TAKING DAMAGE
    @Override
    public void takeDamage(int damage) {
        hp -= damage - armor.getDefense();
    }

    @Override
    public void heal(int health) {
        hp += health;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    @Override
    public void regainFP(int stamina) {
        fp += stamina;
        if (fp > maxFP) {
            fp = maxFP;
        }
    }

    @Override
    public void fillMana(int mana) {
        mp += mana;
        if (mp > maxMP) {
            mp = maxMP;
        }
    }
    @Override
    public void useMana(int mana) {
        mp-=mana;
    }
    @Override
    public void useStamina(int stamina) {
        fp-=stamina;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public int getMp() {
        return mp;
    }

    @Override
    public int getMaxMP() {
        return maxMP;
    }

    public int getFp() {
        return fp;
    }

    @Override
    public int getMaxFP() {
        return maxFP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

}
