package org.example.game;

public class Defender extends Warrior {
    private int attack = 3;
    private int defense = 2;

    protected Defender() {
        super(60);
    }

    @Override
    public void receiveDamage(HasAttack damager) {
        int finalDamage = Math.max(0, damager.getAttack() - getDefense());
        super.receiveDamage(() -> finalDamage);
    }

    @Override
    void equipWeapon(Weapon weapon) {
        this.setHP(getHP() + weapon.getHp());
        this.attack = getAttack() + weapon.getAttack();
        this.defense = getDefense() + weapon.getDefence();
        addWeaponToList(weapon);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    int getDefense() {
        return defense;
    }

}
