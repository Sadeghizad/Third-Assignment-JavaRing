package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Entity{
    private int flasks;
    private boolean isAlive;
    private static int playerCount=0;
    private static List<Player> Players;
    public Player(String name, int hp, int fp, int mp,int superAbilityCooldown, int flasks, Weapon weapon, Armor armor) {
        super(hp, mp, fp,superAbilityCooldown, weapon,armor);
        this.name = name;
        isAlive = true;
        this.flasks = flasks;
        playerCount++;
    }

    public static int getPlayerCount() {
        return playerCount; // Returns total number of players
    }
    public static List<Entity> getPlayers() {
        return new ArrayList<>(Players); // Properly casting List<Player> to List<Entity>
    }


    public abstract void SuperAbility(Entity target);

    public void equipArmor(Armor newArmor) {
        this.armor = newArmor;
        System.out.println("🛡️ You have equipped " + newArmor);
    }

    public void equipWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
        System.out.println("🤺 You have equipped " + newWeapon);
    }


    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }
    public void die() {
        isAlive = false;
    }
    public boolean hasFlask(){
        return flasks > 0;
    }

    public int getFlasks() {
        return flasks;
    }

    public void useFlask(){
        flasks--;
    }
    public void abilityAttack(Entity target) {
        weapon.useAbility(target);
    }
}
