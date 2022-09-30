package org.example.game;

public class Vampire extends Warrior implements KnowsDamageDealt {

    private int vampirism = 50;
    private int attack = 4;

    protected Vampire() {
        super(40);
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
    public int getAttack() {
        return attack;
    }

    int getVampirism() {
        return vampirism;
    }

}
