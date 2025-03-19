package org.project.entity;

import org.project.object.Object;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public abstract class Entity {
    protected String name;
    protected Weapon weapon;
    protected Armor armor;
    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;
    private int fp;
    private int maxFP;
    private int superAbilityCooldown;
    private boolean isDefending = false;
    private int burnDamage = 0;
    private int burnDuration = 0;
    private int stunDuration = 0;

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
    public void reduceMaxHP(int amount) {
        this.maxHP -= amount;
    }
    public void takeDamage(int damage) {
        if(isDefending) {
            System.out.println("\n🛡️ "+this.name+" brace for impact, preparing to defend the next attack!");
            damage = (int)((Math.random()*0.3+0.3)*(damage));
            isDefending=false;
        }
        if(armor != null && !armor.isBroke()){
        hp -= (int)((armor.getDefense()*0.01)*damage);
        armor.reduceDurability((int)(0.7*damage));
        armor.checkBreak();
        }else{
            hp -= damage;
        }
    }

    public void stun(int duration) {
        if (stunDuration == 0) { // Prevent re-stunning while already stunned
            this.stunDuration = duration;
            System.out.println("⚡ " + this.getName() + " is stunned and cannot move for " + duration + " turns!");
        }
    }
    public boolean isStunned() {
        return stunDuration > 0;
    }

    public void reduceStunDuration() {
        if (stunDuration > 0) {
            stunDuration--;
            if (stunDuration == 0) {
                System.out.println("⚡ " + this.getName() + " recovers from the stun!");
            }
        }
    }
    public void applyBurn(int damagePerTurn, int duration) {
        if (burnDuration == 0) { // Prevent stacking
            this.burnDamage = damagePerTurn;
            this.burnDuration = duration;
            System.out.println("🔥 " + this.getName() + " is now burning!");
        } else {
            System.out.println("🔥 " + this.getName() + " is already burning!\nnew burning effect would not apply due kind developers hearts!");
        }
    }
    public void handleBurnEffect() {
        if (burnDuration > 0) {
            System.out.println("🔥 " + this.getName() + " takes " + burnDamage + " burn damage!");
            this.takeDamage(burnDamage);
            burnDuration--;

            if (burnDuration == 0) {
                System.out.println("🔥 The burn effect on " + this.getName() + " has ended.");
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getMaxFP() {
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

    public Armor getArmor() { return armor; }
    public void attack(Entity target) {
        weapon.use(target);
    }


    public void defend(){
        this.useStamina(10);
        isDefending=true;
        System.out.println("\n🛡️ "+this.name+" brace for impact, preparing to defend the next attack!");
    };

    public void reduceSuperCooldown() {
        this.superAbilityCooldown --;
    }
//    Object giveObject();
//    Object lost();
}
