package org.example.game;

public class Warlord extends Warrior {

    private int defense = 2;

    protected Warlord() {
        super(4);
        setInitialHp(100);
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
