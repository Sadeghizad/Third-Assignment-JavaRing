package org.project.object.consumables;

import org.project.entity.Entity;

public class MPFlask extends Consumable {
    private final int refillAmount;

    public MPFlask() {
        this.refillAmount = 20; 
    }

    @Override
    public void use(Entity target) {
        if (target.getMp() > 0) {
            if (target.getMp() <= target.getMaxMP()) {
                System.out.println("❌ You cannot use an MP Flask while you are at full MP.");
            }else {
                target.fillMana(refillAmount);
                System.out.println("🩹 You drank an MP Flask! Restored " + refillAmount + " MP.");
            }
        } else {
            System.out.println("❌ You cannot use an MP Flask while dead.");
        }
    }

    @Override
    public String toString() {
        return "MP Flask - Restores " + refillAmount + " MP.";
    }
}




