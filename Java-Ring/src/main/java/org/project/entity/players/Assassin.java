package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public class Assassin extends Player{
    private boolean invisible = false;
    private int invisibleTurns = 0;

    public Assassin(String name, int hp, int fp, int mp, int superAbilityCooldown, int flasks, Weapon weapon, Armor armor){
        super(name, hp, fp, mp,superAbilityCooldown,flasks, weapon,armor);
    }
    @Override
    public void SuperAbility(Entity target) {
            System.out.println("\n🕶️ You vanish into the shadows! (Invisible for 2 turns)");
            this.invisible = true;
            this.invisibleTurns = 2;
            this.useStamina(35);
            this.useMana(5);
            this.setSuperAbilityCooldown(4);
    }
    @Override
    public void attack(Entity target) {
        if (invisible) {
            System.out.println("\n💀 Silent strike! You deal **increased damage** while invisible!");
            target.takeDamage(this.getWeapon().getDamage() * 2); // Double damage while invisible
            this.invisibleTurns--; // Reduce invisibility duration

            if (this.invisibleTurns <= 0) {
                this.invisible = false;
                System.out.println("\n⚠️ You become visible again!");
            }
        } else {
            super.attack(target);
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (invisible) {
            System.out.println("\n🕶️ You are invisible and evade the attack!");
            this.invisibleTurns--; // Reduce invisibility duration
            if (this.invisibleTurns <= 0) {
                this.invisible = false;
                System.out.println("\n⚠️ You become visible again!");
            }
            return; // Ignore all damage while invisible
        }
        super.takeDamage(damage);
    }
}
