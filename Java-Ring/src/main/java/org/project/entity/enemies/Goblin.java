package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.armors.TornedLeatherArmor;
import org.project.object.weapons.Sword;

import java.util.List;

public class Goblin extends Enemy{
    public Goblin() {
        super(25, 10,50 ,100, new Sword(), new TornedLeatherArmor(),"Goblin");
    }

    @Override
    public void abilityAttack(List<Entity> targets){
        if (targets.isEmpty()) return;
        if (this.getSuperAbilityCooldown() == 0) {

        int targetIndex = (int)(Math.random()*Player.getPlayerCount());
        if(targetIndex == Player.getPlayerCount()){
            targetIndex--;
        }
        Entity target = targets.get(targetIndex);

        System.out.println("\n👹 The Goblin cackles and goes for a sneaky attack on " + target.getName() + "!");
        target.takeDamage(getWeapon().getDamage() + 5);
    }}

    @Override
    public Enemy clone() {
        return new Goblin();
    }
}
