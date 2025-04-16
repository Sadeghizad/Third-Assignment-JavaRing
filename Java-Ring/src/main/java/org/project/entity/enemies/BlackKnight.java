package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.armors.CursedPlateArmor;
import org.project.object.weapons.Reaper;

import java.util.List;

public class BlackKnight extends Enemy {
    private static boolean reaperDropped = false;

    public BlackKnight() {
        super(150, 40, 20, 3, new Reaper(), new CursedPlateArmor(), "Black Knight");
    }

    @Override
    public void attack(Entity target) {
        System.out.println("\n⚔️ The Black Knight swings the Reaper, delivering a devastating blow!");
        int damage = this.getWeapon().getDamage();
        target.takeDamage(damage);
        target.reduceMaxHP(3); 
        System.out.println("☠️ " + target.getName() + " feels their life force being drained! Max HP -3.");
    }

    @Override
    public void abilityAttack(List<Entity> targets) {
        if (this.getSuperAbilityCooldown() == 0) {
            System.out.println("\n🔥 **Cursed Strike!** The Black Knight channels dark energy into the Reaper!");
            for (Entity target : targets) {
                int damage = this.getWeapon().getDamage() + 15;
                target.takeDamage(damage);
                target.reduceMaxHP(5); 
                System.out.println("☠️ " + target.getName() + " is permanently weakened! Max HP -5.");
            }
            this.setSuperAbilityCooldown(3);
        }
    }
    public static void ReaperDropped(){
        reaperDropped = true;
    }
    public static boolean isReaperDropped(){
        return reaperDropped;
    }


    @Override
    public Enemy clone() {
        return new BlackKnight();
    }
}
