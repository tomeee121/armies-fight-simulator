package org.example.game;

import java.util.Collections;
import java.util.stream.Stream;

public class Vampire extends Warrior implements KnowsDamageDealt {

    private int vampirism = 50;

    protected Vampire() {
        super(4);
        setInitialHp(40);
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        int damageDealt = hitAndReportDamage(opponent);
        final int percent = 100;
        int healMyselfBy = damageDealt * getVampirism() / percent;
        setHP(getHP() + healMyselfBy);
    }

    @Override
    void equipWeapon(Weapon weapon) {
        this.setInitialHp(getInitialHP() + weapon.getHp());
        this.setAttack(getAttack() + weapon.getAttack());
        this.vampirism = getVampirism() + weapon.getVampirism();
        addWeaponToList(weapon);
    }

    @Override
    public void removeWeapons() {
        for (Weapon weapon : getWeaponsAvailable()) {
            setInitialHp(getHP() - weapon.getHp());
            setAttack(getAttack() - weapon.getAttack());
            this.vampirism = getVampirism() - weapon.getVampirism();
        }
        getWeaponsAvailable().clear();
    }

    int getVampirism() {
        return vampirism;
    }

}
