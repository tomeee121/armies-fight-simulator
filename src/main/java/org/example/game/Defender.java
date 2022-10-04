package org.example.game;

public class Defender extends Warrior {
    private int defense = 2;

    protected Defender() {
        super(3);
        setInitialHp(60);
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

    @Override
    public void removeWeapons() {
        for (Weapon weapon : getWeaponsAvailable()) {
            setInitialHp(getHP() - weapon.getHp());
            setAttack(getAttack() - weapon.getAttack());
            this.defense = getDefense() - weapon.getDefence();
        }
        getWeaponsAvailable().clear();
    }

    int getDefense() {
        return defense;
    }

}
