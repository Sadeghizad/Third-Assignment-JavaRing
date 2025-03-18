package org.project.object.armors;

public class NoArmor extends Armor {
    public NoArmor() {
        super(0, 0); // No defense, no durability
    }

    @Override
    public void use(org.project.entity.Entity target) {
        System.out.println("you are not wearing any armor.");
    }

    @Override
    public String toString() {
        return "No Armor - No defense.";
    }
}