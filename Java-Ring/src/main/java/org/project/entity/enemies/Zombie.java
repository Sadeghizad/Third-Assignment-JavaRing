package org.project.entity.enemies;


import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.armors.NoArmor;
import org.project.object.weapons.Fist;

import java.util.List;

public class Zombie extends Enemy{
    public Zombie() {
        super(20,0 ,15,100, new Fist(), new NoArmor(),"Zombie");
    }
    @Override
    public void abilityAttack(List<Entity> targets){
        if (targets.isEmpty()) return;
        if (this.getSuperAbilityCooldown() == 0) {
        int targetIndex = (int)(Math.random()* Player.getPlayerCount());
        if(targetIndex == Player.getPlayerCount()){
            targetIndex--;
        }
        Entity target = targets.get(targetIndex);
        System.out.println("\n🧟 The Zombie lurches forward and bites " + target.getName() + "!");
        target.takeDamage(getWeapon().getDamage() + 2);
        target.reduceMaxHP(3);
    }
    }
    @Override
    public Enemy clone() {
        return new Zombie();
    }
}
