package org.example.game;

public class Knight extends Warrior{

    protected Knight() {
        super(7);
        setInitialHp(50);
    }

    @Override
    void equipWeapon(Weapon weapon) {
        this.setInitialHp(getInitialHP() + weapon.getHp());
        setAttack(getAttack() + weapon.getAttack());
        addWeaponToList(weapon);
    }

}
