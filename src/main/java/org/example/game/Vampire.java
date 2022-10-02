package org.example.game;

public class Vampire extends Warrior implements KnowsDamageDealt {

    private int vampirism = 50;

    protected Vampire() {
        super(4);
        setInitialHp(40);
    }

    /**
    Use getter for vampirism to wrap additional properties from weapons equipped
*/

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

    int getVampirism() {
        return vampirism;
    }

}
