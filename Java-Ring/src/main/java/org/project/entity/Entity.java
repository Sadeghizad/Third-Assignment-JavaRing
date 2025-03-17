package org.project.entity;

import org.project.object.Object;

public interface Entity {
    void attack(Entity target);

    void defend();

    void heal(int health);

    void fillMana(int mana);

    void regainFP(int stamina);

    void takeDamage(int damage);

    void useMana(int mana);

    void useStamina(int stamina);

    int getMaxHP();

    int getMaxMP();

    int getMaxFP();

//    Object getObject();
//    Object lost();
}
