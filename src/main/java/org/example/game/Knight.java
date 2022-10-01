package org.example.game;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Knight extends Warrior{

    private int attack = 7;

    @Override
    void equipWeapon(Weapon weapon) {
        this.setHP(getHP() + weapon.getHp());
        this.attack = getAttack() + weapon.getAttack();
        addWeaponToList(weapon);
    }

    @Override
    public int getAttack() {
        return attack;
    }

}
