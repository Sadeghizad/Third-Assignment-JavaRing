package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.armors.TornedLeatherArmor;
import org.project.object.weapons.BrokenSword;

import java.util.List;

public class Skeleton extends Enemy{
    private boolean resurrected = false;
    public Skeleton() {
        super(50, 0,30 ,20, new BrokenSword(), new TornedLeatherArmor(),"Skeleton");
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
        System.out.println("\n☠️ The Skeleton hurls a sharpened bone at " + target.getName() + "!");
        target.takeDamage(getWeapon().getDamage() + 3);
    }
    }

    @Override
    public Enemy clone() {
        return new Skeleton();
    }

    public void resurrect() {
        if (!resurrected) {
            this.resurrected = true;
            this.heal(this.getMaxHP() / 2); 
            System.out.println("\n☠️ The Skeleton's bones rattle as it stands up once more!");
        }
    }

    public boolean hasResurrected() {
        return resurrected;
    }

    
}




