package org.example.game;

import java.util.List;

public class Stealer extends Warrior implements KnowsDamageDealt {

    private int defense = 2;

    protected Stealer() {
        super(3);
        setInitialHp(30);
    }

/**
    Stealer takes his opponent's weapons, uses them and so he takes advantage of enemies weapons while they loose some part of attack, hp etc
*/

    @Override
    public void hit(CanReceiveDamage opponent) {
        hitAndReportDamage(opponent);
        List<Weapon> weaponsAvailable = opponent.getWeaponsAvailable();
        for (Weapon weapon : weaponsAvailable) {
            this.setHP(getHP() + weapon.getHp());
            this.setAttack(getAttack() + weapon.getAttack());
            this.defense = getDefense() + weapon.getDefence();
        }
        opponent.removeWeapons();
    }

    @Override
    public void receiveDamage(HasAttack damager) {
        int finalDamage = Math.max(0, damager.getAttack() - getDefense());
        super.receiveDamage(() -> finalDamage);
    }

    @Override
    void equipWeapon(Weapon weapon) {
        this.setInitialHp(getInitialHP() + weapon.getHp());
        this.setAttack(getAttack() + weapon.getAttack());
        this.defense = getDefense() + weapon.getDefence();
        addWeaponToList(weapon);
    }

    int getDefense() {
        return defense;
    }
}
