package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

// TODO: UPDATE IMPLEMENTATION
public class Skeleton extends Enemy{
    private boolean resurrected = false;
    public Skeleton() {
        super(50, 0,30 ,20, new Sword(), new KnightArmor(),"Skeleton");
    }


    @Override
    public void defend()
    {
        //
    }

    @Override
    public Enemy clone() {
        return new Skeleton();
    }
    public void resurrect() {
        if (!resurrected) {
            this.resurrected = true;
            this.heal(this.getMaxHp() / 2); // Restore 50% HP
            System.out.println("\n☠️ The Skeleton's bones rattle as it stands up once more!");
        }
    }

    public boolean hasResurrected() {
        return resurrected;
    }

    // TODO: DESIGN ENEMY'S WEAPON AND ARMOR AND IMPLEMENT THE CONSTRUCTOR
}
// extras:
// zombie
// golem
// goblin
// drakola
// dragon
// blackknight
